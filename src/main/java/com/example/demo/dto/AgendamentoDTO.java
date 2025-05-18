/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

/**
 *
 * @author debora
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoDTO {
    private Long idagendamento;
    private LocalDateTime datahorario;
    private Long idusuario;
    private String descricao;
    private Long idtratamento;

    public AgendamentoDTO(Long idagendamento, LocalDateTime datahorario, Long idusuario, String descricao, Long idtratamento) {
        this.idagendamento = idagendamento;
        this.datahorario = datahorario;
        this.idusuario = idusuario;
        this.descricao = descricao;
        this.idtratamento = idtratamento;
    }

    public Long getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Long idagendamento) {
        this.idagendamento = idagendamento;
    }

    public LocalDateTime getDatahorario() {
        return datahorario;
    }

    public void setDataHorario(LocalDateTime datahorario) {
        this.datahorario = datahorario;
    }

    public Long getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdtratamento() {
        return idtratamento;
    }

    public void setIdTratamento(Long idtratamento) {
        this.idtratamento = idtratamento;
    }

   
}
