package br.com.pucgo.exercicio5;

/**
 * Classe que representa uma corrida no sistema
 * Cada corrida possui um ID único, endereços de partida e destino, e um passageiro solicitante
 */
public class Corrida {
    private long id;
    private String enderecoPartida;
    private String enderecoDestino;
    private Passageiro passageiro;

    /**
     * Construtor padrão sem argumentos
     * O ID é gerado automaticamente usando System.currentTimeMillis()
     */
    public Corrida() {
        this.id = System.currentTimeMillis();
    }

    /**
     * Construtor que recebe os valores iniciais (exceto o ID)
     * @param enderecoPartida Endereço de onde o passageiro será coletado
     * @param enderecoDestino Endereço de destino da corrida
     * @param passageiro Passageiro que está solicitando a corrida
     */
    public Corrida(String enderecoPartida, String enderecoDestino, Passageiro passageiro) {
        this.id = System.currentTimeMillis();
        this.enderecoPartida = enderecoPartida;
        this.enderecoDestino = enderecoDestino;
        this.passageiro = passageiro;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnderecoPartida() {
        return enderecoPartida;
    }

    public void setEnderecoPartida(String enderecoPartida) {
        this.enderecoPartida = enderecoPartida;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    /**
     * Sobrescreve o toString para retornar a solicitação formatada
     * Ajusta a frase de acordo com o sexo do passageiro
     * @return String formatada com a solicitação da corrida
     */
    @Override
    public String toString() {
        if (passageiro == null) {
            return "Corrida sem passageiro definido";
        }

        String primeiroNome = passageiro.getPrimeiroNome();
        String verbo;

        // Ajusta o texto baseado no sexo do passageiro
        if (passageiro.getSexo() == Sexo.FEMININO) {
            verbo = "pede para pegá-la";
        } else {
            verbo = "pede para pegá-lo";
        }

        return primeiroNome + " " + verbo + " em " + enderecoPartida;
    }
}
