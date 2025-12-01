package br.com.pucgo.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um empréstimo de livros
 */
public class Emprestimo {
    private static int contadorId = 1;

    private int id;
    private Aluno aluno;
    private List<Livro> livros;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;
    private Funcionario funcionarioResponsavel;
    private boolean ativo;
    private boolean renovacaoEspecialAutorizada;
    private BibliotecarioSupervisor supervisorAutorizador;

    public Emprestimo(Aluno aluno, Funcionario funcionarioResponsavel) {
        this.id = contadorId++;
        this.aluno = aluno;
        this.livros = new ArrayList<>();
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(14); // 14 dias padrão
        this.funcionarioResponsavel = funcionarioResponsavel;
        this.ativo = true;
        this.renovacaoEspecialAutorizada = false;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void adicionarLivro(Livro livro) {
        if (livro.isDisponivel()) {
            this.livros.add(livro);
            livro.emprestar();
        } else {
            throw new RuntimeException("Livro não disponível: " + livro.getTitulo());
        }
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public void setDataRealDevolucao(LocalDate dataRealDevolucao) {
        this.dataRealDevolucao = dataRealDevolucao;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isRenovacaoEspecialAutorizada() {
        return renovacaoEspecialAutorizada;
    }

    public BibliotecarioSupervisor getSupervisorAutorizador() {
        return supervisorAutorizador;
    }

    public boolean isEmAtraso() {
        if (!ativo) {
            return false;
        }
        return LocalDate.now().isAfter(dataPrevistaDevolucao);
    }

    public long getDiasAtraso() {
        if (!isEmAtraso()) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dataPrevistaDevolucao, LocalDate.now());
    }

    public void renovar(int diasAdicionais, BibliotecarioSupervisor supervisor) {
        if (supervisor != null && supervisor.autorizarRenovacaoEspecial(this, diasAdicionais)) {
            this.dataPrevistaDevolucao = this.dataPrevistaDevolucao.plusDays(diasAdicionais);
            this.renovacaoEspecialAutorizada = true;
            this.supervisorAutorizador = supervisor;
        }
    }

    public void finalizarEmprestimo() {
        this.dataRealDevolucao = LocalDate.now();
        this.ativo = false;
        // Devolver livros para o acervo (via conferência)
        for (Livro livro : livros) {
            // Os livros serão devolvidos após conferência
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Aluno: %s | Livros: %d | Data Empréstimo: %s | Devolução Prevista: %s | Status: %s",
                id, aluno.getNomeCompleto(), livros.size(), dataEmprestimo, dataPrevistaDevolucao,
                ativo ? (isEmAtraso() ? "EM ATRASO" : "ATIVO") : "FINALIZADO");
    }
}

