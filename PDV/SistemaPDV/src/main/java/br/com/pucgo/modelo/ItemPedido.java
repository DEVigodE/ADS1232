package br.com.pucgo.modelo;

import java.math.BigDecimal;

public class ItemPedido {
    private String descricao;
    private int quantidade;
    private BigDecimal valorUnitario;

    public ItemPedido(String descricao, int quantidade, BigDecimal valorUnitario) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser > 0");
        if (valorUnitario == null || valorUnitario.signum() < 0) throw new IllegalArgumentException("Valor inválido");
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }

    public BigDecimal getSubtotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}

