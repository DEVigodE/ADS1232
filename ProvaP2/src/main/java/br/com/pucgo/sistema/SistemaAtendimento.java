package br.com.pucgo.sistema;

import br.com.pucgo.model.Usuario;
import br.com.pucgo.setor.Setor;
import br.com.pucgo.setor.CentralSuporte;
import br.com.pucgo.setor.LaboratorioProjetos;
import br.com.pucgo.setor.SecretariaAcademica;
import java.util.HashMap;
import java.util.Map;

/**
 * Sistema principal de gerenciamento de atendimento multissetorial
 */
public class SistemaAtendimento {
    private Map<String, Setor> setores;
    private HistoricoAtendimento historico;

    public SistemaAtendimento() {
        this.setores = new HashMap<>();
        this.historico = new HistoricoAtendimento();
        inicializarSetores();
    }

    /**
     * Inicializa os setores do sistema
     */
    private void inicializarSetores() {
        setores.put("SUPORTE", new CentralSuporte());
        setores.put("LABORATORIO", new LaboratorioProjetos());
        setores.put("SECRETARIA", new SecretariaAcademica());
    }

    /**
     * Registra um usuário em um setor específico
     */
    public void registrarUsuario(String codigoSetor, Usuario usuario) {
        Setor setor = setores.get(codigoSetor.toUpperCase());
        if (setor != null) {
            setor.registrarUsuario(usuario);
        } else {
            System.out.println("ERRO: Setor não encontrado - " + codigoSetor);
        }
    }

    /**
     * Realiza atendimento em um setor e registra no histórico
     */
    public void realizarAtendimento(String codigoSetor) {
        Setor setor = setores.get(codigoSetor.toUpperCase());
        if (setor != null) {
            Usuario usuarioAtendido = setor.atender();
            if (usuarioAtendido != null) {
                historico.adicionarRegistro(usuarioAtendido, setor.getNomeSetor());
            }
        } else {
            System.out.println("ERRO: Setor não encontrado - " + codigoSetor);
        }
    }

    /**
     * Exibe o estado de um setor específico
     */
    public void exibirEstadoSetor(String codigoSetor) {
        Setor setor = setores.get(codigoSetor.toUpperCase());
        if (setor != null) {
            setor.exibirEstado();
        } else {
            System.out.println("ERRO: Setor não encontrado - " + codigoSetor);
        }
    }

    /**
     * Exibe o estado de todos os setores
     */
    public void exibirEstadoTodosSetores() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   ESTADO DE TODOS OS SETORES          ║");
        System.out.println("╚════════════════════════════════════════╝");
        for (Setor setor : setores.values()) {
            setor.exibirEstado();
        }
    }

    /**
     * Consulta o último usuário atendido
     */
    public void consultarUltimoAtendimento() {
        historico.exibirUltimoAtendimento();
    }

    /**
     * Exibe o histórico completo de atendimentos
     */
    public void exibirHistoricoCompleto() {
        historico.exibirHistorico();
    }

    /**
     * Retorna o histórico de atendimentos
     */
    public HistoricoAtendimento getHistorico() {
        return historico;
    }

    /**
     * Retorna um setor específico
     */
    public Setor getSetor(String codigoSetor) {
        return setores.get(codigoSetor.toUpperCase());
    }
}

