package br.com.pucgo.pilha;

import java.util.NoSuchElementException;

public class PilhaEncadeada<T> {
    private static class No<E> {
        E valor;
        No<E> prox;
        No(E valor, No<E> prox) { this.valor = valor; this.prox = prox; }
    }

    private No<T> topo;
    private int tamanho;

    public void empilhar(T elemento) {
        topo = new No<>(elemento, topo);
        tamanho++;
    }

    public T desempilhar() {
        if (estaVazia()) throw new NoSuchElementException("Pilha vazia");
        T v = topo.valor;
        topo = topo.prox;
        tamanho--;
        return v;
    }

    public T espiar() {
        if (estaVazia()) throw new NoSuchElementException("Pilha vazia");
        return topo.valor;
    }

    public boolean estaVazia() { return topo == null; }
    public int tamanho() { return tamanho; }
}

