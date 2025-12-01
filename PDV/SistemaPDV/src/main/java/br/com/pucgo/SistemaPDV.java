package br.com.pucgo;

import br.com.pucgo.modelo.*;
import br.com.pucgo.repositorio.*;
import br.com.pucgo.servico.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Sistema PDV completo usando Collections Framework:
 * - ArrayList/LinkedList: Repositórios de entidades (Cliente, Produto, Pedido)
 * - Stack: Histórico de operações (desfazer/refazer)
 * - Queue: Processamento sequencial de pedidos
 */
public class SistemaPDV {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA PDV - Collections Framework ===\n");

        // ========== LISTAS (ArrayList/LinkedList) - Cadastro de Entidades ==========
        System.out.println("--- CADASTRO DE ENTIDADES (ArrayList/LinkedList) ---\n");

        ClienteRepositorio clienteRepo = new ClienteRepositorio();
        ProdutoRepositorio produtoRepo = new ProdutoRepositorio();
        PedidoRepositorio pedidoRepo = new PedidoRepositorio();

        // Cadastrar Empresa
        Empresa empresa = new Empresa(
                "12.345.678/0001-90",
                "Tech Store",
                "Tech Store Comércio de Eletrônicos LTDA",
                new Endereco("Av. Principal", "1000", null, "Centro", "Goiânia", "GO", "74000-000")
        );
        System.out.println("✓ Empresa cadastrada: " + empresa);

        // Cadastrar Clientes
        Cliente cliente1 = new Cliente(
                "111.111.111-11",
                "Ana Silva",
                "(62) 98888-1111",
                LocalDate.of(1990, 3, 15),
                new Endereco("Rua A", "100", null, "Setor Sul", "Goiânia", "GO", "74001-000")
        );
        Cliente cliente2 = new Cliente(
                "222.222.222-22",
                "Bruno Santos",
                "(62) 98888-2222",
                LocalDate.of(1985, 7, 20),
                new Endereco("Rua B", "200", "Ap 301", "Setor Oeste", "Goiânia", "GO", "74002-000")
        );
        Cliente cliente3 = new Cliente(
                "333.333.333-33",
                "Carla Oliveira",
                "(62) 98888-3333",
                LocalDate.of(1995, 12, 10),
                new Endereco("Av. C", "300", null, "Setor Bueno", "Goiânia", "GO", "74003-000")
        );

        clienteRepo.adicionar(cliente1);
        clienteRepo.adicionar(cliente2);
        clienteRepo.adicionar(cliente3);
        System.out.println("✓ " + clienteRepo.total() + " clientes cadastrados");

        // Cadastrar Categorias e Produtos
        Categoria catEletronicos = new Categoria("Eletrônicos", "Smartphones");
        Categoria catInformatica = new Categoria("Informática", "Notebooks");
        Categoria catAcessorios = new Categoria("Acessórios", "Periféricos");

        Produto prod1 = new Produto("Smartphone X", "Última geração", new BigDecimal("2500.00"), 10, catEletronicos, "TechBrand");
        Produto prod2 = new Produto("Notebook Pro", "16GB RAM, 512GB SSD", new BigDecimal("4500.00"), 5, catInformatica, "CompBrand");
        Produto prod3 = new Produto("Mouse Wireless", "Ergonômico", new BigDecimal("150.00"), 30, catAcessorios, "AccBrand");
        Produto prod4 = new Produto("Teclado Mecânico", "RGB", new BigDecimal("450.00"), 15, catAcessorios, "AccBrand");

        produtoRepo.adicionar(prod1);
        produtoRepo.adicionar(prod2);
        produtoRepo.adicionar(prod3);
        produtoRepo.adicionar(prod4);
        System.out.println("✓ " + produtoRepo.total() + " produtos cadastrados");

        // Cadastrar Vendedores e Supervisor
        Vendedor vendedor1 = new Vendedor("João Silva", "444.444.444-44", "joao@techstore.com");
        Vendedor vendedor2 = new Vendedor("Maria Costa", "555.555.555-55", "maria@techstore.com");
        Supervisor supervisor = new Supervisor("Carlos Almeida", "666.666.666-66", new BigDecimal("10000.00"));

        System.out.println("✓ Vendedores e supervisor cadastrados\n");

        // ========== FILA (Queue) - Processamento Sequencial de Pedidos ==========
        System.out.println("--- PROCESSAMENTO DE PEDIDOS (Queue) ---\n");

        ProcessadorPedidos processador = new ProcessadorPedidos();
        HistoricoOperacoes historico = new HistoricoOperacoes();

        // Criar Pedido 1 - Valor baixo (não precisa aprovação)
        Pedido pedido1 = new Pedido(cliente1, vendedor1, empresa);
        pedido1.adicionarItem(new ItemPedido("Mouse Wireless", 2, prod3.getValorUnitario()));
        pedido1.adicionarItem(new ItemPedido("Teclado Mecânico", 1, prod4.getValorUnitario()));
        pedido1.setFormaPagamento(FormaPagamento.PIX);
        pedido1.aplicarDesconto(new BigDecimal("50.00"));

        System.out.println("Pedido 1 criado: " + pedido1);
        historico.registrarOperacao("CRIAR_PEDIDO", "Pedido criado para " + cliente1.getNomeCompleto(), pedido1);
        processador.enfileirar(pedido1);
        pedidoRepo.adicionar(pedido1);

