# 📚 Sistema de Gerenciamento de Biblioteca Universitária

## 📋 Descrição do Projeto

Sistema completo de gerenciamento de empréstimos para Biblioteca Universitária, desenvolvido em Java utilizando Maven. O sistema permite o controle de empréstimos e devoluções de livros, gerenciamento de alunos, funcionários, editoras e unidades da biblioteca.

Este projeto foi desenvolvido com foco em demonstrar a aplicação prática de diferentes **estruturas de dados** em um cenário real de gerenciamento bibliotecário.

---

## 🎯 Funcionalidades Implementadas

### 📖 Cadastros
- ✅ **Alunos**: RA, nome completo, curso, telefone, e-mail institucional e endereço
- ✅ **Livros**: ISBN, título, autor, ano de publicação, categoria, exemplares disponíveis e editora
- ✅ **Editoras**: CNPJ, nome, telefone e e-mail
- ✅ **Unidades da Biblioteca**: CNPJ, nome, endereço e responsável local
- ✅ **Funcionários**: Matrícula, nome, cargo, telefone e e-mail
- ✅ **Bibliotecário Supervisor**: Autorização de renovações especiais

### 📝 Operações Principais
- ✅ **Fila de Atendimento**: Gerenciamento de fila de alunos aguardando atendimento
- ✅ **Empréstimos**: Registro de requisição com múltiplos livros
- ✅ **Devoluções**: Devolução de livros com registro de data
- ✅ **Conferência**: Pilha de livros aguardando inspeção antes de retornar ao acervo
- ✅ **Renovação Especial**: Autorização do supervisor para extensão de prazos
- ✅ **Histórico**: Registro completo de empréstimos e devoluções

### 📊 Relatórios e Consultas
- ✅ Consulta de empréstimos ativos por aluno (busca rápida por RA)
- ✅ Listagem de alunos com livros em atraso
- ✅ Ranking de livros mais solicitados
- ✅ Visualização de categorias únicas de livros
- ✅ Listagem completa de alunos, livros e unidades

---

## 🗂️ Estruturas de Dados Utilizadas

O projeto implementa as seguintes estruturas de dados conforme especificação:

| Estrutura | Implementação Java | Aplicação no Sistema |
|-----------|-------------------|----------------------|
| **Lista** | `ArrayList` e `LinkedList` | Armazenamento de alunos, livros, editoras, unidades e funcionários |
| **Pilha** | `Stack` | Controle de livros devolvidos aguardando conferência (LIFO) |
| **Fila** | `Queue` (via `LinkedList`) | Organização da fila de atendimento de alunos (FIFO) |
| **Mapa** | `HashMap` | Associação entre RA do aluno e seus empréstimos ativos (busca O(1)) |
| **Conjunto** | `HashSet` | Armazenamento de categorias únicas de livros (sem duplicatas) |

---

## 🏗️ Arquitetura do Projeto

```
Biblioteca/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/
│   │           └── com/
│   │               └── pucgo/
│   │                   ├── App.java                          # Classe principal
│   │                   ├── model/                            # Modelos de dados
│   │                   │   ├── Aluno.java
│   │                   │   ├── BibliotecarioSupervisor.java
│   │                   │   ├── Editora.java
│   │                   │   ├── Emprestimo.java
│   │                   │   ├── Endereco.java
│   │                   │   ├── Funcionario.java
│   │                   │   ├── HistoricoEmprestimo.java
│   │                   │   ├── Livro.java
│   │                   │   └── UnidadeBiblioteca.java
│   │                   └── service/                          # Lógica de negócio
│   │                       └── SistemaBiblioteca.java
│   └── test/
│       └── java/
│           └── br/
│               └── com/
│                   └── pucgo/
│                       └── AppTest.java
├── pom.xml                                                    # Configuração Maven
└── README.md                                                  # Este arquivo
```

---

## 🔧 Tecnologias Utilizadas

- **Java 8+**: Linguagem de programação
- **Maven 3.x**: Gerenciamento de dependências e build
- **JUnit 3.8.1**: Testes unitários
- **Collections Framework**: Estruturas de dados nativas do Java

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado
- Maven 3.x instalado
- Variáveis de ambiente JAVA_HOME e MAVEN_HOME configuradas

### Compilação
```bash
cd C:\Users\igor.bittencourt\dev\workspace\Biblioteca
mvn clean compile
```

### Execução
```bash
mvn exec:java -Dexec.mainClass="br.com.pucgo.App"
```

Ou compile e execute de uma vez:
```bash
mvn clean compile exec:java -Dexec.mainClass="br.com.pucgo.App"
```

---

## 📸 Resultado da Execução

