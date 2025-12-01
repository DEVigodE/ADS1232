# 📚 Lista 3 - Estruturas de Dados com Pilhas

**Disciplina:** Algoritmos e Estruturas de Dados  
**Foco:** Implementação e aplicação prática de **Pilhas** (Stack)  
**Linguagem:** Java 8+  

Esta lista contém **5 exercícios práticos** que demonstram diferentes aplicações de pilhas em problemas reais de programação, desde estruturas básicas com arrays até pilhas encadeadas genéricas.

---

## 🎯 Objetivos de Aprendizagem

- Compreender o conceito **LIFO** (Last In, First Out) das pilhas
- Implementar pilhas usando **arrays** e **listas encadeadas**
- Aplicar pilhas em problemas práticos como:
  - Histórico de navegação web
  - Sistemas de desfazer (Undo)
  - Editores de texto
  - Verificação de parênteses balanceados
- Praticar **programação orientada a objetos** com encapsulamento
- Utilizar **genéricos** em Java para estruturas de dados

---

## 📂 Estrutura do Projeto

```
lista3/
├── pom.xml                             # Configuração Maven
├── README.md                           # Este arquivo
└── src/main/java/br/com/pucgo/
    ├── App.java                        # 🎮 Menu principal integrado
    ├── dominio/                        # 📦 Classes de estruturas de dados
    │   ├── Pilha.java                  # Pilha com array (int)
    │   ├── PilhaEncadeada.java         # Pilha encadeada genérica <T>
    │   ├── Fila.java                   # Estrutura auxiliar
    │   └── interfaces/
    │       └── IEstruturaDeDados.java  # Interface base
    ├── exercicio1/                     # 🌐 Histórico de Navegação
    │   ├── App.java
    │   └── HistoricoNavegacao.java
    ├── exercicio2/                     # ↩️ Sistema Desfazer
    │   ├── App.java
    │   └── CentralDeInformacoes.java
    ├── exercicio3/                     # ✏️ Editor de Texto
    │   ├── App.java
    │   └── EditorTexto.java
    ├── exercicio4/                     # 🔄 Inversor de Frases
    │   ├── App.java
    │   └── ReversorFrase.java
    └── exercicio5/                     # 🔍 Verificador de Parênteses
        ├── App.java
        └── VerificadorParenteses.java
```

---

## 🚀 Como Executar

### **Opção 1: Menu Principal Integrado** ⭐
```bash
cd lista3
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.App"
```

### **Opção 2: Exercícios Individuais**
```bash
# Exercício 1 - Histórico de Navegação
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio1.App"

# Exercício 2 - Sistema Desfazer
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio2.App"

# Exercício 3 - Editor de Texto
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio3.App"

# Exercício 4 - Inversor de Frases
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio4.App"

# Exercício 5 - Verificador de Parênteses
mvn compile exec:java -Dexec.mainClass="br.com.pucgo.exercicio5.App"
```

### **Opção 3: Compilação Manual**
```bash
cd src/main/java
javac br/com/pucgo/App.java
java br.com.pucgo.App
```

---

## 📝 Exercícios Implementados

### **1️⃣ Exercício 1: Histórico de Navegação Web** 🌐
**Arquivo:** `exercicio1/HistoricoNavegacao.java`  
**Conceito:** Simulação de um navegador web com histórico

**Funcionalidades:**
- Acessar sites por ID (números inteiros)
- Comando `VOLTAR` remove o último site da pilha
- Comando `HISTORICO` exibe estatísticas
- Comando `SAIR` encerra o programa

**Exemplo de uso:**
```
>>> Comando: 100
🌐 Acessando site ID: 100

>>> Comando: 200  
🌐 Acessando site ID: 200

>>> Comando: VOLTAR
⬅️ Saindo do site ID: 200
🔄 Voltando para site ID: 100
```

---

