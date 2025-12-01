package exercicio1;

public class Pessoa {
    private final String nome;
    private final double peso;
    private final double altura;
    private final String sexo;

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