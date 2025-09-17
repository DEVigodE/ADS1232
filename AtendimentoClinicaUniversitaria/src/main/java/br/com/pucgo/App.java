package br.com.pucgo;

/**
 * Sistema de Atendimento da Clínica Universitária
 * Demonstra o uso de Fila (Queue) e Lista (List) para gerenciar pacientes
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("=== SISTEMA DE ATENDIMENTO - CLÍNICA UNIVERSITÁRIA ===\n");

        // Criando as estruturas de dados
        FilaDePacientes fila = new FilaDePacientes();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        // 1. Inserção de 5 pacientes na fila
        System.out.println("1. ADICIONANDO PACIENTES NA FILA:");
        System.out.println("-".repeat(40));

        fila.adicionarPaciente(new Paciente("João Silva", 45, "123.456.789-01"));
        fila.adicionarPaciente(new Paciente("Maria Santos", 65, "234.567.890-12")); // Prioridade (idosa)
        fila.adicionarPaciente(new Paciente("Pedro Oliveira", 30, "345.678.901-23"));
        fila.adicionarPaciente(new Paciente("Ana Costa", 72, "456.789.012-34")); // Prioridade (idosa)
        fila.adicionarPaciente(new Paciente("Carlos Mendes", 28, "567.890.123-45", true)); // Prioridade (caso urgente)

        // Exibindo estado inicial da fila
        fila.exibirFila();

        // 2. Atendimento de 3 pacientes
        System.out.println("2. ATENDENDO PACIENTES:");
        System.out.println("-".repeat(40));

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
        System.out.println("-".repeat(40));

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
        System.out.println("-".repeat(40));
        atendidos.exibirAtendidos();

        // Estado atual da fila após atendimentos
        System.out.println("5. ESTADO ATUAL DA FILA:");
        System.out.println("-".repeat(40));
        fila.exibirFila();

        // Demonstração de busca na lista de atendidos
        System.out.println("6. DEMONSTRAÇÃO DE BUSCA:");
        System.out.println("-".repeat(40));

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
        System.out.println("-".repeat(40));
        atendidos.exibirEstatisticas();

        // Simulação de mais atendimentos para completar a fila
        System.out.println("8. FINALIZANDO ATENDIMENTOS:");
        System.out.println("-".repeat(40));

        while (!fila.isEmpty()) {
            Paciente paciente = fila.chamarProximoPaciente();
            if (paciente != null) {
                System.out.println("Atendendo: " + paciente.getNome());
                atendidos.adicionarAtendido(paciente);
            }
        }

        // Estatísticas finais
        System.out.println("\n9. ESTATÍSTICAS FINAIS:");
        System.out.println("-".repeat(40));
        atendidos.exibirEstatisticas();

        System.out.println("=== SISTEMA FINALIZADO ===");
    }
}
