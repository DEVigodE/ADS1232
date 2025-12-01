package br.com.pucgo.setor;

import br.com.pucgo.model.Usuario;

/**
 * Interface que define o contrato para todos os setores de atendimento
 */
public interface Setor {
    /**
     * Registra um usuário no setor
     */
    void registrarUsuario(Usuario usuario);

    /**
     * Realiza o atendimento do próximo usuário conforme a política do setor
     * @return o usuário atendido ou null se não houver usuários
     */
    Usuario atender();

    /**
     * Exibe o estado atual da fila/estrutura do setor
     */
    void exibirEstado();

    /**
     * Retorna o nome do setor
     */
    String getNomeSetor();

    /**
     * Retorna a quantidade de usuários aguardando atendimento
     */
    int quantidadeAguardando();
}

