-- =========================================
-- FUNÇÕES E TRIGGERS PARA AUTOMAÇÃO DA FILA
-- =========================================

-- Função para determinar tipo de prioridade automaticamente
CREATE OR REPLACE FUNCTION clinica.determinar_prioridade(idade INTEGER, urgente BOOLEAN)
RETURNS VARCHAR(20) AS $$
BEGIN
    IF urgente THEN
        RETURN 'URGENTE';
    ELSIF idade >= 60 THEN
        RETURN 'IDOSO';
    ELSE
        RETURN 'NORMAL';
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Função para calcular próxima posição na fila
CREATE OR REPLACE FUNCTION clinica.calcular_posicao_fila(p_tipo_prioridade VARCHAR(20))
RETURNS INTEGER AS $$
DECLARE
    nova_posicao INTEGER;
BEGIN
    CASE p_tipo_prioridade
        WHEN 'URGENTE' THEN
            -- Urgentes vão para o início, mas após outros urgentes
            SELECT COALESCE(MAX(posicao_fila), 0) + 1
            INTO nova_posicao
            FROM clinica.fila_atendimento
            WHERE fila_atendimento.tipo_prioridade = 'URGENTE' AND status = 'AGUARDANDO';

        WHEN 'IDOSO' THEN
            -- Idosos vão após urgentes, mas antes de normais
            SELECT COALESCE(MAX(posicao_fila), 0) + 1
            INTO nova_posicao
            FROM clinica.fila_atendimento
            WHERE fila_atendimento.tipo_prioridade IN ('URGENTE', 'IDOSO') AND status = 'AGUARDANDO';

        WHEN 'NORMAL' THEN
            -- Normais vão para o final da fila
            SELECT COALESCE(MAX(posicao_fila), 0) + 1
            INTO nova_posicao
            FROM clinica.fila_atendimento
            WHERE status = 'AGUARDANDO';
    END CASE;

    RETURN COALESCE(nova_posicao, 1);
END;
$$ LANGUAGE plpgsql;

-- Função para reorganizar posições da fila
CREATE OR REPLACE FUNCTION clinica.reorganizar_fila()
RETURNS VOID AS $$
DECLARE
    rec RECORD;
    nova_pos INTEGER := 1;
BEGIN
    -- Reorganiza a fila por prioridade e ordem de chegada
    FOR rec IN
        SELECT id
        FROM clinica.fila_atendimento
        WHERE status = 'AGUARDANDO'
        ORDER BY
            CASE tipo_prioridade
                WHEN 'URGENTE' THEN 1
                WHEN 'IDOSO' THEN 2
                WHEN 'NORMAL' THEN 3
            END,
            data_entrada_fila ASC
    LOOP
        UPDATE clinica.fila_atendimento
        SET posicao_fila = nova_pos
        WHERE id = rec.id;

        nova_pos := nova_pos + 1;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Trigger para atualizar prioridade automática quando paciente é inserido
CREATE OR REPLACE FUNCTION clinica.trigger_paciente_prioridade()
RETURNS TRIGGER AS $$
BEGIN
    -- Define prioridade automática para idosos
    IF NEW.idade >= 60 THEN
        NEW.tem_prioridade := TRUE;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_paciente_prioridade
    BEFORE INSERT OR UPDATE ON clinica.pacientes
    FOR EACH ROW
    EXECUTE FUNCTION clinica.trigger_paciente_prioridade();

-- Trigger para inserir automaticamente na fila quando paciente é cadastrado
CREATE OR REPLACE FUNCTION clinica.trigger_inserir_fila()
RETURNS TRIGGER AS $$
DECLARE
    tipo_prio VARCHAR(20);
    posicao INTEGER;
BEGIN
    -- Determina tipo de prioridade
    tipo_prio := clinica.determinar_prioridade(NEW.idade, NEW.is_urgente);

    -- Calcula posição na fila
    posicao := clinica.calcular_posicao_fila(tipo_prio);

    -- Insere na fila
    INSERT INTO clinica.fila_atendimento (
        paciente_id,
        posicao_fila,
        tipo_prioridade,
        status
    ) VALUES (
        NEW.id,
        posicao,
        tipo_prio,
        'AGUARDANDO'
    );

    -- Registra no histórico
    INSERT INTO clinica.historico_fila (
        paciente_id,
        acao,
        posicao_nova,
        observacao
    ) VALUES (
        NEW.id,
        'ENTRADA',
        posicao,
        'Paciente adicionado à fila automaticamente'
    );

    -- Reorganiza toda a fila para manter consistência
    PERFORM clinica.reorganizar_fila();

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_inserir_fila
    AFTER INSERT ON clinica.pacientes
    FOR EACH ROW
    EXECUTE FUNCTION clinica.trigger_inserir_fila();

-- Função para chamar próximo paciente
CREATE OR REPLACE FUNCTION clinica.chamar_proximo_paciente()
RETURNS TABLE(
    paciente_id INTEGER,
    nome VARCHAR(100),
    idade INTEGER,
    cpf VARCHAR(14),
    tipo_prioridade VARCHAR(20),
    tempo_espera_minutos INTEGER
) AS $$
DECLARE
    proximo_id INTEGER;
    tempo_espera INTEGER;
BEGIN
    -- Encontra o próximo paciente na fila
    SELECT fa.paciente_id,
           EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - fa.data_entrada_fila))/60
    INTO proximo_id, tempo_espera
    FROM clinica.fila_atendimento fa
    WHERE fa.status = 'AGUARDANDO'
    ORDER BY fa.posicao_fila
    LIMIT 1;

    IF proximo_id IS NOT NULL THEN
        -- Atualiza status para CHAMADO
        UPDATE clinica.fila_atendimento
        SET status = 'CHAMADO'
        WHERE fila_atendimento.paciente_id = proximo_id AND status = 'AGUARDANDO';

        -- Registra no histórico
        INSERT INTO clinica.historico_fila (
            paciente_id,
            acao,
            observacao
        ) VALUES (
            proximo_id,
            'CHAMADO',
            'Paciente chamado para atendimento'
        );

        -- Reorganiza a fila
        PERFORM clinica.reorganizar_fila();

        -- Retorna dados do paciente
        RETURN QUERY
        SELECT p.id, p.nome, p.idade, p.cpf, fa.tipo_prioridade, tempo_espera::INTEGER
        FROM clinica.pacientes p
        JOIN clinica.fila_atendimento fa ON p.id = fa.paciente_id
        WHERE p.id = proximo_id;
    END IF;

    RETURN;
END;
$$ LANGUAGE plpgsql;
