package br.com.pucgo;

//Sistema feito com PILHA
public class Pediatria {
    private Paciente[] pacientes;
    private int topo;
    private int capacidade;

    public Pediatria(int capacidade) {
        this.capacidade = capacidade;
        this.pacientes = new Paciente[capacidade];
        this.topo = -1; // Pilha vazia inicialmente
    }

    public boolean push(Paciente paciente) {
        if (isFull()) {
            System.out.println("ERRO: Pilha cheia! Não é possível adicionar o " + paciente);
            return false;
        }

        topo++;
        pacientes[topo] = paciente;
        System.out.println("paciente adicionado: " + paciente);
        return true;
    }

    public Paciente pop() {
        if (isEmpty()) {
            System.out.println("ERRO: Pilha vazia! Não há pacientes para retirar.");
            return null;
        }

        Paciente pacienteRetirado = pacientes[topo];
        pacientes[topo] = null; // Limpa a referência
        topo--;
        System.out.println("paciente retirado: " + pacienteRetirado);
        return pacienteRetirado;
    }


    public Paciente peek() {
        if (isEmpty()) {
            System.out.println("Pilha vazia - não há paciente no topo");
            return null;
        }
        return pacientes[topo];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == capacidade - 1;
    }

    public int size() {
        return topo + 1;
    }

    public void exibirEstado() {
        System.out.println("\n=== ESTADO DA PILHA ===");
        System.out.println("Capacidade: " + capacidade);
        System.out.println("pacientes na pilha: " + size());

        if (isEmpty()) {
            System.out.println("Pilha vazia!");
        } else {
            System.out.println("pacientes (do topo para a base):");
            for (int i = topo; i >= 0; i--) {
                System.out.println("  " + (i + 1) + "º: " + pacientes[i]);
            }
            System.out.println("Próximo paciente (topo): " + peek());
        }
        System.out.println("========================\n");
    }
}
