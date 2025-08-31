package br.com.pucgo.exercicio5;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Classe que gerencia informações de passageiros e corridas
 * Mantém listas de todos os passageiros e corridas cadastradas
 */
public class CentralDeInformacoes {
    private ArrayList<Passageiro> todosOsPassageiros;
    private ArrayList<Corrida> todasAsCorridas;

    /**
     * Construtor da classe CentralDeInformacoes
     * Inicializa as listas de passageiros e corridas
     */
    public CentralDeInformacoes() {
        this.todosOsPassageiros = new ArrayList<>();
        this.todasAsCorridas = new ArrayList<>();
    }

    // ========== MÉTODOS PARA PASSAGEIROS (mantidos do exercício anterior) ==========

    /**
     * Adiciona um passageiro à lista se ele atender aos critérios:
     * - Não pode haver outro passageiro com o mesmo e-mail
     * - Deve ter pelo menos 18 anos de idade
     * @param passageiro O passageiro a ser adicionado
     * @return true se o passageiro foi adicionado com sucesso, false caso contrário
     */
    public boolean adicionarPassageiro(Passageiro passageiro) {
        if (recuperarPassageiroPeloEmail(passageiro.getEmail()) != null) {
            return false; // E-mail já existe
        }

        if (!temIdadeMinima(passageiro.getDataDeNascimento())) {
            return false; // Menor de 18 anos
        }

        todosOsPassageiros.add(passageiro);
        return true;
    }

    /**
     * Recupera um passageiro pelo e-mail
     * @param email O e-mail do passageiro a ser recuperado
     * @return O passageiro com o e-mail especificado, ou null se não encontrado
     */
    public Passageiro recuperarPassageiroPeloEmail(String email) {
        for (Passageiro passageiro : todosOsPassageiros) {
            if (passageiro.getEmail().equals(email)) {
                return passageiro;
            }
        }
        return null;
    }

    public ArrayList<Passageiro> getTodosOsPassageiros() {
        return todosOsPassageiros;
    }

    public void setTodosOsPassageiros(ArrayList<Passageiro> todosOsPassageiros) {
        this.todosOsPassageiros = todosOsPassageiros;
    }

    public int getTotalPassageiros() {
        return todosOsPassageiros.size();
    }

    public String exibirInformacoesPassageiro(Passageiro passageiro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder info = new StringBuilder();

        info.append("=== INFORMAÇÕES DO PASSAGEIRO ===\n");
        info.append("Nome: ").append(passageiro.getNome()).append("\n");
        info.append("E-mail: ").append(passageiro.getEmail()).append("\n");
        info.append("Sexo: ").append(passageiro.getSexo()).append("\n");
        info.append("Data de Nascimento: ").append(sdf.format(passageiro.getDataDeNascimento())).append("\n");
        info.append("Idade: ").append(calcularIdade(passageiro.getDataDeNascimento())).append(" anos");

        return info.toString();
    }

