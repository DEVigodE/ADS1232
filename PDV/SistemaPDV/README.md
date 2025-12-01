# Sistema PDV - Collections Framework

Sistema de Ponto de Vendas completo implementado utilizando as classes da API Collections do Java.

## 📋 Estrutura do Projeto

### Uso das Collections Framework

| Estrutura | Classe da API | Tipo de uso no PDV |
|-----------|--------------|-------------------|
| **Lista** | `ArrayList`, `LinkedList` | Cadastro e armazenamento de entidades (Cliente, Produto, Pedido) |
| **Pilha** | `Stack` | Controle de histórico e operações de desfazer/refazer |
| **Fila** | `Queue` (via LinkedList) | Processamento sequencial de pedidos ou atendimentos |
| **Deque** | `ArrayDeque` | Implementação interna de Fila e Pilha |

## 🏗️ Arquitetura

### Pacote `modelo`
Entidades do domínio:
- **Cliente**: CPF, nome, telefone, data nascimento, endereço
- **Empresa**: CNPJ, nome fantasia, razão social, endereço
- **Produto**: nome, descrição, valor, estoque, categoria, fabricante
- **Categoria**: nome e subcategoria
- **Pedido**: cliente, vendedor, empresa, itens, desconto, status, forma pagamento
- **ItemPedido**: descrição, quantidade, valor unitário
- **Vendedor**: nome, CPF, email
- **Supervisor**: nome, CPF, limite de aprovação
- **FormaPagamento**: enum (Dinheiro, Cartão, PIX, Cheque)
- **PedidoStatus**: enum (Aberto, Em Aprovação, Aprovado, Finalizado, etc.)

### Pacote `repositorio`
Armazenamento usando **ArrayList/LinkedList**:
- **ClienteRepositorio**: gerencia lista de clientes
- **ProdutoRepositorio**: gerencia lista de produtos
- **PedidoRepositorio**: gerencia lista de pedidos

### Pacote `servico`
Lógica de negócio usando **Stack** e **Queue**:

#### **HistoricoOperacoes** (Stack)
- Implementa desfazer/refazer usando duas pilhas
- Registra todas as operações realizadas
- Permite voltar e avançar no histórico

#### **ProcessadorPedidos** (Queue)
- Fila de processamento de pedidos (FIFO)
- Fila de aprovação para supervisor
- Gerencia fluxo sequencial de atendimento

### Pacote `fila`
- **FilaSimples**: wrapper sobre `ArrayDeque` com capacidade limitada
- **AppFila**: demonstração do uso de fila (FIFO) para atendimento

### Pacote `pilha`
- **PilhaEncadeada**: wrapper sobre `ArrayDeque` 
- **AppPilha**: demonstração do uso de pilha (LIFO) para atendimento

## 🎯 Funcionalidades Implementadas

### 1. Cadastro de Entidades (ArrayList/LinkedList)
- Clientes com endereço normalizado
- Produtos vinculados a categoria e fabricante
- Empresas com dados completos (CNPJ, endereço)
- Vendedores e supervisores

### 2. Processamento de Pedidos (Queue)
- Fila FIFO para atendimento sequencial
- Validação de limite para aprovação automática
- Fila separada para pedidos aguardando supervisor
- Baixa de estoque automática

### 3. Histórico de Operações (Stack)
- Registro de todas as operações (criar, aprovar, finalizar, reprovar)
- Desfazer operações (pop da pilha principal)
- Refazer operações (pop da pilha de refazer)
- Timestamp de cada operação

### 4. Aprovação de Pedidos
- Pedidos acima do limite vão para aprovação
- Supervisor com limite de aprovação configurável
- Status detalhado do pedido (aberto, em aprovação, aprovado, finalizado)

### 5. Formas de Pagamento
- Múltiplas opções: Dinheiro, Cartão, PIX, Cheque
- Vinculada a cada pedido

## 🚀 Como Executar

### Compilar o projeto
```bash
mvn clean compile
```

### Executar demonstração com Fila (FIFO)
```bash
mvn exec:java -Dexec.mainClass="br.com.pucgo.fila.AppFila"
```

### Executar demonstração com Pilha (LIFO)
```bash
mvn exec:java -Dexec.mainClass="br.com.pucgo.pilha.AppPilha"
```

### Executar sistema completo
```bash
mvn exec:java -Dexec.mainClass="br.com.pucgo.SistemaPDV"
```

## 📊 Exemplo de Fluxo

### Sistema Completo (SistemaPDV.java)
1. **Cadastro**: 3 clientes, 4 produtos, 1 empresa, 2 vendedores, 1 supervisor
2. **Criação de Pedidos**: 3 pedidos enfileirados para processamento
3. **Processamento (Queue)**: 
   - Pedidos até R$ 5.000 → finalização automática
   - Pedidos acima → fila de aprovação
4. **Aprovação**: Supervisor processa fila de aprovação
5. **Histórico (Stack)**: Desfazer/refazer últimas operações

### AppFila (Atendimento FIFO)
- Clientes aguardam em fila
- Atendimento por ordem de chegada
- Pedidos processados sequencialmente

### AppPilha (Atendimento LIFO)
- Clientes empilhados
- Último a chegar é atendido primeiro
- Útil para priorização de atendimento urgente

## 🔧 Tecnologias

- **Java 17+**
- **Maven 3.x**
- **Collections Framework**: ArrayList, LinkedList, Stack, Queue, ArrayDeque

## 📝 Requisitos Atendidos

✅ Cadastro de cliente com endereço normalizado  
✅ Cadastro de produtos com categoria, subcategoria e fabricante  
✅ Registro de pedidos com vendedor e empresa  
✅ Múltiplas formas de pagamento  
✅ Cadastro de empresa (CNPJ, razão social, etc.)  
✅ Aprovação de pedidos por supervisor  
✅ **Uso de ArrayList/LinkedList** para repositórios  
✅ **Uso de Stack** para histórico (desfazer/refazer)  
✅ **Uso de Queue** para processamento sequencial  

## 👥 Autor

Sistema desenvolvido para disciplina de Algoritmos e Estruturas de Dados.