```
=================================================
  SISTEMA DE GERENCIAMENTO DE BIBLIOTECA
=================================================


--- CADASTRANDO EDITORAS ---
Editora cadastrada com sucesso: Editora Acadêmica
Editora cadastrada com sucesso: Editora Universitária
Editora cadastrada com sucesso: Editora Técnica

--- CADASTRANDO LIVROS ---
Livro cadastrado com sucesso: Estruturas de Dados em Java
Livro cadastrado com sucesso: Algoritmos e Lógica
Livro cadastrado com sucesso: Banco de Dados Moderno
Livro cadastrado com sucesso: Engenharia de Software
Livro cadastrado com sucesso: Redes de Computadores

--- CADASTRANDO UNIDADES DA BIBLIOTECA ---
Unidade cadastrada com sucesso: Biblioteca Central
Unidade cadastrada com sucesso: Biblioteca Campus II

--- CADASTRANDO FUNCIONÁRIOS ---
Funcionário cadastrado com sucesso: Marcos Oliveira
Funcionário cadastrado com sucesso: Dra. Juliana Martins

--- CADASTRANDO ALUNOS ---
Aluno cadastrado com sucesso: Lucas Henrique Souza
Aluno cadastrado com sucesso: Beatriz Ferreira Costa
Aluno cadastrado com sucesso: Rafael Moreira Santos


=== DEMONSTRAÇÃO: FILA DE ATENDIMENTO (Queue) ===
Aluno Lucas Henrique Souza adicionado à fila de atendimento.
Posição na fila: 1
Aluno Beatriz Ferreira Costa adicionado à fila de atendimento.
Posição na fila: 2
Aluno Rafael Moreira Santos adicionado à fila de atendimento.
Posição na fila: 3

=== FILA DE ATENDIMENTO ===
1. Lucas Henrique Souza - RA: 202301001
2. Beatriz Ferreira Costa - RA: 202301002
3. Rafael Moreira Santos - RA: 202301003


=== REALIZANDO EMPRÉSTIMOS ===
Atendendo aluno: Lucas Henrique Souza
Empréstimo realizado com sucesso! ID: 1
Atendendo aluno: Beatriz Ferreira Costa
Empréstimo realizado com sucesso! ID: 2
Atendendo aluno: Rafael Moreira Santos
Empréstimo realizado com sucesso! ID: 3


=== CONSULTANDO EMPRÉSTIMOS ATIVOS (HashMap) ===

Empréstimos do aluno Lucas Henrique Souza:
ID: 1 | Aluno: Lucas Henrique Souza | Livros: 2 | Data Empréstimo: 2025-10-27 | Devolução Prevista: 2025-11-10 | Status: ATIVO


=== CATEGORIAS ÚNICAS DE LIVROS (HashSet) ===

=== CATEGORIAS DE LIVROS ===
Total de categorias únicas: 4
- Banco de Dados
- Engenharia
- Programação
- Redes


=== RENOVAÇÃO ESPECIAL COM AUTORIZAÇÃO DO SUPERVISOR ===
Renovação especial autorizada pelo supervisor Dra. Juliana Martins para o empréstimo ID: 1


=== REALIZANDO DEVOLUÇÕES ===
Livro 'Algoritmos e Lógica' adicionado à pilha de conferência.
Livro 'Engenharia de Software' adicionado à pilha de conferência.
Devolução registrada com sucesso!


=== LIVROS NA PILHA DE CONFERÊNCIA (Stack) ===

=== LIVROS AGUARDANDO CONFERÊNCIA ===
Total: 2
- Engenharia de Software
- Algoritmos e Lógica

--- CONFERINDO LIVROS ---
Livro conferido e devolvido ao acervo: Engenharia de Software
Livro conferido e devolvido ao acervo: Algoritmos e Lógica
Nenhum livro aguardando conferência.


=== RELATÓRIOS DO SISTEMA ===

=== TODOS OS ALUNOS CADASTRADOS ===
RA: 202301001 | Nome: Lucas Henrique Souza | Curso: Ciência da Computação | Email: lucas.souza@aluno.pucgo.edu.br
RA: 202301002 | Nome: Beatriz Ferreira Costa | Curso: Sistemas de Informação | Email: beatriz.costa@aluno.pucgo.edu.br
RA: 202301003 | Nome: Rafael Moreira Santos | Curso: Engenharia de Software | Email: rafael.santos@aluno.pucgo.edu.br

=== TODOS OS LIVROS CADASTRADOS ===
ISBN: 978-3-16-148410-0 | Título: Estruturas de Dados em Java | Autor: João Silva | Disponíveis: 4/5
ISBN: 978-0-13-468599-1 | Título: Algoritmos e Lógica | Autor: Maria Santos | Disponíveis: 3/3
ISBN: 978-0-321-57351-3 | Título: Banco de Dados Moderno | Autor: Carlos Pereira | Disponíveis: 3/4
ISBN: 978-0-134-68599-2 | Título: Engenharia de Software | Autor: Ana Costa | Disponíveis: 6/6
ISBN: 978-0-596-52068-7 | Título: Redes de Computadores | Autor: Pedro Lima | Disponíveis: 1/2

=== TODAS AS UNIDADES ===
Unidade: Biblioteca Central | CNPJ: 10.111.222/0001-33 | Responsável: Prof. Roberto Alves
Unidade: Biblioteca Campus II | CNPJ: 10.111.222/0002-44 | Responsável: Dra. Fernanda Rocha

=== ALUNOS COM LIVROS EM ATRASO ===
Nenhum aluno com atraso.

=== LIVROS MAIS SOLICITADOS ===
1. Engenharia de Software - 1 empréstimos
2. Redes de Computadores - 1 empréstimos
3. Estruturas de Dados em Java - 1 empréstimos
4. Banco de Dados Moderno - 1 empréstimos
5. Algoritmos e Lógica - 1 empréstimos


=================================================
  ESTRUTURAS DE DADOS UTILIZADAS
=================================================
✓ LISTA (ArrayList/LinkedList): Alunos, Livros, Editoras, Unidades
✓ PILHA (Stack): Livros aguardando conferência
✓ FILA (Queue): Fila de atendimento de alunos
✓ MAPA (HashMap): Empréstimos ativos por RA
✓ CONJUNTO (HashSet): Categorias únicas de livros
=================================================

Sistema finalizado com sucesso!
```

