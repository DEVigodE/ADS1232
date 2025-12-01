package br.com.pucgo;

/**
 * Fila de Prioridade usando Lista Encadeada
 * Implementa três níveis de prioridade com nós encadeados
 * Oferece melhor performance para remoções frequentes
 */
public class FilaPrioridadeEncadeada {

    private static class No {
        Paciente paciente;
        int prioridade; // 1=Urgente, 2=Idoso, 3=Normal
        No proximo;

        No(Paciente paciente, int prioridade) {
            this.paciente = paciente;
            this.prioridade = prioridade;
            this.proximo = null;
        }
    }

    private No inicio;
    private int tamanho;
    private int totalInsercoes = 0;
    private long tempoTotalInsercao = 0;
    private long tempoTotalRemocao = 0;

    public FilaPrioridadeEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }

    /**
     * Determina a prioridade numérica do paciente
     */
    private int obterPrioridade(Paciente paciente) {
        if (paciente.isUrgente()) return 1;        // Maior prioridade
        if (paciente.getIdade() >= 60) return 2;   // Prioridade média
        return 3;                                  // Menor prioridade
    }

    /**
     * Adiciona paciente mantendo ordem de prioridade
     * Complexidade: O(n) no pior caso - precisa encontrar posição correta
     */
    public void adicionarPaciente(Paciente paciente) {
        long inicioTempo = System.nanoTime();

        int prioridade = obterPrioridade(paciente);
        No novoNo = new No(paciente, prioridade);

        // Se fila vazia ou novo paciente tem maior prioridade que o primeiro
        if (inicio == null || prioridade < inicio.prioridade) {
            novoNo.proximo = inicio;
            inicio = novoNo;
        } else {
            // Encontra posição correta mantendo ordem de prioridade
            No atual = inicio;
            while (atual.proximo != null && atual.proximo.prioridade <= prioridade) {
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo;
            atual.proximo = novoNo;
        }

        tamanho++;

        String tipoPrioridade = prioridade == 1 ? "🚨 URGENTE" :
                               prioridade == 2 ? "👴 IDOSO" : "👤 NORMAL";
        System.out.println(tipoPrioridade + ": " + paciente.getNome() + " adicionado (prioridade " + prioridade + ")");

        long fimTempo = System.nanoTime();
        tempoTotalInsercao += (fimTempo - inicioTempo);
        totalInsercoes++;
    }

    /**
     * Remove e retorna o paciente de maior prioridade (primeiro da lista)
     * Complexidade: O(1) - sempre remove do início
     */
    public Paciente chamarProximoPaciente() {
        if (inicio == null) return null;

        long inicioTempo = System.nanoTime();

        Paciente paciente = inicio.paciente;
        inicio = inicio.proximo;
        tamanho--;

        String tipoPrioridade = paciente.isUrgente() ? "🚨 URGENTE" :
                               paciente.getIdade() >= 60 ? "👴 IDOSO" : "👤 NORMAL";
        System.out.println(tipoPrioridade + ": Chamando " + paciente.getNome());

        long fimTempo = System.nanoTime();
        tempoTotalRemocao += (fimTempo - inicioTempo);

        return paciente;
    }

    /**
     * Consulta próximo paciente sem remover
     * Complexidade: O(1)
     */
    public Paciente proximoPaciente() {
        return inicio != null ? inicio.paciente : null;
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public int size() {
        return tamanho;
    }

    /**
     * Exibe estado atual da fila ordenada por prioridade
     */
    public void exibirEstadoCompleto() {
        System.out.println("\n=== FILA DE PRIORIDADE ENCADEADA ===");
        if (inicio == null) {
            System.out.println("Fila vazia");
            return;
        }

        No atual = inicio;
        int posicao = 1;
        int urgentes = 0, idosos = 0, normais = 0;

        while (atual != null) {
            String tipoPrioridade = atual.prioridade == 1 ? "🚨" :
                                   atual.prioridade == 2 ? "👴" : "👤";

            System.out.println(posicao + ". " + tipoPrioridade + " " + atual.paciente.getNome() +
                             " (Idade: " + atual.paciente.getIdade() +
                             ", Prioridade: " + atual.prioridade + ")");

            if (atual.prioridade == 1) urgentes++;
            else if (atual.prioridade == 2) idosos++;
            else normais++;

            atual = atual.proximo;
            posicao++;
        }

        System.out.println("\nResumo: " + urgentes + " urgentes, " + idosos + " idosos, " + normais + " normais");
        System.out.println("Total: " + tamanho + " pacientes");
        System.out.println("====================================");
    }

    /**
     * Exibe estatísticas de performance
     */
    public void exibirEstatisticasPerformance() {
        System.out.println("\n=== ANÁLISE DE PERFORMANCE (ENCADEADA) ===");
        if (totalInsercoes > 0) {
            double mediaInsercao = (double) tempoTotalInsercao / totalInsercoes;
            double mediaRemocao = tempoTotalRemocao > 0 ? (double) tempoTotalRemocao / totalInsercoes : 0;

            System.out.println("Total de operações: " + totalInsercoes);
            System.out.println("Tempo médio de inserção: " + String.format("%.2f", mediaInsercao) + " ns");
            System.out.println("Tempo médio de remoção: " + String.format("%.2f", mediaRemocao) + " ns");
            System.out.println("Complexidade inserção: O(n) - busca posição correta");
            System.out.println("Complexidade remoção: O(1) - sempre remove do início");
        }
        System.out.println("==========================================");
    }
}