### **2️⃣ Exercício 2: Sistema de Desfazer (Undo)** ↩️
**Arquivo:** `exercicio2/CentralDeInformacoes.java`  
**Conceito:** Simulação de um editor que permite desfazer comandos

**Funcionalidades:**
- Executar comandos por ID (números inteiros)
- Comando `DESFAZER` remove o último comando da pilha
- Comando `STATUS` mostra estado do sistema
- Comando `SAIR` finaliza o programa

**Exemplo de uso:**
```
>>> Comando: 101
✅ Comando 101 executado com sucesso!

>>> Comando: DESFAZER
↩️ Comando 101 foi desfeito!
🆕 Estado limpo: nenhum comando ativo
```

---

### **3️⃣ Exercício 3: Editor de Texto com Backspace** ✏️
**Arquivo:** `exercicio3/EditorTexto.java`  
**Conceito:** Editor simples que gerencia caracteres com pilha

**Funcionalidades:**
- Digitar texto (caracteres são empilhados)
- Comando `BACKSPACE` remove o último caractere
- Comando `PREVIEW` mostra prévia do texto
- Comando `ENTER` exibe texto final e encerra

**Exemplo de uso:**
```
>>> Digite: Olá
✏️ Adicionados 3 caracteres: "Olá"

>>> Digite: BACKSPACE
⌫ Caractere 'á' removido!

>>> Digite: ENTER
📄 TEXTO FINAL: "Ol"
```

---

### **4️⃣ Exercício 4: Inversor de Frases** 🔄
**Arquivo:** `exercicio4/ReversorFrase.java`  
**Conceito:** Utiliza `PilhaEncadeada<Character>` para inverter texto

**Funcionalidades:**
- Inverte qualquer frase digitada
- Mostra processo detalhado de empilhamento/desempilhamento
- Detecta palíndromos automaticamente
- Interface visual com emojis

**Exemplo de uso:**
```
>>> Digite uma frase: Java
📥 EMPILHANDO: 'J' → 'a' → 'v' → 'a' → PILHA
📤 DESEMPILHANDO: 'a' → 'v' → 'a' → 'J'

🎯 RESULTADO:
📝 Original : "Java"  
🔄 Invertida: "avaJ"
```

---

### **5️⃣ Exercício 5: Verificador de Parênteses** 🔍
**Arquivo:** `exercicio5/VerificadorParenteses.java`  
**Conceito:** Algoritmo clássico para verificar balanceamento de parênteses

**Funcionalidades:**
- Verifica expressões matemáticas
- Análise detalhada posição por posição
- Algoritmo usado em **compiladores** e **interpretadores**
- Detecta erros específicos (sem abertura, sem fechamento)

**Exemplo de uso:**
```
>>> Digite uma expressão: (2+3)*(5-1)
🔍 ANALISANDO: "(2+3)*(5-1)"
   Posição 1: '(' → EMPILHAR | Pilha: 1 elemento(s)
   Posição 4: ')' → DESEMPILHAR | Pilha: 0 elemento(s)
   ...

✅ Status: VÁLIDA - Parênteses balanceados
```

---

## 🏗️ Arquitetura das Classes

### **Estruturas de Dados Base**

#### **`Pilha.java`** 📚
- Implementação com **array de inteiros**
- Métodos: `adicionar()`, `remover()`, `peek()`, `estaVazia()`, `tamanho()`
- Capacidade fixa definida no construtor

#### **`PilhaEncadeada<T>.java`** 🔗
- Implementação **genérica** com nós encadeados
- Métodos: `push()`, `pop()`, `peek()`, `estaVazia()`, `tamanho()`
- Capacidade **dinâmica** (limitada apenas pela memória)

### **Padrões de Design Utilizados**

- **Single Responsibility**: Cada classe tem uma responsabilidade específica
- **Encapsulamento**: Atributos privados com métodos públicos controlados
- **Composition**: Classes de exercício utilizam as estruturas de dados
- **Template Method**: Estrutura similar em todos os exercícios

