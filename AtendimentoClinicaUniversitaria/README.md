# 🏥 Sistema de Atendimento da Clínica Universitária

## 📋 Descrição do Projeto

Este projeto implementa um **sistema de atendimento médico** que demonstra o uso prático das estruturas de dados **Fila (Queue)** e **Lista (List)** em Java. O sistema simula o funcionamento de uma clínica universitária, gerenciando pacientes desde a chegada até o atendimento completo, com **três níveis de implementação progressiva**.

## 🎯 Objetivos Educacionais

- **Praticar conceitos de Fila (Queue)**: Implementação FIFO com sistema de prioridade
- **Praticar conceitos de Lista (List)**: Armazenamento, busca e consulta de dados
- **Aplicar operações fundamentais**: Inserção, remoção e consulta em ambas estruturas
- **Demonstrar uso prático**: Contexto real de gerenciamento hospitalar
- **🆕 Implementar Fila Circular e Encadeada**: Otimização de espaço e desempenho
- **🆕 Sistema de Priorização Avançada**: Fila de prioridade com análise de complexidade
- **🆕 Geração de Estatísticas e Relatórios**: Sistema completo com exportação

## 🏗️ Estrutura do Sistema

### 📁 Classes Implementadas

```
src/main/java/br/com/pucgo/
├── Paciente.java                    # Modelo de dados do paciente
├── FilaDePacientes.java             # Implementação de fila básica
├── FilaCircularDePacientes.java     # 🆕 Fila circular otimizada
├── FilaEncadeadaDePacientes.java    # 🆕 Fila encadeada com nós
├── FilaPrioridadeEncadeada.java     # 🆕 Fila de prioridade encadeada
├── FilaPrioridadeAvancada.java      # 🆕 Sistema de prioridade complexo
├── ListaDeAtendidos.java            # Lista de pacientes atendidos
└── App.java                         # Programa principal com simulação completa
```

### 👤 **Classe Paciente**
- **Atributos**: nome, idade, CPF, prioridade, urgente
- **Prioridade automática**: Pacientes ≥60 anos recebem prioridade
- **Casos urgentes**: Campo booleano para emergências médicas
- **Métodos**: getters, setters, toString, equals (por CPF)

## 📊 **Implementações de Fila Disponíveis**

### 🔄 **1. Fila Circular de Pacientes**
- **Otimização de espaço**: Reutiliza posições do array sem deslocamento
- **Eficiência O(1)**: Inserção e remoção em tempo constante
- **Sem desperdício**: Aproveitamento máximo do array alocado
- **Controle de índices**: Usa módulo para navegação circular

### 🔗 **2. Fila Encadeada de Pacientes** 
- **Estrutura de nós**: Implementação baseada em Linked List
- **Memória dinâmica**: Cresce conforme necessário
- **Eficiência de inserção**: O(1) para início e fim
- **Flexibilidade**: Sem limitação de tamanho pré-definido

### 🏷️ **3. Fila de Prioridade Avançada**
- **Três níveis de prioridade**:
  - 🚨 **URGENTE**: Casos críticos (inserção no início)
  - 👴 **IDOSOS**: Pacientes ≥60 anos
  - 👤 **NORMAL**: Demais pacientes
- **Ordem de atendimento**: URGENTE → IDOSOS → NORMAL
- **Complexidade**: 
  - Inserção: O(1) para urgente/normal, O(n) para idosos
  - Remoção: O(1) sempre

### 📈 **4. Sistema de Estatísticas e Relatórios**
- **Contadores em tempo real**: Total de atendidos por categoria
- **Cálculos automáticos**: Média de idade, percentuais
- **Identificação de extremos**: Paciente mais idoso/jovem
- **Exportação de relatório**: Arquivo .txt com dados completos
- **Histórico detalhado**: Lista completa com classificações

## 🎮 Demonstração do Sistema

O programa principal executa **comparações entre diferentes implementações**:

### **Teste 1: Fila Original (ArrayList)**
- Sistema básico com duas filas separadas
- Demonstração de operações FIFO com prioridade

### **Teste 2: Fila Circular**
- Otimização de espaço no array
- Comparação de eficiência com implementação original

### **Teste 3: Fila Encadeada**
- Implementação com nós dinâmicos
- Análise de vantagens/desvantagens vs array

### **Teste 4: Fila de Prioridade Complexa**
- Sistema com três níveis de prioridade
- Simulação de um dia completo na clínica

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

## 📊 Exemplos de Saída

### **Sistema Básico**
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
```

### **Sistema com Prioridade Avançada**
```
=== NOVO EXERCÍCIO: ESTATÍSTICAS E RELATÓRIOS ===

=== SISTEMA COMPLETO COM RELATÓRIOS ===
1. SIMULAÇÃO DE UM DIA COMPLETO NA CLÍNICA:

📅 CHEGADAS AO LONGO DO DIA:
[MANHÃ] Chegou: Roberto Silva
👤 NORMAL: Roberto Silva adicionado na fila normal
[MANHÃ] Chegou: Fernanda Costa
👴 IDOSO: Fernanda Costa adicionado na fila de idosos
[MEIO-DIA] Chegou: Amanda Urgente
🚨 URGENTE: Amanda Urgente adicionado na fila de emergência
[TARDE] Chegou: Sophia Crítica
🚨 URGENTE: Sophia Crítica adicionado na fila de emergência

