package br.com.pucgo;

import java.util.*;

/**
 * Implementação de um Cache Simples usando LinkedHashMap
 * Demonstra o uso de HashMap para acesso rápido com controle de ordem
 *
 * LinkedHashMap mantém ordem de inserção ou acesso
 * Complexidade: O(1) para get, put, remove
 */
public class CacheSimples<K, V> {

    private final int capacidade;
    private final LinkedHashMap<K, V> cache;
    private int hits = 0;
    private int misses = 0;

    /**
     * Cria um cache com capacidade limitada usando LRU (Least Recently Used)
     */
    public CacheSimples(int capacidade) {
        this.capacidade = capacidade;

        // LinkedHashMap com acesso ordenado e remoção automática do mais antigo
        this.cache = new LinkedHashMap<K, V>(capacidade, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > CacheSimples.this.capacidade;
            }
        };
    }

    /**
     * Adiciona ou atualiza um item no cache
     */
    public void put(K chave, V valor) {
        cache.put(chave, valor);
    }

    /**
     * Busca um item no cache
     */
    public V get(K chave) {
        V valor = cache.get(chave);

        if (valor != null) {
            hits++;
        } else {
            misses++;
        }

        return valor;
    }

    /**
     * Verifica se uma chave está no cache
     */
    public boolean contemChave(K chave) {
        return cache.containsKey(chave);
    }

    /**
     * Remove um item do cache
     */
    public V remove(K chave) {
        return cache.remove(chave);
    }

    /**
     * Limpa todo o cache
     */
    public void limpar() {
        cache.clear();
        hits = 0;
        misses = 0;
    }

    /**
     * Retorna o tamanho atual do cache
     */
    public int tamanho() {
        return cache.size();
    }

    /**
     * Calcula a taxa de acertos (hit rate)
     */
    public double taxaAcertos() {
        int total = hits + misses;
        return total == 0 ? 0.0 : (double) hits / total * 100;
    }

    /**
     * Imprime estatísticas do cache
     */
    public void imprimirEstatisticas() {
        System.out.println("\n=== Estatísticas do Cache ===");
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Itens no cache: " + cache.size());
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Taxa de acertos: " + String.format("%.2f%%", taxaAcertos()));
    }

    /**
     * Mostra o conteúdo do cache na ordem de acesso
     */
    public void mostrarConteudo() {
        System.out.println("\n=== Conteúdo do Cache (ordem de acesso) ===");
        int i = 1;
        for (Map.Entry<K, V> entry : cache.entrySet()) {
            System.out.println(i++ + ". " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    /**
     * Exemplo de uso: Cache de consultas a banco de dados
     */
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO: Cache com LinkedHashMap ===\n");

        // Criar cache com capacidade para 5 itens
        CacheSimples<Integer, String> cache = new CacheSimples<>(5);

        // Simulando consultas a banco de dados
        System.out.println("1. Adicionando dados ao cache:");
        for (int i = 1; i <= 5; i++) {
            cache.put(i, "Usuário " + i);
            System.out.println("  Cached: ID " + i);
        }

        cache.mostrarConteudo();

        // Buscar dados (hits)
        System.out.println("\n2. Buscando dados (devem estar no cache):");
        System.out.println("  ID 1: " + cache.get(1) + " - HIT");
        System.out.println("  ID 3: " + cache.get(3) + " - HIT");
        System.out.println("  ID 5: " + cache.get(5) + " - HIT");

        // Buscar dado não existente (miss)
        System.out.println("\n3. Buscando dado não existente:");
        System.out.println("  ID 10: " + cache.get(10) + " - MISS");

        // Adicionar novo item (vai remover o mais antigo)
        System.out.println("\n4. Adicionando novo item (capacidade: 5):");
        cache.put(6, "Usuário 6");
        System.out.println("  Novo item adicionado. Item mais antigo foi removido.");

        cache.mostrarConteudo();

        // Verificar se ID 2 ainda está no cache (foi removido pois era o LRU)
        System.out.println("\n5. Verificando ID 2 (deve ter sido removido):");
        if (cache.get(2) == null) {
            System.out.println("  ID 2 não está mais no cache (foi o LRU - Least Recently Used)");
        }

        cache.imprimirEstatisticas();

        // Demonstração de performance
        System.out.println("\n=== Teste de Performance ===");
        CacheSimples<Integer, String> cacheTeste = new CacheSimples<>(1000);

        // Simular 10000 acessos com cache
        long inicio = System.nanoTime();
        Random random = new Random(42); // Seed fixo para reprodutibilidade

        // Adicionar itens ao cache
        for (int i = 0; i < 1000; i++) {
            cacheTeste.put(i, "Valor " + i);
        }

        // Simular acessos (80% hits, 20% misses)
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1250); // 0-1249 (80% estarão no cache)
            String valor = cacheTeste.get(id);

            // Simular busca em "banco de dados" se não estiver no cache
            if (valor == null) {
                cacheTeste.put(id, "Valor " + id);
            }
        }
        long fim = System.nanoTime();

        System.out.println("Tempo total: " + (fim - inicio) / 1_000_000.0 + " ms");
        cacheTeste.imprimirEstatisticas();

        System.out.println("\n=== Vantagens do Cache com HashMap ===");
        System.out.println("- Acesso O(1): Extremamente rápido");
        System.out.println("- Reduz carga no banco de dados");
        System.out.println("- Controle de capacidade com LRU");
        System.out.println("- Melhora significativa de performance");
    }
}

