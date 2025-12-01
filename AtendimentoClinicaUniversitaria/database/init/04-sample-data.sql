-- =========================================
-- DADOS DE EXEMPLO PARA TESTE DO SISTEMA
-- =========================================

-- Inserindo pacientes de exemplo (serão automaticamente adicionados à fila)
INSERT INTO clinica.pacientes (nome, idade, cpf, is_urgente) VALUES
-- Pacientes normais
('João Silva', 45, '123.456.789-01', false),
('Pedro Oliveira', 30, '345.678.901-23', false),
('Carlos Mendes', 28, '567.890.123-45', false),
('Lucas Jovem', 22, '777.777.777-77', false),
('Carla Normal', 38, '000.000.000-00', false),

-- Pacientes idosos (prioridade automática)
('Maria Santos', 65, '234.567.890-12', false),
('Ana Costa', 72, '456.789.012-34', false),
('Rosa Sênior', 68, '666.666.666-66', false),
('Alberto Aposentado', 82, '999.999.999-99', false),

-- Casos urgentes
('Carlos Urgente', 35, '222.222.222-22', true),
('Pedro Emergência', 42, '555.555.555-55', true),
('Sofia Crítica', 55, '888.888.888-88', true);

-- Simulando alguns atendimentos já realizados (para histórico)
-- Primeiro, vamos chamar alguns pacientes e finalizar seus atendimentos

-- Chamando o primeiro paciente urgente
SELECT * FROM clinica.chamar_proximo_paciente();

-- Finalizando atendimento do primeiro urgente
SELECT clinica.finalizar_atendimento(
    (SELECT id FROM clinica.pacientes WHERE cpf = '222.222.222-22'),
    'Emergência Cardiológica',
    'Paciente apresentou dor no peito, realizado ECG',
    'Dr. João Cardiologista'
);

-- Chamando próximo paciente
SELECT * FROM clinica.chamar_proximo_paciente();

-- Finalizando atendimento
SELECT clinica.finalizar_atendimento(
    (SELECT id FROM clinica.pacientes WHERE cpf = '555.555.555-55'),
    'Emergência Ortopédica',
    'Fratura no braço direito',
    'Dr. Maria Ortopedista'
);

-- Inserindo mais alguns pacientes para demonstrar a fila dinâmica
INSERT INTO clinica.pacientes (nome, idade, cpf, is_urgente) VALUES
('Roberto Silva', 34, '100.100.100-10', false),
('Fernanda Costa', 67, '200.200.200-20', false),
('Miguel Santos', 23, '300.300.300-30', false),
('Amanda Urgente', 41, '400.400.400-40', true),
('Joaquim Idoso', 78, '500.500.500-50', false);

-- Demonstração de queries úteis
-- (Estas são apenas para documentação, serão executadas quando o banco for consultado)

/*
-- Consultas úteis para o sistema:

-- 1. Ver fila atual ordenada por prioridade
SELECT * FROM clinica.v_fila_atual;

-- 2. Chamar próximo paciente
SELECT * FROM clinica.chamar_proximo_paciente();

-- 3. Ver estatísticas do dia
SELECT * FROM clinica.v_estatisticas_hoje;

-- 4. Finalizar atendimento
SELECT clinica.finalizar_atendimento(PACIENTE_ID, 'Tipo do Atendimento', 'Observações', 'Médico');

-- 5. Buscar posição de um paciente na fila
SELECT * FROM clinica.obter_posicao_paciente('123.456.789-01');

-- 6. Remover paciente da fila
SELECT clinica.remover_da_fila(PACIENTE_ID, 'Motivo da remoção');

-- 7. Relatório de performance
SELECT * FROM clinica.relatorio_performance(CURRENT_DATE - INTERVAL '7 days', CURRENT_DATE);

-- 8. Ver histórico de movimentações
SELECT h.*, p.nome, p.cpf
FROM clinica.historico_fila h
JOIN clinica.pacientes p ON h.paciente_id = p.id
ORDER BY h.data_acao DESC;

-- 9. Pacientes que mais esperaram
SELECT p.nome, p.cpf, a.tempo_espera_minutos
FROM clinica.atendimentos a
JOIN clinica.pacientes p ON a.paciente_id = p.id
ORDER BY a.tempo_espera_minutos DESC
LIMIT 10;

-- 10. Reorganizar fila manualmente (se necessário)
SELECT clinica.reorganizar_fila();
*/
