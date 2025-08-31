package br.com.pucgo.exercicio5;

import br.com.pucgo.dominio.PilhaEncadeada;
import java.util.Scanner;

/**
 * Verificador de Parênteses Balanceados
 * Utiliza PilhaEncadeada<Character> para verificar balanceamento em expressões matemáticas
 * Algoritmo clássico usado em compiladores e interpretadores
 */
public class VerificadorParenteses {
    private Scanner scanner;

    public VerificadorParenteses() {
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        exibirInstrucoes();

        while (true) {
            System.out.print("\n>>> Digite uma expressão (ou 'SAIR' para encerrar): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("SAIR")) {
                System.out.println("🚪 Encerrando verificador... Até mais!");
                break;
            }

            if (entrada.isEmpty()) {
                System.out.println("⚠️ Por favor, digite uma expressão válida!");
                continue;
            }

            verificarBalanceamentoDetalhado(entrada);
        }

        scanner.close();
    }

    private void exibirInstrucoes() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   🔍 VERIFICADOR DE PARÊNTESES BALANCEADOS ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ ✏️  Digite expressões matemáticas           ║");
        System.out.println("║ 📊 Exemplos: (2+3)*(5-1), ((1+2)*3)       ║");
        System.out.println("║ ✅ Verifica se parênteses estão corretos   ║");
        System.out.println("║ 🚪 Digite 'SAIR' para encerrar            ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println("\n💡 Algoritmo clássico usado em compiladores e interpretadores!");
        System.out.println("   • '(' encontrado → empilha (PUSH)");
        System.out.println("   • ')' encontrado → desempilha (POP)");
        System.out.println("   • Expressão válida → pilha vazia no final");
    }

    private void verificarBalanceamentoDetalhado(String expressao) {
        PilhaEncadeada<Character> pilhaParenteses = new PilhaEncadeada<>();
        boolean expressaoValida = true;
        int posicaoErro = -1;

        System.out.println("\n🔍 ANALISANDO: \"" + expressao + "\"");
        System.out.println("📋 Processo detalhado:");

        // Processar cada caractere da expressão
        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);

            if (caractere == '(') {
                pilhaParenteses.push(caractere);
                System.out.println("   Posição " + (i+1) + ": '" + caractere + "' → EMPILHAR | Pilha: " + pilhaParenteses.tamanho() + " elemento(s)");
            }
            else if (caractere == ')') {
                if (pilhaParenteses.estaVazia()) {
                    expressaoValida = false;
                    posicaoErro = i + 1;
                    System.out.println("   Posição " + (i+1) + ": '" + caractere + "' → ❌ ERRO! Pilha vazia - parêntese sem abertura");
                    break;
                } else {
                    pilhaParenteses.pop();
                    System.out.println("   Posição " + (i+1) + ": '" + caractere + "' → DESEMPILHAR | Pilha: " + pilhaParenteses.tamanho() + " elemento(s)");
                }
            }
            else if (Character.isDigit(caractere) || "+-*/=".indexOf(caractere) != -1) {
                System.out.println("   Posição " + (i+1) + ": '" + caractere + "' → IGNORAR (não é parêntese)");
            }
        }

        // Verificação final
        if (expressaoValida && !pilhaParenteses.estaVazia()) {
            expressaoValida = false;
            System.out.println("   ❌ ERRO FINAL: Restam " + pilhaParenteses.tamanho() + " parêntese(s) não fechado(s)");
        }

        exibirResultadoVerificacao(expressao, expressaoValida, posicaoErro, pilhaParenteses.tamanho());
    }

    private void exibirResultadoVerificacao(String expressao, boolean valida, int posicaoErro, int parentesesNaoFechados) {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║              🎯 RESULTADO                 ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.printf("║ 📝 Expressão: %-28s ║%n", "\"" + expressao + "\"");

        if (valida) {
            System.out.println("║ ✅ Status: VÁLIDA - Parênteses balanceados ║");
            System.out.println("║ 🎊 A expressão está correta!              ║");
        } else {
            System.out.println("║ ❌ Status: INVÁLIDA - Erro encontrado     ║");
            if (posicaoErro != -1) {
                System.out.printf("║ 🎯 Erro na posição: %-19d ║%n", posicaoErro);
                System.out.println("║ 📍 Parêntese de fechamento sem abertura   ║");
            } else if (parentesesNaoFechados > 0) {
                System.out.printf("║ 🔓 Parênteses não fechados: %-12d ║%n", parentesesNaoFechados);
            }
        }

        System.out.println("╚═══════════════════════════════════════════╝");

        // Sugestões de exemplos
        if (!valida) {
            System.out.println("\n💡 Exemplos de expressões VÁLIDAS:");
            System.out.println("   • (2+3)");
            System.out.println("   • ((1+2)*3)");
            System.out.println("   • (2+3)*(5-1)");
            System.out.println("   • 2*(3+(4-1))");
        }
    }

    /**
     * Método auxiliar que faz verificação simples (sem detalhes)
     * Útil para integração com outros sistemas
     */
    public static boolean verificarBalanceamentoSimples(String expressao) {
        PilhaEncadeada<Character> pilha = new PilhaEncadeada<>();

        for (char c : expressao.toCharArray()) {
            if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                if (pilha.estaVazia()) {
                    return false;
                }
                pilha.pop();
            }
        }

        return pilha.estaVazia();
    }
}
