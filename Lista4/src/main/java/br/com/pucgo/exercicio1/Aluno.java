package br.com.pucgo.exercicio1;

public class Aluno {
    private String nome;
    private String matricula;
    private String dataNascimento;

    public Aluno(String nome, String matricula, String dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Matrícula: %s | Data de Nascimento: %s",
                           nome, matricula, dataNascimento);
    }
}
