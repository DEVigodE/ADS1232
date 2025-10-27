package br.com.pucgo;

import br.com.pucgo.model.*;
import br.com.pucgo.service.SistemaBiblioteca;

import java.util.Arrays;

/**
 * Sistema de Gerenciamento de Biblioteca Universitária
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("=================================================");
        System.out.println("  SISTEMA DE GERENCIAMENTO DE BIBLIOTECA");
        System.out.println("=================================================\n");

        // Inicializar o sistema
        SistemaBiblioteca sistema = new SistemaBiblioteca();

        // ============ CADASTRAR EDITORAS ============
        System.out.println("\n--- CADASTRANDO EDITORAS ---");
        Editora editora1 = new Editora("12.345.678/0001-90", "Editora Acadêmica", "(62) 3333-4444", "contato@academica.com.br");
        Editora editora2 = new Editora("98.765.432/0001-10", "Editora Universitária", "(62) 3555-6666", "info@universitaria.com.br");
        Editora editora3 = new Editora("11.222.333/0001-44", "Editora Técnica", "(11) 4444-5555", "vendas@tecnica.com.br");

        sistema.cadastrarEditora(editora1);
        sistema.cadastrarEditora(editora2);
        sistema.cadastrarEditora(editora3);

        // ============ CADASTRAR LIVROS ============
        System.out.println("\n--- CADASTRANDO LIVROS ---");
        Livro livro1 = new Livro("978-3-16-148410-0", "Estruturas de Dados em Java", "João Silva", 2020, "Programação", 5, editora1);
        Livro livro2 = new Livro("978-0-13-468599-1", "Algoritmos e Lógica", "Maria Santos", 2019, "Programação", 3, editora2);
        Livro livro3 = new Livro("978-0-321-57351-3", "Banco de Dados Moderno", "Carlos Pereira", 2021, "Banco de Dados", 4, editora3);
        Livro livro4 = new Livro("978-0-134-68599-2", "Engenharia de Software", "Ana Costa", 2022, "Engenharia", 6, editora1);
        Livro livro5 = new Livro("978-0-596-52068-7", "Redes de Computadores", "Pedro Lima", 2020, "Redes", 2, editora2);

        sistema.cadastrarLivro(livro1);
        sistema.cadastrarLivro(livro2);
        sistema.cadastrarLivro(livro3);
        sistema.cadastrarLivro(livro4);
        sistema.cadastrarLivro(livro5);

        // ============ CADASTRAR UNIDADES ============
        System.out.println("\n--- CADASTRANDO UNIDADES DA BIBLIOTECA ---");
        UnidadeBiblioteca unidade1 = new UnidadeBiblioteca("10.111.222/0001-33", "Biblioteca Central", "Prof. Roberto Alves", "(62) 3200-1000");
        Endereco endUnidade1 = new Endereco("Av. Universitária", "1000", "Setor Universitário", "Goiânia", "GO", "74605-010");
        unidade1.setEndereco(endUnidade1);

        UnidadeBiblioteca unidade2 = new UnidadeBiblioteca("10.111.222/0002-44", "Biblioteca Campus II", "Dra. Fernanda Rocha", "(62) 3200-2000");

        sistema.cadastrarUnidade(unidade1);
        sistema.cadastrarUnidade(unidade2);

        // ============ CADASTRAR FUNCIONÁRIOS ============
        System.out.println("\n--- CADASTRANDO FUNCIONÁRIOS ---");
        Funcionario func1 = new Funcionario("F001", "Marcos Oliveira", "Atendente", "(62) 98888-7777", "marcos@biblioteca.edu.br");
        BibliotecarioSupervisor supervisor = new BibliotecarioSupervisor("S001", "Dra. Juliana Martins", "(62) 98777-6666", "juliana@biblioteca.edu.br", "CRB-001234");

        sistema.cadastrarFuncionario(func1);
        sistema.cadastrarFuncionario(supervisor);

        // ============ CADASTRAR ALUNOS ============
        System.out.println("\n--- CADASTRANDO ALUNOS ---");
        Aluno aluno1 = new Aluno("202301001", "Lucas Henrique Souza", "Ciência da Computação", "(62) 99999-1111", "lucas.souza@aluno.pucgo.edu.br");
        Endereco endAluno1 = new Endereco("Rua das Flores", "123", "Setor Central", "Goiânia", "GO", "74000-000");
        aluno1.setEndereco(endAluno1);

        Aluno aluno2 = new Aluno("202301002", "Beatriz Ferreira Costa", "Sistemas de Informação", "(62) 99999-2222", "beatriz.costa@aluno.pucgo.edu.br");
        Aluno aluno3 = new Aluno("202301003", "Rafael Moreira Santos", "Engenharia de Software", "(62) 99999-3333", "rafael.santos@aluno.pucgo.edu.br");

        sistema.cadastrarAluno(aluno1);
        sistema.cadastrarAluno(aluno2);
        sistema.cadastrarAluno(aluno3);

        // ============ DEMONSTRAÇÃO: FILA DE ATENDIMENTO ============
        System.out.println("\n\n=== DEMONSTRAÇÃO: FILA DE ATENDIMENTO (Queue) ===");
        sistema.adicionarAlunoNaFila(aluno1);
        sistema.adicionarAlunoNaFila(aluno2);
        sistema.adicionarAlunoNaFila(aluno3);

        sistema.exibirFilaAtendimento();

        // ============ REALIZAR EMPRÉSTIMOS ============
        System.out.println("\n\n=== REALIZANDO EMPRÉSTIMOS ===");

        // Atender primeiro aluno da fila
        Aluno alunoAtendido1 = sistema.atenderProximoAluno();
        if (alunoAtendido1 != null) {
            sistema.realizarEmprestimo(
                alunoAtendido1.getRa(),
                Arrays.asList("978-3-16-148410-0", "978-0-321-57351-3"),
                func1
            );
        }

        // Atender segundo aluno
        Aluno alunoAtendido2 = sistema.atenderProximoAluno();
        if (alunoAtendido2 != null) {
            sistema.realizarEmprestimo(
                alunoAtendido2.getRa(),
                Arrays.asList("978-0-13-468599-1", "978-0-134-68599-2"),
                func1
            );
        }

        // Atender terceiro aluno
        Aluno alunoAtendido3 = sistema.atenderProximoAluno();
        if (alunoAtendido3 != null) {
            sistema.realizarEmprestimo(
                alunoAtendido3.getRa(),
                Arrays.asList("978-0-596-52068-7"),
                func1
            );
        }

        // ============ CONSULTAR EMPRÉSTIMOS ATIVOS (HashMap) ============
        System.out.println("\n\n=== CONSULTANDO EMPRÉSTIMOS ATIVOS (HashMap) ===");
        System.out.println("\nEmpréstimos do aluno " + aluno1.getNomeCompleto() + ":");
        for (Emprestimo emp : sistema.consultarEmprestimosAtivos(aluno1.getRa())) {
            System.out.println(emp);
        }

        // ============ DEMONSTRAÇÃO: CATEGORIAS ÚNICAS (HashSet) ============
        System.out.println("\n\n=== CATEGORIAS ÚNICAS DE LIVROS (HashSet) ===");
        sistema.listarCategorias();

        // ============ RENOVAÇÃO ESPECIAL ============
        System.out.println("\n\n=== RENOVAÇÃO ESPECIAL COM AUTORIZAÇÃO DO SUPERVISOR ===");
        sistema.renovarEmprestimo(1, 15, supervisor);

        // ============ DEVOLUÇÃO E PILHA DE CONFERÊNCIA ============
        System.out.println("\n\n=== REALIZANDO DEVOLUÇÕES ===");
        sistema.devolverEmprestimo(2); // Devolver empréstimo ID 2

        System.out.println("\n\n=== LIVROS NA PILHA DE CONFERÊNCIA (Stack) ===");
        sistema.exibirLivrosAguardandoConferencia();

        System.out.println("\n--- CONFERINDO LIVROS ---");
        sistema.conferirProximoLivro();
        sistema.conferirProximoLivro();

        sistema.exibirLivrosAguardandoConferencia();

        // ============ RELATÓRIOS ============
        System.out.println("\n\n=== RELATÓRIOS DO SISTEMA ===");

        sistema.listarTodosAlunos();
        sistema.listarTodosLivros();
        sistema.listarTodasUnidades();

        sistema.listarAlunosComAtraso();
        sistema.listarLivrosMaisSolicitados();

        // ============ RESUMO DAS ESTRUTURAS DE DADOS ============
        System.out.println("\n\n=================================================");
        System.out.println("  ESTRUTURAS DE DADOS UTILIZADAS");
        System.out.println("=================================================");
        System.out.println("✓ LISTA (ArrayList/LinkedList): Alunos, Livros, Editoras, Unidades");
        System.out.println("✓ PILHA (Stack): Livros aguardando conferência");
        System.out.println("✓ FILA (Queue): Fila de atendimento de alunos");
        System.out.println("✓ MAPA (HashMap): Empréstimos ativos por RA");
        System.out.println("✓ CONJUNTO (HashSet): Categorias únicas de livros");
        System.out.println("=================================================\n");

        System.out.println("Sistema finalizado com sucesso!");
    }
}
