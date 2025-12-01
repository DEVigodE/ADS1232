package br.com.pucgo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Exercício 1 (Cadastro de Alunos - Vetor)");
            System.out.println("2 - Exercício 2 (Call-Center - Fila)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Iniciando Exercício 1 ---");
                    br.com.pucgo.exercicio1.App.main(new String[]{});
                    break;
                case 2:
                    System.out.println("\n--- Iniciando Exercício 2 ---");
                    br.com.pucgo.exercicio2.App.main(new String[]{});
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
