package br.com.pucgo.setor;

import br.com.pucgo.model.Usuario;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Central de Suporte - Atendimento por ordem de chegada (FIFO - First In First Out)
 * Utiliza Queue (implementada com LinkedList)
 */
public class CentralSuporte extends SetorBase {
    private Queue<Usuario> fila;

    public CentralSuporte() {
        super("Central de Suporte");
        this.fila = new LinkedList<>();
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        fila.offer(usuario);
        System.out.println("[" + nomeSetor + "] Usuário registrado: " + usuario.getNome());
    }

    @Override
    public Usuario atender() {
        Usuario usuario = fila.poll();
        if (usuario != null) {
            System.out.println("[" + nomeSetor + "] Atendendo: " + usuario.getNome());
        } else {
            System.out.println("[" + nomeSetor + "] Nenhum usuário na fila.");
        }
        return usuario;
    }

    @Override
    public void exibirEstado() {
        exibirCabecalho();
        if (fila.isEmpty()) {
            System.out.println("Fila vazia.");
        } else {
            System.out.println("Política: FIFO (Primeiro a chegar, primeiro a ser atendido)");
            System.out.println("Fila de atendimento:");
            int posicao = 1;
            for (Usuario usuario : fila) {
                System.out.println("  " + posicao++ + ". " + usuario);
            }
        }
    }

    @Override
    public int quantidadeAguardando() {
        return fila.size();
    }
}

