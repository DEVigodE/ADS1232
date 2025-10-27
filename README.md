# 📚 Repositório de Listas de Exercícios – Programação Orientada a Objetos (Java)

Este repositório reúne todas as listas de exercícios da disciplina de **Programação Orientada a Objetos (POO)** em **Java**.  

O objetivo é praticar conceitos de **POO** como:  
- Encapsulamento  
- Construtores  
- Validações  
- Herança e polimorfismo (nas listas futuras)  
- **Estruturas de Dados** (Pilhas, Filas)
- Criação e interpretação de **diagramas UML**  
- Organização em pacotes e boas práticas de programação  

---

## 📂 Estrutura do Repositório

```
.
├── lista1/
│   ├── src/...
│   ├── README.md       # README detalhado da Lista 1
│
├── lista2/
│   ├── src/...
│   ├── README.md       # README detalhado da Lista 2
│
├── lista3/
│   ├── src/...
│   ├── README.md       # README detalhado da Lista 3
│
├── Lista4/
│   ├── src/...
│   ├── README.md       # README detalhado da Lista 4
│
├── ControlePratosCantina/
│   ├── src/...         # Sistema de Controle de Pratos com Pilhas
│   ├── pom.xml
│   └── README.md       # Explicação das operações de pilha
│
├── AtendimentoClinicaUniversitaria/
│   ├── src/...         # Sistema de Atendimento com Filas e Listas
│   ├── pom.xml
│   └── README.md       # Demonstração de Filas (Queue) e Listas
│
├── PDV/
│   ├── SistemaPDV/     # Sistema de Ponto de Venda com Pilhas e Filas
│   │   ├── src/...
│   │   └── pom.xml
│   └── umlPDV*.png     # Diagramas UML do Sistema PDV
├── Biblioteca/         # Sistema de Biblioteca com modelo, serviço e testes
│   ├── pom.xml
│   ├── README.md       # README específico do projeto Biblioteca
│   └── src/...         # Código-fonte do projeto Biblioteca
│
└── listaN/
    ├── src/...
    ├── README.md       # README detalhado da Lista N (futuro)
```

- Cada pasta (`listaX/`) contém os arquivos **Java** da lista, além de um **README.md** específico com explicações detalhadas, UMLs e instruções de execução.  
- A pasta `PDV/` contém o **Sistema de Ponto de Venda (SistemaPDV)**, um projeto prático que implementa estruturas de dados avançadas.
- A pasta `ControlePratosCantina/` contém o **Sistema de Controle de Pratos**, uma aplicação prática de pilhas em contexto realista.
- A pasta `AtendimentoClinicaUniversitaria/` contém o **Sistema de Atendimento da Clínica**, demonstrando uso prático de filas e listas.
- A pasta `Biblioteca/` contém o **Sistema de Biblioteca**, um projeto educativo de gerenciamento de biblioteca.
- O `README.md` raiz (este arquivo) serve como guia geral do repositório.  

---

## ✅ Listas e Projetos Disponíveis

- **[Lista 1](./lista1/README.md)** → Introdução à POO em Java, modelagem de classes, atributos, métodos e validações.  
- **[Lista 2](./lista2/README.md)** → Manipulação de arquivos XML, persistência de dados, modelagem avançada e UML.  
- **[Lista 3](./lista3/README.md)** → **🆕 Estruturas de Dados com Pilhas** - Implementação e aplicação prática de pilhas em diferentes cenários.
- **[Lista 4](./Lista4/README.md)** → *(Atualizada recentemente)*
- **[Controle de Pratos na Cantina](./ControlePratosCantina/README.md)** → **🆕 Aplicação Prática de Pilhas** - Sistema realista de controle de pratos usando estrutura LIFO.
- **[Atendimento da Clínica Universitária](./AtendimentoClinicaUniversitaria/README.md)** → **🆕 Sistema com Filas e Listas** - Demonstra uso combinado de Queue (fila de pacientes) e List (pacientes atendidos) com sistema de prioridade, estatísticas e relatórios.
- **[Sistema PDV](./PDV/)** → **Sistema de Ponto de Venda** - Aplicação prática de **Pilhas** e **Filas** em um sistema comercial completo.
- **[Biblioteca](./Biblioteca/README.md)** → Sistema de gerenciamento de biblioteca educativo: modelagem de classes (Livro, Aluno, Emprestimo, etc.), serviços e testes com Maven.

