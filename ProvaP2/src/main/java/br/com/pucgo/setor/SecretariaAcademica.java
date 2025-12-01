package br.com.pucgo.setor;

import br.com.pucgo.model.Usuario;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Secretaria Acadêmica - Atendimento por prioridade
 * Utiliza PriorityQueue (Fila de Prioridade)
 * Menor valor de prioridade = maior prioridade
 */
public class SecretariaAcademica extends SetorBase {
    private PriorityQueue<Usuario> filaPrioridade;

    public SecretariaAcademica() {
        super("Secretaria Acadêmica");
        // Comparator: menor prioridade numérica = maior prioridade de atendimento
        this.filaPrioridade = new PriorityQueue<>(
            Comparator.comparingInt(Usuario::getPrioridade)
        );
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        if (usuario.getPrioridade() <= 0) {
            System.out.println("[" + nomeSetor + "] ERRO: Usuário deve ter prioridade > 0");
            return;
        }
        filaPrioridade.offer(usuario);
        System.out.println("[" + nomeSetor + "] Usuário registrado: " + usuario.getNome()
                         + " (Prioridade: " + usuario.getPrioridade() + ")");
    }

    @Override
    public Usuario atender() {
        Usuario usuario = filaPrioridade.poll();
        if (usuario != null) {
            System.out.println("[" + nomeSetor + "] Atendendo: " + usuario.getNome()
                             + " (Prioridade: " + usuario.getPrioridade() + ")");
        } else {
            System.out.println("[" + nomeSetor + "] Nenhum usuário aguardando.");
        }
        return usuario;
    }

    @Override
    public void exibirEstado() {
        exibirCabecalho();
        if (filaPrioridade.isEmpty()) {
            System.out.println("Fila vazia.");
        } else {
            System.out.println("Política: Por Prioridade (menor número = maior prioridade)");
            System.out.println("Fila de prioridade:");

            // Cria cópia para não alterar a fila original
            PriorityQueue<Usuario> copia = new PriorityQueue<>(
                Comparator.comparingInt(Usuario::getPrioridade)
            );
            copia.addAll(filaPrioridade);

            int posicao = 1;
            while (!copia.isEmpty()) {
                Usuario u = copia.poll();
                System.out.println("  " + posicao++ + ". " + u);
            }
        }
    }

    @Override
    public int quantidadeAguardando() {
        return filaPrioridade.size();
    }
}

