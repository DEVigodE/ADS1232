package exercicio9;

import java.time.Year;

public class Eleitor {
    private final String nome;
    private final int anoNascimento;

    public Eleitor(String nome, int anoNascimento) {
        int anoAtual = Year.now().getValue();
        if (anoNascimento > anoAtual) {
            throw new IllegalArgumentException("Ano de nascimento não pode ser no futuro.");
        }
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public int calcularIdade() {
        int anoAtual = Year.now().getValue();
        return anoAtual - anoNascimento;
    }

    public String calcularTipoEleitor() {
        int idade = calcularIdade();

        if (idade < 16) {
            return "Não Eleitor";
        } else if (idade < 18) {
            return "Eleitor Facultativo";
        } else if (idade <= 65) {
            return "Eleitor Obrigatório";
        } else {
            return "Eleitor Facultativo";
        }
    }
}
