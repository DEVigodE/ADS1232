package br.com.pucgo.dominio.interfaces;

public interface IEstruturaDeDados {
    // Interface que define o contrato de uma Estrutura de Dados
    void adicionar(int valor);  // Insere um elemento
    char remover();              // Remove e retorna um elemento
    int tamanho();              // Retorna o número de elementos
    boolean estaVazia();        // Verifica se está vazia
}
