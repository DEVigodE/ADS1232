package br.com.pucgo.exercicio4;

import br.com.pucgo.dominio.PilhaEncadeada;
import java.util.Scanner;

/**
 * Reversor de Frases usando Pilha Encadeada
 * Utiliza PilhaEncadeada<Character> para inverter frases digitadas
 */
public class ReversorFrase {
    private Scanner scanner;

    public ReversorFrase() {
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        exibirInstrucoes();

        while (true) {
            System.out.print("\n>>> Digite uma frase (ou 'SAIR' para encerrar): ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("SAIR")) {
                System.out.println("🚪 Encerrando programa... Até mais!");
                break;
            }

            if (entrada.trim().isEmpty()) {
                System.out.println("⚠️ Por favor, digite uma frase válida!");
                continue;
            }

            processarReversaoDeFrase(entrada);
        }

        scanner.close();
    }

    private void exibirInstrucoes() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    🔄 INVERSOR DE FRASES COM PILHA     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ ✏️  Digite qualquer frase ou palavra    ║");
        System.out.println("║ 🔄 A frase será invertida usando pilha ║");
        System.out.println("║ 🚪 Digite 'SAIR' para encerrar        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n💡 Como funciona: Cada caractere é empilhado (push) e depois");
        System.out.println("   desempilhado (pop) para formar a frase invertida!");
    }

    private void processarReversaoDeFrase(String fraseOriginal) {
        PilhaEncadeada<Character> pilhaCaracteres = new PilhaEncadeada<>();

        // Fase 1: Empilhar todos os caracteres (PUSH)
        System.out.println("\n📥 EMPILHANDO caracteres:");
        for (int i = 0; i < fraseOriginal.length(); i++) {
            char caractere = fraseOriginal.charAt(i);
            pilhaCaracteres.push(caractere);
            System.out.print("'" + caractere + "' → ");
        }
        System.out.println("PILHA");
        System.out.println("   📊 Total de caracteres empilhados: " + pilhaCaracteres.tamanho());

        // Fase 2: Desempilhar para formar frase invertida (POP)
        System.out.println("\n📤 DESEMPILHANDO para inverter:");
        StringBuilder fraseInvertida = new StringBuilder();

        System.out.print("   PILHA → ");
        while (!pilhaCaracteres.estaVazia()) {
            char caractere = pilhaCaracteres.pop();
            fraseInvertida.append(caractere);
            System.out.print("'" + caractere + "'");
        }
        System.out.println();

        // Resultado final
        exibirResultado(fraseOriginal, fraseInvertida.toString());
    }

    private void exibirResultado(String original, String invertida) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║              🎯 RESULTADO              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ 📝 Original : %-25s ║%n", "\"" + original + "\"");
        System.out.printf("║ 🔄 Invertida: %-25s ║%n", "\"" + invertida + "\"");
        System.out.printf("║ 📏 Tamanho : %-26d ║%n", original.length());
        System.out.println("╚════════════════════════════════════════╝");

        // Verificar se é palíndromo (bônus)
        String originalSemEspacos = original.replaceAll("\\s+", "").toLowerCase();
        String invertidaSemEspacos = invertida.replaceAll("\\s+", "").toLowerCase();

        if (originalSemEspacos.equals(invertidaSemEspacos)) {
            System.out.println("🎊 BÔNUS: Esta frase é um PALÍNDROMO!");
        }
    }
}
