# 🏥 Sistema de Atendimento da Clínica Universitária

## 📋 Descrição do Projeto

Este projeto implementa um **sistema completo de atendimento médico** que demonstra o uso prático das estruturas de dados **Fila (Queue)** e **Lista (List)** em Java, incluindo **persistência em banco de dados PostgreSQL**. O sistema simula o funcionamento de uma clínica universitária, gerenciando pacientes desde a chegada até o atendimento completo, com múltiplas implementações e análise de complexidade algorítmica.

## 🎯 Objetivos Educacionais

- **Praticar conceitos de Fila (Queue)**: Implementação FIFO com sistema de prioridade
- **Praticar conceitos de Lista (List)**: Armazenamento, busca e consulta de dados
- **Aplicar operações fundamentais**: Inserção, remoção e consulta em ambas estruturas
- **Demonstrar uso prático**: Contexto real de gerenciamento hospitalar
- **🆕 Implementar Fila Circular e Encadeada**: Otimização de espaço e desempenho
- **🆕 Sistema de Priorização Avançada**: Fila de prioridade com análise de complexidade
- **🆕 Geração de Estatísticas e Relatórios**: Sistema completo com exportação
- **🆕 Persistência em Banco de Dados**: Sistema real com PostgreSQL e Docker
- **🆕 Análise de Complexidade**: Comparação detalhada entre implementações

## 🏗️ Estrutura do Sistema

### 📁 Classes Java Implementadas

```
src/main/java/br/com/pucgo/
├── Paciente.java                       # Modelo de dados do paciente
├── FilaDePacientes.java                # Implementação de fila básica
├── FilaCircularDePacientes.java        # 🆕 Fila circular otimizada
├── FilaEncadeadaDePacientes.java       # 🆕 Fila encadeada com nós
├── FilaPrioridadeEncadeada.java        # 🆕 Fila de prioridade encadeada
├── FilaPrioridadeAvancada.java         # 🆕 Sistema de prioridade complexo
├── FilaSimplesDatabaseService.java     # 🆕 Serviço de banco de dados
├── ListaDeAtendidos.java               # Lista de pacientes atendidos
└── App.java                            # Programa principal com menu interativo
```

### 🗄️ Sistema de Banco de Dados PostgreSQL

```
database/
├── init/
│   ├── 01-create-tables.sql           # Criação de tabelas e índices
│   ├── 02-functions-triggers.sql      # Funções e triggers automáticos
│   ├── 03-views-procedures.sql        # Views e procedures úteis
│   └── 04-sample-data.sql             # Dados de exemplo
├── docker-compose.yml                 # Configuração Docker
├── database.properties                # Configurações de conexão
├── db-manager.bat                     # Script utilitário Windows
└── README-DATABASE.md                 # Documentação completa do banco
```

## 🚀 **NOVA FUNCIONALIDADE: Fila Simples com Banco de Dados**

### 🗄️ **Sistema Completo de Persistência**
- **Banco PostgreSQL**: Rodando em container Docker
- **Priorização Automática**: Sistema inteligente de 3 níveis
- **Triggers Automáticos**: Inserção automática na fila
- **Views Otimizadas**: Consultas rápidas e relatórios
- **Auditoria Completa**: Histórico de todas as operações

### 🏥 **Funcionalidades do Sistema com Banco**
1. **➕ Adicionar Paciente** - Cadastro automático na fila
2. **📋 Ver Fila Atual** - Visualização organizada por prioridade
3. **🔔 Chamar Próximo** - Chamada automática respeitando prioridades
4. **✅ Finalizar Atendimento** - Registro completo do atendimento
5. **🔍 Consultar Posição** - Busca por CPF com tempo de espera
6. **❌ Remover da Fila** - Remoção com motivo registrado
7. **📊 Estatísticas** - Relatórios em tempo real
8. **📋 Atendidos Hoje** - Lista completa dos atendimentos
9. **📁 Dados de Exemplo** - Carregamento automático para testes

