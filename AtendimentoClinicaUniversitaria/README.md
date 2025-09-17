# 🏥 Sistema de Atendimento da Clínica Universitária

## 📋 Descrição do Projeto

Este projeto implementa um **sistema de atendimento médico** que demonstra o uso prático das estruturas de dados **Fila (Queue)** e **Lista (List)** em Java. O sistema simula o funcionamento de uma clínica universitária, gerenciando pacientes desde a chegada até o atendimento completo.

## 🎯 Objetivos Educacionais

- **Praticar conceitos de Fila (Queue)**: Implementação FIFO com sistema de prioridade
- **Praticar conceitos de Lista (List)**: Armazenamento, busca e consulta de dados
- **Aplicar operações fundamentais**: Inserção, remoção e consulta em ambas estruturas
- **Demonstrar uso prático**: Contexto real de gerenciamento hospitalar

## 🏗️ Estrutura do Sistema

### 📁 Classes Implementadas

```
src/main/java/br/com/pucgo/
├── Paciente.java              # Modelo de dados do paciente
├── FilaDePacientes.java       # Implementação de fila com prioridade
├── ListaDeAtendidos.java      # Lista de pacientes atendidos
└── App.java                   # Programa principal com simulação completa
```

### 👤 **Classe Paciente**
- **Atributos**: nome, idade, CPF, prioridade
- **Prioridade automática**: Pacientes ≥60 anos recebem prioridade
- **Prioridade manual**: Para casos urgentes independente da idade
- **Métodos**: getters, setters, toString, equals (por CPF)

### 🚶‍♀️ **Classe FilaDePacientes (Queue)**
- **Duas filas internas**: Uma para prioridade, outra normal
- **Operações principais**:
  - `adicionarPaciente()` - **enqueue** (adiciona ao final)
  - `chamarProximoPaciente()` - **dequeue** (remove do início)
  - `proximoPaciente()` - **peek** (consulta sem remover)
- **Sistema de prioridade**: Pacientes prioritários sempre atendidos primeiro
- **Visualização**: Exibe estado atual das filas

### 📝 **Classe ListaDeAtendidos (List)**
- **Armazenamento histórico**: Todos os pacientes atendidos
- **Operações principais**:
  - `adicionarAtendido()` - Adiciona paciente ao final da lista
  - `foiAtendido()` - Verifica se CPF está na lista (busca booleana)
  - `buscarAtendido()` - Localiza paciente específico por CPF
  - `exibirAtendidos()` - Lista completa de atendidos
- **Estatísticas**: Média de idade, contadores por prioridade

## 🎮 Demonstração do Sistema

O programa principal (`App.java`) executa uma simulação completa:

1. **Cadastro de 5 pacientes** com diferentes idades e prioridades
2. **Atendimento de 3 pacientes** seguindo ordem de prioridade
3. **Consulta do próximo** sem remover da fila (peek)
4. **Exibição da lista completa** de atendidos
5. **Busca por CPF** na lista de atendidos
6. **Estatísticas dinâmicas** dos atendimentos
7. **Finalização** de todos os atendimentos

## ▶️ Como Executar

### Opção 1: Compilação Manual
```bash
# Navegue até o diretório do projeto
cd AtendimentoClinicaUniversitaria

# Compile as classes
javac -d target/classes -cp src/main/java src/main/java/br/com/pucgo/*.java

# Execute o programa
java -cp target/classes br.com.pucgo.App
```

### Opção 2: Maven (se configurado)
```bash
cd AtendimentoClinicaUniversitaria
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.App"
```

## 📊 Exemplo de Saída

```
=== SISTEMA DE ATENDIMENTO - CLÍNICA UNIVERSITÁRIA ===

1. ADICIONANDO PACIENTES NA FILA:
Paciente João Silva adicionado na fila normal
Paciente Maria Santos adicionado na fila PRIORITÁRIA
Paciente Pedro Oliveira adicionado na fila normal
Paciente Ana Costa adicionado na fila PRIORITÁRIA
Paciente Carlos Mendes adicionado na fila PRIORITÁRIA

=== ESTADO DA FILA ===
Fila Prioritária (3 pacientes):
1. Maria Santos (Idade: 65)
2. Ana Costa (Idade: 72)
3. Carlos Mendes (Idade: 28)

Fila Normal (2 pacientes):
1. João Silva (Idade: 45)
2. Pedro Oliveira (Idade: 30)

2. ATENDENDO PACIENTES:
Atendendo paciente 1: Maria Santos
Atendendo paciente 2: Ana Costa
Atendendo paciente 3: Carlos Mendes

=== PACIENTES ATENDIDOS ===
1. Maria Santos - Idade: 65 - CPF: 234.567.890-12 - Prioridade: SIM
2. Ana Costa - Idade: 72 - CPF: 456.789.012-34 - Prioridade: SIM
3. Carlos Mendes - Idade: 28 - CPF: 567.890.123-45 - Prioridade: SIM

=== ESTATÍSTICAS DE ATENDIMENTO ===
Total de pacientes atendidos: 5
Média de idade dos atendidos: 48,0 anos
Pacientes com prioridade atendidos: 3
Pacientes sem prioridade atendidos: 2
```

## 🔧 Conceitos de Estruturas de Dados Demonstrados

### 📚 **Fila (Queue) - FIFO com Prioridade**
- **First In, First Out**: Primeiro a chegar, primeiro a ser atendido
- **Implementação dual**: Duas listas separadas para prioridade
- **Operações O(1)**: Inserção e remoção em tempo constante
- **Peek operation**: Consulta sem modificar a estrutura

### 📝 **Lista (List) - Armazenamento Dinâmico**
- **Acesso sequencial**: Busca linear por elementos
- **Inserção no final**: Crescimento dinâmico da estrutura
- **Busca por critério**: Localização de elementos por atributo específico
- **Operações estatísticas**: Cálculos sobre o conjunto de dados

## 🏆 Características Avançadas

### ✅ **Sistema de Prioridade Inteligente**
- Idosos (≥60 anos) recebem prioridade automática
- Casos urgentes podem ter prioridade manual
- Fila dupla garante que prioritários sejam sempre atendidos primeiro

### ✅ **Busca e Estatísticas**
- Busca por CPF em tempo linear O(n)
- Cálculo dinâmico de estatísticas (média, contadores)
- Histórico completo de atendimentos

### ✅ **Interface Didática**
- Separação visual clara entre filas normal e prioritária
- Feedback imediato para cada operação
- Demonstração passo a passo do funcionamento

## 🎓 Aplicação Pedagógica

Este sistema é ideal para:
- **Compreender FIFO**: Como filas funcionam na prática
- **Implementar prioridades**: Adaptação de estruturas básicas
- **Praticar busca**: Algoritmos de localização em listas
- **Modelar problemas reais**: Traduzir situações do cotidiano em código
- **Gerenciar estados**: Controle de múltiplas estruturas simultaneamente

## 📝 Exercícios Propostos

1. **Implementar fila circular**: Otimizar o uso de memória
2. **Adicionar mais critérios de prioridade**: Tipo de convênio, urgência médica
3. **Criar histórico de tempos**: Calcular tempo médio de espera
4. **Implementar cancelamentos**: Remover pacientes da fila
5. **Adicionar persistência**: Salvar dados em arquivo

---

**Desenvolvido para fins educacionais** - Demonstração prática de Estruturas de Dados em Java
