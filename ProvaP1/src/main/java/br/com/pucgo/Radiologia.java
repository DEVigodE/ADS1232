package br.com.pucgo;

//Sistema feito com LISTA
public class Radiologia {
    private Paciente[] pacientes;
    private int tamanho;
    private int capacidade;

    public Radiologia(int capacidade) {
        this.capacidade = capacidade;
        this.pacientes = new Paciente[capacidade];
        this.tamanho = 0;
    }

    public boolean add(Paciente paciente) {
        if (isFull()) {
            System.out.println("ERRO: Lista cheia! Não é possível adicionar o " + paciente);
            return false;
        }

        pacientes[tamanho] = paciente;
        tamanho++;
        System.out.println("Paciente adicionado na radiologia: " + paciente);
        return true;
    }

    public boolean insert(int indice, Paciente paciente) {
        if (isFull()) {
            System.out.println("ERRO: Lista cheia! Não é possível inserir o " + paciente);
            return false;
        }

        if (indice < 0 || indice > tamanho) {
            System.out.println("ERRO: Índice inválido! Deve estar entre 0 e " + tamanho);
            return false;
        }

        // Desloca elementos para a direita
        for (int i = tamanho; i > indice; i--) {
            pacientes[i] = pacientes[i - 1];
        }

        pacientes[indice] = paciente;
        tamanho++;
        System.out.println("Paciente inserido na posição " + indice + ": " + paciente);
        return true;
    }

    public Paciente remove(int indice) {
        if (isEmpty()) {
            System.out.println("ERRO: Lista vazia! Não há pacientes para remover.");
            return null;
        }

        if (indice < 0 || indice >= tamanho) {
            System.out.println("ERRO: Índice inválido! Deve estar entre 0 e " + (tamanho - 1));
            return null;
        }

        Paciente pacienteRemovido = pacientes[indice];

        // Desloca elementos para a esquerda
        for (int i = indice; i < tamanho - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }

        pacientes[tamanho - 1] = null; // Limpa a última posição
        tamanho--;
        System.out.println("Paciente removido da posição " + indice + ": " + pacienteRemovido);
        return pacienteRemovido;
    }

    public Paciente removeFirst() {
        if (isEmpty()) {
            System.out.println("ERRO: Lista vazia! Não há pacientes para remover.");
            return null;
        }
        return remove(0);
    }

    public Paciente removeLast() {
        if (isEmpty()) {
            System.out.println("ERRO: Lista vazia! Não há pacientes para remover.");
            return null;
        }
        return remove(tamanho - 1);
    }

    public Paciente get(int indice) {
        if (indice < 0 || indice >= tamanho) {
            System.out.println("ERRO: Índice inválido! Deve estar entre 0 e " + (tamanho - 1));
            return null;
        }
        return pacientes[indice];
    }

    public int indexOf(Paciente paciente) {
        for (int i = 0; i < tamanho; i++) {
            if (pacientes[i] != null && pacientes[i].getCpf().equals(paciente.getCpf())) {
                return i;
            }
        }
        return -1; // Não encontrado
    }

    public boolean contains(Paciente paciente) {
        return indexOf(paciente) != -1;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == capacidade;
    }

    public int size() {
        return tamanho;
    }

    public void clear() {
        for (int i = 0; i < tamanho; i++) {
            pacientes[i] = null;
        }
        tamanho = 0;
        System.out.println("Lista de radiologia limpa!");
    }

    public void exibirEstado() {
        System.out.println("\n=== ESTADO DA LISTA DE RADIOLOGIA ===");
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Pacientes na lista: " + size());

        if (isEmpty()) {
            System.out.println("Lista vazia!");
        } else {
            System.out.println("Pacientes na radiologia:");
            for (int i = 0; i < tamanho; i++) {
                System.out.println("  " + (i + 1) + "º: " + pacientes[i]);
            }
        }
        System.out.println("======================================\n");
    }
}
