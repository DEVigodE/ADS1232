package exercicio6;

public class Cilindro {
    private final double raio;
    private final double altura;

    public Cilindro(double raio, double altura) {
        if (raio < 0 || altura < 0) {
            throw new IllegalArgumentException("O raio e a altura não podem ser negativos.");
        }
        this.raio = raio;
        this.altura = altura;
    }

    public double getRaio() {
        return raio;
    }

    public double getAltura() {
        return altura;
    }

    public double calcularAreaLateral() {
        return 2 * Math.PI * raio * altura;
    }

    public double calcularAreaTotal() {
        return 2 * Math.PI * raio * (altura + raio);
    }

    // Volume = π * r² * h
    public double calcularVolume() {
        return Math.PI * raio * raio * altura;
    }
}
