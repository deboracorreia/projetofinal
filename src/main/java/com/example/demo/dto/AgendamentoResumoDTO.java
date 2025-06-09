/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.model.Agendamento;

import java.time.LocalDateTime;

/**
 * @author debora
 */
public class AgendamentoResumoDTO {

    private Long idagendamento;
    private LocalDateTime datahorario;
    private String descricao;
    private Long idusuario;
    private String nomeUsuario;
    private Long idtratamento;
    private String nomeTratamento;
    private Long idprofissional;
    private String nomeProfissional;

    // Construtores
    public AgendamentoResumoDTO() {
    }

    public AgendamentoResumoDTO(Long idagendamento, LocalDateTime datahorario, String descricao,
                                Long idusuario, String nomeUsuario, Long idtratamento, String nomeTratamento,
                                Long idprofissional, String nomeProfissional) {
        this.idagendamento = idagendamento;
        this.datahorario = datahorario;
        this.descricao = descricao;
        this.idusuario = idusuario;
        this.nomeUsuario = nomeUsuario;
        this.idtratamento = idtratamento;
        this.nomeTratamento = nomeTratamento;
        this.idprofissional = idprofissional;
        this.nomeProfissional = nomeProfissional;
    }

    // Construtor a partir da entidade Agendamento
    public AgendamentoResumoDTO(Agendamento agendamento) {
        this.idagendamento = agendamento.getIdagendamento();
        this.datahorario = agendamento.getDatahorario();
        this.descricao = agendamento.getDescricao();
        this.idusuario = agendamento.getUsuario() != null ? agendamento.getUsuario().getIdusuario() : null;
        this.nomeUsuario = agendamento.getUsuario() != null ? agendamento.getUsuario().getLogin() : null;
        this.idtratamento = agendamento.getTratamento() != null ? agendamento.getTratamento().getIdtratamento() : null;
        this.nomeTratamento = agendamento.getTratamento() != null ? agendamento.getTratamento().getNometratamento() : null;
        this.idprofissional = agendamento.getProfissional() != null ? agendamento.getProfissional().getIdprofissional() : null;
        this.nomeProfissional = agendamento.getProfissional() != null ? agendamento.getProfissional().getEspecialidade() : null;
    }

    // Método para converter DTO para entidade
    public Agendamento toEntity() {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdagendamento(this.idagendamento);
        agendamento.setDatahorario(this.datahorario);
        agendamento.setDescricao(this.descricao);

        // Usuário, Tratamento e Profissional serão setados no service
        return agendamento;
    }

    // Getters e Setters
    public Long getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Long idagendamento) {
        this.idagendamento = idagendamento;
    }

    public LocalDateTime getDatahorario() {
        return datahorario;
    }

    public void setDatahorario(LocalDateTime datahorario) {
        this.datahorario = datahorario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getIdtratamento() {
        return idtratamento;
    }

    public void setIdtratamento(Long idtratamento) {
        this.idtratamento = idtratamento;
    }

    public String getNomeTratamento() {
        return nomeTratamento;
    }

    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    public Long getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Long idprofissional) {
        this.idprofissional = idprofissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    @Override
    public String toString() {
        return "AgendamentoResumoDTO{" +
                "idagendamento=" + idagendamento +
                ", datahorario=" + datahorario +
                ", descricao='" + descricao + '\'' +
                ", idusuario=" + idusuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", idtratamento=" + idtratamento +
                ", nomeTratamento='" + nomeTratamento + '\'' +
                ", idprofissional=" + idprofissional +
                ", nomeProfissional='" + nomeProfissional + '\'' +
                '}';
    }
}