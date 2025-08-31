package br.com.pucgo.exercicio4;

import java.util.Date;

/**
 * Classe que representa um passageiro no sistema
 * O e-mail do passageiro é seu identificador único
 */
public class Passageiro {
    private String nome;
    private Sexo sexo;
    private Date dataDeNascimento;
    private String email;

    /**
     * Construtor da classe Passageiro
     * @param nome Nome do passageiro
     * @param sexo Sexo do passageiro
     * @param dataDeNascimento Data de nascimento do passageiro
     * @param email E-mail do passageiro (identificador único)
     */
    public Passageiro(String nome, Sexo sexo, Date dataDeNascimento, String email) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    // Apenas getter para e-mail (identificador único)
    public String getEmail() {
        return email;
    }

    /**
     * Sobrescreve o toString para retornar o nome do passageiro
     */
    @Override
    public String toString() {
        return nome;
    }
}