### 📊 **Schema do Banco de Dados**

#### Tabelas Principais:
- **`clinica.pacientes`** - Cadastro geral de pacientes
- **`clinica.fila_atendimento`** - Controle da fila com prioridades
- **`clinica.atendimentos`** - Registro de todos os atendimentos
- **`clinica.historico_fila`** - Auditoria de movimentações

#### Funcionalidades Automáticas:
- **Priorização Inteligente**: URGENTE → IDOSO (≥60) → NORMAL
- **Reorganização Automática**: Triggers mantêm a fila ordenada
- **Cálculo de Tempo**: Tempo de espera automático
- **Relatórios Dinâmicos**: Views para estatísticas instantâneas

## 📊 **Implementações de Fila Disponíveis**

### 🗄️ **1. Fila Simples com Banco de Dados (NOVO)**
- **Persistência Real**: Dados salvos em PostgreSQL
- **Priorização Automática**: Sistema inteligente de 3 níveis
- **Auditoria Completa**: Histórico de todas as operações
- **Interface Intuitiva**: Menus organizados e feedback visual
- **Estatísticas Avançadas**: Relatórios em tempo real

### 🔄 **2. Fila Circular de Pacientes**
- **Otimização de espaço**: Reutiliza posições do array sem deslocamento
- **Eficiência O(1)**: Inserção e remoção em tempo constante
- **Sem desperdício**: Aproveitamento máximo do array alocado
- **Controle de índices**: Usa módulo para navegação circular

### 🔗 **3. Fila Encadeada de Pacientes** 
- **Estrutura de nós**: Implementação baseada em Linked List
- **Memória dinâmica**: Cresce conforme necessário
- **Eficiência de inserção**: O(1) para início e fim
- **Flexibilidade**: Sem limitação de tamanho pré-definido

### 🏷️ **4. Fila de Prioridade Avançada**
- **Três níveis de prioridade**:
  - 🚨 **URGENTE**: Casos críticos (inserção no início)
  - 👴 **IDOSOS**: Pacientes ≥60 anos
  - 👤 **NORMAL**: Demais pacientes
- **Ordem de atendimento**: URGENTE → IDOSOS → NORMAL
- **Complexidade**: Análise detalhada de performance

### 📈 **5. Sistema de Estatísticas e Relatórios**
- **Contadores em tempo real**: Total de atendidos por categoria
- **Cálculos automáticos**: Média de idade, percentuais
- **Identificação de extremos**: Paciente mais idoso/jovem
- **Exportação de relatório**: Arquivo .txt com dados completos
- **Histórico detalhado**: Lista completa com classificações

## 🎮 Menu Interativo do Sistema

O programa principal agora oferece um **menu interativo completo**:

### **🗄️ Opção 1: Fila Simples com Banco de Dados (PostgreSQL)**
- Sistema completo com persistência real
- Interface web-like com menu intuitivo
- Operações CRUD completas
- Relatórios em tempo real

### **📋 Opção 2: Teste da Fila Original (ArrayList)**
- Sistema básico com duas filas separadas
- Demonstração de operações FIFO com prioridade

### **🔄 Opção 3: Teste da Fila Circular (Array Fixo)**
- Otimização de espaço no array
- Comparação de eficiência com implementação original

### **🔗 Opção 4: Teste da Fila Encadeada (Linked List)**
- Implementação com nós dinâmicos
- Análise de vantagens/desvantagens vs array

### **🏷️ Opção 5: Teste de Prioridade e Complexidade**
- Sistema com três níveis de prioridade
- Análise comparativa detalhada

### **📊 Opção 6: Comparação de Performance**
- Tabela comparativa de complexidades
- Recomendações de uso para cada implementação

### **🚀 Opção 7: Executar Todos os Testes**
- Execução sequencial de todos os sistemas
- Demonstração completa das funcionalidades

## ⚙️ Configuração e Instalação

