-- =========================================
-- VIEWS E PROCEDURES PARA OPERAÇÕES COMUNS
-- =========================================

-- View para visualizar a fila atual com detalhes dos pacientes
CREATE OR REPLACE VIEW clinica.v_fila_atual AS
SELECT
    fa.posicao_fila,
    p.nome,
    p.idade,
    p.cpf,
    fa.tipo_prioridade,
    fa.data_entrada_fila,
    EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - fa.data_entrada_fila))/60 AS tempo_espera_minutos,
    fa.status
FROM clinica.fila_atendimento fa
JOIN clinica.pacientes p ON fa.paciente_id = p.id
WHERE fa.status = 'AGUARDANDO'
ORDER BY fa.posicao_fila;

-- View para estatísticas diárias
CREATE OR REPLACE VIEW clinica.v_estatisticas_hoje AS
SELECT
    COUNT(*) as total_atendimentos,
    COUNT(*) FILTER (WHERE p.is_urgente = true) as urgentes_atendidos,
    COUNT(*) FILTER (WHERE p.idade >= 60) as idosos_atendidos,
    ROUND(AVG(p.idade), 2) as idade_media,
    MIN(p.idade) as idade_minima,
    MAX(p.idade) as idade_maxima,
    ROUND(AVG(a.tempo_espera_minutos), 2) as tempo_espera_medio
FROM clinica.atendimentos a
JOIN clinica.pacientes p ON a.paciente_id = p.id
WHERE DATE(a.data_atendimento) = CURRENT_DATE;

-- View para relatório de produtividade
CREATE OR REPLACE VIEW clinica.v_relatorio_mensal AS
SELECT
    DATE_TRUNC('day', a.data_atendimento) as data_atendimento,
    COUNT(*) as total_atendimentos,
    COUNT(*) FILTER (WHERE p.is_urgente = true) as casos_urgentes,
    COUNT(*) FILTER (WHERE p.idade >= 60) as pacientes_idosos,
    ROUND(AVG(a.tempo_espera_minutos), 2) as tempo_espera_medio,
    MAX(a.tempo_espera_minutos) as maior_tempo_espera
FROM clinica.atendimentos a
JOIN clinica.pacientes p ON a.paciente_id = p.id
WHERE a.data_atendimento >= DATE_TRUNC('month', CURRENT_DATE)
GROUP BY DATE_TRUNC('day', a.data_atendimento)
ORDER BY data_atendimento DESC;

-- Procedure para finalizar atendimento
CREATE OR REPLACE FUNCTION clinica.finalizar_atendimento(
    p_paciente_id INTEGER,
    p_tipo_atendimento VARCHAR(50) DEFAULT 'Consulta Geral',
    p_observacoes TEXT DEFAULT '',
    p_medico VARCHAR(100) DEFAULT 'Não informado'
)
RETURNS BOOLEAN AS $$
DECLARE
    tempo_espera INTEGER;
    fila_status VARCHAR(20);
BEGIN
    -- Verifica se o paciente estava na fila
    SELECT status INTO fila_status
    FROM clinica.fila_atendimento
    WHERE paciente_id = p_paciente_id;

    IF fila_status IS NULL THEN
        RAISE EXCEPTION 'Paciente não encontrado na fila';
    END IF;

    IF fila_status != 'CHAMADO' THEN
        RAISE EXCEPTION 'Paciente não foi chamado ainda';
    END IF;

    -- Calcula tempo de espera
    SELECT EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - data_entrada_fila))/60
    INTO tempo_espera
    FROM clinica.fila_atendimento
    WHERE paciente_id = p_paciente_id;

    -- Registra o atendimento
    INSERT INTO clinica.atendimentos (
        paciente_id,
        tempo_espera_minutos,
        tipo_atendimento,
        observacoes,
        medico_responsavel
    ) VALUES (
        p_paciente_id,
        tempo_espera,
        p_tipo_atendimento,
        p_observacoes,
        p_medico
    );

    -- Atualiza status na fila
    UPDATE clinica.fila_atendimento
    SET status = 'ATENDIDO'
    WHERE paciente_id = p_paciente_id;

    -- Registra no histórico
    INSERT INTO clinica.historico_fila (
        paciente_id,
        acao,
        observacao
    ) VALUES (
        p_paciente_id,
        'ATENDIDO',
        'Atendimento finalizado - ' || p_tipo_atendimento
    );

    -- Reorganiza a fila
    PERFORM clinica.reorganizar_fila();

    RETURN TRUE;

EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;

-- Procedure para remover paciente da fila
CREATE OR REPLACE FUNCTION clinica.remover_da_fila(
    p_paciente_id INTEGER,
    p_motivo TEXT DEFAULT 'Não informado'
)
RETURNS BOOLEAN AS $$
BEGIN
    -- Atualiza status na fila
    UPDATE clinica.fila_atendimento
    SET status = 'CANCELADO'
    WHERE paciente_id = p_paciente_id AND status IN ('AGUARDANDO', 'CHAMADO');

    IF NOT FOUND THEN
        RETURN FALSE;
    END IF;

    -- Registra no histórico
    INSERT INTO clinica.historico_fila (
        paciente_id,
        acao,
        observacao
    ) VALUES (
        p_paciente_id,
        'REMOVIDO',
        p_motivo
    );

    -- Reorganiza a fila
    PERFORM clinica.reorganizar_fila();

    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

-- Função para obter posição de um paciente na fila
CREATE OR REPLACE FUNCTION clinica.obter_posicao_paciente(p_cpf VARCHAR(14))
RETURNS TABLE(
    posicao INTEGER,
    tipo_prioridade VARCHAR(20),
    tempo_espera_minutos INTEGER,
    pacientes_na_frente INTEGER
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        fa.posicao_fila,
        fa.tipo_prioridade,
        EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - fa.data_entrada_fila))/60,
        (fa.posicao_fila - 1)
    FROM clinica.fila_atendimento fa
    JOIN clinica.pacientes p ON fa.paciente_id = p.id
    WHERE p.cpf = p_cpf AND fa.status = 'AGUARDANDO';
END;
$$ LANGUAGE plpgsql;

-- Função para gerar relatório de performance da fila
CREATE OR REPLACE FUNCTION clinica.relatorio_performance(
    p_data_inicio DATE DEFAULT CURRENT_DATE,
    p_data_fim DATE DEFAULT CURRENT_DATE
)
RETURNS TABLE(
    data_relatorio DATE,
    total_pacientes_atendidos BIGINT,
    tempo_espera_medio NUMERIC,
    tempo_espera_maximo INTEGER,
    pacientes_urgentes BIGINT,
    pacientes_idosos BIGINT,
    taxa_urgencia NUMERIC,
    taxa_idosos NUMERIC
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        DATE(a.data_atendimento) as data_relatorio,
        COUNT(*) as total_pacientes_atendidos,
        ROUND(AVG(a.tempo_espera_minutos), 2) as tempo_espera_medio,
        MAX(a.tempo_espera_minutos) as tempo_espera_maximo,
        COUNT(*) FILTER (WHERE p.is_urgente = true) as pacientes_urgentes,
        COUNT(*) FILTER (WHERE p.idade >= 60) as pacientes_idosos,
        ROUND(
            (COUNT(*) FILTER (WHERE p.is_urgente = true) * 100.0 / COUNT(*)), 2
        ) as taxa_urgencia,
        ROUND(
            (COUNT(*) FILTER (WHERE p.idade >= 60) * 100.0 / COUNT(*)), 2
        ) as taxa_idosos
    FROM clinica.atendimentos a
    JOIN clinica.pacientes p ON a.paciente_id = p.id
    WHERE DATE(a.data_atendimento) BETWEEN p_data_inicio AND p_data_fim
    GROUP BY DATE(a.data_atendimento)
    ORDER BY data_relatorio DESC;
END;
$$ LANGUAGE plpgsql;
