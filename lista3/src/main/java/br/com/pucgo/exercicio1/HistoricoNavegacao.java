package br.com.pucgo.exercicio1;

import br.com.pucgo.dominio.Pilha;
import java.util.Scanner;

/**
 * Histórico de Navegação Web usando Pilha
 * Simula um navegador com funcionalidades de voltar e histórico
 */
public class HistoricoNavegacao {
    private Pilha historicoSites;
    private Scanner scanner;

    public HistoricoNavegacao() {
        this.historicoSites = new Pilha(100);
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        exibirInstrucoes();

        while (true) {
            System.out.print("\n>>> Comando: ");
            String comando = scanner.nextLine().trim();

            if (comando.equalsIgnoreCase("SAIR")) {
                System.out.println("🔒 Fechando navegador... Até logo!");
                break;
            }
            else if (comando.equalsIgnoreCase("VOLTAR")) {
                voltarPaginaAnterior();
            }
            else if (comando.equalsIgnoreCase("HISTORICO")) {
                exibirEstatisticas();
            }
            else {
                acessarNovoSite(comando);
            }
        }

        scanner.close();
    }

    private void exibirInstrucoes() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       🌐 NAVEGADOR WEB SIMPLES       ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 📝 Digite o ID do site (número)      ║");
        System.out.println("║ ⬅️  Digite 'VOLTAR' para retroceder  ║");
        System.out.println("║ 📊 Digite 'HISTORICO' para ver stats ║");
        System.out.println("║ 🚪 Digite 'SAIR' para fechar         ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void acessarNovoSite(String entrada) {
        try {
            int idSite = Integer.parseInt(entrada);
            historicoSites.adicionar(idSite);
            System.out.println("🌐 Acessando site ID: " + idSite);
            System.out.println("📚 Total de páginas no histórico: " + historicoSites.tamanho());
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida! Use: número, 'VOLTAR', 'HISTORICO' ou 'SAIR'");
        }
    }

    private void voltarPaginaAnterior() {
        if (!historicoSites.estaVazia()) {
            int siteAnterior = (int) historicoSites.remover();
            System.out.println("⬅️ Saindo do site ID: " + siteAnterior);

            if (!historicoSites.estaVazia()) {
                System.out.println("🔄 Voltando para site ID: " + historicoSites.peek());
            } else {
                System.out.println("🏠 Voltou para a página inicial");
            }

            System.out.println("📚 Páginas restantes no histórico: " + historicoSites.tamanho());
        } else {
            System.out.println("🚫 Histórico vazio - já está na página inicial!");
        }
    }

    private void exibirEstatisticas() {
        System.out.println("\n📊 ESTATÍSTICAS DO NAVEGADOR:");
        System.out.println("   • Total de páginas no histórico: " + historicoSites.tamanho());

        if (!historicoSites.estaVazia()) {
            System.out.println("   • Página atual: Site ID " + historicoSites.peek());
        } else {
            System.out.println("   • Página atual: Página inicial");
        }
    }
}
