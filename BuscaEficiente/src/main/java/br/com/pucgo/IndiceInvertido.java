package br.com.pucgo;

import java.util.*;

/**
 * Implementação de um Índice Invertido usando HashMap
 * Útil para sistemas de busca e recuperação de informação
 *
 * Complexidade:
 * - Indexação: O(n * m) onde n = número de documentos, m = palavras por documento
 * - Busca: O(1) para encontrar documentos que contêm uma palavra
 */
public class IndiceInvertido {

    // Índice: palavra -> lista de IDs de documentos
    private HashMap<String, Set<Integer>> indice;

    // Armazena os documentos
    private HashMap<Integer, String> documentos;

    public IndiceInvertido() {
        this.indice = new HashMap<>();
        this.documentos = new HashMap<>();
    }

    /**
     * Adiciona um documento ao índice
     * @param docId ID do documento
     * @param conteudo Conteúdo do documento
     */
    public void adicionarDocumento(int docId, String conteudo) {
        documentos.put(docId, conteudo);

        // Processar o conteúdo
        String[] palavras = conteudo.toLowerCase()
                .replaceAll("[^a-záàâãéèêíïóôõöúçñ\\s]", "")
                .split("\\s+");

        // Indexar cada palavra
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                indice.computeIfAbsent(palavra, k -> new HashSet<>()).add(docId);
            }
        }
    }

    /**
     * Busca documentos que contêm uma palavra
     * Complexidade: O(1)
     */
    public Set<Integer> buscar(String palavra) {
        palavra = palavra.toLowerCase();
        return indice.getOrDefault(palavra, Collections.emptySet());
    }

    /**
     * Busca documentos que contêm TODAS as palavras (AND)
     * Complexidade: O(k) onde k = número de palavras
     */
    public Set<Integer> buscarAND(String... palavras) {
        if (palavras.length == 0) {
            return Collections.emptySet();
        }

        Set<Integer> resultado = new HashSet<>(buscar(palavras[0]));

        for (int i = 1; i < palavras.length; i++) {
            resultado.retainAll(buscar(palavras[i]));
        }

        return resultado;
    }

    /**
     * Busca documentos que contêm QUALQUER uma das palavras (OR)
     * Complexidade: O(k) onde k = número de palavras
     */
    public Set<Integer> buscarOR(String... palavras) {
        Set<Integer> resultado = new HashSet<>();

        for (String palavra : palavras) {
            resultado.addAll(buscar(palavra));
        }

        return resultado;
    }

    /**
     * Retorna o conteúdo de um documento
     */
    public String obterDocumento(int docId) {
        return documentos.get(docId);
    }

    /**
     * Retorna estatísticas do índice
     */
    public void imprimirEstatisticas() {
        System.out.println("=== Estatísticas do Índice ===");
        System.out.println("Total de documentos: " + documentos.size());
        System.out.println("Total de palavras únicas: " + indice.size());

        // Encontrar palavra mais frequente
        String palavraMaisFreq = "";
        int maxDocs = 0;

        for (Map.Entry<String, Set<Integer>> entry : indice.entrySet()) {
            if (entry.getValue().size() > maxDocs) {
                maxDocs = entry.getValue().size();
                palavraMaisFreq = entry.getKey();
            }
        }

        System.out.println("Palavra mais frequente: '" + palavraMaisFreq +
                "' (aparece em " + maxDocs + " documentos)");
    }

    /**
     * Exemplo de uso
     */
    public static void main(String[] args) {
        IndiceInvertido indice = new IndiceInvertido();

        // Adicionar documentos
        indice.adicionarDocumento(1, "Java é uma linguagem de programação orientada a objetos");
        indice.adicionarDocumento(2, "Python é uma linguagem de programação simples e poderosa");
        indice.adicionarDocumento(3, "Java e Python são linguagens populares");
        indice.adicionarDocumento(4, "Programação orientada a objetos é um paradigma importante");
        indice.adicionarDocumento(5, "JavaScript é usado para desenvolvimento web");

        System.out.println("=== DEMONSTRAÇÃO: Índice Invertido ===\n");

        // Estatísticas
        indice.imprimirEstatisticas();

        // Busca simples
        System.out.println("\n1. Busca por 'java':");
        Set<Integer> resultados = indice.buscar("java");
        for (int docId : resultados) {
            System.out.println("  Doc " + docId + ": " + indice.obterDocumento(docId));
        }

        // Busca AND
        System.out.println("\n2. Busca AND ('java' E 'objetos'):");
        resultados = indice.buscarAND("java", "objetos");
        for (int docId : resultados) {
            System.out.println("  Doc " + docId + ": " + indice.obterDocumento(docId));
        }

        // Busca OR
        System.out.println("\n3. Busca OR ('java' OU 'python'):");
        resultados = indice.buscarOR("java", "python");
        for (int docId : resultados) {
            System.out.println("  Doc " + docId + ": " + indice.obterDocumento(docId));
        }

        System.out.println("\n=== Vantagens do HashMap para Índice Invertido ===");
        System.out.println("- Busca em O(1): Extremamente rápida para encontrar documentos");
        System.out.println("- Memória eficiente: Armazena apenas palavras únicas");
        System.out.println("- Escalável: Funciona bem com grandes volumes de dados");
    }
}

