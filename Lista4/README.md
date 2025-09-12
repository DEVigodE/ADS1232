# 📋 Lista 4: Listas (Vetores) e Filas

Esta lista aborda exercícios práticos sobre **Estruturas de Dados** focando em **Listas (Vetores)** e **Filas** em Java.

---

## 🎯 Exercícios Implementados

### 📚 **Exercício 1: Sistema de Cadastro de Alunos (Vetor)**
**Pacote:** `br.com.pucgo.exercicio1`

Sistema completo de cadastro de alunos utilizando **vetor (array)** como estrutura de dados principal.

#### 🔧 **Funcionalidades:**
- ✅ **Cadastrar**: Adiciona novo aluno com validação de matrícula única
- ✅ **Listar todos**: Exibe todos os alunos cadastrados
- ✅ **Pesquisar**: Busca aluno por matrícula
- ✅ **Remover**: Remove aluno por matrícula com confirmação

#### 📋 **Atributos do Aluno:**
- Nome
- Matrícula (único)
- Data de Nascimento

#### 🏗️ **Características Técnicas:**
- Utiliza **array fixo** com capacidade para 100 alunos
- Verificação de duplicidade de matrícula
- Reorganização do array após remoção
- Interface de usuário amigável com confirmações

---

### 📞 **Exercício 2: Sistema Call-Center (Fila)**
**Pacote:** `br.com.pucgo.exercicio2`

Sistema de atendimento de call-center utilizando **fila (Queue)** para gerenciar contatos.

#### 🔧 **Funcionalidades:**
- ✅ **Inserir Contato**: Adiciona contato à fila de atendimento
- ✅ **Próximo Contato**: Remove e exibe o primeiro contato da fila
- ✅ **Sair**: Encerra o sistema

#### 📋 **Atributos do Contato:**
- Nome do cliente
- Telefone
- Assunto

#### 🏗️ **Características Técnicas:**
- Utiliza **LinkedList** implementando **Queue** (FIFO)
- Exibe quantidade de contatos na fila
- Mostra posição na fila ao inserir contato
- Interface clara para atendentes

---

## 📂 Estrutura do Projeto

```
Lista4/
├── src/main/java/br/com/pucgo/
│   ├── App.java                    # Menu principal
│   ├── exercicio1/
│   │   ├── Aluno.java             # Classe modelo Aluno
│   │   └── App.java               # Sistema de cadastro
│   └── exercicio2/
│       ├── Contato.java           # Classe modelo Contato
│       └── App.java               # Sistema call-center
└── pom.xml
```

---

## ▶️ Como Executar

### 🚀 **Execução via Maven (Recomendado)**

1. **Menu Principal** (escolhe entre exercícios):
   ```bash
   cd Lista4
   mvn compile exec:java -Dexec.mainClass="br.com.pucgo.App"
   ```

2. **Exercício 1** (Cadastro de Alunos):
   ```bash
   mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio1.App"
   ```

3. **Exercício 2** (Call-Center):
   ```bash
   mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio2.App"
   ```

### ⚙️ **Execução Manual**

```bash
cd Lista4/src/main/java
javac br/com/pucgo/App.java
java br.com.pucgo.App
```

---

## 🎮 Exemplos de Uso

### 📚 **Exercício 1 - Cadastro de Alunos**

```
=== SISTEMA DE CADASTRO DE ALUNOS ===
1 - Cadastrar
2 - Listar todos
3 - Pesquisar
4 - Remover
0 - Sair
Escolha uma opção: 1

--- CADASTRAR ALUNO ---
Nome: João Silva
Matrícula: 12345
Data de Nascimento (dd/mm/aaaa): 15/05/2000
Aluno cadastrado com sucesso!
```

### 📞 **Exercício 2 - Call-Center**

```
=== SISTEMA CALL-CENTER ===
Contatos na fila: 0
1 - Inserir Contato
2 - Próximo Contato
0 - Sair
Escolha uma opção: 1

--- INSERIR CONTATO NA FILA ---
Nome do cliente: Maria Santos
Telefone: (11) 99999-9999
Assunto: Problema com produto
Contato adicionado à fila com sucesso!
Posição na fila: 1
```

---

## 🔍 Conceitos Abordados

### 📋 **Listas (Vetores/Arrays)**
- Estrutura de dados de tamanho fixo
- Acesso direto por índice
- Inserção, remoção e busca
- Reorganização após operações

### 🔄 **Filas (Queues)**
- Estrutura FIFO (First In, First Out)
- Operações: `offer()` (inserir) e `poll()` (remover)
- Aplicação em sistemas de atendimento
- Gerenciamento de ordem de chegada

### 🏗️ **Programação Orientada a Objetos**
- Encapsulamento de dados
- Classes modelo (Aluno, Contato)
- Separação de responsabilidades
- Interface de usuário organizada

---

## 🎯 Aprendizados

1. **Diferenças entre Arrays e Collections**
2. **Quando usar cada estrutura de dados**
3. **Implementação de CRUDs básicos**
4. **Validação e tratamento de dados**
5. **Design de interfaces de usuário em console**
6. **Organização de código em pacotes**

---

## 🚀 Possíveis Melhorias

- [ ] Persistência de dados em arquivo
- [ ] Validação mais robusta de entrada
- [ ] Interface gráfica (GUI)
- [ ] Implementação de pilhas para histórico
- [ ] Sistema de relatórios
- [ ] Backup automático dos dados

---

✍️ **Nota**: Esta lista demonstra na prática o uso de **Listas** e **Filas**, estruturas fundamentais em programação que são amplamente utilizadas em sistemas reais.
