package br.com.pucgo.model;

/**
 * Classe que representa um livro da biblioteca
 */
public class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String categoria;
    private int numeroExemplaresDisponiveis;
    private int numeroExemplaresTotal;
    private Editora editora;

    public Livro(String isbn, String titulo, String autor, int anoPublicacao, String categoria,
                 int numeroExemplaresTotal, Editora editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.numeroExemplaresTotal = numeroExemplaresTotal;
        this.numeroExemplaresDisponiveis = numeroExemplaresTotal;
        this.editora = editora;
    }

    // Getters e Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getNumeroExemplaresDisponiveis() {
        return numeroExemplaresDisponiveis;
    }

    public void setNumeroExemplaresDisponiveis(int numeroExemplaresDisponiveis) {
        this.numeroExemplaresDisponiveis = numeroExemplaresDisponiveis;
    }

    public int getNumeroExemplaresTotal() {
        return numeroExemplaresTotal;
    }

    public void setNumeroExemplaresTotal(int numeroExemplaresTotal) {
        this.numeroExemplaresTotal = numeroExemplaresTotal;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public boolean isDisponivel() {
        return numeroExemplaresDisponiveis > 0;
    }

    public void emprestar() {
        if (isDisponivel()) {
            numeroExemplaresDisponiveis--;
        }
    }

    public void devolver() {
        if (numeroExemplaresDisponiveis < numeroExemplaresTotal) {
            numeroExemplaresDisponiveis++;
        }
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Disponíveis: %d/%d",
                isbn, titulo, autor, numeroExemplaresDisponiveis, numeroExemplaresTotal);
    }
}

