package br.com.pucgo.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private UUID id;
    private LocalDateTime data;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private BigDecimal desconto; // absoluto
    private PedidoStatus status;

    public Pedido(Cliente cliente) {
        this.id = UUID.randomUUID();
        this.data = LocalDateTime.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.desconto = BigDecimal.ZERO;
        this.status = PedidoStatus.ABERTO;
    }

    public void adicionarItem(ItemPedido item) {
        if (status != PedidoStatus.ABERTO) throw new IllegalStateException("Pedido não está aberto");
        itens.add(item);
    }

    public void aplicarDesconto(BigDecimal valor) {
        if (valor == null || valor.signum() < 0) throw new IllegalArgumentException("Desconto inválido");
        this.desconto = valor;
    }

    public BigDecimal getTotalBruto() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalLiquido() {
        BigDecimal bruto = getTotalBruto();
        BigDecimal liq = bruto.subtract(desconto);
        return liq.signum() < 0 ? BigDecimal.ZERO : liq;
    }

    public UUID getId() { return id; }
    public LocalDateTime getData() { return data; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public BigDecimal getDesconto() { return desconto; }
    public PedidoStatus getStatus() { return status; }

    public void finalizar() { this.status = PedidoStatus.FINALIZADO; }
    public void cancelar() { this.status = PedidoStatus.CANCELADO; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", itens=" + itens.size() +
                ", bruto=" + getTotalBruto() +
                ", desconto=" + desconto +
                ", liquido=" + getTotalLiquido() +
                ", status=" + status +
                '}';
    }
}

