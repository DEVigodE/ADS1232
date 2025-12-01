package br.com.pucgo.model;

/**
 * Classe que representa um bibliotecário supervisor
 * Responsável por autorizar renovações especiais
 */
public class BibliotecarioSupervisor extends Funcionario {
    private String numeroRegistroProfissional;

    public BibliotecarioSupervisor(String matricula, String nome, String telefone, String email,
                                    String numeroRegistroProfissional) {
        super(matricula, nome, "Bibliotecário Supervisor", telefone, email);
        this.numeroRegistroProfissional = numeroRegistroProfissional;
    }

    public String getNumeroRegistroProfissional() {
        return numeroRegistroProfissional;
    }

    public void setNumeroRegistroProfissional(String numeroRegistroProfissional) {
        this.numeroRegistroProfissional = numeroRegistroProfissional;
    }

    public boolean autorizarRenovacaoEspecial(Emprestimo emprestimo, int diasAdicionais) {
        // Lógica de autorização
        if (diasAdicionais > 0 && diasAdicionais <= 30) {
            System.out.println("Renovação especial autorizada pelo supervisor " + getNome() +
                    " para o empréstimo ID: " + emprestimo.getId());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Bibliotecário Supervisor: %s | Registro: %s",
                getNome(), numeroRegistroProfissional);
    }
}

