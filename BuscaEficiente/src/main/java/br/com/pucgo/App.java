package br.com.pucgo;

import java.util.*;

/**
 * Demonstração de Indexação e Busca Eficiente com HashMap e TreeMap
 *
 * Este programa demonstra:
 * - HashMap: O(1) para inserção e busca (não ordenado)
 * - TreeMap: O(log n) para inserção e busca (ordenado por chave)
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("=== DEMONSTRAÇÃO: HashMap vs TreeMap ===\n");

        // 1. Demonstração com HashMap
        demonstrarHashMap();

        System.out.println("\n==================================================\n");

        // 2. Demonstração com TreeMap
        demonstrarTreeMap();

        System.out.println("\n==================================================\n");

        // 3. Comparação de Performance
        compararPerformance();

        System.out.println("\n==================================================\n");

        // 4. Caso de Uso Prático: Índice de Palavras
        demonstrarIndiceTexto();
    }

    /**
     * Demonstra operações básicas com HashMap
     * Complexidade: O(1) para get, put, remove
     */
    private static void demonstrarHashMap() {
        System.out.println("### HASHMAP - Busca Não Ordenada (O(1)) ###");

        HashMap<String, String> alunos = new HashMap<>();

        // Inserção
        System.out.println("\n1. Inserindo alunos...");
        alunos.put("2023001", "João Silva");
        alunos.put("2023003", "Maria Santos");
        alunos.put("2023002", "Pedro Oliveira");
        alunos.put("2023005", "Ana Costa");
        alunos.put("2023004", "Carlos Souza");

        System.out.println("Alunos inseridos: " + alunos.size());

        // Busca
        System.out.println("\n2. Buscando aluno 2023003:");
        long inicio = System.nanoTime();
        String aluno = alunos.get("2023003");
        long fim = System.nanoTime();
        System.out.println("Encontrado: " + aluno);
        System.out.println("Tempo: " + (fim - inicio) + " ns");

        // Verificação de existência
        System.out.println("\n3. Verificando existência:");
        System.out.println("Contém chave 2023001? " + alunos.containsKey("2023001"));
        System.out.println("Contém valor 'Ana Costa'? " + alunos.containsValue("Ana Costa"));

        // Iteração (ordem não garantida)
        System.out.println("\n4. Iterando (ordem não garantida):");
        for (Map.Entry<String, String> entry : alunos.entrySet()) {
            System.out.println("  Matrícula: " + entry.getKey() + " -> Nome: " + entry.getValue());
        }

        // Remoção
        System.out.println("\n5. Removendo aluno 2023003:");
        alunos.remove("2023003");
        System.out.println("Alunos restantes: " + alunos.size());
    }

    /**
     * Demonstra operações básicas com TreeMap
     * Complexidade: O(log n) para get, put, remove
     * Mantém elementos ordenados pela chave
     */
    private static void demonstrarTreeMap() {
        System.out.println("### TREEMAP - Busca Ordenada (O(log n)) ###");

        TreeMap<String, String> alunos = new TreeMap<>();

        // Inserção (mesmos dados do HashMap)
        System.out.println("\n1. Inserindo alunos...");
        alunos.put("2023001", "João Silva");
        alunos.put("2023003", "Maria Santos");
        alunos.put("2023002", "Pedro Oliveira");
        alunos.put("2023005", "Ana Costa");
        alunos.put("2023004", "Carlos Souza");

        System.out.println("Alunos inseridos: " + alunos.size());

        // Busca
        System.out.println("\n2. Buscando aluno 2023003:");
        long inicio = System.nanoTime();
        String aluno = alunos.get("2023003");
        long fim = System.nanoTime();
        System.out.println("Encontrado: " + aluno);
        System.out.println("Tempo: " + (fim - inicio) + " ns");

        // Iteração (ordem garantida - ordenado por chave)
        System.out.println("\n3. Iterando (ordem garantida por chave):");
        for (Map.Entry<String, String> entry : alunos.entrySet()) {
            System.out.println("  Matrícula: " + entry.getKey() + " -> Nome: " + entry.getValue());
        }

        // Operações especiais do TreeMap
        System.out.println("\n4. Operações especiais do TreeMap:");
        System.out.println("  Primeira chave: " + alunos.firstKey());
        System.out.println("  Última chave: " + alunos.lastKey());
        System.out.println("  Menor chave >= 2023003: " + alunos.ceilingKey("2023003"));
        System.out.println("  Maior chave <= 2023003: " + alunos.floorKey("2023003"));

        // Sub-mapa
        System.out.println("\n5. Sub-mapa (2023002 a 2023004):");
        SortedMap<String, String> subMapa = alunos.subMap("2023002", "2023005");
        for (Map.Entry<String, String> entry : subMapa.entrySet()) {
            System.out.println("  Matrícula: " + entry.getKey() + " -> Nome: " + entry.getValue());
        }
    }

    /**
     * Compara a performance entre HashMap e TreeMap
     */
    private static void compararPerformance() {
        System.out.println("### COMPARAÇÃO DE PERFORMANCE ###");

        int tamanho = 10000;

        // Teste com HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        long inicio = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            hashMap.put(i, "Valor " + i);
        }
        long fim = System.nanoTime();
        long tempoInsercaoHash = fim - inicio;

        inicio = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            hashMap.get(i);
        }
        fim = System.nanoTime();
        long tempoBuscaHash = fim - inicio;

        // Teste com TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        inicio = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            treeMap.put(i, "Valor " + i);
        }
        fim = System.nanoTime();
        long tempoInsercaoTree = fim - inicio;

        inicio = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            treeMap.get(i);
        }
        fim = System.nanoTime();
        long tempoBuscaTree = fim - inicio;

        // Resultados
        System.out.println("\nTestes com " + tamanho + " elementos:");
        System.out.println("\nHashMap:");
        System.out.println("  Tempo de inserção: " + tempoInsercaoHash / 1_000_000.0 + " ms");
        System.out.println("  Tempo de busca: " + tempoBuscaHash / 1_000_000.0 + " ms");

        System.out.println("\nTreeMap:");
        System.out.println("  Tempo de inserção: " + tempoInsercaoTree / 1_000_000.0 + " ms");
        System.out.println("  Tempo de busca: " + tempoBuscaTree / 1_000_000.0 + " ms");

        System.out.println("\nAnálise:");
        System.out.println("  HashMap é " + String.format("%.2f", (double)tempoInsercaoTree / tempoInsercaoHash) +
                          "x mais rápido na inserção");
        System.out.println("  HashMap é " + String.format("%.2f", (double)tempoBuscaTree / tempoBuscaHash) +
                          "x mais rápido na busca");
    }

    /**
     * Demonstra um caso de uso prático: índice de palavras em um texto
     */
    private static void demonstrarIndiceTexto() {
        System.out.println("### CASO PRÁTICO: Índice de Palavras ###");

        String texto = "Java é uma linguagem de programação. " +
                      "Java é orientada a objetos. " +
                      "Programação em Java é muito utilizada.";

        // Usando HashMap para contar frequência
        HashMap<String, Integer> frequenciaHash = new HashMap<>();
        String[] palavras = texto.toLowerCase()
                                 .replaceAll("[.,;]", "")
                                 .split("\\s+");

        System.out.println("\n1. Contando frequência com HashMap:");
        for (String palavra : palavras) {
            frequenciaHash.put(palavra, frequenciaHash.getOrDefault(palavra, 0) + 1);
        }

        System.out.println("Palavras encontradas (ordem não garantida):");
        for (Map.Entry<String, Integer> entry : frequenciaHash.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " vez(es)");
        }

        // Usando TreeMap para ordenar por palavra
        TreeMap<String, Integer> frequenciaTree = new TreeMap<>(frequenciaHash);

        System.out.println("\n2. Usando TreeMap para ordem alfabética:");
        for (Map.Entry<String, Integer> entry : frequenciaTree.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " vez(es)");
        }

        // TreeMap com comparador customizado (ordem por frequência)
        System.out.println("\n3. TreeMap com comparador customizado (por frequência):");
        TreeMap<String, Integer> ordenadoPorFreq = new TreeMap<>(
            new Comparator<String>() {
                public int compare(String a, String b) {
                    int comp = frequenciaHash.get(b).compareTo(frequenciaHash.get(a));
                    return comp != 0 ? comp : a.compareTo(b);
                }
            }
        );
        ordenadoPorFreq.putAll(frequenciaHash);

        System.out.println("Palavras mais frequentes:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : ordenadoPorFreq.entrySet()) {
            if (count++ < 5) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " vez(es)");
            }
        }

        System.out.println("\n### RESUMO ###");
        System.out.println("- Use HashMap quando: precisa de máxima performance e não precisa de ordem");
        System.out.println("- Use TreeMap quando: precisa manter elementos ordenados ou fazer buscas por intervalo");
    }
}
