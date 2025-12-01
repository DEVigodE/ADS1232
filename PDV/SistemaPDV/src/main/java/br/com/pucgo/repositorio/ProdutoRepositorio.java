package br.com.pucgo.repositorio;

import br.com.pucgo.modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositório usando ArrayList para produtos (Lista)
 */
public class ProdutoRepositorio {
    private final List<Produto> produtos;

    public ProdutoRepositorio() {
        this.produtos = new ArrayList<>();
    }

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Optional<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public List<Produto> listarPorCategoria(String categoria) {
        return produtos.stream()
                .filter(p -> p.getCategoria().getNome().equalsIgnoreCase(categoria))
                .toList();
    }

    public int total() {
        return produtos.size();
    }
}

