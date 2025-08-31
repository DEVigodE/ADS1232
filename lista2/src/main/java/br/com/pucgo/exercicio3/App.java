package br.com.pucgo.exercicio3;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe principal para demonstrar o uso da Persistencia com CentralDeInformacoes
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== TESTANDO PERSISTÊNCIA COM XSTREAM ===\n");

        // Criando a central de informações e persistência
        CentralDeInformacoes central = new CentralDeInformacoes();
        Persistencia persistencia = new Persistencia();

        // Criando datas para testes
        Calendar cal = Calendar.getInstance();

        // Passageiros válidos
        cal.set(1990, Calendar.JANUARY, 15);
        Date dataNasc1 = cal.getTime();

        cal.set(1985, Calendar.MARCH, 10);
        Date dataNasc2 = cal.getTime();

        cal.set(1992, Calendar.DECEMBER, 25);
        Date dataNasc3 = cal.getTime();

        // Criando passageiros
        Passageiro passageiro1 = new Passageiro("João Silva", Sexo.MASCULINO, dataNasc1, "joao@email.com");
        Passageiro passageiro2 = new Passageiro("Maria Santos", Sexo.FEMININO, dataNasc2, "maria@email.com");
        Passageiro passageiro3 = new Passageiro("Carlos Oliveira", Sexo.MASCULINO, dataNasc3, "carlos@email.com");

        // Adicionando passageiros à central
        System.out.println("1. Adicionando passageiros à Central:");
        central.adicionarPassageiro(passageiro1);
        central.adicionarPassageiro(passageiro2);
        central.adicionarPassageiro(passageiro3);
        System.out.println("Total de passageiros adicionados: " + central.getTotalPassageiros());

        // Listando passageiros antes de salvar
        System.out.println("\nPassageiros na central:");
        for (Passageiro p : central.getTodosOsPassageiros()) {
            System.out.println("- " + p + " (" + p.getEmail() + ")");
        }

        // Teste 1: Salvando a central em arquivo XML
        String nomeArquivo = "central_passageiros";
        System.out.println("\n2. Salvando central no arquivo XML:");
        boolean sucessoSalvar = persistencia.salvarCentral(central, nomeArquivo);
        System.out.println("Salvamento " + (sucessoSalvar ? "realizado com sucesso!" : "falhou!"));

        // Teste 2: Recuperando central de arquivo existente
        System.out.println("\n3. Recuperando central do arquivo XML:");
        CentralDeInformacoes centralRecuperada = persistencia.recuperarCentral(nomeArquivo);
        System.out.println("Total de passageiros recuperados: " + centralRecuperada.getTotalPassageiros());

        // Verificando se os dados foram recuperados corretamente
        System.out.println("\nPassageiros na central recuperada:");
        for (Passageiro p : centralRecuperada.getTodosOsPassageiros()) {
            System.out.println("- " + p + " (" + p.getEmail() + ")");
        }

        // Teste 3: Testando busca por e-mail na central recuperada
        System.out.println("\n4. Testando busca na central recuperada:");
        Passageiro encontrado = centralRecuperada.recuperarPassageiroPeloEmail("maria@email.com");
        System.out.println("Busca por 'maria@email.com': " +
                          (encontrado != null ? "Encontrado - " + encontrado : "Não encontrado"));

        // Teste 4: Tentando recuperar de arquivo inexistente
        System.out.println("\n5. Tentando recuperar de arquivo inexistente:");
        CentralDeInformacoes centralNova = persistencia.recuperarCentral("arquivo_inexistente");
        System.out.println("Total de passageiros na nova central: " + centralNova.getTotalPassageiros());

        // Teste 5: Adicionando mais passageiros e salvando novamente
        System.out.println("\n6. Adicionando novo passageiro e salvando novamente:");
        cal.set(1988, Calendar.JUNE, 30);
        Date dataNasc4 = cal.getTime();
        Passageiro passageiro4 = new Passageiro("Ana Costa", Sexo.FEMININO, dataNasc4, "ana@email.com");

        boolean adicionado = centralRecuperada.adicionarPassageiro(passageiro4);
        System.out.println("Novo passageiro " + (adicionado ? "adicionado" : "não foi adicionado"));
        System.out.println("Total atual: " + centralRecuperada.getTotalPassageiros());

        // Salvando a central atualizada
        boolean sucessoSalvar2 = persistencia.salvarCentral(centralRecuperada, "central_atualizada");
        System.out.println("Central atualizada " + (sucessoSalvar2 ? "salva" : "não foi salva") + " com sucesso!");

        // Teste 6: Verificando se arquivos existem
        System.out.println("\n7. Verificando existência de arquivos:");
        System.out.println("Arquivo 'central_passageiros.xml' existe: " +
                          persistencia.arquivoExiste("central_passageiros"));
        System.out.println("Arquivo 'central_atualizada.xml' existe: " +
                          persistencia.arquivoExiste("central_atualizada"));
        System.out.println("Arquivo 'inexistente.xml' existe: " +
                          persistencia.arquivoExiste("inexistente"));

        System.out.println("\n=== DEMONSTRAÇÃO CONCLUÍDA ===");
    }
}
