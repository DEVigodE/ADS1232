package br.com.pucgo.pilha;

import br.com.pucgo.modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AppPilha {
    public static void main(String[] args) {
        PilhaEncadeada<Cliente> pilhaClientes = new PilhaEncadeada<>();
        PilhaEncadeada<Pedido> pilhaPedidos = new PilhaEncadeada<>();

        // Criar empresa e vendedor
        Empresa empresa = new Empresa(
                "98.765.432/0001-10",
                "Store Express",
                "Store Express Comércio LTDA",
                new Endereco("Rua Comercial", "500", null, "Setor Marista", "Goiânia", "GO", "74150-000")
        );
        Vendedor vendedor = new Vendedor("Maria Costa", "987.654.321-00", "maria@store.com");

        // Empilhar clientes (último que chega será atendido primeiro)
        pilhaClientes.empilhar(new Cliente(
                "44444444444",
                "Diego Costa",
                "(62) 90000-0004",
                LocalDate.of(1992, 3, 15),
                new Endereco("Rua D", "400", null, "Setor Sul", "Goiânia", "GO", "74300-000")
        ));
        pilhaClientes.empilhar(new Cliente(
                "55555555555",
                "Elaine Prado",
                "(62) 90000-0005",
                LocalDate.of(1991, 7, 22),
                new Endereco("Rua E", "500", "Casa 2", "Setor Bueno", "Goiânia", "GO", "74400-000")
        ));
        pilhaClientes.empilhar(new Cliente(
                "66666666666",
                "Fábio Neves",
                "(62) 90000-0006",
                LocalDate.of(1985, 11, 5),
                new Endereco("Av F", "600", null, "Jardim América", "Goiânia", "GO", "74500-000")
        ));

        // Atender clientes em ordem LIFO e gerar pedidos
        while (!pilhaClientes.estaVazia()) {
            Cliente c = pilhaClientes.desempilhar();
            Pedido p = new Pedido(c, vendedor, empresa);
            p.adicionarItem(new ItemPedido("Tênis", 1, new BigDecimal("199.90")));
            p.adicionarItem(new ItemPedido("Meias", 3, new BigDecimal("9.90")));
            p.aplicarDesconto(new BigDecimal("5.00"));
            pilhaPedidos.empilhar(p);
            System.out.println("Gerado " + p);
        }

        System.out.println("\nProcessando pedidos (LIFO):");
        while (!pilhaPedidos.estaVazia()) {
            Pedido p = pilhaPedidos.desempilhar();
            p.finalizar();
            System.out.println("Finalizado " + p);
        }

        System.out.println("\nFluxo com pilha concluído.");
    }
}
