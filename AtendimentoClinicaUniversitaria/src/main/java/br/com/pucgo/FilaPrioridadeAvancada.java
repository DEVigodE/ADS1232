package br.com.pucgo;

import java.util.ArrayList;
import java.util.List;

/**
 * Fila de Prioridade Avançada para Sistema de Atendimento
 * Implementa três níveis de prioridade:
 * 1. Casos urgentes (maior prioridade)
 * 2. Pacientes idosos (60+ anos)
 * 3. Pacientes normais (menor prioridade)
 */
public class FilaPrioridadeAvancada {
    private List<Paciente> filaUrgente;     // Prioridade 1: Casos urgentes
    private List<Paciente> filaIdosos;      // Prioridade 2: Pacientes 60+ anos
    private List<Paciente> filaNormal;      // Prioridade 3: Pacientes normais

    private int totalInsercoes = 0;
    private long tempoTotalInsercao = 0;
    private long tempoTotalRemocao = 0;

    public FilaPrioridadeAvancada() {
        this.filaUrgente = new ArrayList<>();
        this.filaIdosos = new ArrayList<>();
        this.filaNormal = new ArrayList<>();
    }

    /**
     * Adiciona paciente na fila apropriada baseado na prioridade
     * Complexidade: O(1) - inserção sempre no final da lista apropriada
     */
    public void adicionarPaciente(Paciente paciente) {
        long inicio = System.nanoTime();

        if (paciente.isUrgente()) {
            filaUrgente.add(paciente);
            System.out.println("🚨 URGENTE: " + paciente.getNome() + " adicionado na fila de emergência");
        } else if (paciente.getIdade() >= 60) {
            filaIdosos.add(paciente);
            System.out.println("👴 IDOSO: " + paciente.getNome() + " adicionado na fila de idosos");
        } else {
            filaNormal.add(paciente);
            System.out.println("👤 NORMAL: " + paciente.getNome() + " adicionado na fila normal");
        }

        long fim = System.nanoTime();
        tempoTotalInsercao += (fim - inicio);
        totalInsercoes++;
    }

    /**
     * Remove e retorna o próximo paciente seguindo ordem de prioridade
     * Complexidade: O(n) devido ao remove(0) que desloca elementos
     */
    public Paciente chamarProximoPaciente() {
        long inicio = System.nanoTime();
        Paciente proximo = null;

        if (!filaUrgente.isEmpty()) {
            proximo = filaUrgente.remove(0);
            System.out.println("🚨 Chamando paciente URGENTE: " + proximo.getNome());
        } else if (!filaIdosos.isEmpty()) {
            proximo = filaIdosos.remove(0);
            System.out.println("👴 Chamando paciente IDOSO: " + proximo.getNome());
        } else if (!filaNormal.isEmpty()) {
            proximo = filaNormal.remove(0);
            System.out.println("👤 Chamando paciente NORMAL: " + proximo.getNome());
        }

        long fim = System.nanoTime();
        tempoTotalRemocao += (fim - inicio);

        return proximo;
    }

    /**
     * Consulta o próximo paciente sem removê-lo
     * Complexidade: O(1)
     */
    public Paciente proximoPaciente() {
        if (!filaUrgente.isEmpty()) {
            return filaUrgente.get(0);
        } else if (!filaIdosos.isEmpty()) {
            return filaIdosos.get(0);
        } else if (!filaNormal.isEmpty()) {
            return filaNormal.get(0);
        }
        return null;
    }

    /**
     * Verifica se todas as filas estão vazias
     */
    public boolean isEmpty() {
        return filaUrgente.isEmpty() && filaIdosos.isEmpty() && filaNormal.isEmpty();
    }

    /**
     * Retorna o total de pacientes em todas as filas
     */
    public int size() {
        return filaUrgente.size() + filaIdosos.size() + filaNormal.size();
    }

    /**
     * Exibe o estado atual de todas as filas
     */
    public void exibirEstadoCompleto() {
        System.out.println("\n=== ESTADO COMPLETO DA FILA DE PRIORIDADE ===");

        System.out.println("\n🚨 FILA URGENTE (" + filaUrgente.size() + " pacientes):");
        if (filaUrgente.isEmpty()) {
            System.out.println("   [Vazia]");
        } else {
            for (int i = 0; i < filaUrgente.size(); i++) {
                Paciente p = filaUrgente.get(i);
                System.out.println("   " + (i + 1) + ". " + p.getNome() + " (Idade: " + p.getIdade() + ")");
            }
        }

        System.out.println("\n👴 FILA IDOSOS (" + filaIdosos.size() + " pacientes):");
        if (filaIdosos.isEmpty()) {
            System.out.println("   [Vazia]");
        } else {
            for (int i = 0; i < filaIdosos.size(); i++) {
                Paciente p = filaIdosos.get(i);
                System.out.println("   " + (i + 1) + ". " + p.getNome() + " (Idade: " + p.getIdade() + ")");
            }
        }

        System.out.println("\n👤 FILA NORMAL (" + filaNormal.size() + " pacientes):");
        if (filaNormal.isEmpty()) {
            System.out.println("   [Vazia]");
        } else {
            for (int i = 0; i < filaNormal.size(); i++) {
                Paciente p = filaNormal.get(i);
                System.out.println("   " + (i + 1) + ". " + p.getNome() + " (Idade: " + p.getIdade() + ")");
            }
        }

        System.out.println("\nTOTAL GERAL: " + size() + " pacientes");
        System.out.println("=============================================");
    }

    /**
     * Exibe estatísticas de performance
     */
    public void exibirEstatisticasPerformance() {
        System.out.println("\n=== ANÁLISE DE PERFORMANCE ===");
        if (totalInsercoes > 0) {
            double mediaInsercao = (double) tempoTotalInsercao / totalInsercoes;
            double mediaRemocao = tempoTotalRemocao > 0 ? (double) tempoTotalRemocao / totalInsercoes : 0;

            System.out.println("Total de inserções: " + totalInsercoes);
            System.out.println("Tempo médio de inserção: " + String.format("%.2f", mediaInsercao) + " nanosegundos");
            System.out.println("Tempo médio de remoção: " + String.format("%.2f", mediaRemocao) + " nanosegundos");
            System.out.println("Complexidade inserção: O(1) - sempre adiciona no final");
            System.out.println("Complexidade remoção: O(n) - remove(0) desloca elementos");
        }
        System.out.println("===============================");
    }

    /**
     * Retorna informações detalhadas sobre a distribuição de prioridades
     */
    public void exibirDistribuicaoPrioridades() {
        int totalPacientes = size();
        if (totalPacientes == 0) {
            System.out.println("Nenhum paciente na fila.");
            return;
        }

        System.out.println("\n=== DISTRIBUIÇÃO DE PRIORIDADES ===");
        System.out.println("🚨 Casos urgentes: " + filaUrgente.size() + " (" +
                         String.format("%.1f", (filaUrgente.size() * 100.0 / totalPacientes)) + "%)");
        System.out.println("👴 Pacientes idosos: " + filaIdosos.size() + " (" +
                         String.format("%.1f", (filaIdosos.size() * 100.0 / totalPacientes)) + "%)");
        System.out.println("👤 Pacientes normais: " + filaNormal.size() + " (" +
                         String.format("%.1f", (filaNormal.size() * 100.0 / totalPacientes)) + "%)");
        System.out.println("===================================");
    }
}
