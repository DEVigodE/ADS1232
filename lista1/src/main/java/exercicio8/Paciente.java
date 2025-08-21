package exercicio8;

public class Paciente {
    private final String nome;
    private final double peso;
    private final double altura;

    public Paciente(String nome, double peso, double altura) {
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser maiores que zero.");
        }
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
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

    public double calcularIMC() {
        return peso / Math.pow(altura, 2);
    }

    public String calcularFaixaPeso() {
        double imc = calcularIMC();

        if (imc < 20) {
            return "Abaixo do peso ideal";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Excesso de peso";
        } else if (imc < 35) {
            return "Obesidade";
        } else {
            return "Obesidade mórbida";
        }
    }
}
