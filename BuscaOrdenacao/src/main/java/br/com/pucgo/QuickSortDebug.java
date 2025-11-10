package br.com.pucgo;
import java.util.*;

/**
 * Demonstração do QuickSort com debug - mostra os passos da ordenação.
 * Execute esta classe para ver o algoritmo funcionando passo a passo.
 */
public class QuickSortDebug {

    private static int nivel = 0; // Para indentação visual da recursão

    public static void main(String[] args) {
        // Lista menor para facilitar visualização
        List<Integer> lista = Arrays.asList(64, 34, 25, 12, 22, 11, 90, 88, 45, 50);

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     DEMONSTRAÇÃO DO QUICKSORT COM DEPURADOR            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.println("Lista Original: " + lista);
        System.out.println("Tamanho: " + lista.size() + " elementos\n");
        System.out.println("Iniciando ordenação...\n");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // Converte para ArrayList para permitir modificações
        List<Integer> listaOrdenavel = new ArrayList<>(lista);
        quickSortDebug(listaOrdenavel);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("\n✓ Ordenação Completa!");
        System.out.println("Lista Final: " + listaOrdenavel + "\n");
    }

    private static void quickSortDebug(List<Integer> lista) {
        quickSortRecursivoDebug(lista, 0, lista.size() - 1);
    }

    // Método auxiliar para criar indentação (compatível com Java 8)
    private static String getIndentacao(int nivel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    private static void quickSortRecursivoDebug(List<Integer> lista, int inicio, int fim) {
        String indentacao = getIndentacao(nivel);

        if (inicio < fim) {
            System.out.println(indentacao + "┌─ Nível " + nivel + ": Processando índices [" + inicio + ".." + fim + "]");
            System.out.println(indentacao + "│  Sublista: " + lista.subList(inicio, fim + 1));
            System.out.println(indentacao + "│  Pivô (último elemento): " + lista.get(fim));

            int indicePivo = particionarDebug(lista, inicio, fim);

            System.out.println(indentacao + "│  Após particionamento: " + lista.subList(inicio, fim + 1));
            System.out.println(indentacao + "│  Pivô está na posição correta: índice " + indicePivo + " = " + lista.get(indicePivo));
            System.out.println(indentacao + "└─ Dividindo em duas sublistas...\n");

            // Ordena recursivamente a esquerda
            if (indicePivo - 1 > inicio) {
                System.out.println(indentacao + "   ↓ Recursão à ESQUERDA do pivô");
                nivel++;
                quickSortRecursivoDebug(lista, inicio, indicePivo - 1);
                nivel--;
            }

            // Ordena recursivamente a direita
            if (indicePivo + 1 < fim) {
                System.out.println(indentacao + "   ↓ Recursão à DIREITA do pivô");
                nivel++;
                quickSortRecursivoDebug(lista, indicePivo + 1, fim);
                nivel--;
            }

        } else {
            System.out.println(indentacao + "⚡ Caso base: sublista com 1 elemento (já ordenada)\n");
        }
    }

    private static int particionarDebug(List<Integer> lista, int inicio, int fim) {
        int pivo = lista.get(fim);
        int i = inicio - 1;
        String indentacao = getIndentacao(nivel);

        System.out.println(indentacao + "│  Particionando:");

        for (int j = inicio; j < fim; j++) {
            if (lista.get(j) <= pivo) {
                i++;
                if (i != j) {
                    System.out.println(indentacao + "│    → Troca: " + lista.get(i) + " ↔ " + lista.get(j));
                    int temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }

        // Coloca o pivô na posição correta
        int posicaoPivo = i + 1;
        if (posicaoPivo != fim) {
            System.out.println(indentacao + "│    → Troca pivô: " + lista.get(posicaoPivo) + " ↔ " + lista.get(fim));
            int temp = lista.get(posicaoPivo);
            lista.set(posicaoPivo, lista.get(fim));
            lista.set(fim, temp);
        }

        return posicaoPivo;
    }
}