---

## ▶️ Como Executar as Listas

1. Entre na pasta da lista desejada (ex.: `cd lista1`).  
2. Siga as instruções do `README.md` específico da lista.  
   - **Lista 1**:  
     ```bash
     cd lista1/src/main/java
     javac org/devigode/App.java
     java org.devigode.App
     ```
   - **Lista 3** (Pilhas):
     ```bash
     cd lista3
     mvn compile exec:java -Dexec.mainClass="br.com.pucgo.App"
     ```
   - **Controle de Pratos na Cantina**:
     ```bash
     cd ControlePratosCantina/src/main/java
     javac br/com/pucgo/*.java
     java br.com.pucgo.App
     ```
   - **Atendimento da Clínica Universitária** (Filas e Listas):
     ```bash
     cd AtendimentoClinicaUniversitaria
     javac -d target/classes -cp src/main/java src/main/java/br/com/pucgo/*.java
     java -cp target/classes br.com.pucgo.App
     ```
   - **Sistema PDV** (Pilhas e Filas):
     ```bash
     cd PDV/SistemaPDV
     mvn compile exec:java -Dexec.mainClass="br.com.pucgo.fila.App"
     # ou para aplicação com pilhas
     mvn compile exec:java -Dexec.mainClass="br.com.pucgo.pilha.App"
     ```
   - **Biblioteca**:
     ```bash
     cd Biblioteca
     mvn clean compile exec:java -Dexec.mainClass="br.com.pucgo.App"
     # ou executar exercícios separadamente:
     # mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio1.App"
     # mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio2.App"
     ```

## 🎯 Destaques por Lista

- **Lista 1**: Fundamentos de POO, encapsulamento e modelagem básica
- **Lista 2**: Persistência XML, validações avançadas e estruturas complexas  
- **Lista 3**: **Estruturas de Dados** - Pilhas com array e encadeadas, aplicações práticas como histórico de navegação, sistema de undo, editor de texto e verificador de parênteses
- **Lista 4**: *(Conteúdo a ser detalhado)*
- **Controle de Pratos na Cantina**: **Aplicação Realista de Pilhas** - Simula o funcionamento de uma cantina usando:
  - 🍽️ **Pilha de Pratos** com operações push, pop e peek
  - ⚠️ **Tratamento de Erros** para pilha cheia e vazia
  - 📊 **Visualização do Estado** da pilha em tempo real
  - 🎯 **Contexto Real** - demonstra como pilhas resolvem problemas do dia a dia
- **Atendimento da Clínica Universitária**: **Sistema com Filas e Listas** - Demonstra o uso de:
  - ⏳ **Fila de Pacientes**: Gerencia a ordem de atendimento
  - 📋 **Lista de Pacientes Atendidos**: Histórico dos atendimentos
  - ⚙️ **Sistema de Prioridade**: Atende pacientes com base em critérios definidos
  - 🔄 **Fila Circular e Encadeada**: Implementações que otimizam espaço e desempenho, com comparação de eficiência entre array e lista encadeada.
  - 🏷️ **Priorização e Complexidade**: Fila de prioridade para idosos (≥60 anos) e casos urgentes, inserção prioritária.
  - 📈 **Estatísticas e Relatórios**: Métodos para contar atendidos, calcular média de idade, encontrar paciente mais idoso e exportar relatório diário em .txt.
- **Sistema PDV**: **Aplicação Comercial Completa** - Sistema de Ponto de Venda implementando:
  - 🛒 **Filas** para gerenciamento de clientes e pedidos
  - 📚 **Pilhas** para histórico de operações
  - 👤 Modelagem completa (Cliente, Pedido, ItemPedido, Endereço)
  - 🔄 Estados de pedido (PedidoStatus)
  - 📊 Diagramas UML detalhados
- **Biblioteca**: **Sistema de Gerenciamento de Biblioteca** - Projeto educativo com:
  - 📚 Modelagem de classes (Livro, Aluno, Emprestimo, etc.)
  - 🛠️ Serviços para gerenciamento de empréstimos e devoluções
  - 📦 Estrutura de diretórios organizada com modelo, serviço e testes
  - 🔄 Integração com Maven para build e execução

