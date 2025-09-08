package br.com.pucgo.fila;

import br.com.pucgo.modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AppFila {
    public static void main(String[] args) {
        // Filas para clientes aguardando atendimento e pedidos aguardando processamento
        FilaSimples<Cliente> filaClientes = new FilaSimples<>(10);
        FilaSimples<Pedido> filaPedidos = new FilaSimples<>(10);

        // Cadastrar alguns clientes (enfileirar)
        filaClientes.enfileirar(new Cliente(
                "11111111111",
                "Ana Souza",
                "(62) 90000-0001",
                LocalDate.of(1990, 1, 10),
                new Endereco("Rua A", "100", null, "Centro", "Goiânia", "GO", "74000-000")
        ));
        filaClientes.enfileirar(new Cliente(
                "22222222222",
                "Bruno Lima",
                "(62) 90000-0002",
                LocalDate.of(1988, 5, 20),
                new Endereco("Rua B", "200", "Ap 12", "Setor Oeste", "Goiânia", "GO", "74100-000")
        ));
        filaClientes.enfileirar(new Cliente(
                "33333333333",
                "Carla Nunes",
                "(62) 90000-0003",
                LocalDate.of(1995, 8, 30),
                new Endereco("Av C", "300", null, "Campinas", "Goiânia", "GO", "74200-000")
        ));

        // Atender clientes por ordem de chegada e gerar pedidos
        while (!filaClientes.estaVazia()) {
            Cliente c = filaClientes.desenfileirar();
            Pedido p = new Pedido(c);
            p.adicionarItem(new ItemPedido("Camiseta", 2, new BigDecimal("49.90")));
            p.adicionarItem(new ItemPedido("Calça", 1, new BigDecimal("129.90")));
            p.aplicarDesconto(new BigDecimal("10.00"));
            filaPedidos.enfileirar(p);
            System.out.println("Gerado " + p);
        }

        System.out.println("\nProcessando pedidos (FIFO):");
        while (!filaPedidos.estaVazia()) {
            Pedido p = filaPedidos.desenfileirar();
            p.finalizar();
            System.out.println("Finalizado " + p);
        }

        System.out.println("\nFluxo com fila concluído.");
    }
}
