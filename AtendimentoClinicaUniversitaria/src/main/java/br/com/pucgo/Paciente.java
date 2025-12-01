package br.com.pucgo;

/**
 * Classe que representa um paciente da clínica universitária
 */
public class Paciente {
    private String nome;
    private int idade;
    private String cpf;
    private boolean prioridade;
    private boolean urgente; // Novo campo para casos urgentes

    public Paciente(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.prioridade = idade >= 60; // Prioridade automática para idosos
        this.urgente = false;
    }

    public Paciente(String nome, int idade, String cpf, boolean prioridade) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.prioridade = prioridade;
        this.urgente = false;
    }

    // Novo construtor para casos urgentes
    public Paciente(String nome, int idade, String cpf, boolean prioridade, boolean urgente) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.prioridade = prioridade;
        this.urgente = urgente;
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

    public boolean isUrgente() {
        return urgente;
    }

    // Setters
    public void setPrioridade(boolean prioridade) {
        this.prioridade = prioridade;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    @Override
    public String toString() {
        return String.format("Paciente{nome='%s', idade=%d, cpf='%s', prioridade=%s, urgente=%s}",
                           nome, idade, cpf, prioridade ? "SIM" : "NÃO", urgente ? "SIM" : "NÃO");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return cpf.equals(paciente.cpf);
    }
}
