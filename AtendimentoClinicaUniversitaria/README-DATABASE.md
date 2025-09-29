```

### Cenário 3: Relatórios Gerenciais
```sql
-- Estatísticas de hoje
SELECT * FROM clinica.v_estatisticas_hoje;

-- Pacientes que mais esperaram
SELECT p.nome, a.tempo_espera_minutos
FROM clinica.atendimentos a
JOIN clinica.pacientes p ON a.paciente_id = p.id
ORDER BY a.tempo_espera_minutos DESC
LIMIT 5;

-- Histórico de movimentações
SELECT h.acao, p.nome, h.data_acao, h.observacao
FROM clinica.historico_fila h
JOIN clinica.pacientes p ON h.paciente_id = p.id
ORDER BY h.data_acao DESC
LIMIT 10;
```

## 🔍 Monitoramento e Manutenção

### Verificar Saúde do Sistema
```sql
-- Pacientes aguardando há muito tempo
SELECT nome, data_entrada_fila, 
       EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - data_entrada_fila))/60 as minutos_esperando
FROM clinica.v_fila_atual
WHERE EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - data_entrada_fila))/60 > 60;

-- Verificar integridade da fila
SELECT posicao_fila, COUNT(*) 
FROM clinica.fila_atendimento 
WHERE status = 'AGUARDANDO'
GROUP BY posicao_fila 
HAVING COUNT(*) > 1;
```

### Limpeza de Dados Antigos
```sql
-- Arquivar atendimentos antigos (mais de 1 ano)
DELETE FROM clinica.atendimentos 
WHERE data_atendimento < CURRENT_DATE - INTERVAL '1 year';

-- Limpar histórico muito antigo
DELETE FROM clinica.historico_fila 
WHERE data_acao < CURRENT_DATE - INTERVAL '6 months';
```

## 🚨 Solução de Problemas

### Problemas Comuns

1. **Container não inicia**
   ```cmd
   # Verificar se a porta 5432 está livre
   netstat -an | findstr 5432
   
   # Verificar logs
   db-manager.bat logs
   ```

2. **Fila desorganizada**
   ```sql
   -- Reorganizar manualmente
   SELECT clinica.reorganizar_fila();
   ```

3. **Performance lenta**
   ```sql
   -- Verificar índices
   SELECT schemaname, tablename, indexname 
   FROM pg_indexes 
   WHERE schemaname = 'clinica';
   
   -- Atualizar estatísticas
   ANALYZE;
   ```

## 📝 Logs e Auditoria

O sistema mantém logs completos de todas as operações:
- Entrada de pacientes na fila
- Chamadas para atendimento
- Finalizações de atendimento
- Remoções da fila
- Reposicionamentos

Para acessar:
```sql
SELECT h.*, p.nome 
FROM clinica.historico_fila h
JOIN clinica.pacientes p ON h.paciente_id = p.id
WHERE DATE(h.data_acao) = CURRENT_DATE
ORDER BY h.data_acao DESC;
```

## 🎯 Melhorias Futuras

1. **Notificações**
   - WebSockets para atualizações em tempo real
   - SMS/Email para chamar pacientes

2. **Analytics Avançado**
   - Previsão de tempo de espera
   - Otimização automática da fila

3. **Integração**
   - API REST para aplicações externas
   - Dashboard web em tempo real

4. **Backup Automático**
   - Rotinas de backup agendadas
   - Replicação para alta disponibilidade

---

## 📞 Suporte

Para dúvidas ou problemas:
1. Verificar logs: `db-manager.bat logs`
2. Consultar este README
3. Verificar integridade: `SELECT clinica.reorganizar_fila();`

**Desenvolvido para o curso ADS1232 - Estruturas de Dados**
# Sistema de Atendimento - Clínica Universitária
## Banco de Dados PostgreSQL

Este projeto implementa um sistema completo de banco de dados para gerenciar filas de atendimento em uma clínica universitária, com suporte a prioridades automáticas e relatórios detalhados.

## 🏗️ Arquitetura do Banco de Dados

### Tabelas Principais

1. **`clinica.pacientes`** - Cadastro geral de pacientes
   - `id` (SERIAL PRIMARY KEY)
   - `nome` (VARCHAR(100))
   - `idade` (INTEGER)
   - `cpf` (VARCHAR(14) UNIQUE)
   - `tem_prioridade` (BOOLEAN) - Automático para idosos (≥60 anos)
   - `is_urgente` (BOOLEAN) - Casos de emergência
   - `data_cadastro` (TIMESTAMP)

2. **`clinica.fila_atendimento`** - Controle da fila com prioridades
   - `id` (SERIAL PRIMARY KEY)
   - `paciente_id` (INTEGER REFERENCES pacientes)
   - `posicao_fila` (INTEGER)
   - `tipo_prioridade` (VARCHAR: URGENTE, IDOSO, NORMAL)
   - `data_entrada_fila` (TIMESTAMP)
   - `status` (VARCHAR: AGUARDANDO, CHAMADO, ATENDIDO, CANCELADO)

3. **`clinica.atendimentos`** - Registro de atendimentos realizados
   - `id` (SERIAL PRIMARY KEY)
   - `paciente_id` (INTEGER REFERENCES pacientes)
   - `data_atendimento` (TIMESTAMP)
   - `tempo_espera_minutos` (INTEGER)
   - `tipo_atendimento` (VARCHAR(50))
   - `observacoes` (TEXT)
   - `medico_responsavel` (VARCHAR(100))

4. **`clinica.historico_fila`** - Auditoria completa das movimentações
   - `id` (SERIAL PRIMARY KEY)
   - `paciente_id` (INTEGER REFERENCES pacientes)
   - `acao` (VARCHAR: ENTRADA, CHAMADO, ATENDIDO, REMOVIDO, REPOSICIONADO)
   - `data_acao` (TIMESTAMP)
   - `posicao_anterior` (INTEGER)
   - `posicao_nova` (INTEGER)
   - `observacao` (TEXT)

## 🚀 Instalação e Configuração

### Pré-requisitos
- Docker Desktop instalado
- Docker Compose
- 4GB de RAM disponível
- Porta 5432 livre

### Passos de Instalação

1. **Clone/baixe o projeto** e navegue até o diretório:
   ```cmd
   cd "C:\Users\igor.bittencourt\Proton Drive\igorobm\My files\repo\ADS1232\AtendimentoClinicaUniversitaria"
   ```

2. **Inicie o banco de dados** usando o script utilitário:
   ```cmd
   db-manager.bat start
   ```

   Ou manualmente:
   ```cmd
   docker-compose up -d clinica-db
   ```

3. **Verifique se está funcionando**:
   ```cmd
   db-manager.bat status
   ```

### Informações de Conexão
- **Host:** localhost
- **Porta:** 5432
- **Database:** clinica_universitaria
- **Usuário:** clinica_user
- **Senha:** clinica_pass

## 🔧 Comandos Úteis

### Gerenciamento do Container
```cmd
# Iniciar banco
db-manager.bat start

