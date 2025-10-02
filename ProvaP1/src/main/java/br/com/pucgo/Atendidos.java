package br.com.pucgo;

//Sistema feito com FILA
public class Atendidos {
    private Paciente[] pacientes;
    private int frente;
    private int tras;
    private int capacidade;
    private int tamanho;

    public Atendidos(int capacidade) {
        this.capacidade = capacidade;
        this.pacientes = new Paciente[capacidade];
        this.frente = 0;
        this.tras = -1;
        this.tamanho = 0;
    }

    public boolean enqueue(Paciente paciente) {
        if (isFull()) {
            System.out.println("ERRO: Fila cheia! Não é possível adicionar o " + paciente);
            return false;
        }

        tras = (tras + 1) % capacidade; // Fila circular
        pacientes[tras] = paciente;
        tamanho++;
        System.out.println("Paciente atendido adicionado: " + paciente);
        return true;
    }

    public Paciente dequeue() {
        if (isEmpty()) {
            System.out.println("ERRO: Fila vazia! Não há pacientes atendidos para retirar.");
            return null;
        }

        Paciente pacienteRetirado = pacientes[frente];
        pacientes[frente] = null; // Limpa a referência
        frente = (frente + 1) % capacidade; // Fila circular
        tamanho--;
        System.out.println("Paciente atendido retirado: " + pacienteRetirado);
        return pacienteRetirado;
    }

    public Paciente front() {
        if (isEmpty()) {
            System.out.println("Fila vazia - não há paciente na frente");
            return null;
        }
        return pacientes[frente];
    }

    public Paciente rear() {
        if (isEmpty()) {
            System.out.println("Fila vazia - não há paciente no final");
            return null;
        }
        return pacientes[tras];
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

    public void exibirEstado() {
        System.out.println("\n=== ESTADO DA FILA DE ATENDIDOS ===");
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Pacientes atendidos na fila: " + size());

        if (isEmpty()) {
            System.out.println("Fila vazia!");
        } else {
            System.out.println("Pacientes atendidos (da frente para trás):");
            int contador = 1;
            int indice = frente;
            for (int i = 0; i < tamanho; i++) {
                System.out.println("  " + contador + "º: " + pacientes[indice]);
                indice = (indice + 1) % capacidade;
                contador++;
            }
            System.out.println("Primeiro da fila: " + front());
            System.out.println("Último da fila: " + rear());
        }
        System.out.println("=====================================\n");
    }
}
