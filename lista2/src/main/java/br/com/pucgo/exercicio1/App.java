package br.com.pucgo.exercicio1;

import java.util.Date;
import java.util.Calendar;

public class App 
{
    public static void main( String[] args )
    {
        Calendar cal = Calendar.getInstance();

        cal.set(1990, Calendar.JANUARY, 1);
        Date dataNasc1 = cal.getTime();

        cal.set(1985, Calendar.MAY, 15);
        Date dataNasc2 = cal.getTime();

        cal.set(1992, Calendar.DECEMBER, 22);
        Date dataNasc3 = cal.getTime();

        Passageiro passageiro1 = new Passageiro("João Silva", Sexo.MASCULINO, dataNasc1, "joao.silva@email.com");
        Passageiro passageiro2 = new Passageiro("Maria Santos", Sexo.FEMININO, dataNasc2, "maria.santos@email.com");
        Passageiro passageiro3 = new Passageiro("Alex Oliveira", Sexo.OUTRO, dataNasc3, "alex.oliveira@email.com");

        System.out.println("=== DEMONSTRAÇÃO DA CLASSE PASSAGEIRO ===");
        System.out.println("Passageiro 1: " + passageiro1);
        System.out.println("E-mail: " + passageiro1.getEmail());
        System.out.println("Sexo: " + passageiro1.getSexo());
        System.out.println("Data de Nascimento: " + passageiro1.getDataDeNascimento());

        System.out.println("\nPassageiro 2: " + passageiro2);
        System.out.println("E-mail: " + passageiro2.getEmail());
        System.out.println("Sexo: " + passageiro2.getSexo());

        System.out.println("\nPassageiro 3: " + passageiro3);
        System.out.println("E-mail: " + passageiro3.getEmail());
        System.out.println("Sexo: " + passageiro3.getSexo());

        System.out.println("\n=== ALTERANDO NOME DO PASSAGEIRO 1 ===");
        System.out.println("Nome anterior: " + passageiro1.getNome());
        passageiro1.setNome("João Carlos Silva");
        System.out.println("Nome atual: " + passageiro1.getNome());
        System.out.println("toString após alteração: " + passageiro1);
    }
}
