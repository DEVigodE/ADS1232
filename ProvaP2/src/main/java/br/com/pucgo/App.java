package br.com.pucgo;


        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("║  DEMONSTRAÇÃO CONCLUÍDA COM SUCESSO!                      ║");
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");

        sistema.exibirEstadoTodosSetores();
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("│  FASE 6: ESTADO FINAL DOS SETORES      │");
        System.out.println("\n┌─────────────────────────────────────────┐");
        // ============ DEMONSTRAÇÃO: ESTADO FINAL ============

        sistema.exibirHistoricoCompleto();
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("│  FASE 5: HISTÓRICO COMPLETO            │");
        System.out.println("\n┌─────────────────────────────────────────┐");
        // ============ DEMONSTRAÇÃO: HISTÓRICO ============

        sistema.consultarUltimoAtendimento();
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("│  FASE 4: ÚLTIMO ATENDIMENTO            │");
        System.out.println("\n┌─────────────────────────────────────────┐");
        // ============ DEMONSTRAÇÃO: CONSULTAR ÚLTIMO ATENDIMENTO ============

        sistema.realizarAtendimento("SECRETARIA"); // Fernando (prioridade 2)
        sistema.realizarAtendimento("SECRETARIA"); // Patrícia (prioridade 1)
        sistema.realizarAtendimento("SECRETARIA"); // Juliana (prioridade 1)
        System.out.println("\n--- Atendimentos: Secretaria Acadêmica ---");
        // Atendimentos na Secretaria (Por prioridade - menor número primeiro)

        sistema.realizarAtendimento("LABORATORIO"); // Carlos (penúltimo)
        sistema.realizarAtendimento("LABORATORIO"); // Beatriz (último)
        System.out.println("\n--- Atendimentos: Laboratório de Projetos ---");
        // Atendimentos no Laboratório (LIFO - último a chegar)

        sistema.realizarAtendimento("SUPORTE"); // Maria (segundo)
        sistema.realizarAtendimento("SUPORTE"); // João (primeiro)
        System.out.println("--- Atendimentos: Central de Suporte ---");
        // Atendimentos na Central de Suporte (FIFO - primeiro a chegar)

        System.out.println("└─────────────────────────────────────────┘\n");
        System.out.println("│  FASE 3: REALIZANDO ATENDIMENTOS       │");
        System.out.println("\n┌─────────────────────────────────────────┐");
        // ============ DEMONSTRAÇÃO: ATENDIMENTO ============

        sistema.exibirEstadoTodosSetores();
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("│  FASE 2: ESTADO INICIAL DOS SETORES    │");
        System.out.println("\n┌─────────────────────────────────────────┐");
        // ============ DEMONSTRAÇÃO: EXIBIÇÃO DO ESTADO ============

        sistema.registrarUsuario("SECRETARIA", new Usuario("Patrícia Gomes", "012345", 1));
        sistema.registrarUsuario("SECRETARIA", new Usuario("Fernando Alves", "901234", 2));
        sistema.registrarUsuario("SECRETARIA", new Usuario("Juliana Ferreira", "890123", 1));
        sistema.registrarUsuario("SECRETARIA", new Usuario("Rafael Oliveira", "789012", 3));
        System.out.println("\n--- Secretaria Acadêmica ---");
        // Secretaria Acadêmica (Prioridade)

        sistema.registrarUsuario("LABORATORIO", new Usuario("Beatriz Souza", "678901"));
        sistema.registrarUsuario("LABORATORIO", new Usuario("Carlos Lima", "567890"));
        sistema.registrarUsuario("LABORATORIO", new Usuario("Ana Paula", "456789"));
        System.out.println("\n--- Laboratório de Projetos ---");
        // Laboratório de Projetos (LIFO)

        sistema.registrarUsuario("SUPORTE", new Usuario("Pedro Costa", "345678"));
        sistema.registrarUsuario("SUPORTE", new Usuario("Maria Santos", "234567"));
        sistema.registrarUsuario("SUPORTE", new Usuario("João Silva", "123456"));
        System.out.println("--- Central de Suporte ---");
        // Central de Suporte (FIFO)

        System.out.println("└─────────────────────────────────────────┘\n");
        System.out.println("│  FASE 1: REGISTRO DE USUÁRIOS          │");
        System.out.println("\n┌─────────────────────────────────────────┐");
        // ============ DEMONSTRAÇÃO: INSERÇÃO ============

        SistemaAtendimento sistema = new SistemaAtendimento();
        // Criação do sistema

        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        System.out.println("║  Universidade - Gestão de Atendimentos                   ║");
        System.out.println("║  SISTEMA DE CONTROLE DE ATENDIMENTO MULTISSETORIAL       ║");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
 * Sistema de Controle de Atendimento Multissetorial
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
