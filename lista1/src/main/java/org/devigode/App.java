package org.devigode;

import exercicio1.Pessoa;
import exercicio2.Piramide;
import exercicio3.Esfera;           // versão com setter/validação (a primeira que combinamos)
import exercicio4.Retangulo;
import exercicio5.Paralelepipedo;
import exercicio6.Cilindro;
import exercicio7.Cone;
import exercicio8.Paciente;
import exercicio9.Eleitor;
import exercicio11.Funcionario;

public class App {
    public static void main(String[] args) {

        System.out.println("===== Lista 1 =====");

        // ------------------ Exercício 1: Pessoa (IMC) ------------------
        System.out.println("\nExercício 1: Pessoa");
        Pessoa pessoa = new Pessoa("Igor", 102.4, 1.70, "M");
        System.out.println("👤 Dados da Pessoa:");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Peso: " + pessoa.getPeso() + " kg");
        System.out.println("Altura: " + pessoa.getAltura() + " m");
        System.out.println("Sexo: " + pessoa.getSexo());
        double imcPessoa = pessoa.calcularIMC();
        System.out.printf("IMC: %.2f%n", imcPessoa);

        // ------------------ Exercício 2: Pirâmide (Volume) ------------------
        System.out.println("\nExercício 2: Pirâmide");
        Piramide piramide = new Piramide(9.0, 12.0); // base, altura
        double volPiramide = piramide.calcularVolume();
        System.out.printf("Volume da pirâmide (base=9.0, altura=12.0): %.2f%n", volPiramide);

        // ------------------ Exercício 3: Esfera (Área e Volume) ------------------
        System.out.println("\nExercício 3: Esfera");
        Esfera esfera = new Esfera(5.0); // raio
        System.out.printf("Raio da esfera: %.2f%n", esfera.getRaio());
        System.out.printf("Área da esfera: %.2f%n", esfera.calcularArea());
        System.out.printf("Volume da esfera: %.2f%n", esfera.calcularVolume());
        // Se quiser testar a validação do setter (versão com setter):
        // esfera.setRaio(6.0);

        // ------------------ Exercício 4: Retângulo (Área e Perímetro) ------------------
        System.out.println("\nExercício 4: Retângulo");
        Retangulo ret = new Retangulo(8.0, 3.5); // comprimento, largura
        System.out.printf("Comprimento: %.2f | Largura: %.2f%n", ret.getComprimento(), ret.getLargura());
        System.out.printf("Área: %.2f%n", ret.calcularArea());
        System.out.printf("Perímetro: %.2f%n", ret.calcularPerimetro());

        // ------------------ Exercício 5: Paralelepípedo (Área e Volume) ------------------
        System.out.println("\nExercício 5: Paralelepípedo");
        Paralelepipedo plp = new Paralelepipedo(2.0, 3.0, 4.0); // altura, largura, comprimento
        System.out.printf("Altura: %.2f | Largura: %.2f | Comprimento: %.2f%n",
                plp.getAltura(), plp.getLargura(), plp.getComprimento());
        System.out.printf("Volume: %.2f%n", plp.calcularVolume());
        System.out.printf("Área total: %.2f%n", plp.calcularArea());

        // ------------------ Exercício 6: Cilindro (Áreas e Volume) ------------------
        System.out.println("\nExercício 6: Cilindro");
        Cilindro cil = new Cilindro(3.0, 10.0); // raio, altura
        System.out.printf("Raio: %.2f | Altura: %.2f%n", cil.getRaio(), cil.getAltura());
        System.out.printf("Área lateral: %.2f%n", cil.calcularAreaLateral());
        System.out.printf("Área total: %.2f%n", cil.calcularAreaTotal());
        System.out.printf("Volume: %.2f%n", cil.calcularVolume());

        // ------------------ Exercício 7: Cone (Geratriz, Áreas e Volume) ------------------
        System.out.println("\nExercício 7: Cone");
        Cone cone = new Cone(3.0, 4.0); // raio, altura
        System.out.printf("Raio: %.2f | Altura: %.2f%n", cone.getRaio(), cone.getAltura());
        System.out.printf("Geratriz: %.2f%n", cone.calcularGeratriz());
        System.out.printf("Área lateral: %.2f%n", cone.calcularAreaLateral());
        System.out.printf("Área total: %.2f%n", cone.calcularAreaTotal());
        System.out.printf("Volume: %.2f%n", cone.calcularVolume());

        // ------------------ Exercício 8: Paciente (IMC e Faixa) ------------------
        System.out.println("\nExercício 8: Paciente");
        Paciente pac = new Paciente("Alice", 68.0, 1.70);
        System.out.println("Nome: " + pac.getNome());
        System.out.printf("Peso: %.2f kg | Altura: %.2f m%n", pac.getPeso(), pac.getAltura());
        double imc = pac.calcularIMC();
        System.out.printf("IMC: %.2f%n", imc);
        System.out.println("Faixa de risco: " + pac.calcularFaixaPeso());

        // ------------------ Exercício 9: Eleitor (Tipo por Idade) ------------------
        System.out.println("\nExercício 9: Eleitor");
        Eleitor e1 = new Eleitor("Bruno", 2008); // ajuste o ano para testar as faixas
        Eleitor e2 = new Eleitor("Clara", 1960);
        System.out.println(e1.getNome() + " | Idade: " + e1.calcularIdade() + " | Tipo: " + e1.calcularTipoEleitor());
        System.out.println(e2.getNome() + " | Idade: " + e2.calcularIdade() + " | Tipo: " + e2.calcularTipoEleitor());

        // ------------------ Exercício 11: Funcionário (INSS, IR, Salário Líquido) ------------------
        System.out.println("\nExercício 11: Funcionário");
        Funcionario func = new Funcionario("Daniela", 3200.00, 2);
        System.out.println("Nome: " + func.getNome());
        System.out.printf("Salário bruto: R$ %.2f | Filhos: %d%n", func.getSalarioMes(), func.getNumeroFilhos());
        System.out.printf("INSS: R$ %.2f%n", func.calcularINSS());
        System.out.printf("IR: R$ %.2f%n", func.calcularIR());
        System.out.printf("Salário líquido: R$ %.2f%n", func.calcularSalarioLiquido());

        // Demonstração de aumento
        func.aumentarSalario(8.0); // +8%
        System.out.printf("\nApós aumento de 8%% -> Salário bruto: R$ %.2f%n", func.getSalarioMes());
        System.out.printf("INSS: R$ %.2f | IR: R$ %.2f | Líquido: R$ %.2f%n",
                func.calcularINSS(), func.calcularIR(), func.calcularSalarioLiquido());

        System.out.println("\n===== Fim =====");
    }
}
