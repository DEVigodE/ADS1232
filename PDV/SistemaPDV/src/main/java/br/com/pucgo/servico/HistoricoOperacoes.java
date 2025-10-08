package br.com.pucgo.servico;

import br.com.pucgo.modelo.Pedido;

import java.util.Stack;

/**
 * Controle de histórico usando Stack para desfazer/refazer operações (Pilha)
 */
public class HistoricoOperacoes {
    private final Stack<Operacao> historicoDesfazer;
    private final Stack<Operacao> historicoRefazer;

    public HistoricoOperacoes() {
        this.historicoDesfazer = new Stack<>();
        this.historicoRefazer = new Stack<>();
    }

    public void registrarOperacao(String tipo, String descricao, Pedido pedido) {
        Operacao operacao = new Operacao(tipo, descricao, pedido);
        historicoDesfazer.push(operacao);
        historicoRefazer.clear(); // Limpa histórico de refazer ao fazer nova operação
    }

    public Operacao desfazer() {
        if (historicoDesfazer.isEmpty()) {
            throw new IllegalStateException("Não há operações para desfazer");
        }
        Operacao operacao = historicoDesfazer.pop();
        historicoRefazer.push(operacao);
        return operacao;
    }

    public Operacao refazer() {
        if (historicoRefazer.isEmpty()) {
            throw new IllegalStateException("Não há operações para refazer");
        }
        Operacao operacao = historicoRefazer.pop();
        historicoDesfazer.push(operacao);
        return operacao;
    }

    public boolean podeDesfazer() {
        return !historicoDesfazer.isEmpty();
    }

    public boolean podeRefazer() {
        return !historicoRefazer.isEmpty();
    }

    public int tamanhoHistorico() {
        return historicoDesfazer.size();
    }

    public void limparHistorico() {
        historicoDesfazer.clear();
        historicoRefazer.clear();
    }

    public static class Operacao {
        private final String tipo;
        private final String descricao;
        private final Pedido pedido;
        private final long timestamp;

        public Operacao(String tipo, String descricao, Pedido pedido) {
            this.tipo = tipo;
            this.descricao = descricao;
            this.pedido = pedido;
            this.timestamp = System.currentTimeMillis();
        }

        public String getTipo() { return tipo; }
        public String getDescricao() { return descricao; }
        public Pedido getPedido() { return pedido; }
        public long getTimestamp() { return timestamp; }

        @Override
        public String toString() {
            return tipo + ": " + descricao + " (Pedido ID: " + pedido.getId() + ")";
        }
    }
}

