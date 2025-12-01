package br.com.pucgo.modelo;

import java.math.BigDecimal;
import java.util.UUID;

public class Supervisor {
    private UUID id;
    private String nome;
    private String cpf;
    private BigDecimal limiteAprovacao;

    public Supervisor(String nome, String cpf, BigDecimal limiteAprovacao) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.limiteAprovacao = limiteAprovacao;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public BigDecimal getLimiteAprovacao() { return limiteAprovacao; }

    public boolean podeAprovar(BigDecimal valor) {
        return valor.compareTo(limiteAprovacao) <= 0;
    }

    @Override
    public String toString() {
        return nome + " (Supervisor - Limite: R$ " + limiteAprovacao + ")";
    }
}


