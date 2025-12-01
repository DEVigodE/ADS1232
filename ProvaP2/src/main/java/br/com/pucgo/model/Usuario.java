package br.com.pucgo.model;

/**
 * Representa um usuário do sistema de atendimento
 */
public class Usuario {
    private String nome;
    private String documento;
    private int prioridade; // Usado apenas na Secretaria Acadêmica

    public Usuario(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
        this.prioridade = 0;
    }

    public Usuario(String nome, String documento, int prioridade) {
        this.nome = nome;
        this.documento = documento;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        if (prioridade > 0) {
            return String.format("Usuario{nome='%s', documento='%s', prioridade=%d}",
                               nome, documento, prioridade);
        }
        return String.format("Usuario{nome='%s', documento='%s'}", nome, documento);
    }
}