# Parar banco
db-manager.bat stop

# Reiniciar banco
db-manager.bat restart

# Ver logs
db-manager.bat logs

# Verificar status
db-manager.bat status

# Reset completo (APAGA TODOS OS DADOS!)
db-manager.bat reset
```

### Conexão Manual
```cmd
# Conectar via psql
docker exec -it clinica-universitaria-db psql -U clinica_user -d clinica_universitaria

# Backup do banco
docker exec clinica-universitaria-db pg_dump -U clinica_user clinica_universitaria > backup.sql

# Restaurar backup
docker exec -i clinica-universitaria-db psql -U clinica_user -d clinica_universitaria < backup.sql
```

## 📊 Funcionalidades Implementadas

### 1. Gerenciamento Automático de Prioridades
- **URGENTE:** Casos de emergência (prioridade máxima)
- **IDOSO:** Pacientes ≥60 anos (prioridade média)
- **NORMAL:** Demais pacientes (ordem de chegada)

### 2. Funções Principais

#### Adicionar Paciente (automático via trigger)
```sql
INSERT INTO clinica.pacientes (nome, idade, cpf, is_urgente) 
VALUES ('João Silva', 45, '123.456.789-01', false);
-- Paciente é automaticamente adicionado à fila
```

#### Chamar Próximo Paciente
```sql
SELECT * FROM clinica.chamar_proximo_paciente();
```

#### Finalizar Atendimento
```sql
SELECT clinica.finalizar_atendimento(
    paciente_id, 
    'Consulta Geral', 
    'Observações do atendimento', 
    'Dr. João'
);
```

#### Remover da Fila
```sql
SELECT clinica.remover_da_fila(paciente_id, 'Motivo da remoção');
```

### 3. Views e Relatórios

#### Visualizar Fila Atual
```sql
SELECT * FROM clinica.v_fila_atual;
```

#### Estatísticas do Dia
```sql
SELECT * FROM clinica.v_estatisticas_hoje;
```

#### Relatório de Performance
```sql
SELECT * FROM clinica.relatorio_performance('2024-01-01', '2024-01-31');
```

#### Buscar Posição na Fila
```sql
SELECT * FROM clinica.obter_posicao_paciente('123.456.789-01');
```

## 🎯 Exemplos de Uso

### Cenário 1: Dia Normal de Atendimento
```sql
-- 1. Cadastrar pacientes (automático na fila)
INSERT INTO clinica.pacientes (nome, idade, cpf) VALUES 
('Ana Silva', 45, '111.111.111-11'),
('João Idoso', 70, '222.222.222-22'),
('Maria Jovem', 25, '333.333.333-33');

-- 2. Ver fila organizada
SELECT * FROM clinica.v_fila_atual;

-- 3. Chamar próximo (idoso tem prioridade)
SELECT * FROM clinica.chamar_proximo_paciente();

-- 4. Finalizar atendimento
SELECT clinica.finalizar_atendimento(2, 'Cardiologia', 'Pressão normal', 'Dr. Carlos');
```

### Cenário 2: Emergência
```sql
-- 1. Paciente urgente chega
INSERT INTO clinica.pacientes (nome, idade, cpf, is_urgente) 
VALUES ('Pedro Urgente', 35, '444.444.444-44', true);

-- 2. Ver que foi para o início da fila
SELECT * FROM clinica.v_fila_atual;

-- 3. Atender imediatamente
SELECT * FROM clinica.chamar_proximo_paciente();
