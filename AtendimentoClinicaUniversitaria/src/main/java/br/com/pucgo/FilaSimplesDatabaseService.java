package br.com.pucgo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço de Fila Simples integrado com Banco de Dados PostgreSQL
 * Implementa operações básicas de fila usando persistência em banco
 */
public class FilaSimplesDatabaseService {

    private static final String URL = "jdbc:postgresql://localhost:5433/clinica_universitaria";
    private static final String USER = "clinica_user";
    private static final String PASSWORD = "clinica_pass";

    private Connection connection;

    /**
     * Construtor - estabelece conexão com o banco
     */
    public FilaSimplesDatabaseService() {
        try {
            // Carrega o driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexão com banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver PostgreSQL não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar com banco de dados: " + e.getMessage());
            System.err.println("💡 Verifique se o Docker está rodando e o banco está ativo");
        }
    }

    /**
     * Adiciona um paciente na fila (será inserido automaticamente pela trigger)
     */
    public boolean adicionarPaciente(Paciente paciente) {
        String sql = "INSERT INTO clinica.pacientes (nome, idade, cpf, is_urgente) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getCpf());
            stmt.setBoolean(4, paciente.isUrgente());

            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("✅ Paciente " + paciente.getNome() + " adicionado à fila com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao adicionar paciente: " + e.getMessage());
            if (e.getMessage().contains("duplicate key")) {
                System.err.println("💡 CPF já existe no sistema");
            }
        }
        return false;
    }

    /**
     * Visualiza a fila atual ordenada por prioridade
     */
    public void exibirFilaAtual() {
        String sql = "SELECT * FROM clinica.v_fila_atual ORDER BY posicao_fila";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📋 FILA ATUAL DE ATENDIMENTO:");
            System.out.println("=" + repeat("=", 85));
            System.out.printf("%-4s %-20s %-5s %-15s %-12s %-8s%n",
                            "POS", "NOME", "IDADE", "CPF", "PRIORIDADE", "ESPERA");
            System.out.println(repeat("-", 85));

            int count = 0;
            while (rs.next()) {
                count++;
                System.out.printf("%-4d %-20s %-5d %-15s %-12s %-8.0f min%n",
                    rs.getInt("posicao_fila"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("cpf"),
                    rs.getString("tipo_prioridade"),
                    rs.getDouble("tempo_espera_minutos")
                );
            }

            if (count == 0) {
                System.out.println("🏥 Fila vazia - Todos os pacientes foram atendidos!");
            } else {
                System.out.println(repeat("-", 85));
                System.out.println("📊 Total de pacientes na fila: " + count);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao consultar fila: " + e.getMessage());
        }
    }

    /**
     * Chama o próximo paciente da fila
     */
    public Paciente chamarProximoPaciente() {
        String sql = "SELECT * FROM clinica.chamar_proximo_paciente()";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Paciente paciente = new Paciente(
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("cpf")
                );
                paciente.setUrgente(rs.getString("tipo_prioridade").equals("URGENTE"));

                System.out.println("🔔 CHAMANDO PRÓXIMO PACIENTE:");
                System.out.println("   Nome: " + paciente.getNome());
                System.out.println("   Idade: " + paciente.getIdade() + " anos");
                System.out.println("   CPF: " + paciente.getCpf());
                System.out.println("   Prioridade: " + rs.getString("tipo_prioridade"));
                System.out.println("   Tempo de espera: " + rs.getInt("tempo_espera_minutos") + " minutos");

                return paciente;
            } else {
                System.out.println("ℹ️ Não há pacientes na fila para chamar");
                return null;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao chamar próximo paciente: " + e.getMessage());
            return null;
        }
    }

    /**
     * Finaliza o atendimento de um paciente
     */
    public boolean finalizarAtendimento(String cpf, String tipoAtendimento, String observacoes, String medico) {
        // Primeiro busca o ID do paciente
        String sqlBusca = "SELECT id FROM clinica.pacientes WHERE cpf = ?";
        String sqlFinalizar = "SELECT clinica.finalizar_atendimento(?, ?, ?, ?)";

        try {
            // Busca o ID do paciente
            int pacienteId = -1;
            try (PreparedStatement stmtBusca = connection.prepareStatement(sqlBusca)) {
                stmtBusca.setString(1, cpf);
                try (ResultSet rs = stmtBusca.executeQuery()) {
                    if (rs.next()) {
                        pacienteId = rs.getInt("id");
                    } else {
                        System.err.println("❌ Paciente com CPF " + cpf + " não encontrado");
                        return false;
                    }
                }
            }

            // Finaliza o atendimento
            try (PreparedStatement stmtFinalizar = connection.prepareStatement(sqlFinalizar)) {
                stmtFinalizar.setInt(1, pacienteId);
                stmtFinalizar.setString(2, tipoAtendimento);
                stmtFinalizar.setString(3, observacoes);
                stmtFinalizar.setString(4, medico);

                try (ResultSet rs = stmtFinalizar.executeQuery()) {
                    if (rs.next() && rs.getBoolean(1)) {
                        System.out.println("✅ Atendimento finalizado com sucesso!");
                        return true;
                    } else {
                        System.err.println("❌ Não foi possível finalizar o atendimento");
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao finalizar atendimento: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove um paciente da fila
     */
    public boolean removerDaFila(String cpf, String motivo) {
        String sqlBusca = "SELECT id FROM clinica.pacientes WHERE cpf = ?";
        String sqlRemover = "SELECT clinica.remover_da_fila(?, ?)";

        try {
            // Busca o ID do paciente
            int pacienteId = -1;
            try (PreparedStatement stmtBusca = connection.prepareStatement(sqlBusca)) {
                stmtBusca.setString(1, cpf);
                try (ResultSet rs = stmtBusca.executeQuery()) {
                    if (rs.next()) {
                        pacienteId = rs.getInt("id");
                    } else {
                        System.err.println("❌ Paciente com CPF " + cpf + " não encontrado");
                        return false;
                    }
                }
            }

            // Remove da fila
            try (PreparedStatement stmtRemover = connection.prepareStatement(sqlRemover)) {
                stmtRemover.setInt(1, pacienteId);
                stmtRemover.setString(2, motivo);

                try (ResultSet rs = stmtRemover.executeQuery()) {
                    if (rs.next() && rs.getBoolean(1)) {
                        System.out.println("✅ Paciente removido da fila: " + motivo);
                        return true;
                    } else {
                        System.err.println("❌ Não foi possível remover o paciente da fila");
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao remover paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca a posição de um paciente na fila
     */
    public void consultarPosicaoPaciente(String cpf) {
        String sql = "SELECT * FROM clinica.obter_posicao_paciente(?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n📍 POSIÇÃO NA FILA:");
                    System.out.println("   CPF: " + cpf);
                    System.out.println("   Posição: " + rs.getInt("posicao") + "º na fila");
                    System.out.println("   Prioridade: " + rs.getString("tipo_prioridade"));
                    System.out.println("   Tempo esperando: " + rs.getInt("tempo_espera_minutos") + " minutos");
                    System.out.println("   Pacientes na frente: " + rs.getInt("pacientes_na_frente"));
                } else {
                    System.out.println("ℹ️ Paciente com CPF " + cpf + " não está na fila ou já foi atendido");
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao consultar posição: " + e.getMessage());
        }
    }

    /**
     * Exibe estatísticas do dia
     */
    public void exibirEstatisticasHoje() {
        String sql = "SELECT * FROM clinica.v_estatisticas_hoje";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📊 ESTATÍSTICAS DE HOJE:");
            System.out.println("=" + repeat("=", 50));

            if (rs.next()) {
                System.out.println("📈 Total de atendimentos: " + rs.getInt("total_atendimentos"));
                System.out.println("🚨 Casos urgentes: " + rs.getInt("urgentes_atendidos"));
                System.out.println("👴 Pacientes idosos: " + rs.getInt("idosos_atendidos"));
                System.out.println("📊 Idade média: " + String.format("%.1f", rs.getDouble("idade_media")) + " anos");
                System.out.println("⏰ Tempo médio de espera: " + String.format("%.1f", rs.getDouble("tempo_espera_medio")) + " min");

                if (rs.getInt("total_atendimentos") > 0) {
                    double taxaUrgencia = (rs.getInt("urgentes_atendidos") * 100.0) / rs.getInt("total_atendimentos");
                    double taxaIdosos = (rs.getInt("idosos_atendidos") * 100.0) / rs.getInt("total_atendimentos");
                    System.out.println("📊 Taxa de urgência: " + String.format("%.1f", taxaUrgencia) + "%");
                    System.out.println("📊 Taxa de idosos: " + String.format("%.1f", taxaIdosos) + "%");
                }
            } else {
                System.out.println("ℹ️ Nenhum atendimento realizado hoje");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar estatísticas: " + e.getMessage());
        }
    }

    /**
     * Lista todos os pacientes atendidos hoje
     */
    public void listarAtendidosHoje() {
        String sql = "SELECT p.nome, p.idade, p.cpf, a.data_atendimento, a.tempo_espera_minutos, " +
                    "a.tipo_atendimento, a.medico_responsavel " +
                    "FROM clinica.atendimentos a " +
                    "JOIN clinica.pacientes p ON a.paciente_id = p.id " +
                    "WHERE DATE(a.data_atendimento) = CURRENT_DATE " +
                    "ORDER BY a.data_atendimento";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📋 PACIENTES ATENDIDOS HOJE:");
            System.out.println("=" + repeat("=", 90));
            System.out.printf("%-20s %-5s %-15s %-8s %-20s %-15s%n",
                            "NOME", "IDADE", "CPF", "ESPERA", "TIPO", "MÉDICO");
            System.out.println(repeat("-", 90));

            int count = 0;
            while (rs.next()) {
                count++;
                System.out.printf("%-20s %-5d %-15s %-8d %-20s %-15s%n",
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("cpf"),
                    rs.getInt("tempo_espera_minutos"),
                    rs.getString("tipo_atendimento"),
                    rs.getString("medico_responsavel")
                );
            }

            if (count == 0) {
                System.out.println("ℹ️ Nenhum paciente foi atendido hoje");
            } else {
                System.out.println(repeat("-", 90));
                System.out.println("📊 Total de atendimentos hoje: " + count);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar atendidos: " + e.getMessage());
        }
    }

    /**
     * Testa a conexão com o banco
     */
    public boolean testarConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                // Executa uma query simples para testar
                try (PreparedStatement stmt = connection.prepareStatement("SELECT 1");
                     ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro na conexão: " + e.getMessage());
        }
        return false;
    }

    /**
     * Fecha a conexão com o banco
     */
    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Conexão com banco de dados fechada");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao fechar conexão: " + e.getMessage());
        }
    }

    /**
     * Método utilitário para repetir uma string (compatível com Java 8+)
     */
    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
