package br.com.pucgo.fila;

import java.util.NoSuchElementException;

public class FilaSimples<T> {
    private Object[] dados;
    private int inicio;
    private int fim;
    private int tamanho;

    public FilaSimples(int capacidade) {
        if (capacidade <= 0) throw new IllegalArgumentException("Capacidade deve ser > 0");
        this.dados = new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public void enfileirar(T elemento) {
        if (estaCheia()) throw new IllegalStateException("Fila cheia");
        dados[fim] = elemento;
        fim = (fim + 1) % dados.length;
        tamanho++;
    }

    @SuppressWarnings("unchecked")
    public T desenfileirar() {
        if (estaVazia()) throw new NoSuchElementException("Fila vazia");
        T elem = (T) dados[inicio];
        dados[inicio] = null;
        inicio = (inicio + 1) % dados.length;
        tamanho--;
        return elem;
    }

    @SuppressWarnings("unchecked")
    public T espiar() {
        if (estaVazia()) throw new NoSuchElementException("Fila vazia");
        return (T) dados[inicio];
    }

    public boolean estaVazia() { return tamanho == 0; }
    public boolean estaCheia() { return tamanho == dados.length; }
    public int tamanho() { return tamanho; }
    public int capacidade() { return dados.length; }
}

