package br.com.pucgo.sistema;

import br.com.pucgo.model.RegistroAtendimento;
import br.com.pucgo.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia o histórico geral de atendimentos
 * Utiliza ArrayList para manter o histórico
 */
public class HistoricoAtendimento {
    private List<RegistroAtendimento> historico;
    private RegistroAtendimento ultimoAtendimento;

    public HistoricoAtendimento() {
        this.historico = new ArrayList<>();
        this.ultimoAtendimento = null;
    }

    /**
     * Adiciona um registro ao histórico
     */
    public void adicionarRegistro(Usuario usuario, String setor) {
        if (usuario != null) {
            RegistroAtendimento registro = new RegistroAtendimento(usuario, setor);
            historico.add(registro);
            ultimoAtendimento = registro;
            System.out.println("✓ Registro adicionado ao histórico.");
        }
    }

    /**
     * Retorna o último usuário atendido
     */
    public RegistroAtendimento getUltimoAtendimento() {
        return ultimoAtendimento;
    }

    /**
     * Exibe todo o histórico de atendimentos
     */
    public void exibirHistorico() {
        System.out.println("\n========================================");
        System.out.println("HISTÓRICO GERAL DE ATENDIMENTOS");
        System.out.println("Total de atendimentos: " + historico.size());
        System.out.println("========================================");

        if (historico.isEmpty()) {
            System.out.println("Nenhum atendimento registrado.");
        } else {
            for (int i = 0; i < historico.size(); i++) {
                System.out.println((i + 1) + ". " + historico.get(i));
            }
        }
    }

    /**
     * Exibe informação do último atendimento
     */
    public void exibirUltimoAtendimento() {
        System.out.println("\n========================================");
        System.out.println("ÚLTIMO ATENDIMENTO");
        System.out.println("========================================");

        if (ultimoAtendimento != null) {
            System.out.println(ultimoAtendimento);
        } else {
            System.out.println("Nenhum atendimento realizado ainda.");
        }
    }

    /**
     * Retorna a quantidade total de atendimentos
     */
    public int getTotalAtendimentos() {
        return historico.size();
    }

    /**
     * Retorna lista de registros (para consultas)
     */
    public List<RegistroAtendimento> getHistorico() {
        return new ArrayList<>(historico); // Retorna cópia para encapsulamento
    }
}

