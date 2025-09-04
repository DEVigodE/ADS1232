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
└── listaN/
    ├── src/...
    ├── README.md       # README detalhado da Lista N (futuro)
```

- Cada pasta (`listaX/`) contém os arquivos **Java** da lista, além de um **README.md** específico com explicações detalhadas, UMLs e instruções de execução.  
- O `README.md` raiz (este arquivo) serve como guia geral do repositório.  

---

## ✅ Listas Disponíveis

- **[Lista 1](./lista1/README.md)** → Introdução à POO em Java, modelagem de classes, atributos, métodos e validações.  
- **[Lista 2](./lista2/README.md)** → Manipulação de arquivos XML, persistência de dados, modelagem avançada e UML.  
- **[Lista 3](./lista3/README.md)** → **🆕 Estruturas de Dados com Pilhas** - Implementação e aplicação prática de pilhas em diferentes cenários.
- **Lista N** → *(em breve)*  

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

---

## 🎯 Destaques por Lista

- **Lista 1**: Fundamentos de POO, encapsulamento e modelagem básica
- **Lista 2**: Persistência XML, validações avançadas e estruturas complexas  
- **Lista 3**: **Estruturas de Dados** - Pilhas com array e encadeadas, aplicações práticas como histórico de navegação, sistema de undo, editor de texto e verificador de parênteses

---

✍️ **Observação**: Cada lista possui **arquivos de UML** (`.mmd` e `.png`) para facilitar a visualização dos diagramas de classe.

---

## 🆕 Novidades

- A **Lista 2** possui seu próprio [README detalhado](./lista2/README.md) com instruções de execução, estrutura, exemplos e diagramas UML.
- A **Lista 3** apresenta implementações completas de **Estruturas de Dados** focadas em **Pilhas**, com exercícios práticos e interface visual melhorada. Veja o [README da Lista 3](./lista3/README.md).
