package br.com.pucgo.exercicio2;

public class Contato {
    private String nome;
    private String telefone;
    private String assunto;

    public Contato(String nome, String telefone, String assunto) {
        this.nome = nome;
        this.telefone = telefone;
        this.assunto = assunto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Telefone: %s | Assunto: %s",
                           nome, telefone, assunto);
    }
}
