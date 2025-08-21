package exercicio11;

public class Funcionario {
    private final String nome;
    private double salarioMes;
    private final int numeroFilhos;

    public Funcionario(String nome, double salarioMes, int numeroFilhos) {
        if (salarioMes < 0 || numeroFilhos < 0) {
            throw new IllegalArgumentException("Salário e número de filhos não podem ser negativos.");
        }
        this.nome = nome;
        this.salarioMes = salarioMes;
        this.numeroFilhos = numeroFilhos;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioMes() {
        return salarioMes;
    }

    public int getNumeroFilhos() {
        return numeroFilhos;
    }

    public void aumentarSalario(double percentual) {
        if (percentual < 0) throw new IllegalArgumentException("Percentual não pode ser negativo.");
        this.salarioMes += this.salarioMes * (percentual / 100.0);
    }

    public double calcularINSS() {
        if (salarioMes <= 2545.0) {
            return salarioMes * 0.06;
        } else {
            return salarioMes * 0.10;
        }
    }

    public double calcularIR() {
        if (salarioMes <= 1903.98) {
            return 0.0;
        } else if (salarioMes <= 2826.65) {
            return salarioMes * 0.075;
        } else if (salarioMes <= 3751.05) {
            return salarioMes * 0.15;
        } else if (salarioMes <= 4664.68) {
            return salarioMes * 0.225;
        } else {
            return salarioMes * 0.275;
        }
    }

    public double calcularSalarioLiquido() {
        return salarioMes - calcularINSS() - calcularIR();
    }
}
