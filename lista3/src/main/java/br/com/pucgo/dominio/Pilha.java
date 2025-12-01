package br.com.pucgo.dominio;

import br.com.pucgo.dominio.interfaces.IEstruturaDeDados;

// Implementação de uma Pilha (LIFO - Last In, First Out)
public class Pilha implements IEstruturaDeDados {
    private int[] elementos;  // Array para armazenar os valores
    private int topo;         // Indica a posição do último elemento inserido

    public Pilha(int capacidade) { // 10
        this.elementos = new int[capacidade];
        this.topo = -1; // Pilha vazia
    }

    // Insere um novo valor no topo da pilha
    @Override
    public void adicionar(int valor) { // push()
        if (topo == elementos.length - 1) {
            throw new RuntimeException("A pilha está cheia!");
        }
        elementos[++topo] = valor;
    }

    // Remove o valor do topo da pilha e o retorna
    @Override
    public char remover() { // pop()
        if (estaVazia()) {
            throw new RuntimeException("A pilha está vazia!");
        }
        return (char)elementos[topo--];
    }

    // Retorna o tamanho atual da pilha
    @Override
    public int tamanho() {
        return topo + 1;
    }

    // Verifica se a pilha está vazia
    @Override
    public boolean estaVazia() {
        return topo == -1;
    }

    // Retorna o elemento do topo sem removê-lo
    public int peek() {
        if (estaVazia()) {
            throw new RuntimeException("A pilha está vazia!");
        }
        return elementos[topo];
    }
}