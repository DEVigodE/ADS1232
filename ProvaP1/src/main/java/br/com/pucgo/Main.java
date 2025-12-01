package br.com.pucgo;

public class Main
{
    public static void main( String[] args )
    {
        // Criando as estruturas de dados
        Pediatria pediatria = new Pediatria(10);
        Radiologia radiologia = new Radiologia(10);
        Atendidos atendidos = new Atendidos(20);

        System.out.println("=== SISTEMA HOSPITALAR - CADASTRO INICIAL ===\n");

        // Cadastro na Pediatria (PILHA)
        System.out.println("--- CADASTRANDO PACIENTES NA PEDIATRIA ---");
        pediatria.push(new Paciente("João Silva", 45, "123.456.789-01"));
        pediatria.push(new Paciente("Maria Santos", 65, "234.567.890-12"));
        pediatria.push(new Paciente("Pedro Oliveira", 30, "345.678.901-23"));
        pediatria.push(new Paciente("Ana Costa", 72, "456.789.012-34"));
        pediatria.push(new Paciente("Carlos Mendes", 28, "567.890.123-45"));

        // Cadastro na Radiologia (LISTA)
        System.out.println("\n--- CADASTRANDO PACIENTES NA RADIOLOGIA ---");
        radiologia.add(new Paciente("Fernanda Lima", 35, "678.901.234-56"));
        radiologia.add(new Paciente("Roberto Silva", 42, "789.012.345-67"));
        radiologia.add(new Paciente("Lucia Oliveira", 29, "890.123.456-78"));
        radiologia.insert(1, new Paciente("Paulo Santos", 50, "901.234.567-89"));
        radiologia.add(new Paciente("Beatriz Costa", 38, "012.345.678-90"));

        // Exibir estados iniciais
        System.out.println("\n=== ESTADO INICIAL DAS ESTRUTURAS ===");
        pediatria.exibirEstado();
        radiologia.exibirEstado();
        atendidos.exibirEstado();

        // Simulando atendimentos
        System.out.println("=== INICIANDO ATENDIMENTOS ===\n");

        // Atendendo pacientes da Pediatria
        System.out.println("--- ATENDENDO PACIENTES DA PEDIATRIA ---");
        atenderPaciente(pediatria, atendidos, "PEDIATRIA");
        atenderPaciente(pediatria, atendidos, "PEDIATRIA");

        // Atendendo pacientes da Radiologia
        System.out.println("\n--- ATENDENDO PACIENTES DA RADIOLOGIA ---");
        atenderPacienteRadiologia(radiologia, atendidos);
        atenderPacienteRadiologia(radiologia, atendidos);

        // Exibir estados após atendimentos
        System.out.println("\n=== ESTADO APÓS ATENDIMENTOS ===");
        pediatria.exibirEstado();
        radiologia.exibirEstado();
        atendidos.exibirEstado();

        // Continuando atendimentos
        System.out.println("=== CONTINUANDO ATENDIMENTOS ===\n");
        atenderPaciente(pediatria, atendidos, "PEDIATRIA");
        atenderPacienteRadiologia(radiologia, atendidos);

        // Estado final
        System.out.println("\n=== ESTADO FINAL DE TODAS AS ESTRUTURAS ===");
        pediatria.exibirEstado();
        radiologia.exibirEstado();
        atendidos.exibirEstado();
    }

    // Método para atender paciente da Pediatria (pilha)
    public static void atenderPaciente(Pediatria pediatria, Atendidos atendidos, String departamento) {
        Paciente paciente = pediatria.pop();
        if (paciente != null) {
            System.out.println("Atendendo paciente do " + departamento + ": " + paciente.getNome());
            atendidos.enqueue(paciente);
        }
    }

    // Método para atender paciente da Radiologia (lista) - remove o primeiro
    public static void atenderPacienteRadiologia(Radiologia radiologia, Atendidos atendidos) {
        Paciente paciente = radiologia.removeFirst();
        if (paciente != null) {
            System.out.println("Atendendo paciente da RADIOLOGIA: " + paciente.getNome());
            atendidos.enqueue(paciente);
        }
    }
}
