package br.com.pucgo.modelo;

import java.util.UUID;

public class Empresa {
    private UUID id;
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private Endereco endereco;

    public Empresa(String cnpj, String nomeFantasia, String razaoSocial, Endereco endereco) {
        this.id = UUID.randomUUID();
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
    }

    public UUID getId() { return id; }
    public String getCnpj() { return cnpj; }
    public String getNomeFantasia() { return nomeFantasia; }
    public String getRazaoSocial() { return razaoSocial; }
    public Endereco getEndereco() { return endereco; }

    @Override
    public String toString() {
        return nomeFantasia + " (CNPJ: " + cnpj + ")";
    }
}

