package br.com.pucgo.modelo;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal valorUnitario;
    private int quantidadeEstoque;
    private Categoria categoria;
    private String fabricante;

    public Produto(String nome, String descricao, BigDecimal valorUnitario, int quantidadeEstoque,
                   Categoria categoria, String fabricante) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.fabricante = fabricante;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public Categoria getCategoria() { return categoria; }
    public String getFabricante() { return fabricante; }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean temEstoque(int quantidade) {
        return quantidadeEstoque >= quantidade;
    }

    public void baixarEstoque(int quantidade) {
        if (!temEstoque(quantidade)) {
            throw new IllegalStateException("Estoque insuficiente");
        }
        quantidadeEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + valorUnitario + " (Estoque: " + quantidadeEstoque + ")";
    }
}

