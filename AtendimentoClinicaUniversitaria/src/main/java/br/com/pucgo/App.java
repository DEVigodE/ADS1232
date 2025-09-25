package br.com.pucgo;

/**
 * Sistema de Atendimento da Clínica Universitária
 * Demonstra o uso de Fila (Queue) e Lista (List) para gerenciar pacientes
 * Inclui teste de diferentes implementações de fila
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("=== SISTEMA DE ATENDIMENTO - CLÍNICA UNIVERSITÁRIA ===\n");

        // Teste da implementação original
        testeFilaOriginal();

        // Teste da fila circular
        testeFilaCircular();

        // Teste da fila encadeada
        testeFilaEncadeada();

        // Comparação de performance
        comparacaoPerformance();
    }

    /**
     * Método utilitário para repetir uma string (compatível com Java 8+)
     */
    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static void testeFilaOriginal() {
        System.out.println("=== TESTE 1: FILA ORIGINAL (ArrayList) ===");
        System.out.println(repeat("-", 50));

        // Criando as estruturas de dados
        FilaDePacientes fila = new FilaDePacientes();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        // 1. Inserção de 5 pacientes na fila
        System.out.println("1. ADICIONANDO PACIENTES NA FILA:");
        System.out.println(repeat("-", 40));

        fila.adicionarPaciente(new Paciente("João Silva", 45, "123.456.789-01"));
        fila.adicionarPaciente(new Paciente("Maria Santos", 65, "234.567.890-12")); // Prioridade (idosa)
        fila.adicionarPaciente(new Paciente("Pedro Oliveira", 30, "345.678.901-23"));
        fila.adicionarPaciente(new Paciente("Ana Costa", 72, "456.789.012-34")); // Prioridade (idosa)
        fila.adicionarPaciente(new Paciente("Carlos Mendes", 28, "567.890.123-45", true)); // Prioridade (caso urgente)

        // Exibindo estado inicial da fila
        fila.exibirFila();

        // 2. Atendimento de 3 pacientes
        System.out.println("2. ATENDENDO PACIENTES:");
        System.out.println(repeat("-", 40));

        for (int i = 1; i <= 3; i++) {
            Paciente proximoPaciente = fila.chamarProximoPaciente();
            if (proximoPaciente != null) {
                System.out.println("Atendendo paciente " + i + ": " + proximoPaciente.getNome());
                atendidos.adicionarAtendido(proximoPaciente);
                System.out.println();
            }
        }

        // 3. Exibição do próximo paciente a ser atendido
        System.out.println("3. PRÓXIMO PACIENTE A SER ATENDIDO:");
        System.out.println(repeat("-", 40));

        Paciente proximo = fila.proximoPaciente();
        if (proximo != null) {
            System.out.println("Próximo: " + proximo.getNome() +
                             " (Idade: " + proximo.getIdade() +
                             ", Prioridade: " + (proximo.temPrioridade() ? "SIM" : "NÃO") + ")");
        } else {
            System.out.println("Não há pacientes na fila.");
        }
        System.out.println();

        // 4. Exibição da lista completa de pacientes atendidos
        System.out.println("4. LISTA DE PACIENTES ATENDIDOS:");
        System.out.println(repeat("-", 40));
        atendidos.exibirAtendidos();

        // Estado atual da fila após atendimentos
        System.out.println("5. ESTADO ATUAL DA FILA:");
        System.out.println(repeat("-", 40));
        fila.exibirFila();

        // Demonstração de busca na lista de atendidos
        System.out.println("6. DEMONSTRAÇÃO DE BUSCA:");
        System.out.println(repeat("-", 40));

        String cpfBusca = "234.567.890-12";
        if (atendidos.foiAtendido(cpfBusca)) {
            Paciente encontrado = atendidos.buscarAtendido(cpfBusca);
            System.out.println("✓ Paciente com CPF " + cpfBusca + " já foi atendido: " + encontrado.getNome());
        } else {
            System.out.println("✗ Paciente com CPF " + cpfBusca + " ainda não foi atendido.");
        }

        cpfBusca = "345.678.901-23";
        if (atendidos.foiAtendido(cpfBusca)) {
            Paciente encontrado = atendidos.buscarAtendido(cpfBusca);
            System.out.println("✓ Paciente com CPF " + cpfBusca + " já foi atendido: " + encontrado.getNome());
        } else {
            System.out.println("✗ Paciente com CPF " + cpfBusca + " ainda não foi atendido.");
        }
        System.out.println();

        // Desafio Extra: Estatísticas
        System.out.println("7. DESAFIO EXTRA - ESTATÍSTICAS:");
        System.out.println(repeat("-", 40));
        atendidos.exibirEstatisticas();

        // Simulação de mais atendimentos para completar a fila
        System.out.println("8. FINALIZANDO ATENDIMENTOS:");
        System.out.println(repeat("-", 40));

        while (!fila.isEmpty()) {
            Paciente paciente = fila.chamarProximoPaciente();
            if (paciente != null) {
                System.out.println("Atendendo: " + paciente.getNome());
                atendidos.adicionarAtendido(paciente);
            }
        }

        // Estatísticas finais
        System.out.println("\n9. ESTATÍSTICAS FINAIS:");
        System.out.println(repeat("-", 40));
        atendidos.exibirEstatisticas();

        System.out.println("\n=== TESTE 1 FINALIZADO ===\n");
    }

    private static void testeFilaCircular() {
        System.out.println("=== TESTE 2: FILA CIRCULAR (Array Fixo) ===");
        System.out.println(repeat("-", 50));

        FilaCircularDePacientes filaCircular = new FilaCircularDePacientes(10);
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        System.out.println("1. ADICIONANDO PACIENTES NA FILA CIRCULAR:");
        System.out.println(repeat("-", 45));

        // Criando pacientes
        Paciente[] pacientes = {
            new Paciente("Alice Ferreira", 35, "111.222.333-44"),
            new Paciente("Bruno Lima", 28, "222.333.444-55"),
            new Paciente("Carla Souza", 42, "333.444.555-66"),
            new Paciente("Diego Pereira", 31, "444.555.666-77"),
            new Paciente("Elena Martins", 39, "555.666.777-88")
        };

        // Adicionando pacientes
        for (Paciente p : pacientes) {
            if (filaCircular.enqueue(p)) {
                System.out.println("✓ Adicionado: " + p.getNome());
            } else {
                System.out.println("✗ Fila cheia! Não foi possível adicionar: " + p.getNome());
            }
        }

        System.out.println("\nEstado da fila: Tamanho = " + filaCircular.size());
        System.out.println("Próximo paciente: " + (filaCircular.peek() != null ? filaCircular.peek().getNome() : "Nenhum"));

        System.out.println("\n2. ATENDENDO PACIENTES (FILA CIRCULAR):");
        System.out.println(repeat("-", 45));

        for (int i = 1; i <= 3; i++) {
            Paciente paciente = filaCircular.dequeue();
            if (paciente != null) {
                System.out.println("Atendendo paciente " + i + ": " + paciente.getNome());
                atendidos.adicionarAtendido(paciente);
            }
        }

        System.out.println("\nEstado da fila após atendimentos:");
        System.out.println("Tamanho restante: " + filaCircular.size());
        System.out.println("Próximo paciente: " + (filaCircular.peek() != null ? filaCircular.peek().getNome() : "Nenhum"));

        System.out.println("\n3. ADICIONANDO MAIS PACIENTES (TESTE CIRCULARIDADE):");
        System.out.println(repeat("-", 50));

        Paciente[] novosPacientes = {
            new Paciente("Felipe Santos", 33, "666.777.888-99"),
            new Paciente("Gabriela Costa", 29, "777.888.999-00")
        };

        for (Paciente p : novosPacientes) {
            if (filaCircular.enqueue(p)) {
                System.out.println("✓ Adicionado: " + p.getNome() + " (aproveitando espaço circular)");
            }
        }

        System.out.println("\nTamanho final da fila: " + filaCircular.size());

        // Finalizando atendimentos
        while (!filaCircular.isEmpty()) {
            Paciente paciente = filaCircular.dequeue();
            if (paciente != null) {
                System.out.println("Finalizando atendimento: " + paciente.getNome());
                atendidos.adicionarAtendido(paciente);
            }
        }

        System.out.println("\n4. ESTATÍSTICAS FILA CIRCULAR:");
        System.out.println(repeat("-", 35));
        atendidos.exibirEstatisticas();

        System.out.println("\n=== TESTE 2 FINALIZADO ===\n");
    }

    private static void testeFilaEncadeada() {
        System.out.println("=== TESTE 3: FILA ENCADEADA (Linked List) ===");
        System.out.println(repeat("-", 50));

        FilaEncadeadaDePacientes filaEncadeada = new FilaEncadeadaDePacientes();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        System.out.println("1. ADICIONANDO PACIENTES NA FILA ENCADEADA:");
        System.out.println(repeat("-", 47));

        // Criando pacientes
        Paciente[] pacientes = {
            new Paciente("Hugo Oliveira", 44, "888.999.000-11"),
            new Paciente("Isabela Rocha", 37, "999.000.111-22"),
            new Paciente("Joaquim Silva", 52, "000.111.222-33"),
            new Paciente("Karina Alves", 26, "111.222.333-44"),
            new Paciente("Leonardo Dias", 41, "222.333.444-55")
        };

        // Adicionando pacientes
        for (Paciente p : pacientes) {
            filaEncadeada.enqueue(p);
            System.out.println("✓ Adicionado: " + p.getNome());
        }

        System.out.println("\nEstado da fila: Tamanho = " + filaEncadeada.size());
        System.out.println("Próximo paciente: " + (filaEncadeada.peek() != null ? filaEncadeada.peek().getNome() : "Nenhum"));

        System.out.println("\n2. ATENDENDO PACIENTES (FILA ENCADEADA):");
        System.out.println(repeat("-", 47));

        for (int i = 1; i <= 3; i++) {
            Paciente paciente = filaEncadeada.dequeue();
            if (paciente != null) {
                System.out.println("Atendendo paciente " + i + ": " + paciente.getNome());
                atendidos.adicionarAtendido(paciente);
            }
        }

        System.out.println("\nEstado da fila após atendimentos:");
        System.out.println("Tamanho restante: " + filaEncadeada.size());
        System.out.println("Próximo paciente: " + (filaEncadeada.peek() != null ? filaEncadeada.peek().getNome() : "Nenhum"));

        System.out.println("\n3. ADICIONANDO MAIS PACIENTES (CAPACIDADE DINÂMICA):");
        System.out.println(repeat("-", 55));

        Paciente[] novosPacientes = {
            new Paciente("Mariana Gomes", 34, "333.444.555-66"),
            new Paciente("Nicolas Barbosa", 38, "444.555.666-77"),
            new Paciente("Olívia Fernandes", 43, "555.666.777-88")
        };

        for (Paciente p : novosPacientes) {
            filaEncadeada.enqueue(p);
            System.out.println("✓ Adicionado: " + p.getNome() + " (sem limite de capacidade)");
        }

        System.out.println("\nTamanho final da fila: " + filaEncadeada.size());

        // Finalizando atendimentos
        while (!filaEncadeada.isEmpty()) {
            Paciente paciente = filaEncadeada.dequeue();
            if (paciente != null) {
                System.out.println("Finalizando atendimento: " + paciente.getNome());
                atendidos.adicionarAtendido(paciente);
            }
        }

        System.out.println("\n4. ESTATÍSTICAS FILA ENCADEADA:");
        System.out.println(repeat("-", 38));
        atendidos.exibirEstatisticas();

        System.out.println("\n=== TESTE 3 FINALIZADO ===\n");
    }

    private static void comparacaoPerformance() {
        System.out.println("=== COMPARAÇÃO DAS IMPLEMENTAÇÕES ===");
        System.out.println(repeat("-", 50));

        System.out.println("CARACTERÍSTICAS DAS IMPLEMENTAÇÕES:");
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ IMPLEMENTAÇÃO        │ INSERÇÃO │ REMOÇÃO │ MEMÓRIA │ CAPACIDADE     ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Fila Original        │   O(1)   │  O(n)   │ Dinâmica│ Ilimitada      ║");
        System.out.println("║ (ArrayList)          │          │ (shift) │         │                ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Fila Circular        │   O(1)   │  O(1)   │  Fixa   │ Limitada       ║");
        System.out.println("║ (Array Fixo)         │          │         │         │ (definida)     ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Fila Encadeada       │   O(1)   │  O(1)   │ Dinâmica│ Ilimitada      ║");
        System.out.println("║ (Linked List)        │          │         │         │ (só memória)   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");

        System.out.println("\nVANTAGENS E DESVANTAGENS:");
        System.out.println(repeat("-", 30));

        System.out.println("\n🏥 FILA ORIGINAL (ArrayList):");
        System.out.println("  ✓ Fácil implementação");
        System.out.println("  ✓ Suporte a prioridade");
        System.out.println("  ✗ Remoção custosa O(n) - desloca elementos");
        System.out.println("  ✗ Ineficiente para muitas operações de dequeue");

        System.out.println("\n🔄 FILA CIRCULAR (Array Fixo):");
        System.out.println("  ✓ Inserção e remoção O(1)");
        System.out.println("  ✓ Reutiliza espaço do array");
        System.out.println("  ✓ Baixo overhead de memória");
        System.out.println("  ✗ Capacidade limitada");
        System.out.println("  ✗ Precisa dimensionamento correto");

        System.out.println("\n🔗 FILA ENCADEADA (Linked List):");
        System.out.println("  ✓ Inserção e remoção O(1)");
        System.out.println("  ✓ Capacidade dinâmica ilimitada");
        System.out.println("  ✓ Flexível para crescimento");
        System.out.println("  ✗ Overhead de ponteiros");
        System.out.println("  ✗ Localidade de memória pior");

        System.out.println("\n📊 RECOMENDAÇÃO DE USO:");
        System.out.println(repeat("-", 25));
        System.out.println("• Fila Circular: Para sistemas com capacidade conhecida e muitas operações");
        System.out.println("• Fila Encadeada: Para sistemas com demanda variável e crescimento dinâmico");
        System.out.println("• Fila Original: Para prototipagem rápida ou quando prioridade é necessária");

        System.out.println("\n=== SISTEMA FINALIZADO ===");
    }
}