=== ESTADO COMPLETO DA FILA DE PRIORIDADE ===

🚨 FILA URGENTE (3 pacientes):
   1. Amanda Urgente (Idade: 41)
   2. Paulo Emergência (Idade: 52)
   3. Sophia Crítica (Idade: 36)

👴 FILA IDOSOS (5 pacientes):
   1. Fernanda Costa (Idade: 67)
   2. Joaquim Idoso (Idade: 78)
   3. Rosana Sênior (Idade: 63)
```

### **Relatório Diário Exportado (relatorio_clinica.txt)**
```
============================================================
===        RELATÓRIO DIÁRIO DE ATENDIMENTOS         ===
===          CLÍNICA UNIVERSITÁRIA                  ===
============================================================

Data/Hora do Relatório: 25/09/2025 20:23:27

📊 ESTATÍSTICAS GERAIS:
----------------------------------------------------------
Total de pacientes atendidos: 15
Média de idade: 45,5 anos
Paciente mais idoso: Joaquim Idoso (78 anos)
Paciente mais jovem: Carla Jovem (19 anos)

🚨 DISTRIBUIÇÃO POR PRIORIDADE:
----------------------------------------------------------
Casos urgentes atendidos: 3
Pacientes idosos atendidos: 5
Pacientes normais atendidos: 7

📈 PERCENTUAIS:
----------------------------------------------------------
Urgentes: 20,0%
Idosos: 33,3%
Normais: 46,7%

📋 LISTA COMPLETA DE ATENDIDOS:
----------------------------------------------------------
1. URGENTE - Amanda Urgente (Idade: 41, CPF: 400.400.400-40)
2. URGENTE - Paulo Emergência (Idade: 52, CPF: 700.700.700-70)
3. URGENTE - Sophia Crítica (Idade: 36, CPF: 101.101.101-01)
4. IDOSO - Fernanda Costa (Idade: 67, CPF: 200.200.200-20)
5. IDOSO - Joaquim Idoso (Idade: 78, CPF: 500.500.500-50)
...
15. NORMAL - Eduardo Final (Idade: 45, CPF: 606.606.606-06)
```

## 🔧 Conceitos de Estruturas de Dados Demonstrados

### 📚 **Análise de Complexidade**

| Implementação | Inserção | Remoção | Espaço | Vantagens |
|---------------|----------|---------|---------|-----------|
| **Fila Array** | O(1) | O(n)* | O(n) fixo | Simples, acesso rápido |
| **Fila Circular** | O(1) | O(1) | O(n) otimizado | Sem desperdício de espaço |
| **Fila Encadeada** | O(1) | O(1) | O(n) dinâmico | Flexibilidade total |
| **Fila Prioridade** | O(1)/O(n) | O(1) | O(n) | Atendimento inteligente |

*O(n) apenas se houver deslocamento de elementos

### 🏆 **Quando Usar Cada Estrutura**

- **Fila Circular**: Quando o tamanho máximo é conhecido e deseja-se otimizar espaço
- **Fila Encadeada**: Quando não há limite de tamanho e inserções/remoções são frequentes  
- **Fila de Prioridade**: Quando existe hierarquia natural de atendimento
- **Array Simples**: Para implementações básicas e didáticas

## 🎓 Aplicação Pedagógica

Este sistema é ideal para:
- **Compreender FIFO**: Como filas funcionam na prática
- **Implementar prioridades**: Adaptação de estruturas básicas
- **Comparar implementações**: Análise de eficiência e complexidade
- **Praticar busca**: Algoritmos de localização em listas
- **Modelar problemas reais**: Traduzir situações do cotidiano em código
- **Gerenciar estados**: Controle de múltiplas estruturas simultaneamente
- **Gerar relatórios**: Processamento e exportação de dados

## 📝 Exercícios Propostos

### **Nível Básico**
1. Modificar critérios de prioridade (tipo de convênio)
2. Implementar cancelamento de consultas
3. Adicionar campo "especialidade médica"

### **Nível Intermediário**
4. Criar fila com múltiplas prioridades numéricas
5. Implementar tempo de espera estimado
6. Adicionar histórico de horários de atendimento

### **Nível Avançado**
7. Implementar heap para fila de prioridade
8. Criar dashboard em tempo real
9. Integrar com banco de dados
10. Adicionar métricas de desempenho do sistema

## 📁 Arquivos de Relatório Gerados

O sistema gera automaticamente os seguintes relatórios:

- **`relatorio_clinica.txt`**: Relatório diário completo com estatísticas
- **`Relatorio.txt`**: Log de execução do sistema básico  
- **`Relatorio2.txt`**: Comparação entre implementações
- **`relatorio3.txt`**: Demonstração do sistema avançado com prioridades

---

**Desenvolvido para fins educacionais** - Demonstração prática de Estruturas de Dados em Java