        // Criar Pedido 2 - Valor alto (precisa aprovação supervisor)
        Pedido pedido2 = new Pedido(cliente2, vendedor2, empresa);
        pedido2.adicionarItem(new ItemPedido("Notebook Pro", 3, prod2.getValorUnitario()));
        pedido2.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        pedido2.aplicarDesconto(new BigDecimal("500.00"));

        System.out.println("Pedido 2 criado: " + pedido2);
        historico.registrarOperacao("CRIAR_PEDIDO", "Pedido criado para " + cliente2.getNomeCompleto(), pedido2);
        processador.enfileirar(pedido2);
        pedidoRepo.adicionar(pedido2);

        // Criar Pedido 3 - Valor médio
        Pedido pedido3 = new Pedido(cliente3, vendedor1, empresa);
        pedido3.adicionarItem(new ItemPedido("Smartphone X", 2, prod1.getValorUnitario()));
        pedido3.setFormaPagamento(FormaPagamento.CARTAO_DEBITO);

        System.out.println("Pedido 3 criado: " + pedido3);
        historico.registrarOperacao("CRIAR_PEDIDO", "Pedido criado para " + cliente3.getNomeCompleto(), pedido3);
        processador.enfileirar(pedido3);
        pedidoRepo.adicionar(pedido3);

        System.out.println("\n✓ " + processador.totalNaFila() + " pedidos na fila de processamento\n");

        // Processar pedidos da fila
        BigDecimal limiteAutomatico = new BigDecimal("5000.00");

        while (processador.temPedidosNaFila()) {
            Pedido pedido = processador.processarProximo();

            if (pedido.precisaAprovacao(limiteAutomatico)) {
                System.out.println("⚠ Pedido excede limite de R$ " + limiteAutomatico);
                processador.enviarParaAprovacao(pedido);
                historico.registrarOperacao("ENVIAR_APROVACAO", "Pedido enviado para aprovação", pedido);
            } else {
                pedido.finalizar();
                System.out.println("✓ Pedido finalizado automaticamente: " + pedido.getId());
                historico.registrarOperacao("FINALIZAR_PEDIDO", "Pedido finalizado", pedido);
            }
            System.out.println();
        }

        // Processar fila de aprovação
        if (processador.temPedidosEmAprovacao()) {
            System.out.println("--- APROVAÇÃO DE PEDIDOS PELO SUPERVISOR ---\n");

            while (processador.temPedidosEmAprovacao()) {
                Pedido pedido = processador.proximoPedidoAprovacao();
                System.out.println("Supervisor " + supervisor.getNome() + " analisando: " + pedido.getId());

                try {
                    pedido.aprovar(supervisor);
                    pedido.finalizar();
                    System.out.println("✓ Pedido aprovado e finalizado: " + pedido.getTotalLiquido());
                    historico.registrarOperacao("APROVAR_PEDIDO", "Pedido aprovado por supervisor", pedido);
                } catch (IllegalStateException e) {
                    pedido.reprovar();
                    System.out.println("✗ Pedido reprovado: " + e.getMessage());
                    historico.registrarOperacao("REPROVAR_PEDIDO", "Pedido reprovado", pedido);
                }
                System.out.println();
            }
        }

        // ========== PILHA (Stack) - Histórico de Operações (Desfazer/Refazer) ==========
        System.out.println("--- HISTÓRICO DE OPERAÇÕES (Stack) ---\n");

        System.out.println("Total de operações registradas: " + historico.tamanhoHistorico());

        System.out.println("\nDesfazendo últimas 2 operações:");
        if (historico.podeDesfazer()) {
            HistoricoOperacoes.Operacao op1 = historico.desfazer();
            System.out.println("↶ Desfeito: " + op1);
        }
        if (historico.podeDesfazer()) {
            HistoricoOperacoes.Operacao op2 = historico.desfazer();
            System.out.println("↶ Desfeito: " + op2);
        }

        System.out.println("\nRefazendo 1 operação:");
        if (historico.podeRefazer()) {
            HistoricoOperacoes.Operacao op = historico.refazer();
            System.out.println("↷ Refeito: " + op);
        }

        // ========== RESUMO FINAL ==========
        System.out.println("\n=== RESUMO FINAL DO SISTEMA ===\n");
        System.out.println("📊 Estatísticas:");
        System.out.println("  • Clientes cadastrados: " + clienteRepo.total());
        System.out.println("  • Produtos cadastrados: " + produtoRepo.total());
        System.out.println("  • Pedidos realizados: " + pedidoRepo.total());
        System.out.println("  • Operações no histórico: " + historico.tamanhoHistorico());

        System.out.println("\n📦 Status dos Pedidos:");
        for (Pedido p : pedidoRepo.listarTodos()) {
            System.out.println("  • " + p.getCliente().getNomeCompleto() +
                             " - R$ " + p.getTotalLiquido() +
                             " - " + p.getStatus() +
                             " (" + p.getFormaPagamento() + ")");
        }

        System.out.println("\n✓ Sistema PDV executado com sucesso!");
        System.out.println("\n=== Collections Framework Utilizadas ===");
        System.out.println("• ArrayList: ClienteRepositorio, ProdutoRepositorio");
        System.out.println("• LinkedList: PedidoRepositorio, Queue (ProcessadorPedidos)");
        System.out.println("• Stack: HistoricoOperacoes (desfazer/refazer)");
        System.out.println("• Queue: ProcessadorPedidos (filas de processamento e aprovação)");
    }
}

