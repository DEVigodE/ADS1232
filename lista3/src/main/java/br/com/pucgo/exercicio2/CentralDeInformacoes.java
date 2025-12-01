package br.com.pucgo.exercicio2;

import br.com.pucgo.dominio.Pilha;
import java.util.Scanner;

/**
 * Central de Informações - Sistema de Desfazer Comandos
 * Utiliza pilha para gerenciar comandos executados e permitir desfazer
 */
public class CentralDeInformacoes {
    private Pilha pilhaComandos;
    private Scanner scanner;

    public CentralDeInformacoes() {
        this.pilhaComandos = new Pilha(100);
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        exibirInstrucoes();

        while (true) {
            System.out.print("\n>>> Comando: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("SAIR")) {
                System.out.println("💾 Salvando trabalho... Sistema encerrado!");
                break;
            }
            else if (entrada.equalsIgnoreCase("DESFAZER")) {
                desfazerUltimoComando();
            }
            else if (entrada.equalsIgnoreCase("STATUS")) {
                exibirStatusSistema();
            }
            else {
                executarNovoComando(entrada);
            }
        }

        scanner.close();
    }

    private void exibirInstrucoes() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║    ⚡ SISTEMA DE DESFAZER COMANDOS    ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ 🔢 Digite ID do comando (número)      ║");
        System.out.println("║ ↩️  Digite 'DESFAZER' para reverter   ║");
        System.out.println("║ 📊 Digite 'STATUS' para ver situação ║");
        System.out.println("║ 🚪 Digite 'SAIR' para finalizar      ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private void executarNovoComando(String entrada) {
        try {
            int idComando = Integer.parseInt(entrada);
            pilhaComandos.adicionar(idComando);
            System.out.println("✅ Comando " + idComando + " executado com sucesso!");
            System.out.println("📝 Total de comandos executados: " + pilhaComandos.tamanho());
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida! Use: número, 'DESFAZER', 'STATUS' ou 'SAIR'");
        }
    }

    private void desfazerUltimoComando() {
        if (!pilhaComandos.estaVazia()) {
            int comandoDesfeito = (int) pilhaComandos.remover();
            System.out.println("↩️ Comando " + comandoDesfeito + " foi desfeito!");

            if (!pilhaComandos.estaVazia()) {
                System.out.println("🔄 Estado atual: último comando executado foi " + pilhaComandos.peek());
            } else {
                System.out.println("🆕 Estado limpo: nenhum comando ativo");
            }

            System.out.println("📝 Comandos restantes: " + pilhaComandos.tamanho());
        } else {
            System.out.println("🚫 Nenhum comando para desfazer - estado limpo!");
        }
    }

    private void exibirStatusSistema() {
        System.out.println("\n📊 STATUS DO SISTEMA:");
        System.out.println("   • Total de comandos ativos: " + pilhaComandos.tamanho());

        if (!pilhaComandos.estaVazia()) {
            System.out.println("   • Último comando executado: " + pilhaComandos.peek());
            System.out.println("   • Próximo a ser desfeito: " + pilhaComandos.peek());
        } else {
            System.out.println("   • Sistema em estado limpo");
        }
    }
}
