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
    private Vendedor vendedor;
    private Empresa empresa;
    private List<ItemPedido> itens;
    private BigDecimal desconto; // absoluto
    private PedidoStatus status;
    private FormaPagamento formaPagamento;
    private Supervisor supervisorAprovacao;

    public Pedido(Cliente cliente, Vendedor vendedor, Empresa empresa) {
        this.id = UUID.randomUUID();
        this.data = LocalDateTime.now();
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.empresa = empresa;
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

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void solicitarAprovacao() {
        this.status = PedidoStatus.EM_APROVACAO;
    }

    public void aprovar(Supervisor supervisor) {
        if (status != PedidoStatus.EM_APROVACAO) {
            throw new IllegalStateException("Pedido não está em aprovação");
        }
        if (!supervisor.podeAprovar(getTotalLiquido())) {
            throw new IllegalStateException("Supervisor não tem limite para aprovar este valor");
        }
        this.supervisorAprovacao = supervisor;
        this.status = PedidoStatus.APROVADO;
    }

    public void reprovar() {
        this.status = PedidoStatus.REPROVADO;
    }

    public boolean precisaAprovacao(BigDecimal limiteAutomatico) {
        return getTotalLiquido().compareTo(limiteAutomatico) > 0;
    }

    public UUID getId() { return id; }
    public LocalDateTime getData() { return data; }
    public Cliente getCliente() { return cliente; }
    public Vendedor getVendedor() { return vendedor; }
    public Empresa getEmpresa() { return empresa; }
    public List<ItemPedido> getItens() { return itens; }
    public BigDecimal getDesconto() { return desconto; }
    public PedidoStatus getStatus() { return status; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public Supervisor getSupervisorAprovacao() { return supervisorAprovacao; }

    public void finalizar() { this.status = PedidoStatus.FINALIZADO; }
    public void cancelar() { this.status = PedidoStatus.CANCELADO; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente.getNomeCompleto() + '\'' +
                ", vendedor='" + vendedor.getNome() + '\'' +
                ", itens=" + itens.size() +
                ", bruto=" + getTotalBruto() +
                ", desconto=" + desconto +
                ", liquido=" + getTotalLiquido() +
                ", status=" + status +
                ", pagamento=" + formaPagamento +
                '}';
    }
}
