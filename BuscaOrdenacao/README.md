# 📊 Análise de Algoritmos de Busca e Ordenação

## 📁 Arquivos do Projeto

- **App.java** - Programa principal com comparação de desempenho
- **QuickSortDebug.java** - Versão com depurador para visualizar o algoritmo
- **RELATORIO_ANALISE_ALGORITMOS.md** - Relatório completo da análise

---

## 🚀 Como Executar

### Executar Comparação de Desempenho:
```bash
cd "src/main/java"
javac br/com/pucgo/App.java
java br.com.pucgo.App
```

### Executar Depurador (Visualização Passo a Passo):
```bash
cd "src/main/java"
javac br/com/pucgo/QuickSortDebug.java
java br.com.pucgo.QuickSortDebug
```

---

## 📈 Resultados Obtidos

### Comparação de Desempenho (10.000 elementos)

| Algoritmo | Tempo | Velocidade Relativa |
|-----------|-------|---------------------|
| BubbleSort | 563,126 ms | 1x (referência) |
| QuickSort | 6,399 ms | **88x mais rápido** |
| Collections.sort | 10,318 ms | **54x mais rápido** |

### Gráfico Visual:
```
BubbleSort     ████████████████████████████████████████████████████████ 563.1 ms
QuickSort      ██ 6.4 ms
Collections    ███ 10.3 ms
```

---

## 🎯 Principais Conclusões

### 1️⃣ Algoritmo Mais Rápido
**QuickSort** - 6,399 ms (88x mais rápido que BubbleSort)

### 2️⃣ Menor Complexidade
**QuickSort e TimSort** - O(n log n) no caso médio

### 3️⃣ Melhor para Grandes Volumes
**Collections.sort (TimSort)** por garantir O(n log n) no pior caso e ser otimizado

---

## 🔍 Detalhes da Implementação

### QuickSort Recursivo

O algoritmo implementado utiliza a estratégia **Dividir e Conquistar**:

1. **Escolhe um pivô** (último elemento da sublista)
2. **Particiona** a lista em elementos menores e maiores que o pivô
3. **Recursivamente** ordena as duas partições
4. **Combina** as partições ordenadas

#### Complexidade:
- **Temporal**: O(n log n) médio, O(n²) pior caso
- **Espacial**: O(log n) pela pilha de recursão

#### Código Principal:
```java
private static void quickSortRecursivo(List<Integer> lista, int inicio, int fim) {
    if (inicio < fim) {
        int indicePivo = particionar(lista, inicio, fim);
        quickSortRecursivo(lista, inicio, indicePivo - 1); // Esquerda
        quickSortRecursivo(lista, indicePivo + 1, fim);    // Direita
    }
}
```

---

## 📸 Screenshots para o Relatório

### 1. Print do Depurador
Execute `QuickSortDebug.java` e capture a saída mostrando:
- Lista original
- Passos da partição
- Níveis de recursão
- Lista final ordenada

### 2. Tabela de Comparação
Execute `App.java` e capture a tabela de desempenho.

### 3. Código no Debugger (IDE)
- Coloque um breakpoint no método `particionar()`
- Execute em modo debug
- Capture a tela mostrando as variáveis locais

---

## 📝 Checklist de Entrega

- [x] Implementação do QuickSort recursivo
- [x] Comparação de desempenho com BubbleSort
- [x] Comparação de desempenho com Collections.sort
- [x] Print do depurador mostrando a lista sendo ordenada
- [x] Tabela de comparação de desempenho
- [x] Conclusão sobre qual é mais rápido
- [x] Conclusão sobre menor complexidade
- [x] Conclusão sobre adequação para grandes volumes

---

## 🎓 Conceitos Demonstrados

### Dividir e Conquistar
O QuickSort divide o problema em subproblemas menores, resolve cada um recursivamente e combina os resultados.

### Análise de Complexidade
- **BubbleSort**: O(n²) - Dois loops aninhados
- **QuickSort**: O(n log n) - Divisão logarítmica com trabalho linear
- **TimSort**: O(n log n) - Híbrido otimizado (Merge + Insertion)

### Trade-offs
- **Tempo vs Espaço**: QuickSort usa menos memória mas pode ser mais lento em casos específicos
- **Pior caso vs Caso médio**: QuickSort tem pior caso ruim mas caso médio excelente
- **Simplicidade vs Performance**: BubbleSort é simples mas ineficiente

---

## 📚 Referências

- **Introduction to Algorithms** (CLRS) - Capítulo 7 (QuickSort)
- **Java Collections Framework** - TimSort implementation
- **Análise de Algoritmos** - Complexidade Assintótica

---

## 👨‍💻 Autor

Desenvolvido para a disciplina de Estrutura de Dados Orientada a Objetos  
PUC Goiás - 2025

---

## 🔗 Links Úteis

- [Visualização do QuickSort](https://visualgo.net/en/sorting)
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- [Java Documentation - Collections.sort](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#sort-java.util.List-)

