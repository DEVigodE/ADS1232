package br.com.pucgo.exercicio2;

/**
 * Exercício 2: Sistema de Desfazer (Undo) Operações
 * Classe principal para executar o sistema de desfazer comandos
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIO 2: SISTEMA DE DESFAZER OPERAÇÕES ===\n");

        // Instanciar e executar o sistema de desfazer
        CentralDeInformacoes central = new CentralDeInformacoes();
        central.executar();
    }
}
