package exercicio5;

public class Paralelepipedo {
    private final double altura;
    private final double largura;
    private final double comprimento;

    public Paralelepipedo(double altura, double largura, double comprimento) {
        if (altura < 0 || largura < 0 || comprimento < 0) {
            throw new IllegalArgumentException("Altura, largura e comprimento não podem ser negativos.");
        }
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public double getAltura() {
        return altura;
    }

    public double getLargura() {
        return largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public double calcularVolume() {
        return altura * largura * comprimento;
    }

    public double calcularArea() {
        return 2 * (altura * largura + altura * comprimento + largura * comprimento);
    }
}