---

## 💡 Destaques Técnicos

### 1. **Fila de Atendimento (Queue)**
- Implementa o padrão FIFO (First In, First Out)
- Alunos são atendidos na ordem de chegada
- Utiliza `LinkedList` como implementação da interface `Queue`

### 2. **Pilha de Conferência (Stack)**
- Implementa o padrão LIFO (Last In, First Out)
- Livros devolvidos são conferidos na ordem inversa
- Garante que o último livro devolvido seja o primeiro a ser conferido

### 3. **Mapa de Empréstimos (HashMap)**
- Busca de empréstimos por RA em tempo constante O(1)
- Cada aluno pode ter múltiplos empréstimos ativos
- Facilita a consulta rápida de pendências

### 4. **Conjunto de Categorias (HashSet)**
- Garante unicidade das categorias de livros
- Evita duplicação de dados
- Operações de inserção e busca em O(1)

### 5. **Listas Dinâmicas (ArrayList)**
- Armazenamento eficiente de entidades
- Fácil iteração e manipulação
- Redimensionamento automático

---

## 📐 Modelagem de Classes

### Principais Classes de Modelo

- **Aluno**: Representa os estudantes universitários
- **Livro**: Controla o acervo bibliográfico
- **Emprestimo**: Registra transações de empréstimo
- **BibliotecarioSupervisor**: Autoriza renovações especiais
- **HistoricoEmprestimo**: Mantém histórico de transações

### Classe de Serviço

- **SistemaBiblioteca**: Centraliza toda a lógica de negócio e gerencia as estruturas de dados

---

## 🔄 Fluxo de Operações

```
1. Cadastro → Alunos, Livros, Editoras são cadastrados no sistema
              ↓
2. Fila de Atendimento → Alunos entram na fila (Queue)
              ↓
3. Atendimento → Aluno é retirado da fila (poll)
              ↓
4. Empréstimo → Livros são emprestados e registrados no HashMap
              ↓
5. Devolução → Livros devolvidos vão para pilha de conferência (Stack)
              ↓
6. Conferência → Livros são conferidos e retornam ao acervo (pop)
              ↓
7. Histórico → Todas operações são registradas para consultas
```

---

## 🎓 Conceitos Aplicados

- ✅ Programação Orientada a Objetos (POO)
- ✅ Encapsulamento e Abstração
- ✅ Herança (BibliotecarioSupervisor extends Funcionario)
- ✅ Polimorfismo
- ✅ Estruturas de Dados (Lista, Pilha, Fila, Mapa, Conjunto)
- ✅ Java Collections Framework
- ✅ Tratamento de datas com LocalDate
- ✅ Padrões de projeto (Service Layer)
- ✅ Separação de responsabilidades (Model/Service)

---

## 📈 Melhorias Futuras

- [ ] Implementar interface gráfica (JavaFX ou Swing)
- [ ] Adicionar persistência de dados (banco de dados ou arquivos)
- [ ] Implementar sistema de multas por atraso
- [ ] Adicionar autenticação e autorização
- [ ] Criar API REST para acesso remoto
- [ ] Implementar notificações por e-mail
- [ ] Adicionar relatórios em PDF
- [ ] Implementar busca avançada de livros
- [ ] Adicionar reserva de livros

---

## 👥 Autor

Desenvolvido como projeto acadêmico para demonstração de estruturas de dados aplicadas.

**Instituição**: PUC Goiás  
**Curso**: Ciência da Computação / Sistemas de Informação  
**Data**: Outubro de 2025

---

## 📄 Licença

Este projeto é de uso acadêmico e educacional.

---

## 🙏 Agradecimentos

Agradecimentos especiais aos professores e colegas que contribuíram com ideias e feedback para o desenvolvimento deste projeto.

---

**📚 Sistema de Gerenciamento de Biblioteca - PUC Goiás © 2025**

