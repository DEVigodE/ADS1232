package br.com.pucgo.exercicio1;

import java.util.Scanner;

public class App {
    private static final int MAX_ALUNOS = 100;
    private static Aluno[] alunos = new Aluno[MAX_ALUNOS];
    private static int totalAlunos = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarTodos();
                    break;
                case 3:
                    pesquisarAluno();
                    break;
                case 4:
                    removerAluno();
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
        System.out.println("\n=== SISTEMA DE CADASTRO DE ALUNOS ===");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar todos");
        System.out.println("3 - Pesquisar");
        System.out.println("4 - Remover");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno() {
        if (totalAlunos >= MAX_ALUNOS) {
            System.out.println("Limite máximo de alunos atingido!");
            return;
        }

        System.out.println("\n--- CADASTRAR ALUNO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        // Verificar se matrícula já existe
        if (buscarPorMatricula(matricula) != -1) {
            System.out.println("Erro: Matrícula já cadastrada!");
            return;
        }

        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();

        alunos[totalAlunos] = new Aluno(nome, matricula, dataNascimento);
        totalAlunos++;

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void listarTodos() {
        System.out.println("\n--- LISTA DE TODOS OS ALUNOS ---");

        if (totalAlunos == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (int i = 0; i < totalAlunos; i++) {
            System.out.println((i + 1) + ". " + alunos[i]);
        }

        System.out.println("\nTotal de alunos cadastrados: " + totalAlunos);
    }

    private static void pesquisarAluno() {
        System.out.println("\n--- PESQUISAR ALUNO ---");
        System.out.print("Digite a matrícula para pesquisar: ");
        String matricula = scanner.nextLine();

        int indice = buscarPorMatricula(matricula);

        if (indice != -1) {
            System.out.println("Aluno encontrado:");
            System.out.println(alunos[indice]);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private static void removerAluno() {
        System.out.println("\n--- REMOVER ALUNO ---");
        System.out.print("Digite a matrícula do aluno a ser removido: ");
        String matricula = scanner.nextLine();

        int indice = buscarPorMatricula(matricula);

        if (indice != -1) {
            System.out.println("Aluno encontrado:");
            System.out.println(alunos[indice]);

            System.out.print("Confirma a remoção? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                // Mover todos os elementos posteriores uma posição para trás
                for (int i = indice; i < totalAlunos - 1; i++) {
                    alunos[i] = alunos[i + 1];
                }
                alunos[totalAlunos - 1] = null;
                totalAlunos--;

                System.out.println("Aluno removido com sucesso!");
            } else {
                System.out.println("Remoção cancelada.");
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private static int buscarPorMatricula(String matricula) {
        for (int i = 0; i < totalAlunos; i++) {
            if (alunos[i].getMatricula().equals(matricula)) {
                return i;
            }
        }
        return -1;
    }
}
