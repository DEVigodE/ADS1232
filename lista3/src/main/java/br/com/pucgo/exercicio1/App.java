package br.com.pucgo.exercicio1;

/**
 * Exercício 1: Histórico de Navegação Web
 * Classe principal para executar o simulador de navegador
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIO 1: HISTÓRICO DE NAVEGAÇÃO WEB ===\n");

        // Instanciar e executar o histórico de navegação
        HistoricoNavegacao navegador = new HistoricoNavegacao();
        navegador.executar();
    }
}
