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

public class AtendimentoDTO {
    private Long idatendimento;
    private Long idagendamento;
    private Long idprofissional;
    private String descricao;
    private LocalDate data;

    public AtendimentoDTO(Long idatendimento, Long idagendamento, Long idprofissional, String descricao, LocalDate data) {
        this.idatendimento = idatendimento;
        this.idagendamento = idagendamento;
        this.idprofissional = idprofissional;
        this.descricao = descricao;
        this.data = data;
    }

    public Long getIdatendimento() {
        return idatendimento;
    }

    public void setIdatendimento(Long idatendimento) {
        this.idatendimento = idatendimento;
    }

    public Long getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Long idagendamento) {
        this.idagendamento = idagendamento;
    }

    public Long getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Long idprofissional) {
        this.idprofissional = idprofissional;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    

   
}
