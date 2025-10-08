package br.com.pucgo.repositorio;

import br.com.pucgo.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositório usando ArrayList para armazenamento de entidades (Lista)
 */
public class ClienteRepositorio {
    private final List<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public Optional<Cliente> buscarPorId(UUID id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public int total() {
        return clientes.size();
    }
}