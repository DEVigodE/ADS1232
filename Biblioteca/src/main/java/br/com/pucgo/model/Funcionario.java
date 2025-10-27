package br.com.pucgo.model;

/**
 * Classe que representa um funcionário da biblioteca
 */
public class Funcionario {
    private String matricula;
    private String nome;
    private String cargo;
    private String telefone;
    private String email;

    public Funcionario(String matricula, String nome, String cargo, String telefone, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Funcionário: %s | Matrícula: %s | Cargo: %s", nome, matricula, cargo);
    }
}

