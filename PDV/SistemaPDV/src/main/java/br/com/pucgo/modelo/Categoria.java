package br.com.pucgo.modelo;

import java.util.UUID;

public class Categoria {
    private UUID id;
    private String nome;
    private String subcategoria;

    public Categoria(String nome, String subcategoria) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.subcategoria = subcategoria;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getSubcategoria() { return subcategoria; }

    @Override
    public String toString() {
        return nome + " / " + subcategoria;
    }
}