---

## 🍽️ Controle de Pratos na Cantina - Características Especiais

O **Sistema de Controle de Pratos** é uma aplicação educativa que demonstra o uso prático de pilhas em um contexto familiar:

### 📋 Funcionalidades Implementadas:
- **Adicionar Pratos (Push)**: Empilha pratos limpos no topo
- **Retirar Pratos (Pop)**: Remove pratos do topo para uso
- **Consultar Topo (Peek)**: Verifica próximo prato sem remover
- **Controle de Estado**: Verifica se está cheia ou vazia
- **Tratamento de Erros**: Simula situações de overflow e underflow

### 🔧 Conceitos de Estruturas de Dados:
- **LIFO (Last In, First Out)**: Princípio fundamental das pilhas
- **Implementação com Arrays**: Estrutura baseada em vetores sem usar Collections
- **Operações O(1)**: Todas as operações são realizadas em tempo constante
- **Modelagem Real**: Aplicação prática de conceitos teóricos

---

## 🏥 Sistema de Atendimento da Clínica Universitária - Características Especiais

O **Sistema de Atendimento da Clínica** é uma aplicação educativa que demonstra o uso combinado de filas e listas em um contexto hospitalar:

### 📋 Funcionalidades Implementadas:
- **Fila de Pacientes (Queue)**: Gerencia ordem de atendimento com sistema FIFO
- **Fila Circular de Pacientes**: Otimiza o uso do array, evitando deslocamento de elementos.
- **Fila Encadeada de Pacientes**: Implementação baseada em nós (Linked List), eficiente para inserções e remoções.
- **Comparação de Eficiência**: Discussão sobre desempenho e complexidade das diferentes implementações de fila.
- **Sistema de Prioridade**: Fila separada para idosos (≥60 anos) e casos urgentes (campo urgente), inserção prioritária.
- **Lista de Atendidos**: Histórico completo dos pacientes já consultados
- **Busca Inteligente**: Localização de pacientes por CPF na lista de atendidos
- **Estatísticas em Tempo Real**: Média de idade, distribuição por prioridade, paciente mais idoso
- **Relatório Diário**: Geração de relatório com total de atendidos, média de idade, paciente mais idoso e exportação em .txt

### 🔧 Conceitos de Estruturas de Dados:
- **FIFO (First In, First Out)**: Princípio fundamental das filas implementado com prioridade
- **Lista Dinâmica**: Armazenamento flexível para histórico de atendimentos
- **Operações Queue**: enqueue (adicionar), dequeue (remover), peek (consultar)
- **Operações List**: add, search, display com algoritmos de busca linear
- **Modelagem Real**: Simula situações práticas de gerenciamento hospitalar
- **Complexidade Algorítmica**: Discussão sobre O(1) e O(n) nas operações de fila normal vs fila de prioridade
- **Vantagens de Estruturas**: Quando usar array, lista encadeada ou fila de prioridade na prática

---

## 🆕 Novidades

- A **Lista 2** possui seu próprio [README detalhado](./lista2/README.md) com instruções de execução, estrutura, exemplos e diagramas UML.
- A **Lista 3** apresenta implementações completas de **Estruturas de Dados** focadas em **Pilhas**, com exercícios práticos e interface visual melhorada. Veja o [README da Lista 3](./lista3/README.md).
- **🆕 Controle de Pratos na Cantina** - Nova aplicação prática demonstrando o uso de pilhas em um contexto realista e educativo. Sistema completo com tratamento de erros e visualização do estado.
- A **Lista 4** foi adicionada recentemente ao repositório.
- O **Sistema PDV** combina **Pilhas** e **Filas** em uma aplicação comercial real, demonstrando o uso prático dessas estruturas em sistemas de produção.
- **🆕 Atendimento da Clínica Universitária** - Novo sistema demonstrando o uso de filas e listas em um contexto de atendimento médico, com gerenciamento de pacientes, prioridades, estatísticas e relatórios.
- **🆕 Projeto Biblioteca** - Adição do sistema de gerenciamento de biblioteca, com modelagem de classes, serviços e integração com Maven. Veja o [README da Biblioteca](./Biblioteca/README.md) para detalhes.

