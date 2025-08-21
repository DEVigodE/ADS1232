package exercicio4;

public class Retangulo {
    private final double comprimento;
    private final double largura;

    // Construtor com validação
    public Retangulo(double comprimento, double largura) {
        if (comprimento < 0 || largura < 0) {
            throw new IllegalArgumentException("O comprimento e a largura não podem ser negativos.");
        }
        this.comprimento = comprimento;
        this.largura = largura;
    }

    // Getters
    public double getComprimento() {
        return comprimento;
    }

    public double getLargura() {
        return largura;
    }

    // Método para calcular a área
    public double calcularArea() {
        return comprimento * largura;
    }

    // Método para calcular o perímetro
    public double calcularPerimetro() {
        return 2 * (comprimento + largura);
    }
}