### 🐳 **Pré-requisitos para Banco de Dados**
- Docker Desktop instalado
- 4GB de RAM disponível
- Porta 5433 livre (ou configurar outra no docker-compose.yml)

### 📦 **Dependências Maven**
```xml
<dependencies>
    <!-- PostgreSQL JDBC Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.1</version>
    </dependency>
    
    <!-- JUnit para testes -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### ▶️ **Como Executar**

#### **Opção 1: Sistema Completo com Banco (Recomendado)**
```bash
# 1. Iniciar o banco PostgreSQL
db-manager.bat start

# 2. Compilar e executar a aplicação
mvn clean compile
mvn exec:java -Dexec.mainClass="br.com.pucgo.App"

# 3. Escolher opção 1 no menu
```

#### **Opção 2: Apenas Testes em Memória**
```bash
# Compilação manual
javac -d target/classes -cp src/main/java src/main/java/br/com/pucgo/*.java
java -cp target/classes br.com.pucgo.App

# Maven
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.App"
```

#### **Opção 3: Gerenciamento do Banco**
```bash
# Iniciar banco
db-manager.bat start

# Ver logs
db-manager.bat logs

# Parar banco
db-manager.bat stop

# Status
db-manager.bat status

# Reset completo (CUIDADO: apaga dados!)
db-manager.bat reset
```

## 📊 **Análise de Complexidade Algorítmica**

### 🔍 **Comparação Detalhada das Implementações**

| ESTRUTURA                | INSERÇÃO | REMOÇÃO | BUSCA | ESPAÇO | ORDEM      |
|--------------------------|----------|---------|-------|--------|------------|
| **Fila Normal**          | O(1)     | O(n)    | N/A   | O(n)   | FIFO       |
| **Fila Circular**        | O(1)     | O(1)    | N/A   | O(n)   | FIFO       |
| **Fila Encadeada**       | O(1)     | O(1)    | N/A   | O(n)   | FIFO       |
| **Fila com Banco**       | O(1)*    | O(1)*   | O(1)  | ∞      | Por Prio   |
| **Prioridade ArrayList** | O(1)     | O(n)    | O(1)  | O(n)   | Por Prio   |
| **Prioridade Encadeada** | O(n)     | O(1)    | O(1)  | O(n)   | Por Prio   |

*\* Complexidade do banco pode variar com índices e otimizações*

### 🎯 **Recomendações de Uso**

#### **🗄️ Fila com Banco de Dados - USE QUANDO:**
- Sistema real de produção
- Necessário histórico e auditoria
- Múltiplos usuários/aplicações
- Relatórios gerenciais importantes
- Dados não podem ser perdidos

#### **🔄 Fila Circular - USE QUANDO:**
- Capacidade conhecida e limitada
- Muitas operações de inserção/remoção
- Memória limitada
- Performance crítica

#### **🔗 Fila Encadeada - USE QUANDO:**
- Tamanho varia muito dinamicamente
- Não há limite pré-definido
- Inserções/remoções frequentes
- Flexibilidade é prioritária

#### **🏷️ Fila de Prioridade - USE QUANDO:**
- Diferentes níveis de urgência
- Atendimento não é estritamente FIFO
- Classificação automática necessária
- Relatórios por categoria importantes

## 📊 **Exemplos de Saída do Sistema**

### **🗄️ Sistema com Banco de Dados**
```
=== SISTEMA DE ATENDIMENTO - CLÍNICA UNIVERSITÁRIA ===

============================================================
📋 MENU PRINCIPAL - SISTEMA DA CLÍNICA UNIVERSITÁRIA
============================================================
1️⃣  Fila Simples com Banco de Dados (PostgreSQL)
2️⃣  Teste da Fila Original (ArrayList)
3️⃣  Teste da Fila Circular (Array Fixo)
4️⃣  Teste da Fila Encadeada (Linked List)
5️⃣  Teste de Prioridade e Complexidade
6️⃣  Comparação de Performance
7️⃣  Executar Todos os Testes
0️⃣  Sair
------------------------------------------------------------
📝 Escolha uma opção: 1

============================================================
🗄️  TESTE: FILA SIMPLES COM BANCO DE DADOS
============================================================
✅ Conexão com banco de dados estabelecida com sucesso!

--------------------------------------------------
🏥 MENU DA FILA COM BANCO DE DADOS
--------------------------------------------------
1️⃣  Adicionar Paciente
2️⃣  Ver Fila Atual
3️⃣  Chamar Próximo Paciente
4️⃣  Finalizar Atendimento
5️⃣  Consultar Posição de Paciente
6️⃣  Remover Paciente da Fila
7️⃣  Estatísticas de Hoje
8️⃣  Listar Atendidos Hoje
9️⃣  Carregar Dados de Exemplo
0️⃣  Voltar ao Menu Principal
--------------------------------------------------
📝 Escolha uma opção: 2

📋 FILA ATUAL DE ATENDIMENTO:
=====================================================================================
POS  NOME                 IDADE CPF             PRIORIDADE   ESPERA
-------------------------------------------------------------------------------------
1    Carlos Urgente       35    222.222.222-22 URGENTE      5 min
2    Sofia Crítica        55    888.888.888-88 URGENTE      3 min
3    Maria Santos         65    234.567.890-12 IDOSO        8 min
4    Ana Costa            72    456.789.012-34 IDOSO        6 min
5    João Silva           45    123.456.789-01 NORMAL       10 min
6    Pedro Oliveira       30    345.678.901-23 NORMAL       7 min
-------------------------------------------------------------------------------------
📊 Total de pacientes na fila: 6
```

### **📊 Relatórios e Estatísticas**
```
📊 ESTATÍSTICAS DE HOJE:
==================================================
📈 Total de atendimentos: 15
🚨 Casos urgentes: 3
👴 Pacientes idosos: 7
📊 Idade média: 52.4 anos
⏰ Tempo médio de espera: 12.8 min
📊 Taxa de urgência: 20.0%
📊 Taxa de idosos: 46.7%
```

## 🔗 **Documentação Adicional**

- **📄 README-DATABASE.md** - Documentação completa do banco de dados
- **🗄️ database.properties** - Configurações de conexão
- **🐳 docker-compose.yml** - Configuração do PostgreSQL
- **📜 Scripts SQL** - Estrutura completa do banco em `database/init/`

## 🎯 **Conceitos Demonstrados**

### **📚 Estruturas de Dados**
- ✅ Fila (Queue) - FIFO
- ✅ Lista (List) - Operações CRUD
- ✅ Fila Circular - Otimização de espaço
- ✅ Lista Encadeada - Memória dinâmica
- ✅ Fila de Prioridade - Ordenação automática

### **🧠 Algoritmos e Complexidade**
- ✅ Análise Big O - Tempo e espaço
- ✅ Trade-offs - Memória vs Performance
- ✅ Otimização - Diferentes estratégias
- ✅ Comparação - Benchmarks práticos

### **🗄️ Persistência e Banco de Dados**
- ✅ JDBC - Conexão Java-PostgreSQL
- ✅ SQL Avançado - Triggers, Views, Functions
- ✅ Docker - Containerização
- ✅ Transações - Integridade de dados

### **🏗️ Engenharia de Software**
- ✅ Padrão MVC - Separação de responsabilidades
- ✅ POO - Encapsulamento e abstração
- ✅ Clean Code - Código legível e mantível
- ✅ Documentação - README e comentários

## 👨‍💻 **Desenvolvido por**

**Curso:** ADS1232 - Estruturas de Dados  
**Data:** Setembro 2024  
**Versão:** 2.0 - Sistema Completo com Banco de Dados

---

🏥 **Sistema de Atendimento da Clínica Universitária** - Demonstrando estruturas de dados na prática com persistência real!
