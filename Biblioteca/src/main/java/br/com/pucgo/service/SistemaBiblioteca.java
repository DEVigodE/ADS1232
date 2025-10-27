package br.com.pucgo.service;

import br.com.pucgo.model.*;

import java.util.*;

/**
 * Classe principal que gerencia todo o sistema da biblioteca
 * Utiliza diferentes estruturas de dados conforme especificado
 */
public class SistemaBiblioteca {

    // LISTA: Armazenar e listar entidades
    private List<Aluno> alunos;
    private List<Livro> livros;
    private List<Editora> editoras;
    private List<UnidadeBiblioteca> unidades;
    private List<Funcionario> funcionarios;
    private List<HistoricoEmprestimo> historicoEmprestimos;

    // PILHA: Controle dos livros devolvidos aguardando conferência
    private Stack<Livro> livrosAguardandoConferencia;

    // FILA: Organização da fila de atendimento
    private Queue<Aluno> filaAtendimento;

    // MAPA: Associação entre alunos e seus empréstimos ativos
    private Map<String, List<Emprestimo>> emprestimosAtivosPorRA;

    // CONJUNTO: Armazena as categorias únicas de livros
    private Set<String> categoriasUnicas;

    public SistemaBiblioteca() {
        this.alunos = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.editoras = new ArrayList<>();
        this.unidades = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();

        this.livrosAguardandoConferencia = new Stack<>();
        this.filaAtendimento = new LinkedList<>();
        this.emprestimosAtivosPorRA = new HashMap<>();
        this.categoriasUnicas = new HashSet<>();
    }

    // ============ MÉTODOS DE CADASTRO ============

    public void cadastrarAluno(Aluno aluno) {
        if (buscarAlunoPorRA(aluno.getRa()) == null) {
            alunos.add(aluno);
            System.out.println("Aluno cadastrado com sucesso: " + aluno.getNomeCompleto());
        } else {
            System.out.println("Aluno com RA " + aluno.getRa() + " já cadastrado!");
        }
    }

    public void cadastrarLivro(Livro livro) {
        if (buscarLivroPorISBN(livro.getIsbn()) == null) {
            livros.add(livro);
            categoriasUnicas.add(livro.getCategoria());
            System.out.println("Livro cadastrado com sucesso: " + livro.getTitulo());
        } else {
            System.out.println("Livro com ISBN " + livro.getIsbn() + " já cadastrado!");
        }
    }

    public void cadastrarEditora(Editora editora) {
        if (buscarEditoraPorCNPJ(editora.getCnpj()) == null) {
            editoras.add(editora);
            System.out.println("Editora cadastrada com sucesso: " + editora.getNome());
        } else {
            System.out.println("Editora com CNPJ " + editora.getCnpj() + " já cadastrada!");
        }
    }

    public void cadastrarUnidade(UnidadeBiblioteca unidade) {
        if (buscarUnidadePorCNPJ(unidade.getCnpj()) == null) {
            unidades.add(unidade);
            System.out.println("Unidade cadastrada com sucesso: " + unidade.getNomeUnidade());
        } else {
            System.out.println("Unidade com CNPJ " + unidade.getCnpj() + " já cadastrada!");
        }
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("Funcionário cadastrado com sucesso: " + funcionario.getNome());
    }

    // ============ MÉTODOS DE BUSCA ============

    public Aluno buscarAlunoPorRA(String ra) {
        for (Aluno aluno : alunos) {
            if (aluno.getRa().equals(ra)) {
                return aluno;
            }
        }
        return null;
    }

