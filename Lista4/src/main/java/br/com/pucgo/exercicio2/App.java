package br.com.pucgo.exercicio2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    private static Queue<Contato> filaContatos = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    inserirContato();
                    break;
                case 2:
                    proximoContato();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA CALL-CENTER ===");
        System.out.println("Contatos na fila: " + filaContatos.size());
        System.out.println("1 - Inserir Contato");
        System.out.println("2 - Próximo Contato");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void inserirContato() {
        System.out.println("\n--- INSERIR CONTATO NA FILA ---");

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Assunto: ");
        String assunto = scanner.nextLine();

        Contato novoContato = new Contato(nome, telefone, assunto);
        filaContatos.offer(novoContato);

        System.out.println("Contato adicionado à fila com sucesso!");
        System.out.println("Posição na fila: " + filaContatos.size());
    }

    private static void proximoContato() {
        System.out.println("\n--- PRÓXIMO CONTATO ---");

        if (filaContatos.isEmpty()) {
            System.out.println("Não há contatos na fila!");
            return;
        }

        Contato proximoContato = filaContatos.poll();

        System.out.println("=== DADOS DO CLIENTE ===");
        System.out.println(proximoContato);
        System.out.println("========================");
        System.out.println("Atenda este cliente agora!");
        System.out.println("Contatos restantes na fila: " + filaContatos.size());
    }
}
