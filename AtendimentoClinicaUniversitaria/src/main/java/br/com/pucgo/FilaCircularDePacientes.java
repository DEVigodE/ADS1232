package br.com.pucgo;

/**
 * Fila circular de pacientes usando array fixo.
 */
public class FilaCircularDePacientes {
    private Paciente[] fila;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircularDePacientes(int capacidade) {
        this.capacidade = capacidade;
        this.fila = new Paciente[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean enqueue(Paciente paciente) {
        if (tamanho == capacidade) return false; // fila cheia
        fila[fim] = paciente;
        fim = (fim + 1) % capacidade;
        tamanho++;
        return true;
    }

    public Paciente dequeue() {
        if (tamanho == 0) return null; // fila vazia
        Paciente paciente = fila[inicio];
        fila[inicio] = null;
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return paciente;
    }

    public Paciente peek() {
        if (tamanho == 0) return null;
        return fila[inicio];
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
}