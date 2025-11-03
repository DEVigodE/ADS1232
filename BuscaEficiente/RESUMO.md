=== DEMONSTRAÇÃO: HashMap vs TreeMap ===

### HASHMAP - Busca Não Ordenada (O(1)) ###

1. Inserindo alunos...
   Alunos inseridos: 5

2. Buscando aluno 2023003:
   Encontrado: Maria Santos
   Tempo: 7600 ns

3. Verificando existência:
   Contém chave 2023001? true
   Contém valor 'Ana Costa'? true

4. Iterando (ordem não garantida):
   Matrícula: 2023001 -> Nome: João Silva
   Matrícula: 2023002 -> Nome: Pedro Oliveira
   Matrícula: 2023003 -> Nome: Maria Santos
   Matrícula: 2023004 -> Nome: Carlos Souza
   Matrícula: 2023005 -> Nome: Ana Costa

5. Removendo aluno 2023003:
   Alunos restantes: 4

==================================================

### TREEMAP - Busca Ordenada (O(log n)) ###

1. Inserindo alunos...
   Alunos inseridos: 5

2. Buscando aluno 2023003:
   Encontrado: Maria Santos
   Tempo: 41700 ns

3. Iterando (ordem garantida por chave):
   Matrícula: 2023001 -> Nome: João Silva
   Matrícula: 2023002 -> Nome: Pedro Oliveira
   Matrícula: 2023003 -> Nome: Maria Santos
   Matrícula: 2023004 -> Nome: Carlos Souza
   Matrícula: 2023005 -> Nome: Ana Costa

4. Operações especiais do TreeMap:
   Primeira chave: 2023001
   Última chave: 2023005
   Menor chave >= 2023003: 2023003
   Maior chave <= 2023003: 2023003

5. Sub-mapa (2023002 a 2023004):
   Matrícula: 2023002 -> Nome: Pedro Oliveira
   Matrícula: 2023003 -> Nome: Maria Santos
   Matrícula: 2023004 -> Nome: Carlos Souza

==================================================

### COMPARAÇÃO DE PERFORMANCE ###

Testes com 10000 elementos:

HashMap:
Tempo de inserção: 6.3455 ms
Tempo de busca: 1.4319 ms

TreeMap:
Tempo de inserção: 17.9591 ms
Tempo de busca: 4.5914 ms

Análise:
HashMap é 2,83x mais rápido na inserção
HashMap é 3,21x mais rápido na busca

==================================================

### CASO PRÁTICO: Índice de Palavras ###

1. Contando frequência com HashMap:
   Palavras encontradas (ordem não garantida):
   de: 1 vez(es)
   a: 1 vez(es)
   utilizada: 1 vez(es)
   java: 3 vez(es)
   linguagem: 1 vez(es)
   programação: 2 vez(es)
   orientada: 1 vez(es)
   uma: 1 vez(es)
   em: 1 vez(es)
   é: 3 vez(es)
   muito: 1 vez(es)
   objetos: 1 vez(es)

2. Usando TreeMap para ordem alfabética:
   a: 1 vez(es)
   de: 1 vez(es)
   em: 1 vez(es)
   java: 3 vez(es)
   linguagem: 1 vez(es)
   muito: 1 vez(es)
   objetos: 1 vez(es)
   orientada: 1 vez(es)
   programação: 2 vez(es)
   uma: 1 vez(es)
   utilizada: 1 vez(es)
   é: 3 vez(es)

3. TreeMap com comparador customizado (por frequência):
   Palavras mais frequentes:
   java: 3 vez(es)
   é: 3 vez(es)
   programação: 2 vez(es)
   a: 1 vez(es)
   de: 1 vez(es)

### RESUMO ###
- Use HashMap quando: precisa de máxima performance e não precisa de ordem
- Use TreeMap quando: precisa manter elementos ordenados ou fazer buscas por intervalo
  Disconnected from the target VM, address: '127.0.0.1:62420', transport: 'socket'