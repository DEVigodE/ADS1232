
# 📚 Lista 2 – Programação Orientada a Objetos (Java)

Esta lista aprofunda conceitos de POO com foco em persistência, manipulação de dados, modelagem e UML. Cada exercício traz desafios práticos e simulações de sistemas reais.

---

## 📂 Estrutura dos Exercícios

- **Exercício 1:**  
   - Modela o passageiro e seu sexo.
   - Foco em atributos, validações e encapsulamento.
   - Arquivo principal: `Passageiro.java`, `Sexo.java`.
   - Diagrama UML disponível.

- **Exercício 2:**  
   - Introduz a classe `CentralDeInformacoes` para gerenciar passageiros.
   - Manipulação de dados e relacionamento entre classes.
   - Persistência simulada via XML.
   - Arquivos principais: `CentralDeInformacoes.java`, `Passageiro.java`, `Sexo.java`.
   - Diagrama UML disponível.

- **Exercício 3:**  
   - Adiciona a classe `Persistencia` para salvar/carregar dados.
   - Simula operações de leitura/escrita em arquivos.
   - Arquivos principais: `Persistencia.java`, `CentralDeInformacoes.java`, `Passageiro.java`.
   - Diagrama UML disponível.

- **Exercício 4:**  
   - Evolui o sistema de persistência e gerenciamento.
   - Refina as classes e métodos de manipulação.
   - Arquivos principais: `Persistencia.java`, `CentralDeInformacoes.java`, `Passageiro.java`.
   - Diagrama UML disponível.

- **Exercício 5:**  
   - Introduz a classe `Corrida` para simular corridas de passageiros.
   - Sistema mais completo, com gerenciamento de corridas e passageiros.
   - Arquivos principais: `Corrida.java`, `CentralDeInformacoes.java`, `Passageiro.java`.
   - Diagrama UML disponível.

---

## ▶️ Como Executar

1. Entre na pasta da lista:

    ```bash
    cd lista2
    ```

2. Compile e execute o programa principal de cada exercício:

    ```bash
    cd src/main/java
    javac br/com/pucgo/exercicioX/App.java
    java br.com.pucgo.exercicioX.App
    ```
    > Substitua `exercicioX` pelo número do exercício desejado.

3. Para rodar os testes (se houver):

    ```bash
    mvn test
    ```

---

## 📊 Diagramas UML

Cada exercício possui diagramas UML (`.mmd` e `.png`) para facilitar a visualização da estrutura das classes e seus relacionamentos.

---

## 📄 Observações

- Os arquivos XML simulam persistência e manipulação de dados.
- Consulte os diagramas UML para entender a modelagem proposta.
- Utilize o `pom.xml` para gerenciar dependências e facilitar a execução dos testes.

---

Bons estudos!
