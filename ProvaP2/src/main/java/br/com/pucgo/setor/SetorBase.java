package br.com.pucgo.setor;

/**
 * Classe abstrata que implementa comportamentos comuns a todos os setores
 */
public abstract class SetorBase implements Setor {
    protected String nomeSetor;

    public SetorBase(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    @Override
    public String getNomeSetor() {
        return nomeSetor;
    }

    /**
     * Método auxiliar para exibir cabeçalho do estado
     */
    protected void exibirCabecalho() {
        System.out.println("\n========================================");
        System.out.println("SETOR: " + nomeSetor);
        System.out.println("Usuários aguardando: " + quantidadeAguardando());
        System.out.println("========================================");
    }
}

