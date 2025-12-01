package br.com.pucgo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa um registro de atendimento no histórico
 */
public class RegistroAtendimento {
    private Usuario usuario;
    private String setor;
    private LocalDateTime dataHora;

    public RegistroAtendimento(Usuario usuario, String setor) {
        this.usuario = usuario;
        this.setor = setor;
        this.dataHora = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getSetor() {
        return setor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Registro{setor='%s', usuario=%s, data=%s}",
                           setor, usuario.getNome(), dataHora.format(formatter));
    }
}

