# 📘 Lista de Exercícios – Programação Orientada a Objetos (Java)

Este projeto contém a implementação de uma lista de exercícios em **Java**, onde cada exercício pede a modelagem de uma classe com atributos, métodos e regras de negócio específicas, além de um **diagrama UML** correspondente.  

O objetivo é praticar **POO (Programação Orientada a Objetos)**: encapsulamento, validação, construtores, cálculos e organização em pacotes.  

---

## 📂 Estrutura do Projeto

```
src/
 └── main/
     └── java/
         └── org/devigode/
             └── App.java                # Classe principal que executa todos os exercícios
         └── exercicio1/
             └── Pessoa.java
         └── exercicio2/
             └── Piramide.java
         └── exercicio3/
             └── Esfera.java
         └── exercicio4/
             └── Retangulo.java
         └── exercicio5/
             └── Paralelepipedo.java
         └── exercicio6/
             └── Cilindro.java
         └── exercicio7/
             └── Cone.java
         └── exercicio8/
             └── Paciente.java
         └── exercicio9/
             └── Eleitor.java
         └── exercicio11/
             └── Funcionario.java
```

- Cada pacote (`exercicioX`) corresponde ao número do exercício.
- Dentro de cada pacote há **uma classe Java** que implementa a solução.
- A classe **`App.java`** executa todos os exercícios sequencialmente, exibindo resultados no console.

---

## 📌 Exercícios Implementados

- **Exercício 1** – Pessoa (dados e IMC)  
- **Exercício 2** – Pirâmide (volume)  
- **Exercício 3** – Esfera (área e volume)  
- **Exercício 4** – Retângulo (área e perímetro)  
- **Exercício 5** – Paralelepípedo (área e volume)  
- **Exercício 6** – Cilindro (áreas e volume)  
- **Exercício 7** – Cone (geratriz, áreas e volume)  
- **Exercício 8** – Paciente (IMC e faixa de risco)  
- **Exercício 9** – Eleitor (tipo conforme idade)  
- **Exercício 11** – Funcionário (INSS, IR, aumento, salário líquido)  

---

## ▶️ Como Rodar o Projeto

### Pré-requisitos
- **Java JDK 17+** instalado
- **Maven** (se for usar como projeto Maven) ou suporte a projetos Java no seu IDE (IntelliJ, Eclipse, VSCode, NetBeans etc.)

### Passo 1: Compilar
Navegue até a pasta `src/main/java` e compile todas as classes:

```bash
javac org/devigode/App.java
```

> Isso também compilará todas as classes importadas (`Pessoa`, `Piramide`, `Esfera` etc.).

### Passo 2: Executar
Execute a classe principal `App`:

```bash
java org.devigode.App
```

---

## 💡 Observações

- Os **diagramas UML** podem ser gerados a partir das definições em **Mermaid** (fornecidas em cada exercício).  
- Se você estiver usando o **IntelliJ IDEA** ou **Eclipse**, basta rodar o `App.java` clicando em ▶️ no editor.  
- O código contém **validações** (exemplo: raio não pode ser negativo, salário não pode ser menor que zero, etc.), garantindo consistência dos dados.  
