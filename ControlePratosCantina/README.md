# Sistema de Controle de Pratos na Cantina

## Explicação do Problema e Solução

A estrutura de dados **pilha (Stack)** é ideal para resolver o problema da cantina porque segue o princípio **LIFO (Last In, First Out)**, que é exatamente como funciona uma pilha física de pratos:

- **Push (Empilhar)**: Quando um funcionário da cozinha coloca um prato limpo, ele sempre vai no topo da pilha, seguindo a ordem natural.
- **Pop (Desempilhar)**: Quando um aluno vai almoçar, ele pega sempre o prato do topo, que foi o último a ser colocado e está mais acessível.
- **Peek (Consultar)**: O atendente pode verificar qual prato está disponível no topo sem precisar removê-lo, útil para conferir se há pratos disponíveis.

Essa implementação garante eficiência (operações O(1)), organização natural (pratos mais recentes são usados primeiro) e facilita o controle de capacidade da pilha, evitando que pratos caiam por sobrecarga ou que alunos tentem pegar pratos inexistentes.

## Funcionalidades Implementadas

✅ Adicionar pratos (push)
✅ Retirar pratos (pop) 
✅ Consultar prato do topo (peek)
✅ Verificar se está vazia ou cheia
✅ Exibir estado completo da pilha
✅ Tratamento de erros (pilha cheia/vazia)

## Como Executar

```bash
javac br/com/pucgo/*.java
java br.com.pucgo.App
```
