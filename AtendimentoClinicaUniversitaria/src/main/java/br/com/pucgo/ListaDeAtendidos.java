package br.com.pucgo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa uma lista de pacientes atendidos
 * Permite armazenar, buscar e exibir informações dos pacientes atendidos
 * Inclui funcionalidades de estatísticas e relatórios
 */
public class ListaDeAtendidos {
    private List<Paciente> atendidos;

    public ListaDeAtendidos() {
        this.atendidos = new ArrayList<>();
    }

    /**
     * Adiciona um paciente na lista de atendidos
     */
    public void adicionarAtendido(Paciente paciente) {
        atendidos.add(paciente);
        System.out.println("Paciente " + paciente.getNome() + " foi atendido e adicionado à lista de atendidos");
    }

    /**
     * Verifica se um paciente específico já foi atendido
     */
    public boolean foiAtendido(String cpf) {
        for (Paciente paciente : atendidos) {
            if (paciente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca um paciente atendido pelo CPF
     */
    public Paciente buscarAtendido(String cpf) {
        for (Paciente paciente : atendidos) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        return null;
    }

    /**
     * Exibe todos os pacientes atendidos
     */
    public void exibirAtendidos() {
        System.out.println("\n=== PACIENTES ATENDIDOS ===");
        if (atendidos.isEmpty()) {
            System.out.println("Nenhum paciente foi atendido ainda.");
        } else {
            for (int i = 0; i < atendidos.size(); i++) {
                Paciente p = atendidos.get(i);
                System.out.println((i + 1) + ". " + p.getNome() +
                                 " - Idade: " + p.getIdade() +
                                 " - CPF: " + p.getCpf() +
                                 " - Prioridade: " + (p.temPrioridade() ? "SIM" : "NÃO") +
                                 " - Urgente: " + (p.isUrgente() ? "SIM" : "NÃO"));
            }
        }
        System.out.println("Total de atendidos: " + atendidos.size());
        System.out.println("============================\n");
    }

    // ========== NOVOS MÉTODOS PARA ESTATÍSTICAS E RELATÓRIOS ==========

    /**
     * Conta quantos pacientes já foram atendidos
     * @return número total de pacientes atendidos
     */
    public int contarAtendidos() {
        return atendidos.size();
    }

    /**
     * Calcula a média de idade dos pacientes atendidos
     * @return média de idade ou 0.0 se não há pacientes atendidos
     */
    public double calcularMediaIdade() {
        if (atendidos.isEmpty()) {
            return 0.0;
        }

        int somaIdades = 0;
        for (Paciente paciente : atendidos) {
            somaIdades += paciente.getIdade();
        }

        return (double) somaIdades / atendidos.size();
    }

    /**
     * Encontra o paciente mais idoso entre os atendidos
     * @return paciente mais idoso ou null se não há pacientes atendidos
     */
    public Paciente encontrarMaisIdoso() {
        if (atendidos.isEmpty()) {
            return null;
        }

        Paciente maisIdoso = atendidos.get(0);
        for (Paciente paciente : atendidos) {
            if (paciente.getIdade() > maisIdoso.getIdade()) {
                maisIdoso = paciente;
            }
        }

        return maisIdoso;
    }

    /**
     * Encontra o paciente mais jovem entre os atendidos
     * @return paciente mais jovem ou null se não há pacientes atendidos
     */
    public Paciente encontrarMaisJovem() {
        if (atendidos.isEmpty()) {
            return null;
        }

        Paciente maisJovem = atendidos.get(0);
        for (Paciente paciente : atendidos) {
            if (paciente.getIdade() < maisJovem.getIdade()) {
                maisJovem = paciente;
            }
        }

        return maisJovem;
    }

    /**
     * Conta quantos pacientes atendidos eram casos urgentes
     * @return número de casos urgentes atendidos
     */
    public int contarUrgentes() {
        int count = 0;
        for (Paciente paciente : atendidos) {
            if (paciente.isUrgente()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Conta quantos pacientes atendidos eram idosos (60+ anos)
     * @return número de idosos atendidos
     */
    public int contarIdosos() {
        int count = 0;
        for (Paciente paciente : atendidos) {
            if (paciente.getIdade() >= 60) {
                count++;
            }
        }
        return count;
    }

    /**
     * Exibe um relatório diário completo com estatísticas
     */
    public void exibirRelatorioDiario() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("=== RELATÓRIO DIÁRIO DE ATENDIMENTOS ===");
        System.out.println(repeat("=", 60));

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Data/Hora do Relatório: " + agora.format(formatter));

        System.out.println("\n📊 ESTATÍSTICAS GERAIS:");
        System.out.println("Total de pacientes atendidos: " + contarAtendidos());

        if (!atendidos.isEmpty()) {
            System.out.println("Média de idade: " + String.format("%.1f", calcularMediaIdade()) + " anos");

            Paciente maisIdoso = encontrarMaisIdoso();
            Paciente maisJovem = encontrarMaisJovem();

            System.out.println("Paciente mais idoso: " + maisIdoso.getNome() +
                             " (" + maisIdoso.getIdade() + " anos)");
            System.out.println("Paciente mais jovem: " + maisJovem.getNome() +
                             " (" + maisJovem.getIdade() + " anos)");
        }

        System.out.println("\n🚨 DISTRIBUIÇÃO POR PRIORIDADE:");
        System.out.println("Casos urgentes atendidos: " + contarUrgentes());
        System.out.println("Pacientes idosos atendidos: " + contarIdosos());
        System.out.println("Pacientes normais atendidos: " +
                         (contarAtendidos() - contarUrgentes() - contarIdosos()));

        if (contarAtendidos() > 0) {
            System.out.println("\n📈 PERCENTUAIS:");
            double percentualUrgentes = (contarUrgentes() * 100.0) / contarAtendidos();
            double percentualIdosos = (contarIdosos() * 100.0) / contarAtendidos();
            double percentualNormais = 100.0 - percentualUrgentes - percentualIdosos;

            System.out.println("Urgentes: " + String.format("%.1f", percentualUrgentes) + "%");
            System.out.println("Idosos: " + String.format("%.1f", percentualIdosos) + "%");
            System.out.println("Normais: " + String.format("%.1f", percentualNormais) + "%");
        }

        System.out.println("\n📋 LISTA COMPLETA DE ATENDIDOS:");
        if (atendidos.isEmpty()) {
            System.out.println("Nenhum paciente foi atendido hoje.");
        } else {
            for (int i = 0; i < atendidos.size(); i++) {
                Paciente p = atendidos.get(i);
                String tipo = p.isUrgente() ? "🚨 URGENTE" :
                             p.getIdade() >= 60 ? "👴 IDOSO" : "👤 NORMAL";
                System.out.println((i + 1) + ". " + tipo + " - " + p.getNome() +
                                 " (Idade: " + p.getIdade() + ", CPF: " + p.getCpf() + ")");
            }
        }

        System.out.println("\n" + repeat("=", 60));
        System.out.println("=== FIM DO RELATÓRIO DIÁRIO ===");
        System.out.println(repeat("=", 60));
    }

    /**
     * DESAFIO EXTRA: Exporta o relatório diário para um arquivo .txt
     * @param nomeArquivo nome do arquivo a ser criado (sem extensão)
     * @return true se o arquivo foi criado com sucesso, false caso contrário
     */
    public boolean exportarRelatorio(String nomeArquivo) {
        try {
            String nomeCompleto = nomeArquivo + ".txt";
            FileWriter writer = new FileWriter(nomeCompleto);

            // Cabeçalho do relatório
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            writer.write("============================================================\n");
            writer.write("===        RELATÓRIO DIÁRIO DE ATENDIMENTOS         ===\n");
            writer.write("===          CLÍNICA UNIVERSITÁRIA                  ===\n");
            writer.write("============================================================\n\n");
            writer.write("Data/Hora do Relatório: " + agora.format(formatter) + "\n\n");

            // Estatísticas gerais
            writer.write("📊 ESTATÍSTICAS GERAIS:\n");
            writer.write("----------------------------------------------------------\n");
            writer.write("Total de pacientes atendidos: " + contarAtendidos() + "\n");

            if (!atendidos.isEmpty()) {
                writer.write("Média de idade: " + String.format("%.1f", calcularMediaIdade()) + " anos\n");

                Paciente maisIdoso = encontrarMaisIdoso();
                Paciente maisJovem = encontrarMaisJovem();

                writer.write("Paciente mais idoso: " + maisIdoso.getNome() +
                           " (" + maisIdoso.getIdade() + " anos)\n");
                writer.write("Paciente mais jovem: " + maisJovem.getNome() +
                           " (" + maisJovem.getIdade() + " anos)\n\n");
            }

            // Distribuição por prioridade
            writer.write("🚨 DISTRIBUIÇÃO POR PRIORIDADE:\n");
            writer.write("----------------------------------------------------------\n");
            writer.write("Casos urgentes atendidos: " + contarUrgentes() + "\n");
            writer.write("Pacientes idosos atendidos: " + contarIdosos() + "\n");
            writer.write("Pacientes normais atendidos: " +
                       (contarAtendidos() - contarUrgentes() - contarIdosos()) + "\n\n");

            // Percentuais
            if (contarAtendidos() > 0) {
                writer.write("📈 PERCENTUAIS:\n");
                writer.write("----------------------------------------------------------\n");
                double percentualUrgentes = (contarUrgentes() * 100.0) / contarAtendidos();
                double percentualIdosos = (contarIdosos() * 100.0) / contarAtendidos();
                double percentualNormais = 100.0 - percentualUrgentes - percentualIdosos;

                writer.write("Urgentes: " + String.format("%.1f", percentualUrgentes) + "%\n");
                writer.write("Idosos: " + String.format("%.1f", percentualIdosos) + "%\n");
                writer.write("Normais: " + String.format("%.1f", percentualNormais) + "%\n\n");
            }

            // Lista completa
            writer.write("📋 LISTA COMPLETA DE ATENDIDOS:\n");
            writer.write("----------------------------------------------------------\n");
            if (atendidos.isEmpty()) {
                writer.write("Nenhum paciente foi atendido hoje.\n");
            } else {
                for (int i = 0; i < atendidos.size(); i++) {
                    Paciente p = atendidos.get(i);
                    String tipo = p.isUrgente() ? "URGENTE" :
                                 p.getIdade() >= 60 ? "IDOSO" : "NORMAL";
                    writer.write((i + 1) + ". " + tipo + " - " + p.getNome() +
                               " (Idade: " + p.getIdade() + ", CPF: " + p.getCpf() + ")\n");
                }
            }

            writer.write("\n============================================================\n");
            writer.write("===              FIM DO RELATÓRIO                   ===\n");
            writer.write("============================================================\n");

            writer.close();

            System.out.println("✅ Relatório exportado com sucesso para: " + nomeCompleto);
            return true;

        } catch (IOException e) {
            System.err.println("❌ Erro ao exportar relatório: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método utilitário para repetir strings (compatível com Java 8+)
     */
    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    // ========== MÉTODOS LEGADOS (MANTIDOS PARA COMPATIBILIDADE) ==========

    /**
     * @deprecated Use contarAtendidos() ao invés
     */
    public int getTotalAtendidos() {
        return contarAtendidos();
    }

    /**
     * @deprecated Use calcularMediaIdade() ao invés
     */
    public double getMediaIdade() {
        return calcularMediaIdade();
    }

    /**
     * Exibe estatísticas básicas (método legado)
     */
    public void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DE ATENDIMENTO ===");
        System.out.println("Total de pacientes atendidos: " + contarAtendidos());

        if (!atendidos.isEmpty()) {
            System.out.println("Média de idade dos atendidos: " + String.format("%.1f", calcularMediaIdade()) + " anos");

            Paciente maisIdoso = encontrarMaisIdoso();
            if (maisIdoso != null) {
                System.out.println("Paciente mais idoso: " + maisIdoso.getNome() +
                                 " (" + maisIdoso.getIdade() + " anos)");
            }

            System.out.println("Casos urgentes: " + contarUrgentes());
            System.out.println("Pacientes idosos (60+): " + contarIdosos());
        } else {
            System.out.println("Nenhum paciente foi atendido ainda.");
        }
        System.out.println("====================================\n");
    }
}
