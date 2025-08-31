package br.com.pucgo.exercicio2;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe principal para demonstrar o uso da CentralDeInformacoes
 */
public class App {
    public static void main(String[] args) {
        // Criando a central de informações
        CentralDeInformacoes central = new CentralDeInformacoes();

        // Criando datas para testes
        Calendar cal = Calendar.getInstance();

        // Passageiro maior de 18 anos (válido)
        cal.set(1990, Calendar.JANUARY, 15);
        Date dataNasc1 = cal.getTime();

        // Passageiro maior de 18 anos (válido)
        cal.set(1985, Calendar.MARCH, 10);
        Date dataNasc2 = cal.getTime();

        // Passageiro menor de 18 anos (inválido)
        cal.set(2010, Calendar.MAY, 20);
        Date dataNascMenor = cal.getTime();

        // Criando passageiros
        Passageiro passageiro1 = new Passageiro("João Silva", Sexo.MASCULINO, dataNasc1, "joao@email.com");
        Passageiro passageiro2 = new Passageiro("Maria Santos", Sexo.FEMININO, dataNasc2, "maria@email.com");
        Passageiro passageiroMenor = new Passageiro("Pedro Jovem", Sexo.MASCULINO, dataNascMenor, "pedro@email.com");
        Passageiro passageiroDuplicado = new Passageiro("Ana Silva", Sexo.FEMININO, dataNasc1, "joao@email.com"); // E-mail duplicado

        System.out.println("=== TESTANDO CENTRAL DE INFORMAÇÕES ===\n");

        // Teste 1: Adicionando passageiro válido
        System.out.println("1. Tentando adicionar " + passageiro1 + ":");
        boolean resultado1 = central.adicionarPassageiro(passageiro1);
        System.out.println("Resultado: " + (resultado1 ? "SUCESSO" : "FALHOU"));
        System.out.println("Total de passageiros: " + central.getTotalPassageiros() + "\n");

        // Teste 2: Adicionando outro passageiro válido
        System.out.println("2. Tentando adicionar " + passageiro2 + ":");
        boolean resultado2 = central.adicionarPassageiro(passageiro2);
        System.out.println("Resultado: " + (resultado2 ? "SUCESSO" : "FALHOU"));
        System.out.println("Total de passageiros: " + central.getTotalPassageiros() + "\n");

        // Teste 3: Tentando adicionar passageiro menor de idade
        System.out.println("3. Tentando adicionar " + passageiroMenor + " (menor de 18 anos):");
        boolean resultado3 = central.adicionarPassageiro(passageiroMenor);
        System.out.println("Resultado: " + (resultado3 ? "SUCESSO" : "FALHOU - Menor de 18 anos"));
        System.out.println("Total de passageiros: " + central.getTotalPassageiros() + "\n");

        // Teste 4: Tentando adicionar passageiro com e-mail duplicado
        System.out.println("4. Tentando adicionar " + passageiroDuplicado + " (e-mail duplicado):");
        boolean resultado4 = central.adicionarPassageiro(passageiroDuplicado);
        System.out.println("Resultado: " + (resultado4 ? "SUCESSO" : "FALHOU - E-mail já existe"));
        System.out.println("Total de passageiros: " + central.getTotalPassageiros() + "\n");

        // Teste 5: Recuperando passageiro por e-mail
        System.out.println("=== TESTANDO RECUPERAÇÃO POR E-MAIL ===");
        System.out.println("5. Buscando passageiro com e-mail 'joao@email.com':");
        Passageiro encontrado = central.recuperarPassageiroPeloEmail("joao@email.com");
        System.out.println("Resultado: " + (encontrado != null ? "Encontrado - " + encontrado : "Não encontrado"));

        System.out.println("\n6. Buscando passageiro com e-mail 'inexistente@email.com':");
        Passageiro naoEncontrado = central.recuperarPassageiroPeloEmail("inexistente@email.com");
        System.out.println("Resultado: " + (naoEncontrado != null ? "Encontrado - " + naoEncontrado : "Não encontrado"));

        // Teste 6: Listando todos os passageiros
        System.out.println("\n=== LISTA DE TODOS OS PASSAGEIROS ===");
        for (int i = 0; i < central.getTodosOsPassageiros().size(); i++) {
            Passageiro p = central.getTodosOsPassageiros().get(i);
            System.out.println((i + 1) + ". " + p + " - " + p.getEmail());
        }

        System.out.println("\nTotal final de passageiros cadastrados: " + central.getTotalPassageiros());
    }
}