    public Livro buscarLivroPorISBN(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public Editora buscarEditoraPorCNPJ(String cnpj) {
        for (Editora editora : editoras) {
            if (editora.getCnpj().equals(cnpj)) {
                return editora;
            }
        }
        return null;
    }

    public UnidadeBiblioteca buscarUnidadePorCNPJ(String cnpj) {
        for (UnidadeBiblioteca unidade : unidades) {
            if (unidade.getCnpj().equals(cnpj)) {
                return unidade;
            }
        }
        return null;
    }

    // ============ FILA DE ATENDIMENTO ============

    public void adicionarAlunoNaFila(Aluno aluno) {
        filaAtendimento.offer(aluno);
        System.out.println("Aluno " + aluno.getNomeCompleto() + " adicionado à fila de atendimento.");
        System.out.println("Posição na fila: " + filaAtendimento.size());
    }

    public Aluno atenderProximoAluno() {
        Aluno aluno = filaAtendimento.poll();
        if (aluno != null) {
            System.out.println("Atendendo aluno: " + aluno.getNomeCompleto());
            return aluno;
        } else {
            System.out.println("Não há alunos na fila de atendimento.");
            return null;
        }
    }

    public void exibirFilaAtendimento() {
        if (filaAtendimento.isEmpty()) {
            System.out.println("Fila de atendimento vazia.");
        } else {
            System.out.println("\n=== FILA DE ATENDIMENTO ===");
            int posicao = 1;
            for (Aluno aluno : filaAtendimento) {
                System.out.println(posicao + ". " + aluno.getNomeCompleto() + " - RA: " + aluno.getRa());
                posicao++;
            }
        }
    }

    // ============ EMPRÉSTIMOS ============

    public Emprestimo realizarEmprestimo(String raAluno, List<String> isbnsLivros,
                                         Funcionario funcionario) {
        Aluno aluno = buscarAlunoPorRA(raAluno);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return null;
        }

        Emprestimo emprestimo = new Emprestimo(aluno, funcionario);

        for (String isbn : isbnsLivros) {
            Livro livro = buscarLivroPorISBN(isbn);
            if (livro != null && livro.isDisponivel()) {
                emprestimo.adicionarLivro(livro);
            } else {
                System.out.println("Livro com ISBN " + isbn + " não disponível!");
            }
        }

        if (!emprestimo.getLivros().isEmpty()) {
            // Adicionar ao mapa de empréstimos ativos
            if (!emprestimosAtivosPorRA.containsKey(raAluno)) {
                emprestimosAtivosPorRA.put(raAluno, new ArrayList<>());
            }
            emprestimosAtivosPorRA.get(raAluno).add(emprestimo);

            // Adicionar ao histórico
            for (Livro livro : emprestimo.getLivros()) {
                historicoEmprestimos.add(new HistoricoEmprestimo(emprestimo, livro));
            }

            System.out.println("Empréstimo realizado com sucesso! ID: " + emprestimo.getId());
            return emprestimo;
        } else {
            System.out.println("Nenhum livro pôde ser emprestado.");
            return null;
        }
    }

    public List<Emprestimo> consultarEmprestimosAtivos(String raAluno) {
        return emprestimosAtivosPorRA.getOrDefault(raAluno, new ArrayList<>());
    }

    // ============ DEVOLUÇÃO E CONFERÊNCIA ============

    public void devolverEmprestimo(int emprestimoId) {
        Emprestimo emprestimo = buscarEmprestimoPorId(emprestimoId);

        if (emprestimo != null && emprestimo.isAtivo()) {
            emprestimo.finalizarEmprestimo();

            // Adicionar livros na pilha de conferência
            for (Livro livro : emprestimo.getLivros()) {
                livrosAguardandoConferencia.push(livro);
                System.out.println("Livro '" + livro.getTitulo() + "' adicionado à pilha de conferência.");
            }

            // Remover do mapa de empréstimos ativos
            String ra = emprestimo.getAluno().getRa();
            if (emprestimosAtivosPorRA.containsKey(ra)) {
                emprestimosAtivosPorRA.get(ra).remove(emprestimo);
            }

            System.out.println("Devolução registrada com sucesso!");
            if (emprestimo.isEmAtraso()) {
                System.out.println("ATENÇÃO: Devolução com " + emprestimo.getDiasAtraso() + " dias de atraso!");
            }
        } else {
            System.out.println("Empréstimo não encontrado ou já finalizado.");
        }
    }

