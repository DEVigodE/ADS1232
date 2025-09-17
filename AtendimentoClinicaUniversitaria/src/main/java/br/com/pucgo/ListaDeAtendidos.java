package br.com.pucgo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa uma lista de pacientes atendidos
 * Permite armazenar, buscar e exibir informações dos pacientes atendidos
 */
public class ListaDeAtendidos {
    private List<Paciente> atendidos;

    public ListaDeAtendidos() {
        this.atendidos = new ArrayList<>();
    }

    /**
     * Adiciona um paciente na lista de atendidos
     */
    public void adicionarAtendido(Paciente paciente) {
        atendidos.add(paciente);
        System.out.println("Paciente " + paciente.getNome() + " foi atendido e adicionado à lista de atendidos");
    }

    /**
     * Verifica se um paciente específico já foi atendido
     */
    public boolean foiAtendido(String cpf) {
        for (Paciente paciente : atendidos) {
            if (paciente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca um paciente atendido pelo CPF
     */
    public Paciente buscarAtendido(String cpf) {
        for (Paciente paciente : atendidos) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        return null;
    }

    /**
     * Exibe todos os pacientes atendidos
     */
    public void exibirAtendidos() {
        System.out.println("\n=== PACIENTES ATENDIDOS ===");
        if (atendidos.isEmpty()) {
            System.out.println("Nenhum paciente foi atendido ainda.");
        } else {
            for (int i = 0; i < atendidos.size(); i++) {
                Paciente p = atendidos.get(i);
                System.out.println((i + 1) + ". " + p.getNome() +
                                 " - Idade: " + p.getIdade() +
                                 " - CPF: " + p.getCpf() +
                                 " - Prioridade: " + (p.temPrioridade() ? "SIM" : "NÃO"));
            }
        }
        System.out.println("Total de atendidos: " + atendidos.size());
        System.out.println("============================\n");
    }

    /**
     * Retorna a quantidade de pacientes atendidos
     */
    public int getTotalAtendidos() {
        return atendidos.size();
    }

    /**
     * Calcula a média de idade dos pacientes atendidos
     */
    public double getMediaIdade() {
        if (atendidos.isEmpty()) {
            return 0.0;
        }

        int somaIdades = 0;
        for (Paciente paciente : atendidos) {
            somaIdades += paciente.getIdade();
        }

        return (double) somaIdades / atendidos.size();
    }

    /**
     * Conta quantos pacientes atendidos tinham prioridade
     */
    public int getTotalPrioridade() {
        int count = 0;
        for (Paciente paciente : atendidos) {
            if (paciente.temPrioridade()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Exibe estatísticas dos atendimentos
     */
    public void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DE ATENDIMENTO ===");
        System.out.println("Total de pacientes atendidos: " + getTotalAtendidos());
        System.out.printf("Média de idade dos atendidos: %.1f anos%n", getMediaIdade());
        System.out.println("Pacientes com prioridade atendidos: " + getTotalPrioridade());
        System.out.println("Pacientes sem prioridade atendidos: " + (getTotalAtendidos() - getTotalPrioridade()));
        System.out.println("=====================================\n");
    }
}
