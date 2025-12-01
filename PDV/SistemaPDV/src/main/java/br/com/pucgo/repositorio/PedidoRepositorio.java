package br.com.pucgo.repositorio;

import br.com.pucgo.modelo.Pedido;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositório usando LinkedList para pedidos (Lista)
 */
public class PedidoRepositorio {
    private final List<Pedido> pedidos;

    public PedidoRepositorio() {
        this.pedidos = new LinkedList<>();
    }

    public void adicionar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Optional<Pedido> buscarPorId(UUID id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Pedido> listarTodos() {
        return new LinkedList<>(pedidos);
    }

    public int total() {
        return pedidos.size();
    }
}


