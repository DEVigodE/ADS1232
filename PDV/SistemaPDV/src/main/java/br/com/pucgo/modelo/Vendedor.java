package br.com.pucgo.modelo;

import java.util.UUID;

public class Vendedor {
    private UUID id;
    private String nome;
    private String cpf;
    private String email;

    public Vendedor(String nome, String cpf, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}

