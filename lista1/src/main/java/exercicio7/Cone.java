package exercicio7;

public class Cone {
    private final double raio;
    private final double altura;

    public Cone(double raio, double altura) {
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

    public double calcularGeratriz() {
        return Math.sqrt(Math.pow(altura, 2) + Math.pow(raio, 2));
    }

    public double calcularAreaLateral() {
        double g = calcularGeratriz();
        return Math.PI * raio * g;
    }

    public double calcularAreaTotal() {
        double g = calcularGeratriz();
        return Math.PI * raio * (g + raio);
    }

    public double calcularVolume() {
        return (1.0 / 3.0) * Math.PI * Math.pow(raio, 2) * altura;
    }
}
