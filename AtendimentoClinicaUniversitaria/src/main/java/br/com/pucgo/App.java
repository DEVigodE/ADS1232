package br.com.pucgo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        // Novo exercício: Fila de Prioridade e Complexidade
        testePrioridadeEComplexidade();
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

    /**
     * NOVO EXERCÍCIO: Priorização e Complexidade
     * Trabalha com Fila de Prioridade e análise de complexidade algorítmica
     */
    private static void testePrioridadeEComplexidade() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("=== NOVO EXERCÍCIO: PRIORIZAÇÃO E COMPLEXIDADE ===");
        System.out.println(repeat("=", 60));

        // Teste 1: Fila de Prioridade com ArrayList
        testePrioridadeArrayList();

        // Teste 2: Fila de Prioridade Encadeada
        testePrioridadeEncadeada();

        // Análise comparativa
        analiseComparativaComplexidade();
    }

    private static void testePrioridadeArrayList() {
        System.out.println("\n=== TESTE 4: FILA DE PRIORIDADE AVANÇADA (ArrayList) ===");
        System.out.println(repeat("-", 60));

        FilaPrioridadeAvancada filaPrioridade = new FilaPrioridadeAvancada();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        System.out.println("1. CRIANDO SIMULAÇÃO COM 10 PACIENTES (IDADES E URGÊNCIAS VARIADAS):");
        System.out.println(repeat("-", 70));

        // Criando 10 pacientes com diferentes prioridades
        Paciente[] pacientesSimulacao = {
            new Paciente("Ana Silva", 45, "111.111.111-11"),                    // Normal
            new Paciente("Carlos Urgente", 35, "222.222.222-22", false, true), // Urgente
            new Paciente("Maria Idosa", 75, "333.333.333-33"),                 // Idosa
            new Paciente("João Normal", 28, "444.444.444-44"),                 // Normal
            new Paciente("Pedro Emergência", 42, "555.555.555-55", false, true), // Urgente
            new Paciente("Rosa Sênior", 68, "666.666.666-66"),                 // Idosa
            new Paciente("Lucas Jovem", 22, "777.777.777-77"),                 // Normal
            new Paciente("Sofia Crítica", 55, "888.888.888-88", false, true),  // Urgente
            new Paciente("Alberto Aposentado", 82, "999.999.999-99"),          // Idoso
            new Paciente("Carla Normal", 38, "000.000.000-00")                 // Normal
        };

        // Adicionando pacientes (simulando chegadas aleatórias)
        for (Paciente p : pacientesSimulacao) {
            filaPrioridade.adicionarPaciente(p);
        }

        System.out.println("\n2. ESTADO INICIAL DA FILA:");
        filaPrioridade.exibirEstadoCompleto();
        filaPrioridade.exibirDistribuicaoPrioridades();

        System.out.println("\n3. ATENDENDO TODOS OS PACIENTES (OBSERVAR ORDEM DE PRIORIDADE):");
        System.out.println(repeat("-", 65));

        int contador = 1;
        while (!filaPrioridade.isEmpty()) {
            Paciente paciente = filaPrioridade.chamarProximoPaciente();
            if (paciente != null) {
                System.out.println("Atendimento #" + contador + ": " + paciente.getNome() +
                                 " (Idade: " + paciente.getIdade() +
                                 ", Urgente: " + (paciente.isUrgente() ? "SIM" : "NÃO") + ")");
                atendidos.adicionarAtendido(paciente);
                contador++;
            }
        }

        System.out.println("\n4. ESTATÍSTICAS FINAIS:");
        System.out.println(repeat("-", 25));
        atendidos.exibirEstatisticas();
        filaPrioridade.exibirEstatisticasPerformance();

        System.out.println("\n=== TESTE 4 FINALIZADO ===\n");
    }

    private static void testePrioridadeEncadeada() {
        System.out.println("=== TESTE 5: FILA DE PRIORIDADE ENCADEADA ===");
        System.out.println(repeat("-", 50));

        FilaPrioridadeEncadeada filaEncadeada = new FilaPrioridadeEncadeada();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        System.out.println("1. ADICIONANDO OS MESMOS 10 PACIENTES NA FILA ENCADEADA:");
        System.out.println(repeat("-", 60));

        // Mesmos pacientes para comparação justa
        Paciente[] pacientesSimulacao = {
            new Paciente("Ana Silva", 45, "111.111.111-11"),                    // Normal
            new Paciente("Carlos Urgente", 35, "222.222.222-22", false, true), // Urgente
            new Paciente("Maria Idosa", 75, "333.333.333-33"),                 // Idosa
            new Paciente("João Normal", 28, "444.444.444-44"),                 // Normal
            new Paciente("Pedro Emergência", 42, "555.555.555-55", false, true), // Urgente
            new Paciente("Rosa Sênior", 68, "666.666.666-66"),                 // Idosa
            new Paciente("Lucas Jovem", 22, "777.777.777-77"),                 // Normal
            new Paciente("Sofia Crítica", 55, "888.888.888-88", false, true),  // Urgente
            new Paciente("Alberto Aposentado", 82, "999.999.999-99"),          // Idoso
            new Paciente("Carla Normal", 38, "000.000.000-00")                 // Normal
        };

        // Adicionando na fila encadeada
        for (Paciente p : pacientesSimulacao) {
            filaEncadeada.adicionarPaciente(p);
        }

        System.out.println("\n2. ESTADO DA FILA ENCADEADA (JÁ ORDENADA POR PRIORIDADE):");
        filaEncadeada.exibirEstadoCompleto();

        System.out.println("\n3. ATENDENDO PACIENTES (FILA ENCADEADA):");
        System.out.println(repeat("-", 45));

        int contador = 1;
        while (!filaEncadeada.isEmpty()) {
            Paciente paciente = filaEncadeada.chamarProximoPaciente();
            if (paciente != null) {
                System.out.println("Atendimento #" + contador + ": " + paciente.getNome() +
                                 " (Idade: " + paciente.getIdade() +
                                 ", Urgente: " + (paciente.isUrgente() ? "SIM" : "NÃO") + ")");
                atendidos.adicionarAtendido(paciente);
                contador++;
            }
        }

        System.out.println("\n4. ESTATÍSTICAS DA IMPLEMENTAÇÃO ENCADEADA:");
        System.out.println(repeat("-", 45));
        atendidos.exibirEstatisticas();
        filaEncadeada.exibirEstatisticasPerformance();

        System.out.println("\n=== TESTE 5 FINALIZADO ===\n");
    }

    private static void analiseComparativaComplexidade() {
        System.out.println("=== ANÁLISE COMPARATIVA DE COMPLEXIDADE ===");
        System.out.println(repeat("=", 50));

        System.out.println("\n📊 COMPLEXIDADE ALGORÍTMICA DAS IMPLEMENTAÇÕES:");
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ ESTRUTURA              │ INSERÇÃO │ REMOÇÃO │ BUSCA │ ESPAÇO │ ORDEM    ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Fila Normal            │   O(1)   │  O(n)   │  N/A  │ O(n)   │ FIFO     ║");
        System.out.println("║ Fila Circular          │   O(1)   │  O(1)   │  N/A  │ O(n)   │ FIFO     ║");
        System.out.println("║ Fila Encadeada         │   O(1)   │  O(1)   │  N/A  │ O(n)   │ FIFO     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Prioridade ArrayList   │   O(1)   │  O(n)   │ O(1)  │ O(n)   │ Por Prio ║");
        System.out.println("║ Prioridade Encadeada   │   O(n)   │  O(1)   │ O(1)  │ O(n)   │ Por Prio ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");

        System.out.println("\n🔍 DISCUSSÃO DETALHADA:");
        System.out.println(repeat("-", 25));

        System.out.println("\n1️⃣ FILA DE PRIORIDADE com ARRAYLIST:");
        System.out.println("   ✅ INSERÇÃO O(1): Adiciona sempre no final da lista apropriada");
        System.out.println("   ❌ REMOÇÃO O(n): remove(0) precisa deslocar todos os elementos");
        System.out.println("   ✅ BUSCA O(1): Acesso direto ao primeiro elemento");
        System.out.println("   📝 Mantém 3 listas separadas (urgente, idoso, normal)");

        System.out.println("\n2️⃣ FILA DE PRIORIDADE ENCADEADA:");
        System.out.println("   ❌ INSERÇÃO O(n): Precisa encontrar posição correta na ordem");
        System.out.println("   ✅ REMOÇÃO O(1): Remove sempre do início, sem deslocamentos");
        System.out.println("   ✅ BUSCA O(1): Acesso direto ao primeiro nó");
        System.out.println("   📝 Mantém ordem única por prioridade usando ponteiros");

        System.out.println("\n🚀 QUANDO USAR CADA ESTRUTURA:");
        System.out.println(repeat("-", 35));

        System.out.println("\n🏥 FILA DE PRIORIDADE ARRAYLIST:");
        System.out.println("   • Ideal quando: Muitas inserções, poucas remoções");
        System.out.println("   • Cenário: Cadastro em lote, atendimento esporádico");
        System.out.println("   • Vantagem: Inserção muito rápida O(1)");
        System.out.println("   • Desvantagem: Remoção custosa O(n)");

        System.out.println("\n🔗 FILA DE PRIORIDADE ENCADEADA:");
        System.out.println("   • Ideal quando: Muitas remoções, inserções controladas");
        System.out.println("   • Cenário: Atendimento contínuo, chegadas esparsas");
        System.out.println("   • Vantagem: Remoção muito rápida O(1)");
        System.out.println("   • Desvantagem: Inserção pode ser custosa O(n)");

        System.out.println("\n⚖️ FILA ENCADEADA vs ARRAY - VANTAGENS:");
        System.out.println(repeat("-", 45));

        System.out.println("\n🔗 LISTA ENCADEADA É MELHOR QUANDO:");
        System.out.println("   1. Frequência alta de remoções (chamadas de pacientes)");
        System.out.println("   2. Tamanho da fila varia muito dinamicamente");
        System.out.println("   3. Não há limite pré-definido de capacidade");
        System.out.println("   4. Prioridade dos elementos muda frequentemente");
        System.out.println("   5. Sistema tem pouca memória disponível (sem desperdício)");

        System.out.println("\n📊 ARRAY É MELHOR QUANDO:");
        System.out.println("   1. Acesso frequente por índice");
        System.out.println("   2. Capacidade conhecida e estável");
        System.out.println("   3. Cache locality é importante");
        System.out.println("   4. Overhead de ponteiros deve ser minimizado");
        System.out.println("   5. Muitas inserções em batch");

        System.out.println("\n💡 RECOMENDAÇÃO PRÁTICA:");
        System.out.println(repeat("-", 25));
        System.out.println("Para sistemas hospitalares reais:");
        System.out.println("• Use ENCADEADA se atendimento é contínuo (pronto-socorro)");
        System.out.println("• Use ARRAYLIST se chegadas são em lote (agendamentos)");
        System.out.println("• Considere HEAP para grandes volumes com prioridades complexas");

        System.out.println("\n" + repeat("=", 60));
        System.out.println("=== EXERCÍCIO DE PRIORIZAÇÃO E COMPLEXIDADE FINALIZADO ===");
        System.out.println(repeat("=", 60));

        // Novo exercício: Estatísticas e Relatórios
        testeEstatisticasRelatorios();
    }

    /**
     * NOVO EXERCÍCIO: Estatísticas e Relatórios
     * Aplica operações sobre listas para gerar informações úteis
     */
    private static void testeEstatisticasRelatorios() {
        System.out.println("\n" + repeat("=", 70));
        System.out.println("=== NOVO EXERCÍCIO: ESTATÍSTICAS E RELATÓRIOS ===");
        System.out.println(repeat("=", 70));

        // Criando uma nova simulação completa para demonstrar as estatísticas
        testeRelatorioCompleto();
    }

    private static void testeRelatorioCompleto() {
        System.out.println("\n=== TESTE 6: SISTEMA COMPLETO COM RELATÓRIOS ===");
        System.out.println(repeat("-", 55));

        // Usando a fila de prioridade avançada para uma simulação completa
        FilaPrioridadeAvancada fila = new FilaPrioridadeAvancada();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        System.out.println("1. SIMULAÇÃO DE UM DIA COMPLETO NA CLÍNICA:");
        System.out.println(repeat("-", 50));

        // Criando uma simulação diversificada com 15 pacientes
        Paciente[] pacientesDia = {
            // Manhã - chegadas normais
            new Paciente("Roberto Silva", 34, "100.100.100-10"),
            new Paciente("Fernanda Costa", 67, "200.200.200-20"),
            new Paciente("Miguel Santos", 23, "300.300.300-30"),

            // Meio da manhã - caso urgente chega
            new Paciente("Amanda Urgente", 41, "400.400.400-40", false, true),
            new Paciente("Joaquim Idoso", 78, "500.500.500-50"),

            // Almoço - mais chegadas
            new Paciente("Carla Jovem", 19, "600.600.600-60"),
            new Paciente("Paulo Emergência", 52, "700.700.700-70", false, true),
            new Paciente("Rosana Sênior", 63, "800.800.800-80"),

            // Tarde - pico de atendimento
            new Paciente("Lucas Normal", 28, "900.900.900-90"),
            new Paciente("Sophia Crítica", 36, "101.101.101-01", false, true),
            new Paciente("Antônio Aposentado", 72, "202.202.202-02"),
            new Paciente("Beatriz Normal", 31, "303.303.303-03"),

            // Final do dia
            new Paciente("Carlos Idoso", 69, "404.404.404-04"),
            new Paciente("Diana Jovem", 25, "505.505.505-05"),
            new Paciente("Eduardo Final", 45, "606.606.606-06")
        };

        // Simulando chegadas ao longo do dia
        System.out.println("📅 CHEGADAS AO LONGO DO DIA:");
        for (int i = 0; i < pacientesDia.length; i++) {
            String periodo = i < 3 ? "MANHÃ" :
                           i < 8 ? "MEIO-DIA" :
                           i < 12 ? "TARDE" : "FINAL";
            System.out.println("[" + periodo + "] Chegou: " + pacientesDia[i].getNome());
            fila.adicionarPaciente(pacientesDia[i]);
        }

        System.out.println("\n2. ESTADO INICIAL DA FILA ORGANIZADA POR PRIORIDADE:");
        fila.exibirEstadoCompleto();
        fila.exibirDistribuicaoPrioridades();

        System.out.println("\n3. PROCESSANDO TODOS OS ATENDIMENTOS DO DIA:");
        System.out.println(repeat("-", 55));

        int numeroAtendimento = 1;
        while (!fila.isEmpty()) {
            Paciente paciente = fila.chamarProximoPaciente();
            if (paciente != null) {
                System.out.println("🏥 Atendimento #" + numeroAtendimento + ": " +
                                 paciente.getNome() + " - Finalizado!");
                atendidos.adicionarAtendido(paciente);
                numeroAtendimento++;

                // Pequena pausa visual a cada 5 atendimentos
                if (numeroAtendimento % 5 == 1) {
                    System.out.println("   ... processando atendimentos ...");
                }
            }
        }

        System.out.println("\n4. TESTANDO NOVOS MÉTODOS DE ESTATÍSTICAS:");
        System.out.println(repeat("-", 50));

        // Demonstrando os novos métodos implementados
        System.out.println("📊 MÉTODOS IMPLEMENTADOS NO EXERCÍCIO:");
        System.out.println("• contarAtendidos(): " + atendidos.contarAtendidos());
        System.out.println("• calcularMediaIdade(): " +
                         String.format("%.2f", atendidos.calcularMediaIdade()) + " anos");

        Paciente maisIdoso = atendidos.encontrarMaisIdoso();
        Paciente maisJovem = atendidos.encontrarMaisJovem();

        System.out.println("• encontrarMaisIdoso(): " + maisIdoso.getNome() +
                         " (" + maisIdoso.getIdade() + " anos)");
        System.out.println("• encontrarMaisJovem(): " + maisJovem.getNome() +
                         " (" + maisJovem.getIdade() + " anos)");
        System.out.println("• contarUrgentes(): " + atendidos.contarUrgentes());
        System.out.println("• contarIdosos(): " + atendidos.contarIdosos());

        System.out.println("\n5. EXIBINDO RELATÓRIO DIÁRIO COMPLETO:");
        System.out.println(repeat("-", 45));
        atendidos.exibirRelatorioDiario();

        System.out.println("\n6. DESAFIO EXTRA - EXPORTANDO RELATÓRIO PARA ARQUIVO:");
        System.out.println(repeat("-", 60));

        // Criando nome do arquivo com data/hora atual
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String nomeArquivo = "relatorio_clinica_" + agora.format(formatter);

        boolean sucesso = atendidos.exportarRelatorio(nomeArquivo);
        if (sucesso) {
            System.out.println("📄 O relatório foi salvo e pode ser aberto em qualquer editor de texto.");
            System.out.println("📂 Localização: " + System.getProperty("user.dir") + "\\" + nomeArquivo + ".txt");
            System.out.println("💡 Este arquivo pode ser enviado por email, impresso ou arquivado.");
        }

        System.out.println("\n7. COMPARAÇÃO COM MÉTODOS ANTERIORES:");
        System.out.println(repeat("-", 45));
        System.out.println("🔄 Chamando método legado exibirEstatisticas():");
        atendidos.exibirEstatisticas();

        System.out.println("\n8. ANÁLISE DOS RESULTADOS OBTIDOS:");
        System.out.println(repeat("-", 40));
        System.out.println("✅ OBJETIVOS DO EXERCÍCIO ALCANÇADOS:");
        System.out.println("   • ✓ Contar pacientes atendidos: " + atendidos.contarAtendidos() + " pacientes");
        System.out.println("   • ✓ Calcular média de idade: " +
                         String.format("%.1f", atendidos.calcularMediaIdade()) + " anos");
        System.out.println("   • ✓ Encontrar mais idoso: " + maisIdoso.getNome() +
                         " (" + maisIdoso.getIdade() + " anos)");
        System.out.println("   • ✓ Relatório diário: Formato completo com estatísticas");
        System.out.println("   • ✓ Desafio extra: Exportação para arquivo .txt");

        System.out.println("\n📈 INSIGHTS GERADOS PELAS ESTATÍSTICAS:");
        double percentualUrgentes = (atendidos.contarUrgentes() * 100.0) / atendidos.contarAtendidos();
        double percentualIdosos = (atendidos.contarIdosos() * 100.0) / atendidos.contarAtendidos();

        System.out.println("• " + String.format("%.1f", percentualUrgentes) + "% dos atendimentos foram urgentes");
        System.out.println("• " + String.format("%.1f", percentualIdosos) + "% dos pacientes eram idosos");
        System.out.println("• Faixa etária: " + maisJovem.getIdade() + " a " + maisIdoso.getIdade() + " anos");
        System.out.println("• Sistema priorizou corretamente: urgentes → idosos → normais");

        System.out.println("\n" + repeat("=", 70));
        System.out.println("=== EXERCÍCIO DE ESTATÍSTICAS E RELATÓRIOS FINALIZADO ===");
        System.out.println(repeat("=", 70));
        System.out.println("\n🎯 TODOS OS EXERCÍCIOS CONCLUÍDOS COM SUCESSO!");
        System.out.println("📚 Sistema completo implementado com:");
        System.out.println("   • Filas (normal, circular, encadeada)");
        System.out.println("   • Prioridades (urgente, idoso, normal)");
        System.out.println("   • Estatísticas (contagem, médias, extremos)");
        System.out.println("   • Relatórios (tela e arquivo)");
        System.out.println("   • Análise de complexidade algorítmica");
    }
}
