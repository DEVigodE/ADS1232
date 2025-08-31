package br.com.pucgo.exercicio5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Classe principal com interface interativa para gerenciar passageiros e corridas
 * Permite cadastrar, listar e consultar passageiros e corridas
 */
public class App {
    private static CentralDeInformacoes central;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        central = new CentralDeInformacoes();

        // Inicializa o sistema
        inicializarSistema();

        // Loop principal do programa
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            String opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "1":
                    cadastrarNovoPassageiro();
                    break;
                case "2":
                    listarTodosPassageiros();
                    break;
                case "3":
                    exibirInformacoesPassageiro();
                    break;
                case "4":
                    cadastrarNovaCorrida();
                    break;
                case "5":
                    listarTodasCorridas();
                    break;
                case "6":
                    listarCorridasDeUmPassageiro();
                    break;
                case "S":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }

        System.out.println("Sistema encerrado. Até logo!");
        scanner.close();
    }

    /**
     * Inicializa o sistema
     */
    private static void inicializarSistema() {
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE PASSAGEIROS E CORRIDAS ===");
        System.out.println("Sistema inicializado com sucesso!\n");
    }

    /**
     * Exibe o menu principal do sistema
     */
    private static void exibirMenu() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1 - Novo passageiro");
        System.out.println("2 - Listar todos os passageiros");
        System.out.println("3 - Exibir informações de um passageiro específico");
        System.out.println("4 - Nova corrida");
        System.out.println("5 - Listar todas as corridas");
        System.out.println("6 - Listar corridas de um passageiro");
        System.out.println("S - Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Cadastra um novo passageiro no sistema
     */
    private static void cadastrarNovoPassageiro() {
        System.out.println("\n=== CADASTRO DE NOVO PASSAGEIRO ===");

        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode estar vazio!\n");
                return;
            }

            System.out.print("E-mail: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("E-mail não pode estar vazio!\n");
                return;
            }

            if (central.recuperarPassageiroPeloEmail(email) != null) {
                System.out.println("Erro: Já existe um passageiro com este e-mail!\n");
                return;
            }

            Sexo sexo = coletarSexo();
            if (sexo == null) {
                return;
            }

            Date dataNascimento = coletarDataNascimento();
            if (dataNascimento == null) {
                return;
            }

            Passageiro novoPassageiro = new Passageiro(nome, sexo, dataNascimento, email);
            boolean adicionado = central.adicionarPassageiro(novoPassageiro);

            if (adicionado) {
                System.out.println("Passageiro cadastrado com sucesso!\n");
            } else {
                System.out.println("Erro: Passageiro deve ter pelo menos 18 anos de idade!\n");
            }

        } catch (Exception e) {
            System.out.println("Erro inesperado no cadastro: " + e.getMessage() + "\n");
        }
    }

    /**
     * Cadastra uma nova corrida no sistema
     */
    private static void cadastrarNovaCorrida() {
        System.out.println("\n=== CADASTRO DE NOVA CORRIDA ===");

        if (!central.temPassageiros()) {
            System.out.println("Erro: Não há passageiros cadastrados no sistema!");
            System.out.println("Cadastre um passageiro primeiro.\n");
            return;
        }

        try {
            System.out.print("E-mail do passageiro: ");
            String emailPassageiro = scanner.nextLine().trim();

            if (emailPassageiro.isEmpty()) {
                System.out.println("E-mail não pode estar vazio!\n");
                return;
            }

            Passageiro passageiro = central.recuperarPassageiroPeloEmail(emailPassageiro);
            if (passageiro == null) {
                System.out.println("Erro: Passageiro não encontrado com o e-mail: " + emailPassageiro + "\n");
                return;
            }

            System.out.print("Endereço de partida: ");
            String enderecoPartida = scanner.nextLine().trim();
            if (enderecoPartida.isEmpty()) {
                System.out.println("Endereço de partida não pode estar vazio!\n");
                return;
            }

            System.out.print("Endereço de destino: ");
            String enderecoDestino = scanner.nextLine().trim();
            if (enderecoDestino.isEmpty()) {
                System.out.println("Endereço de destino não pode estar vazio!\n");
                return;
            }

            Corrida novaCorrida = new Corrida(enderecoPartida, enderecoDestino, passageiro);
            boolean adicionada = central.adicionarCorrida(novaCorrida);

            if (adicionada) {
                System.out.println("Corrida cadastrada com sucesso!");
                System.out.println("ID da corrida: " + novaCorrida.getId());
                System.out.println("Solicitação: " + novaCorrida.toString() + "\n");
            } else {
                System.out.println("Erro: Não foi possível cadastrar a corrida (ID duplicado).\n");
            }

        } catch (Exception e) {
            System.out.println("Erro inesperado no cadastro da corrida: " + e.getMessage() + "\n");
        }
    }

    /**
     * Coleta o sexo do passageiro
     */
    private static Sexo coletarSexo() {
        System.out.println("Sexo:");
        System.out.println("1 - Masculino");
        System.out.println("2 - Feminino");
        System.out.println("3 - Outro");
        System.out.print("Escolha (1-3): ");

        String opcao = scanner.nextLine().trim();
        switch (opcao) {
            case "1":
                return Sexo.MASCULINO;
            case "2":
                return Sexo.FEMININO;
            case "3":
                return Sexo.OUTRO;
            default:
                System.out.println("Opção inválida para sexo!\n");
                return null;
        }
    }

    /**
     * Coleta a data de nascimento do passageiro
     */
    private static Date coletarDataNascimento() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine().trim();

        try {
            Date data = sdf.parse(dataStr);

            if (data.after(new Date())) {
                System.out.println("Erro: Data de nascimento não pode ser futura!\n");
                return null;
            }

            return data;

        } catch (ParseException e) {
            System.out.println("Erro: Data inválida! Use o formato dd/MM/yyyy\n");
            return null;
        }
    }

    /**
     * Lista todos os passageiros cadastrados
     */
    private static void listarTodosPassageiros() {
        System.out.println();
        System.out.println(central.listarTodosPassageiros());
    }

    /**
     * Lista todas as corridas cadastradas
     */
    private static void listarTodasCorridas() {
        System.out.println();
        System.out.println(central.listarTodasCorridas());
    }

    /**
     * Lista as corridas de um passageiro específico
     */
    private static void listarCorridasDeUmPassageiro() {
        System.out.println("\n=== CONSULTA DE CORRIDAS POR PASSAGEIRO ===");

        if (!central.temPassageiros()) {
            System.out.println("Nenhum passageiro cadastrado no sistema.\n");
            return;
        }

        System.out.print("Digite o e-mail do passageiro: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("E-mail não pode estar vazio!\n");
            return;
        }

        System.out.println();
        System.out.println(central.listarCorridasDeUmPassageiro(email));
    }

    /**
     * Exibe informações detalhadas de um passageiro específico
     */
    private static void exibirInformacoesPassageiro() {
        System.out.println("\n=== CONSULTA DE PASSAGEIRO ===");

        if (!central.temPassageiros()) {
            System.out.println("Nenhum passageiro cadastrado no sistema.\n");
            return;
        }

        System.out.print("Digite o e-mail do passageiro: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("E-mail não pode estar vazio!\n");
            return;
        }

        Passageiro passageiro = central.recuperarPassageiroPeloEmail(email);

        if (passageiro != null) {
            System.out.println();
            System.out.println(central.exibirInformacoesPassageiro(passageiro));
            System.out.println();
        } else {
            System.out.println("Passageiro não encontrado com o e-mail: " + email + "\n");
        }
    }
}