    public int calcularIdade(Date dataDeNascimento) {
        Calendar hoje = Calendar.getInstance();
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataDeNascimento);

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--;
        }

        return idade;
    }

    public String listarTodosPassageiros() {
        if (todosOsPassageiros.isEmpty()) {
            return "Nenhum passageiro cadastrado.";
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== LISTA DE PASSAGEIROS CADASTRADOS ===\n");
        lista.append("Total: ").append(getTotalPassageiros()).append(" passageiro(s)\n\n");

        for (int i = 0; i < todosOsPassageiros.size(); i++) {
            Passageiro p = todosOsPassageiros.get(i);
            lista.append(String.format("%d. %s (%s)\n", (i + 1), p.getNome(), p.getEmail()));
        }

        return lista.toString();
    }

    public boolean temPassageiros() {
        return !todosOsPassageiros.isEmpty();
    }

    private boolean temIdadeMinima(Date dataDeNascimento) {
        Calendar hoje = Calendar.getInstance();
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataDeNascimento);

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--;
        }

        return idade >= 18;
    }

    // ========== MÉTODOS PARA CORRIDAS (novos) ==========

    /**
     * Adiciona uma corrida à lista
     * @param corrida A corrida a ser adicionada
     * @return true se a corrida foi adicionada com sucesso, false se já existe uma corrida com mesmo ID
     */
    public boolean adicionarCorrida(Corrida corrida) {
        // Verifica se já existe uma corrida com o mesmo ID
        if (recuperarCorridaPeloId(corrida.getId()) != null) {
            return false; // ID já existe
        }

        todasAsCorridas.add(corrida);
        return true;
    }

    /**
     * Recupera uma corrida pelo ID
     * @param id O ID da corrida a ser recuperada
     * @return A corrida com o ID especificado, ou null se não encontrada
     */
    public Corrida recuperarCorridaPeloId(long id) {
        for (Corrida corrida : todasAsCorridas) {
            if (corrida.getId() == id) {
                return corrida;
            }
        }
        return null;
    }

    /**
     * Recupera todas as corridas solicitadas por um passageiro específico
     * @param emailPassageiro E-mail do passageiro (identificador)
     * @return Lista de corridas do passageiro, lista vazia se não há corridas, ou null se passageiro não existe
     */
    public ArrayList<Corrida> recuperarCorridasDeUmPassageiro(String emailPassageiro) {
        // Primeiro verifica se o passageiro existe
        Passageiro passageiro = recuperarPassageiroPeloEmail(emailPassageiro);
        if (passageiro == null) {
            return null; // Passageiro não existe
        }

        // Cria lista para as corridas do passageiro
        ArrayList<Corrida> corridasDoPassageiro = new ArrayList<>();

        // Busca todas as corridas deste passageiro
        for (Corrida corrida : todasAsCorridas) {
            if (corrida.getPassageiro() != null &&
                corrida.getPassageiro().getEmail().equals(emailPassageiro)) {
                corridasDoPassageiro.add(corrida);
            }
        }

        return corridasDoPassageiro; // Retorna lista (pode estar vazia)
    }

    /**
     * Getter para a lista de todas as corridas
     * @return ArrayList contendo todas as corridas
     */
    public ArrayList<Corrida> getTodasAsCorridas() {
        return todasAsCorridas;
    }

    /**
     * Setter para a lista de todas as corridas
     * @param todasAsCorridas ArrayList de corridas
     */
    public void setTodasAsCorridas(ArrayList<Corrida> todasAsCorridas) {
        this.todasAsCorridas = todasAsCorridas;
    }

    /**
     * Método para obter o número total de corridas cadastradas
     * @return Número de corridas na lista
     */
    public int getTotalCorridas() {
        return todasAsCorridas.size();
    }

    /**
     * Método útil para listar todas as corridas de forma formatada
     * @return String com lista formatada de todas as corridas
     */
    public String listarTodasCorridas() {
        if (todasAsCorridas.isEmpty()) {
            return "Nenhuma corrida cadastrada.";
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== LISTA DE CORRIDAS CADASTRADAS ===\n");
        lista.append("Total: ").append(getTotalCorridas()).append(" corrida(s)\n\n");

        for (int i = 0; i < todasAsCorridas.size(); i++) {
            Corrida c = todasAsCorridas.get(i);
            lista.append(String.format("%d. %s\n", (i + 1), c.toString()));
            lista.append(String.format("   ID: %d | Destino: %s\n", c.getId(), c.getEnderecoDestino()));
        }

        return lista.toString();
    }

    /**
     * Verifica se existem corridas cadastradas
     * @return true se há corridas, false se não há
     */
    public boolean temCorridas() {
        return !todasAsCorridas.isEmpty();
    }

    /**
     * Método útil para listar as corridas de um passageiro de forma formatada
     * @param emailPassageiro E-mail do passageiro
     * @return String formatada com as corridas do passageiro
     */
    public String listarCorridasDeUmPassageiro(String emailPassageiro) {
        ArrayList<Corrida> corridas = recuperarCorridasDeUmPassageiro(emailPassageiro);

        if (corridas == null) {
            return "Passageiro não encontrado no sistema.";
        }

        if (corridas.isEmpty()) {
            Passageiro passageiro = recuperarPassageiroPeloEmail(emailPassageiro);
            return "O passageiro " + passageiro.getNome() + " não possui corridas cadastradas.";
        }

        StringBuilder lista = new StringBuilder();
        Passageiro passageiro = recuperarPassageiroPeloEmail(emailPassageiro);
        lista.append("=== CORRIDAS DE ").append(passageiro.getNome().toUpperCase()).append(" ===\n");
        lista.append("Total: ").append(corridas.size()).append(" corrida(s)\n\n");

        for (int i = 0; i < corridas.size(); i++) {
            Corrida c = corridas.get(i);
            lista.append(String.format("%d. %s\n", (i + 1), c.toString()));
            lista.append(String.format("   ID: %d | Destino: %s\n", c.getId(), c.getEnderecoDestino()));
        }

        return lista.toString();
    }
}
