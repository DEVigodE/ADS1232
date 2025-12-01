package br.com.pucgo.model;

/**
 * Classe que representa uma unidade da biblioteca
 */
public class UnidadeBiblioteca {
    private String cnpj;
    private String nomeUnidade;
    private Endereco endereco;
    private String responsavelLocal;
    private String telefone;

    public UnidadeBiblioteca(String cnpj, String nomeUnidade, String responsavelLocal, String telefone) {
        this.cnpj = cnpj;
        this.nomeUnidade = nomeUnidade;
        this.responsavelLocal = responsavelLocal;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getResponsavelLocal() {
        return responsavelLocal;
    }

    public void setResponsavelLocal(String responsavelLocal) {
        this.responsavelLocal = responsavelLocal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return String.format("Unidade: %s | CNPJ: %s | Responsável: %s",
                nomeUnidade, cnpj, responsavelLocal);
    }
}

