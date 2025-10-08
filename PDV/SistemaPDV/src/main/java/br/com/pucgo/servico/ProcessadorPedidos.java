package br.com.pucgo.servico;

import br.com.pucgo.modelo.Pedido;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Processamento sequencial de pedidos usando Queue (Fila)
 */
public class ProcessadorPedidos {
    private final Queue<Pedido> filaPedidos;
    private final Queue<Pedido> filaAprovacao;

    public ProcessadorPedidos() {
        this.filaPedidos = new LinkedList<>();
        this.filaAprovacao = new LinkedList<>();
    }

    /**
     * Adiciona pedido na fila de processamento
     */
    public void enfileirar(Pedido pedido) {
        filaPedidos.add(pedido);
        System.out.println("✓ Pedido enfileirado para processamento: " + pedido.getId());
    }

    /**
     * Processa próximo pedido da fila
     */
    public Pedido processarProximo() {
        if (filaPedidos.isEmpty()) {
            throw new IllegalStateException("Não há pedidos para processar");
        }
        Pedido pedido = filaPedidos.poll();
        System.out.println("⚙ Processando pedido: " + pedido.getId());
        return pedido;
    }

    /**
     * Envia pedido para fila de aprovação do supervisor
     */
    public void enviarParaAprovacao(Pedido pedido) {
        pedido.solicitarAprovacao();
        filaAprovacao.add(pedido);
        System.out.println("⏳ Pedido enviado para aprovação: " + pedido.getId());
    }

    /**
     * Processa próximo pedido da fila de aprovação
     */
    public Pedido proximoPedidoAprovacao() {
        if (filaAprovacao.isEmpty()) {
            throw new IllegalStateException("Não há pedidos aguardando aprovação");
        }
        return filaAprovacao.poll();
    }

    /**
     * Verifica pedido no início da fila sem remover
     */
    public Pedido espiarProximo() {
        return filaPedidos.peek();
    }

    /**
     * Verifica próximo pedido em aprovação sem remover
     */
    public Pedido espiarProximoAprovacao() {
        return filaAprovacao.peek();
    }

    public boolean temPedidosNaFila() {
        return !filaPedidos.isEmpty();
    }

    public boolean temPedidosEmAprovacao() {
        return !filaAprovacao.isEmpty();
    }

    public int totalNaFila() {
        return filaPedidos.size();
    }

    public int totalEmAprovacao() {
        return filaAprovacao.size();
    }

    public void limparFilas() {
        filaPedidos.clear();
        filaAprovacao.clear();
    }
}