---

## 🎨 Interface Visual

O projeto utiliza uma **interface visual melhorada** com:
- **Emojis** para melhor experiência do usuário
- **Bordas ASCII** em estilo caixa para menus
- **Cores visuais** através de emojis temáticos
- **Feedback detalhado** das operações

### Exemplo de Interface:
```
╔══════════════════════════════════════╗
║       🌐 NAVEGADOR WEB SIMPLES       ║
╠══════════════════════════════════════╣
║ 📝 Digite o ID do site (número)      ║
║ ⬅️  Digite 'VOLTAR' para retroceder  ║
║ 📊 Digite 'HISTORICO' para ver stats ║
║ 🚪 Digite 'SAIR' para fechar         ║
╚══════════════════════════════════════╝
```

---

## 🔧 Compatibilidade

- **Java 8+** (compatível com versões anteriores)
- **Maven** para gerenciamento de dependências
- **IDE** qualquer (IntelliJ IDEA, Eclipse, VS Code)
- **Sistema Operacional** multiplataforma (Windows, Linux, macOS)

---

## 💡 Conceitos de Estruturas de Dados Abordados

### **LIFO (Last In, First Out)**
- Princípio fundamental das pilhas
- Último elemento inserido é o primeiro a ser removido
- Analogia: pilha de pratos

### **Operações Básicas de Pilha**
- **Push**: Inserir elemento no topo
- **Pop**: Remover elemento do topo  
- **Peek/Top**: Visualizar elemento do topo sem remover
- **isEmpty**: Verificar se pilha está vazia
- **Size**: Obter tamanho atual

### **Aplicações Práticas**
- **Histórico** (navegador, editores)
- **Undo/Redo** (editores, IDEs)
- **Verificação de sintaxe** (parênteses, chaves)
- **Avaliação de expressões**
- **Chamadas de função** (call stack)

---

## 🎓 Exercícios Adicionais Sugeridos

Para praticar mais, tente implementar:

1. **Calculadora com Pilha** - Avaliação de expressões pós-fixas
2. **Navegador com Histórico Duplo** - Pilha para frente e para trás
3. **Editor com Múltiplos Undos** - Pilha de estados completos
4. **Verificador de Chaves/Colchetes** - Extensão do verificador de parênteses
5. **Conversor de Bases Numéricas** - Usando pilha para conversão

---

## 📖 Referências e Estudos

### **Bibliografia Recomendada**
- **"Estruturas de Dados e Algoritmos em Java"** - Robert Lafore
- **"Algoritmos: Teoria e Prática"** - Cormen, Leiserson, Rivest, Stein
- **"Java: The Complete Reference"** - Herbert Schildt

### **Documentação Online**
- [Java Stack Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html)
- [Algoritmos de Pilha - GeeksforGeeks](https://www.geeksforgeeks.org/stack-data-structure/)

---

## ✅ Checklist de Funcionalidades

- [x] **Pilha com Array** - Implementação básica com inteiros
- [x] **Pilha Encadeada Genérica** - Suporte a qualquer tipo `<T>`
- [x] **Histórico de Navegação** - Simulação de navegador web
- [x] **Sistema de Undo** - Desfazer comandos executados
- [x] **Editor de Texto** - Backspace com pilha de caracteres
- [x] **Inversor de Frases** - Algoritmo com pilha encadeada
- [x] **Verificador de Parênteses** - Algoritmo clássico de compiladores
- [x] **Menu Principal Integrado** - Navegação entre exercícios
- [x] **Interface Visual Melhorada** - Emojis e formatação ASCII
- [x] **Documentação Completa** - README detalhado
- [x] **Compatibilidade Java 8+** - Sem dependências modernas

---

🎉 **Projeto completo e funcional!** Todos os exercícios estão implementados com qualidade profissional e prontos para uso educativo.
