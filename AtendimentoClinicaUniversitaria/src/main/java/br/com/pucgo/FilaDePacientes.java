package br.com.pucgo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa uma fila de pacientes para atendimento
 * Suporta prioridade para pacientes idosos ou casos urgentes
 */
public class FilaDePacientes {
    private List<Paciente> fila;
    private List<Paciente> filaPrioridade;

    public FilaDePacientes() {
        this.fila = new ArrayList<>();
        this.filaPrioridade = new ArrayList<>();
    }

    /**
     * Adiciona um paciente na fila (enqueue)
     * Pacientes com prioridade vão para uma fila separada
     */
    public void adicionarPaciente(Paciente paciente) {
        if (paciente.temPrioridade()) {
            filaPrioridade.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " adicionado na fila PRIORITÁRIA");
        } else {
            fila.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " adicionado na fila normal");
        }
    }

    /**
     * Remove e retorna o próximo paciente da fila (dequeue)
     * Prioridade: primeiro fila prioritária, depois fila normal
     */
    public Paciente chamarProximoPaciente() {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.remove(0);
        } else if (!fila.isEmpty()) {
            return fila.remove(0);
        }
        return null;
    }

    /**
     * Consulta o próximo paciente sem removê-lo (peek)
     */
    public Paciente proximoPaciente() {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.get(0);
        } else if (!fila.isEmpty()) {
            return fila.get(0);
        }
        return null;
    }

    /**
     * Verifica se a fila está vazia
     */
    public boolean isEmpty() {
        return fila.isEmpty() && filaPrioridade.isEmpty();
    }

    /**
     * Retorna o tamanho total da fila
     */
    public int tamanho() {
        return fila.size() + filaPrioridade.size();
    }

    /**
     * Exibe o estado atual da fila
     */
    public void exibirFila() {
        System.out.println("\n=== ESTADO DA FILA ===");
        System.out.println("Fila Prioritária (" + filaPrioridade.size() + " pacientes):");
        for (int i = 0; i < filaPrioridade.size(); i++) {
            System.out.println((i + 1) + ". " + filaPrioridade.get(i).getNome() +
                             " (Idade: " + filaPrioridade.get(i).getIdade() + ")");
        }

        System.out.println("\nFila Normal (" + fila.size() + " pacientes):");
        for (int i = 0; i < fila.size(); i++) {
            System.out.println((i + 1) + ". " + fila.get(i).getNome() +
                             " (Idade: " + fila.get(i).getIdade() + ")");
        }
        System.out.println("========================\n");
    }
}
