package br.com.pucgo.exercicio5;

/**
 * Exercício 5: Verificador de Parênteses Balanceados
 * Classe principal para executar o verificador de expressões matemáticas
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIO 5: VERIFICADOR DE PARÊNTESES BALANCEADOS ===\n");

        // Instanciar e executar o verificador de parênteses
        VerificadorParenteses verificador = new VerificadorParenteses();
        verificador.executar();
    }
}
