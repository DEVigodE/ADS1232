package br.com.pucgo.model;

/**
 * Classe que representa um aluno da universidade
 */
public class Aluno {
    private String ra; // Registro Acadêmico
    private String nomeCompleto;
    private String curso;
    private String telefone;
    private String emailInstitucional;
    private Endereco endereco;

    public Aluno(String ra, String nomeCompleto, String curso, String telefone, String emailInstitucional) {
        this.ra = ra;
        this.nomeCompleto = nomeCompleto;
        this.curso = curso;
        this.telefone = telefone;
        this.emailInstitucional = emailInstitucional;
    }

    // Getters e Setters
    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return String.format("RA: %s | Nome: %s | Curso: %s | Email: %s",
                ra, nomeCompleto, curso, emailInstitucional);
    }
}

