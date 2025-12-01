package br.com.pucgo;

import java.util.*;

/**
 * Implementação de uma Agenda Telefônica usando TreeMap
 * Demonstra as vantagens de manter dados ordenados
 *
 * Complexidade:
 * - Inserção: O(log n)
 * - Busca: O(log n)
 * - Listagem ordenada: O(n)
 */
public class AgendaTelefonica {

    // TreeMap mantém os contatos ordenados por nome
    private TreeMap<String, Contato> agenda;

    public AgendaTelefonica() {
        this.agenda = new TreeMap<>();
    }

    /**
     * Classe interna para representar um contato
     */
    public static class Contato {
        private String nome;
        private String telefone;
        private String email;

        public Contato(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        @Override
        public String toString() {
            return String.format("%-20s | %-15s | %s", nome, telefone, email);
        }

        public String getNome() { return nome; }
        public String getTelefone() { return telefone; }
        public String getEmail() { return email; }
    }

    /**
     * Adiciona um contato à agenda
     */
    public void adicionarContato(String nome, String telefone, String email) {
        Contato contato = new Contato(nome, telefone, email);
        agenda.put(nome.toLowerCase(), contato);
    }

    /**
     * Busca um contato por nome exato
     */
    public Contato buscarContato(String nome) {
        return agenda.get(nome.toLowerCase());
    }

    /**
     * Remove um contato
     */
    public boolean removerContato(String nome) {
        return agenda.remove(nome.toLowerCase()) != null;
    }

    /**
     * Lista todos os contatos em ordem alfabética
     */
    public void listarContatos() {
        System.out.println("\n=== Lista de Contatos (Ordem Alfabética) ===");
        System.out.println(String.format("%-20s | %-15s | %s", "Nome", "Telefone", "Email"));
        StringBuilder linha = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            linha.append("-");
        }
        System.out.println(linha.toString());

        for (Contato contato : agenda.values()) {
            System.out.println(contato);
        }
    }

    /**
     * Busca contatos por prefixo (ex: todos que começam com "Ana")
     * Aproveitando a estrutura ordenada do TreeMap
     */
    public List<Contato> buscarPorPrefixo(String prefixo) {
        prefixo = prefixo.toLowerCase();
        List<Contato> resultados = new ArrayList<>();

        // Usar subMap para eficiência
        String fim = prefixo + Character.MAX_VALUE;
        SortedMap<String, Contato> subMapa = agenda.subMap(prefixo, fim);

        resultados.addAll(subMapa.values());
        return resultados;
    }

    /**
     * Lista contatos em um intervalo (ex: de "A" até "M")
     */
    public void listarIntervalo(String inicio, String fim) {
        System.out.println("\n=== Contatos de " + inicio + " até " + fim + " ===");

        SortedMap<String, Contato> subMapa = agenda.subMap(
            inicio.toLowerCase(),
            fim.toLowerCase() + "z"
        );

        for (Contato contato : subMapa.values()) {
            System.out.println(contato);
        }
    }

    /**
     * Retorna o primeiro e último contato alfabeticamente
     */
    public void mostrarExtremos() {
        if (!agenda.isEmpty()) {
            System.out.println("\nPrimeiro contato: " + agenda.firstEntry().getValue());
            System.out.println("Último contato: " + agenda.lastEntry().getValue());
        }
    }

    /**
     * Retorna estatísticas
     */
    public void imprimirEstatisticas() {
        System.out.println("\n=== Estatísticas ===");
        System.out.println("Total de contatos: " + agenda.size());

        if (!agenda.isEmpty()) {
            System.out.println("Primeiro nome: " + agenda.firstKey());
            System.out.println("Último nome: " + agenda.lastKey());
        }
    }

    /**
     * Exemplo de uso
     */
    public static void main(String[] args) {
        AgendaTelefonica agenda = new AgendaTelefonica();

        System.out.println("=== DEMONSTRAÇÃO: Agenda Telefônica com TreeMap ===\n");

        // Adicionando contatos (em ordem aleatória)
        System.out.println("Adicionando contatos...");
        agenda.adicionarContato("Maria Silva", "(11) 98765-4321", "maria@email.com");
        agenda.adicionarContato("João Santos", "(11) 91234-5678", "joao@email.com");
        agenda.adicionarContato("Ana Costa", "(21) 99876-5432", "ana@email.com");
        agenda.adicionarContato("Pedro Oliveira", "(31) 98888-7777", "pedro@email.com");
        agenda.adicionarContato("Carlos Souza", "(41) 97777-6666", "carlos@email.com");
        agenda.adicionarContato("Ana Paula", "(51) 96666-5555", "anapaula@email.com");
        agenda.adicionarContato("Bruno Lima", "(61) 95555-4444", "bruno@email.com");

        // Listar todos (automaticamente ordenado)
        agenda.listarContatos();

        // Buscar contato específico
        System.out.println("\n1. Buscando 'Maria Silva':");
        Contato contato = agenda.buscarContato("Maria Silva");
        if (contato != null) {
            System.out.println("Encontrado: " + contato);
        }

        // Buscar por prefixo
        System.out.println("\n2. Buscando contatos que começam com 'Ana':");
        List<Contato> resultados = agenda.buscarPorPrefixo("Ana");
        for (Contato c : resultados) {
            System.out.println("  " + c);
        }

        // Listar intervalo
        agenda.listarIntervalo("B", "M");

        // Mostrar extremos
        agenda.mostrarExtremos();

        // Estatísticas
        agenda.imprimirEstatisticas();

        System.out.println("\n=== Vantagens do TreeMap para Agenda ===");
        System.out.println("- Manutenção automática da ordem alfabética");
        System.out.println("- Busca eficiente por prefixo");
        System.out.println("- Navegação por intervalos");
        System.out.println("- Operações de primeiro/último elemento");
    }
}