    public void conferirProximoLivro() {
        if (!livrosAguardandoConferencia.isEmpty()) {
            Livro livro = livrosAguardandoConferencia.pop();
            livro.devolver(); // Retornar ao acervo disponível
            System.out.println("Livro conferido e devolvido ao acervo: " + livro.getTitulo());
        } else {
            System.out.println("Não há livros aguardando conferência.");
        }
    }

    public void exibirLivrosAguardandoConferencia() {
        if (livrosAguardandoConferencia.isEmpty()) {
            System.out.println("Nenhum livro aguardando conferência.");
        } else {
            System.out.println("\n=== LIVROS AGUARDANDO CONFERÊNCIA ===");
            System.out.println("Total: " + livrosAguardandoConferencia.size());
            Stack<Livro> temp = new Stack<>();
            temp.addAll(livrosAguardandoConferencia);
            while (!temp.isEmpty()) {
                System.out.println("- " + temp.pop().getTitulo());
            }
        }
    }

    // ============ RENOVAÇÃO ESPECIAL ============

    public void renovarEmprestimo(int emprestimoId, int diasAdicionais,
                                   BibliotecarioSupervisor supervisor) {
        Emprestimo emprestimo = buscarEmprestimoPorId(emprestimoId);
        if (emprestimo != null && emprestimo.isAtivo()) {
            emprestimo.renovar(diasAdicionais, supervisor);
        } else {
            System.out.println("Empréstimo não encontrado ou já finalizado.");
        }
    }

    // ============ CONSULTAS E RELATÓRIOS ============

    public void listarAlunosComAtraso() {
        System.out.println("\n=== ALUNOS COM LIVROS EM ATRASO ===");
        boolean encontrou = false;

        for (Map.Entry<String, List<Emprestimo>> entry : emprestimosAtivosPorRA.entrySet()) {
            for (Emprestimo emprestimo : entry.getValue()) {
                if (emprestimo.isEmAtraso()) {
                    System.out.println(emprestimo);
                    System.out.println("   Dias de atraso: " + emprestimo.getDiasAtraso());
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aluno com atraso.");
        }
    }

    public void listarLivrosMaisSolicitados() {
        System.out.println("\n=== LIVROS MAIS SOLICITADOS ===");
        Map<String, Integer> contadorLivros = new HashMap<>();

        for (HistoricoEmprestimo historico : historicoEmprestimos) {
            String isbn = historico.getIsbnLivro();
            contadorLivros.put(isbn, contadorLivros.getOrDefault(isbn, 0) + 1);
        }

        // Ordenar por frequência
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(contadorLivros.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (int i = 0; i < Math.min(10, lista.size()); i++) {
            Map.Entry<String, Integer> entry = lista.get(i);
            Livro livro = buscarLivroPorISBN(entry.getKey());
            if (livro != null) {
                System.out.println((i + 1) + ". " + livro.getTitulo() +
                        " - " + entry.getValue() + " empréstimos");
            }
        }
    }

    public void listarCategorias() {
        System.out.println("\n=== CATEGORIAS DE LIVROS ===");
        System.out.println("Total de categorias únicas: " + categoriasUnicas.size());
        for (String categoria : categoriasUnicas) {
            System.out.println("- " + categoria);
        }
    }

    public void listarTodosAlunos() {
        System.out.println("\n=== TODOS OS ALUNOS CADASTRADOS ===");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    public void listarTodosLivros() {
        System.out.println("\n=== TODOS OS LIVROS CADASTRADOS ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public void listarTodasUnidades() {
        System.out.println("\n=== TODAS AS UNIDADES ===");
        for (UnidadeBiblioteca unidade : unidades) {
            System.out.println(unidade);
        }
    }

    // ============ MÉTODOS AUXILIARES ============

    private Emprestimo buscarEmprestimoPorId(int id) {
        for (List<Emprestimo> emprestimos : emprestimosAtivosPorRA.values()) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getId() == id) {
                    return emprestimo;
                }
            }
        }
        return null;
    }

    // ============ GETTERS ============

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Editora> getEditoras() {
        return editoras;
    }

    public List<UnidadeBiblioteca> getUnidades() {
        return unidades;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Set<String> getCategoriasUnicas() {
        return categoriasUnicas;
    }
}


