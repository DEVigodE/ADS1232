package br.com.pucgo.setor;

import br.com.pucgo.model.Usuario;
import java.util.Stack;

/**
 * Laboratório de Projetos - Atendimento LIFO (Last In First Out)
 * Utiliza Stack (Pilha)
 */
public class LaboratorioProjetos extends SetorBase {
    private Stack<Usuario> pilha;

    public LaboratorioProjetos() {
        super("Laboratório de Projetos");
        this.pilha = new Stack<>();
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        pilha.push(usuario);
        System.out.println("[" + nomeSetor + "] Usuário registrado: " + usuario.getNome());
    }

    @Override
    public Usuario atender() {
        if (pilha.isEmpty()) {
            System.out.println("[" + nomeSetor + "] Nenhum usuário aguardando.");
            return null;
        }
        Usuario usuario = pilha.pop();
        System.out.println("[" + nomeSetor + "] Atendendo: " + usuario.getNome());
        return usuario;
    }

    @Override
    public void exibirEstado() {
        exibirCabecalho();
        if (pilha.isEmpty()) {
            System.out.println("Pilha vazia.");
        } else {
            System.out.println("Política: LIFO (Último a chegar, primeiro a ser atendido)");
            System.out.println("Pilha de atendimento (topo -> base):");
            int posicao = pilha.size();
            for (int i = pilha.size() - 1; i >= 0; i--) {
                System.out.println("  " + posicao-- + ". " + pilha.get(i));
            }
        }
    }

    @Override
    public int quantidadeAguardando() {
        return pilha.size();
    }
}

