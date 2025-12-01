package br.com.pucgo.fila;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class FilaSimples<T> {
    private final ArrayDeque<T> fila;
    private final int capacidade;

    public FilaSimples(int capacidade) {
        if (capacidade <= 0) throw new IllegalArgumentException("Capacidade deve ser > 0");
        this.capacidade = capacidade;
        this.fila = new ArrayDeque<>(capacidade);
    }

    public void enfileirar(T elemento) {
        if (estaCheia()) throw new IllegalStateException("Fila cheia");
        fila.addLast(elemento);
    }

    public T desenfileirar() {
        if (estaVazia()) throw new NoSuchElementException("Fila vazia");
        return fila.removeFirst();
    }

    public T espiar() {
        if (estaVazia()) throw new NoSuchElementException("Fila vazia");
        return fila.peekFirst();
    }

    public boolean estaVazia() { return fila.isEmpty(); }
    public boolean estaCheia() { return fila.size() == capacidade; }
    public int tamanho() { return fila.size(); }
    public int capacidade() { return capacidade; }
}
