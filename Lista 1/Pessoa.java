package com.devigode;

public class Pessoa {
    private String nome;
    private double peso;
    private double altura;
    private String sexo;

    public Pessoa(String nome, double peso, double altura, String sexo) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.sexo = sexo;
    }

    public double calcularIMC() {
        if (altura <= 0) {
            throw new IllegalArgumentException("Altura deve ser maior que zero.");
        }
        return peso / (altura * altura);
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public String getSexo() {
        return sexo;
    }

}