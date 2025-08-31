package br.com.pucgo.exercicio4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Classe principal com interface interativa para gerenciar passageiros
 * Permite cadastrar, listar e consultar passageiros com persistência em XML
 */
public class App {
    private static final String ARQUIVO_DADOS = "passageiros_sistema";
    private static CentralDeInformacoes central;
    private static Persistencia persistencia;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        persistencia = new Persistencia();

        // Inicializa o sistema recuperando dados salvos anteriormente
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
                case "S":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }

        // Salva os dados antes de sair
        salvarDados();
        System.out.println("Sistema encerrado. Até logo!");
        scanner.close();
    }

    /**
     * Inicializa o sistema tentando recuperar dados do arquivo XML
     */
    private static void inicializarSistema() {
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE PASSAGEIROS ===");
        System.out.println("Inicializando sistema...");

        central = persistencia.recuperarCentral(ARQUIVO_DADOS);

        if (central.temPassageiros()) {
            System.out.println("Dados recuperados com sucesso!");
            System.out.println("Passageiros encontrados: " + central.getTotalPassageiros());
        } else {
            System.out.println("Nenhum dado anterior encontrado. Iniciando sistema novo.");
        }
        System.out.println();
    }

    /**
     * Exibe o menu principal do sistema
     */
    private static void exibirMenu() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1 - Novo passageiro");
        System.out.println("2 - Listar todos os passageiros");
        System.out.println("3 - Exibir informações de um passageiro específico");
        System.out.println("S - Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Cadastra um novo passageiro no sistema
     */
    private static void cadastrarNovoPassageiro() {
        System.out.println("\n=== CADASTRO DE NOVO PASSAGEIRO ===");

        try {
            // Coleta dados do passageiro
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

            // Verifica se já existe passageiro com esse e-mail
            if (central.recuperarPassageiroPeloEmail(email) != null) {
                System.out.println("Erro: Já existe um passageiro com este e-mail!\n");
                return;
            }

            // Coleta sexo
            Sexo sexo = coletarSexo();
            if (sexo == null) {
                return; // Usuário cancelou ou erro
            }

            // Coleta data de nascimento
            Date dataNascimento = coletarDataNascimento();
            if (dataNascimento == null) {
                return; // Usuário cancelou ou erro
            }

            // Cria e tenta adicionar o passageiro
            Passageiro novoPassageiro = new Passageiro(nome, sexo, dataNascimento, email);
            boolean adicionado = central.adicionarPassageiro(novoPassageiro);

            if (adicionado) {
                System.out.println("Passageiro cadastrado com sucesso!");
                salvarDados();
                System.out.println("Dados salvos automaticamente.\n");
            } else {
                System.out.println("Erro: Passageiro deve ter pelo menos 18 anos de idade!\n");
            }

        } catch (Exception e) {
            System.out.println("Erro inesperado no cadastro: " + e.getMessage() + "\n");
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
        sdf.setLenient(false); // Não permite datas inválidas como 32/01/2000

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine().trim();

        try {
            Date data = sdf.parse(dataStr);

            // Verifica se a data não é futura
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

    /**
     * Salva os dados da central no arquivo XML
     */
    private static void salvarDados() {
        boolean sucesso = persistencia.salvarCentral(central, ARQUIVO_DADOS);
        if (!sucesso) {
            System.out.println("Aviso: Houve problema ao salvar os dados!");
        }
    }
}
