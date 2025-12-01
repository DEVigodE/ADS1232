package br.com.pucgo;

/**
 * Classe que implementa uma pilha de pratos usando array
 * Simula o sistema da cantina onde pratos são empilhados e retirados do topo
 */
public class PilhaDePratos {
    private String[] pratos;
    private int topo;
    private int capacidade;

    /**
     * Construtor da pilha de pratos
     * @param capacidade Número máximo de pratos que a pilha pode conter
     */
    public PilhaDePratos(int capacidade) {
        this.capacidade = capacidade;
        this.pratos = new String[capacidade];
        this.topo = -1; // Pilha vazia inicialmente
    }

    /**
     * Adiciona um prato no topo da pilha (push)
     * @param prato Identificação do prato a ser adicionado
     * @return true se o prato foi adicionado com sucesso, false se a pilha estiver cheia
     */
    public boolean push(String prato) {
        if (isFull()) {
            System.out.println("ERRO: Pilha cheia! Não é possível adicionar o " + prato);
            return false;
        }

        topo++;
        pratos[topo] = prato;
        System.out.println("Prato adicionado: " + prato);
        return true;
    }

    /**
     * Remove e retorna o prato do topo da pilha (pop)
     * @return O prato do topo, ou null se a pilha estiver vazia
     */
    public String pop() {
        if (isEmpty()) {
            System.out.println("ERRO: Pilha vazia! Não há pratos para retirar.");
            return null;
        }

        String pratoRetirado = pratos[topo];
        pratos[topo] = null; // Limpa a referência
        topo--;
        System.out.println("Prato retirado: " + pratoRetirado);
        return pratoRetirado;
    }

    /**
     * Consulta o prato do topo sem removê-lo (peek)
     * @return O prato do topo, ou null se a pilha estiver vazia
     */
    public String peek() {
        if (isEmpty()) {
            System.out.println("Pilha vazia - não há prato no topo");
            return null;
        }
        return pratos[topo];
    }

    /**
     * Verifica se a pilha está vazia
     * @return true se vazia, false caso contrário
     */
    public boolean isEmpty() {
        return topo == -1;
    }

    /**
     * Verifica se a pilha está cheia
     * @return true se cheia, false caso contrário
     */
    public boolean isFull() {
        return topo == capacidade - 1;
    }

    /**
     * Retorna o número de pratos na pilha
     * @return Quantidade de pratos
     */
    public int size() {
        return topo + 1;
    }

    /**
     * Exibe o estado atual da pilha
     */
    public void exibirEstado() {
        System.out.println("\n=== ESTADO DA PILHA ===");
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Pratos na pilha: " + size());

        if (isEmpty()) {
            System.out.println("Pilha vazia!");
        } else {
            System.out.println("Pratos (do topo para a base):");
            for (int i = topo; i >= 0; i--) {
                System.out.println("  " + (i + 1) + "º: " + pratos[i]);
            }
            System.out.println("Próximo prato (topo): " + peek());
        }
        System.out.println("========================\n");
    }
}
