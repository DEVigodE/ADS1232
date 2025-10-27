package br.com.pucgo.model;

import java.time.LocalDate;

/**
 * Classe que representa o histórico de um empréstimo
 */
public class HistoricoEmprestimo {
    private int emprestimoId;
    private String raAluno;
    private String nomeAluno;
    private String isbnLivro;
    private String tituloLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;
    private boolean emAtraso;
    private long diasAtraso;

    public HistoricoEmprestimo(Emprestimo emprestimo, Livro livro) {
        this.emprestimoId = emprestimo.getId();
        this.raAluno = emprestimo.getAluno().getRa();
        this.nomeAluno = emprestimo.getAluno().getNomeCompleto();
        this.isbnLivro = livro.getIsbn();
        this.tituloLivro = livro.getTitulo();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataPrevistaDevolucao = emprestimo.getDataPrevistaDevolucao();
        this.dataRealDevolucao = emprestimo.getDataRealDevolucao();
        this.emAtraso = emprestimo.isEmAtraso();
        this.diasAtraso = emprestimo.getDiasAtraso();
    }

    // Getters
    public int getEmprestimoId() {
        return emprestimoId;
    }

    public String getRaAluno() {
        return raAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getIsbnLivro() {
        return isbnLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public boolean isEmAtraso() {
        return emAtraso;
    }

    public long getDiasAtraso() {
        return diasAtraso;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Aluno: %s | Livro: %s | Empréstimo: %s | Devolução: %s | Atraso: %s",
                emprestimoId, nomeAluno, tituloLivro, dataEmprestimo,
                dataRealDevolucao != null ? dataRealDevolucao.toString() : "Não devolvido",
                emAtraso ? diasAtraso + " dias" : "Não");
    }
}

