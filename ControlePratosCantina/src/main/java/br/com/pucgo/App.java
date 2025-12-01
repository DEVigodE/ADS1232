package br.com.pucgo;

/**
 * Simulação do Sistema de Controle de Pratos na Cantina
 * Demonstra o uso de pilha (Stack) para gerenciar pratos
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("=== SIMULAÇÃO: CONTROLE DE PRATOS NA CANTINA ===\n");

        // Criando uma pilha com capacidade para 10 pratos
        PilhaDePratos pilha = new PilhaDePratos(10);

        System.out.println("1. ADICIONANDO 5 PRATOS À PILHA:");
        System.out.println("----------------------------------");
        // Adicionar 5 pratos à pilha
        pilha.push("Prato Branco #1");
        pilha.push("Prato Branco #2");
        pilha.push("Prato Branco #3");
        pilha.push("Prato Branco #4");
        pilha.push("Prato Branco #5");

        // Exibir estado após adicionar os pratos
        pilha.exibirEstado();

        System.out.println("2. RETIRANDO 2 PRATOS DA PILHA:");
        System.out.println("----------------------------------");
        // Retirar 2 pratos, exibindo quais foram retirados
        String prato1 = pilha.pop();
        String prato2 = pilha.pop();

        System.out.println("\n3. CONSULTANDO O PRATO DO TOPO:");
        System.out.println("----------------------------------");
        // Mostrar qual prato está no topo após as remoções
        String pratoTopo = pilha.peek();
        if (pratoTopo != null) {
            System.out.println("Prato no topo: " + pratoTopo);
        }

        System.out.println("\n4. ESTADO FINAL DA PILHA:");
        System.out.println("----------------------------------");
        // Exibir o estado final da pilha
        pilha.exibirEstado();

        // DESAFIO EXTRA: Simulando erros
        System.out.println("5. DESAFIO EXTRA - SIMULANDO ERROS:");
        System.out.println("----------------------------------");

        // Tentando adicionar pratos até a pilha ficar cheia
        System.out.println("Tentando encher a pilha...");
        for (int i = 6; i <= 12; i++) {
            pilha.push("Prato Extra #" + i);
        }

        // Tentando esvaziar completamente a pilha
        System.out.println("\nTentando esvaziar completamente a pilha...");
        while (!pilha.isEmpty()) {
            pilha.pop();
        }

        // Tentando retirar de uma pilha vazia
        System.out.println("\nTentando retirar prato de pilha vazia:");
        pilha.pop();

        System.out.println("\n=== FIM DA SIMULAÇÃO ===");
    }
}
