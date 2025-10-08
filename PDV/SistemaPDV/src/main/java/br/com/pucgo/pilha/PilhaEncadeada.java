package br.com.pucgo.pilha;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class PilhaEncadeada<T> {
    private final Deque<T> pilha;

    public PilhaEncadeada() {
        this.pilha = new ArrayDeque<>();
    }

    public void empilhar(T elemento) {
        pilha.addFirst(elemento);
    }

    public T desempilhar() {
        if (estaVazia()) throw new NoSuchElementException("Pilha vazia");
        return pilha.removeFirst();
    }

    public T espiar() {
        if (estaVazia()) throw new NoSuchElementException("Pilha vazia");
        return pilha.peekFirst();
    }

    public boolean estaVazia() { return pilha.isEmpty(); }
    public int tamanho() { return pilha.size(); }
}
