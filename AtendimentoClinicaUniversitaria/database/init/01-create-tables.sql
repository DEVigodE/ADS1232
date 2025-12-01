-- =========================================
-- SISTEMA DE ATENDIMENTO CLÍNICA UNIVERSITÁRIA
-- Script de Inicialização do Banco de Dados
-- =========================================

-- Criação do schema principal
CREATE SCHEMA IF NOT EXISTS clinica;

-- Tabela de Pacientes
CREATE TABLE clinica.pacientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INTEGER NOT NULL CHECK (idade > 0 AND idade <= 120),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    tem_prioridade BOOLEAN DEFAULT FALSE,
    is_urgente BOOLEAN DEFAULT FALSE,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT cpf_format CHECK (cpf ~ '^[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}$')
);

-- Tabela de Fila de Atendimento
CREATE TABLE clinica.fila_atendimento (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER REFERENCES clinica.pacientes(id) ON DELETE CASCADE,
    posicao_fila INTEGER NOT NULL,
    tipo_prioridade VARCHAR(20) DEFAULT 'NORMAL',
    data_entrada_fila TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'AGUARDANDO',

    CONSTRAINT tipo_prioridade_check CHECK (tipo_prioridade IN ('URGENTE', 'IDOSO', 'NORMAL')),
    CONSTRAINT status_check CHECK (status IN ('AGUARDANDO', 'CHAMADO', 'ATENDIDO', 'CANCELADO'))
);

-- Tabela de Atendimentos Realizados
CREATE TABLE clinica.atendimentos (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER REFERENCES clinica.pacientes(id),
    data_atendimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tempo_espera_minutos INTEGER,
    tipo_atendimento VARCHAR(50),
    observacoes TEXT,
    medico_responsavel VARCHAR(100)
);

-- Tabela de Histórico da Fila (para auditoria)
CREATE TABLE clinica.historico_fila (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER REFERENCES clinica.pacientes(id),
    acao VARCHAR(20) NOT NULL,
    data_acao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    posicao_anterior INTEGER,
    posicao_nova INTEGER,
    observacao TEXT,

    CONSTRAINT acao_check CHECK (acao IN ('ENTRADA', 'CHAMADO', 'ATENDIDO', 'REMOVIDO', 'REPOSICIONADO'))
);

-- Criação de índices para melhorar performance
CREATE INDEX idx_pacientes_cpf ON clinica.pacientes(cpf);
CREATE INDEX idx_fila_posicao ON clinica.fila_atendimento(posicao_fila);
CREATE INDEX idx_fila_prioridade ON clinica.fila_atendimento(tipo_prioridade, posicao_fila);
CREATE INDEX idx_fila_status ON clinica.fila_atendimento(status);
CREATE INDEX idx_atendimentos_data ON clinica.atendimentos(data_atendimento);
CREATE INDEX idx_historico_data ON clinica.historico_fila(data_acao);

-- Comentários nas tabelas
COMMENT ON TABLE clinica.pacientes IS 'Cadastro geral de pacientes da clínica universitária';
COMMENT ON TABLE clinica.fila_atendimento IS 'Controle da fila de atendimento com prioridades';
COMMENT ON TABLE clinica.atendimentos IS 'Registro de todos os atendimentos realizados';
COMMENT ON TABLE clinica.historico_fila IS 'Auditoria de movimentações na fila de atendimento';

-- Comentários nas colunas principais
COMMENT ON COLUMN clinica.pacientes.tem_prioridade IS 'Prioridade automática para idosos (>= 60 anos)';
COMMENT ON COLUMN clinica.pacientes.is_urgente IS 'Casos de urgência/emergência';
COMMENT ON COLUMN clinica.fila_atendimento.tipo_prioridade IS 'URGENTE > IDOSO > NORMAL';
COMMENT ON COLUMN clinica.fila_atendimento.posicao_fila IS 'Posição na fila considerando prioridades';
