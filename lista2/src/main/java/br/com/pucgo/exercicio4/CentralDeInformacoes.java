package br.com.pucgo.exercicio4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Classe que gerencia informações de passageiros
 * Mantém uma lista de todos os passageiros cadastrados
 */
public class CentralDeInformacoes {
    private ArrayList<Passageiro> todosOsPassageiros;

    /**
     * Construtor da classe CentralDeInformacoes
     * Inicializa a lista de passageiros
     */
    public CentralDeInformacoes() {
        this.todosOsPassageiros = new ArrayList<>();
    }

    /**
     * Adiciona um passageiro à lista se ele atender aos critérios:
     * - Não pode haver outro passageiro com o mesmo e-mail
     * - Deve ter pelo menos 18 anos de idade
     * @param passageiro O passageiro a ser adicionado
     * @return true se o passageiro foi adicionado com sucesso, false caso contrário
     */
    public boolean adicionarPassageiro(Passageiro passageiro) {
        // Verifica se já existe um passageiro com o mesmo e-mail
        if (recuperarPassageiroPeloEmail(passageiro.getEmail()) != null) {
            return false; // E-mail já existe
        }

        // Verifica se o passageiro tem pelo menos 18 anos
        if (!temIdadeMinima(passageiro.getDataDeNascimento())) {
            return false; // Menor de 18 anos
        }

        // Adiciona o passageiro à lista
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
        return null; // Passageiro não encontrado
    }

    /**
     * Getter para a lista de todos os passageiros
     * @return ArrayList contendo todos os passageiros
     */
    public ArrayList<Passageiro> getTodosOsPassageiros() {
        return todosOsPassageiros;
    }

    /**
     * Setter para a lista de todos os passageiros
     * @param todosOsPassageiros ArrayList de passageiros
     */
    public void setTodosOsPassageiros(ArrayList<Passageiro> todosOsPassageiros) {
        this.todosOsPassageiros = todosOsPassageiros;
    }

    /**
     * Método auxiliar para verificar se uma data corresponde a pelo menos 18 anos
     * @param dataDeNascimento Data de nascimento a ser verificada
     * @return true se a pessoa tem pelo menos 18 anos, false caso contrário
     */
    private boolean temIdadeMinima(Date dataDeNascimento) {
        Calendar hoje = Calendar.getInstance();
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataDeNascimento);

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        // Ajusta a idade se o aniversário ainda não ocorreu este ano
        if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--;
        }

        return idade >= 18;
    }

    /**
     * Método para obter o número total de passageiros cadastrados
     * @return Número de passageiros na lista
     */
    public int getTotalPassageiros() {
        return todosOsPassageiros.size();
    }

    /**
     * Método útil para exibir informações detalhadas de um passageiro
     * @param passageiro O passageiro cujas informações serão exibidas
     * @return String com as informações formatadas
     */
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

    /**
     * Método útil para calcular a idade de um passageiro
     * @param dataDeNascimento Data de nascimento
     * @return Idade em anos
     */
    public int calcularIdade(Date dataDeNascimento) {
        Calendar hoje = Calendar.getInstance();
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataDeNascimento);

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        // Ajusta a idade se o aniversário ainda não ocorreu este ano
        if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--;
        }

        return idade;
    }

    /**
     * Método útil para listar todos os passageiros de forma resumida
     * @return String com lista formatada de todos os passageiros
     */
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

    /**
     * Verifica se existe algum passageiro cadastrado
     * @return true se há passageiros, false se não há
     */
    public boolean temPassageiros() {
        return !todosOsPassageiros.isEmpty();
    }
}
