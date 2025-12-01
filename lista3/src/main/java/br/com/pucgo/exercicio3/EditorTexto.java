package br.com.pucgo.exercicio3;

import br.com.pucgo.dominio.Pilha;
import java.util.Scanner;

/**
 * Editor de Texto Simples com funcionalidade de Backspace
 * Utiliza pilha para gerenciar caracteres digitados
 */
public class EditorTexto {
    private Pilha pilhaCaracteres;
    private Scanner scanner;

    public EditorTexto() {
        this.pilhaCaracteres = new Pilha(1000);
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        exibirInstrucoes();

        while (true) {
            System.out.print("\n>>> Digite: ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("ENTER")) {
                String textoFinal = construirTextoFinal();
                exibirResultadoFinal(textoFinal);
                break;
            }
            else if (entrada.equalsIgnoreCase("BACKSPACE")) {
                removerUltimoCaractere();
            }
            else if (entrada.equalsIgnoreCase("PREVIEW")) {
                exibirPreviewTexto();
            }
            else {
                adicionarCaracteres(entrada);
            }
        }

        scanner.close();
    }

    private void exibirInstrucoes() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      📝 EDITOR DE TEXTO SIMPLES      ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ ✏️  Digite texto para adicionar       ║");
        System.out.println("║ ⌫  Digite 'BACKSPACE' para apagar    ║");
        System.out.println("║ 👁️  Digite 'PREVIEW' para visualizar ║");
        System.out.println("║ ✅ Digite 'ENTER' para finalizar     ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void adicionarCaracteres(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            pilhaCaracteres.adicionar((int) caractere);
        }

        System.out.println("✏️ Adicionados " + texto.length() + " caracteres: \"" + texto + "\"");
        System.out.println("📊 Total de caracteres: " + pilhaCaracteres.tamanho());
    }

    private void removerUltimoCaractere() {
        if (!pilhaCaracteres.estaVazia()) {
            char removido = pilhaCaracteres.remover();
            System.out.println("⌫ Caractere '" + removido + "' removido!");
            System.out.println("📊 Caracteres restantes: " + pilhaCaracteres.tamanho());
        } else {
            System.out.println("🚫 Nada para remover - texto está vazio!");
        }
    }

    private void exibirPreviewTexto() {
        if (pilhaCaracteres.estaVazia()) {
            System.out.println("👁️ PREVIEW: [texto vazio]");
        } else {
            String preview = construirTextoTemporario();
            System.out.println("👁️ PREVIEW: \"" + preview + "\" (" + pilhaCaracteres.tamanho() + " chars)");
        }
    }

    private String construirTextoFinal() {
        StringBuilder texto = new StringBuilder();
        Pilha pilhaAuxiliar = new Pilha(1000);

        // Transferir para pilha auxiliar para manter ordem correta
        while (!pilhaCaracteres.estaVazia()) {
            pilhaAuxiliar.adicionar((int) pilhaCaracteres.remover());
        }

        // Construir texto na ordem correta
        while (!pilhaAuxiliar.estaVazia()) {
            texto.append((char) pilhaAuxiliar.remover());
        }

        return texto.toString();
    }

    private String construirTextoTemporario() {
        // Cria uma cópia temporária sem alterar a pilha original
        Pilha pilhaTemp = new Pilha(1000);
        Pilha pilhaAux = new Pilha(1000);

        // Copia elementos preservando a pilha original
        while (!pilhaCaracteres.estaVazia()) {
            int caractere = (int) pilhaCaracteres.remover();
            pilhaTemp.adicionar(caractere);
            pilhaAux.adicionar(caractere);
        }

        // Restaura a pilha original
        while (!pilhaAux.estaVazia()) {
            pilhaCaracteres.adicionar((int) pilhaAux.remover());
        }

        // Constrói o texto
        StringBuilder texto = new StringBuilder();
        while (!pilhaTemp.estaVazia()) {
            texto.insert(0, (char) pilhaTemp.remover());
        }

        return texto.toString();
    }

    private void exibirResultadoFinal(String texto) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           📄 TEXTO FINAL             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ \"" + texto + "\"");
        System.out.println("║ Total: " + texto.length() + " caracteres");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
