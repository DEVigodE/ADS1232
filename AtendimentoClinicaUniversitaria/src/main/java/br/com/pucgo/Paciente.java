package br.com.pucgo;

/**
 * Classe que representa um paciente da clínica universitária
 */
public class Paciente {
    private String nome;
    private int idade;
    private String cpf;
    private boolean prioridade;

    public Paciente(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.prioridade = idade >= 60; // Prioridade automática para idosos
    }

    public Paciente(String nome, int idade, String cpf, boolean prioridade) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.prioridade = prioridade;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean temPrioridade() {
        return prioridade;
    }

    // Setters
    public void setPrioridade(boolean prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return String.format("Paciente{nome='%s', idade=%d, cpf='%s', prioridade=%s}",
                           nome, idade, cpf, prioridade ? "SIM" : "NÃO");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return cpf.equals(paciente.cpf);
    }
}
