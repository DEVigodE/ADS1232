package br.com.pucgo.dominio;

/**
 * Implementação de uma Pilha Encadeada genérica (LIFO - Last In, First Out)
 * Utiliza nós encadeados ao invés de array
 */
public class PilhaEncadeada<T> {

    // Classe interna para representar um nó da pilha
    private class No {
        T dado;
        No proximo;

        public No(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No topo;
    private int tamanho;

    public PilhaEncadeada() {
        this.topo = null;
        this.tamanho = 0;
    }

    /**
     * Empilha um elemento no topo da pilha
     */
    public void push(T elemento) {
        No novoNo = new No(elemento);
        novoNo.proximo = topo;
        topo = novoNo;
        tamanho++;
    }

    /**
     * Desempilha e retorna o elemento do topo da pilha
     */
    public T pop() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia - não é possível remover elemento");
        }

        T elemento = topo.dado;
        topo = topo.proximo;
        tamanho--;
        return elemento;
    }

    /**
     * Retorna o elemento do topo sem removê-lo
     */
    public T peek() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia - não há elemento no topo");
        }
        return topo.dado;
    }

    /**
     * Verifica se a pilha está vazia
     */
    public boolean estaVazia() {
        return topo == null;
    }

    /**
     * Retorna o número de elementos na pilha
     */
    public int tamanho() {
        return tamanho;
    }
}
