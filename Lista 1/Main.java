package com.devigode;

public class Main {
    public static void main(String[] args) {

        System.out.println("Lista 1");
        System.out.println("Exercicio 1");

        Pessoa pessoa = new Pessoa("Igor", 102.4, 1.70, "M");
        System.out.println("\n👤 Dados da Pessoa:");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Peso: " + pessoa.getPeso() + " kg");
        System.out.println("Altura: " + pessoa.getAltura() + " m");
        System.out.println("Sexo: " + pessoa.getSexo());
        double imc = pessoa.calcularIMC();
        System.out.printf("IMC: %.2f\n", imc);

        System.out.println("Exercicio 2");
        Piramide p = new Piramide(9.0, 12.0);
        double volume = p.calcularVolume();
        System.out.printf("\nVolume da pirâmide (base=9.0, altura=12.0): %.2f\n", volume);
    }

}