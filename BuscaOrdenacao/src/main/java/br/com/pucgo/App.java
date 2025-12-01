package br.com.pucgo;
import java.util.*;
/**
 * Exemplo prático de comparação entre algoritmos de busca e ordenação.
 * Desenvolvido para fins educacionais na disciplina de
 * Estrutura de Dados Orientada a Objetos.
 */
public class App {
    public static void main(String[] args) {
        // Gera uma lista de números aleatórios
        List<Integer> numeros = gerarListaAleatoria(10000);
        List<Integer> copiaBubble = new ArrayList<>(numeros);
        List<Integer> copiaQuick = new ArrayList<>(numeros);
        List<Integer> copiaCollections = new ArrayList<>(numeros);

        // Exibe os 10 primeiros números
        System.out.println("========================================");
        System.out.println("COMPARAÇÃO DE ALGORITMOS DE ORDENAÇÃO");
        System.out.println("========================================");
        System.out.println("Tamanho da lista: " + numeros.size());
        System.out.println("Exemplo de lista gerada: " + numeros.subList(0, 10) + "...\n");

        // Medição de desempenho: ordenação com BubbleSort
        System.out.println("--- BUBBLE SORT ---");
        long inicio = System.nanoTime();
        bubbleSort(copiaBubble);
        long fim = System.nanoTime();
        double tempoBubble = (fim - inicio) / 1_000_000.0;
        System.out.println("Tempo BubbleSort: " + tempoBubble + " ms");
        System.out.println("Lista ordenada (primeiros 10): " + copiaBubble.subList(0, 10) + "...\n");

        // Medição de desempenho: ordenação com QuickSort
        System.out.println("--- QUICK SORT ---");
        inicio = System.nanoTime();
        quickSort(copiaQuick);
        fim = System.nanoTime();
        double tempoQuick = (fim - inicio) / 1_000_000.0;
        System.out.println("Tempo QuickSort: " + tempoQuick + " ms");
        System.out.println("Lista ordenada (primeiros 10): " + copiaQuick.subList(0, 10) + "...\n");

        // Ordenação com Collections.sort (baseada em TimSort)
        System.out.println("--- COLLECTIONS.SORT (TimSort) ---");
        inicio = System.nanoTime();
        Collections.sort(copiaCollections);
        fim = System.nanoTime();
        double tempoCollections = (fim - inicio) / 1_000_000.0;
        System.out.println("Tempo Collections.sort: " + tempoCollections + " ms");
        System.out.println("Lista ordenada (primeiros 10): " + copiaCollections.subList(0, 10) + "...\n");

        // Tabela de comparação
        System.out.println("========================================");
        System.out.println("TABELA DE COMPARAÇÃO DE DESEMPENHO");
        System.out.println("========================================");
        System.out.printf("%-20s | %-15s | %-15s%n", "Algoritmo", "Tempo (ms)", "Complexidade");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-20s | %-15.3f | %-15s%n", "BubbleSort", tempoBubble, "O(n²)");
        System.out.printf("%-20s | %-15.3f | %-15s%n", "QuickSort", tempoQuick, "O(n log n)");
        System.out.printf("%-20s | %-15.3f | %-15s%n", "Collections.sort", tempoCollections, "O(n log n)");
        System.out.println("========================================\n");

        // Busca linear vs binária
        int alvo = copiaCollections.get(new Random().nextInt(copiaCollections.size()));
        System.out.println("--- BUSCA ---");
        System.out.println("Número a ser buscado: " + alvo);
        inicio = System.nanoTime();
        boolean achouLinear = buscaLinear(copiaCollections, alvo);
        fim = System.nanoTime();
        System.out.println("Busca Linear -> Encontrado: " + achouLinear + " | Tempo: " + (fim - inicio) + " ns");
        inicio = System.nanoTime();
        boolean achouBinaria = Collections.binarySearch(copiaCollections, alvo) >= 0;
        fim = System.nanoTime();
        System.out.println("Busca Binária -> Encontrado: " + achouBinaria + " | Tempo: " + (fim - inicio) + " ns");
    }
    // ---------- Métodos Auxiliares ----------
    private static List<Integer> gerarListaAleatoria(int tamanho) {
        Random r = new Random();
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            lista.add(r.nextInt(100_000)); // números até 100 mil
        }
        return lista;
    }
    // Implementação simples de BubbleSort
    private static void bubbleSort(List<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    // Troca os elementos de posição
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    // Implementação do QuickSort recursivo
    private static void quickSort(List<Integer> lista) {
        quickSortRecursivo(lista, 0, lista.size() - 1);
    }

    private static void quickSortRecursivo(List<Integer> lista, int inicio, int fim) {
        // Caso base: se a sublista tem 1 ou nenhum elemento, já está ordenada
        if (inicio < fim) {
            // Particiona a lista e obtém a posição do pivô
            int indicePivo = particionar(lista, inicio, fim);

            // Ordena recursivamente as duas metades
            quickSortRecursivo(lista, inicio, indicePivo - 1); // sublista à esquerda do pivô
            quickSortRecursivo(lista, indicePivo + 1, fim);    // sublista à direita do pivô
        }
    }

    private static int particionar(List<Integer> lista, int inicio, int fim) {
        // Escolhe o último elemento como pivô
        int pivo = lista.get(fim);
        int i = inicio - 1; // índice do menor elemento

        // Percorre a sublista
        for (int j = inicio; j < fim; j++) {
            // Se o elemento atual é menor ou igual ao pivô
            if (lista.get(j) <= pivo) {
                i++;
                // Troca lista[i] com lista[j]
                int temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        // Coloca o pivô na posição correta
        int temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fim));
        lista.set(fim, temp);

        return i + 1; // retorna a posição do pivô
    }

    private static boolean buscaLinear(List<Integer> lista, int alvo) {
        for (int num : lista) {
            if (num == alvo) return true;
        }
        return false;
    }
}