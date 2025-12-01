package br.com.pucgo.modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Cliente {
    private UUID id;
    private String cpf;
    private String nomeCompleto;
    private String telefone;
    private LocalDate dataNascimento;
    private Endereco endereco;

    public Cliente(String cpf, String nomeCompleto, String telefone, LocalDate dataNascimento, Endereco endereco) {
        this.id = UUID.randomUUID();
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public UUID getId() { return id; }
    public String getCpf() { return cpf; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getTelefone() { return telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Endereco getEndereco() { return endereco; }

    @Override
    public String toString() {
        return nomeCompleto + " (CPF: " + cpf + ")";
    }
}

