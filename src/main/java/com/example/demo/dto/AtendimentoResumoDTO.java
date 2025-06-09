/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.model.Atendimento;
import com.example.demo.model.Atendimento.StatusAtendimento;

import java.time.LocalDateTime;

/**
 * @author debora
 */
public class AtendimentoResumoDTO {

    private Long idatendimento;
    private Long idagendamento;
    private String descricao;
    private LocalDateTime data;
    private StatusAtendimento status;

    // Dados do agendamento para exibição
    private LocalDateTime dataAgendamento;
    private String nomeUsuario;
    private String nomeProfissional;
    private String nomeTratamento;
    private String descricaoAgendamento;

    // Construtores
    public AtendimentoResumoDTO() {
    }

    public AtendimentoResumoDTO(Long idatendimento, Long idagendamento, String descricao,
                                LocalDateTime data, StatusAtendimento status,
                                LocalDateTime dataAgendamento, String nomeUsuario,
                                String nomeProfissional, String nomeTratamento, String descricaoAgendamento) {
        this.idatendimento = idatendimento;
        this.idagendamento = idagendamento;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.dataAgendamento = dataAgendamento;
        this.nomeUsuario = nomeUsuario;
        this.nomeProfissional = nomeProfissional;
        this.nomeTratamento = nomeTratamento;
        this.descricaoAgendamento = descricaoAgendamento;
    }

    // Construtor a partir da entidade Atendimento
    public AtendimentoResumoDTO(Atendimento atendimento) {
        this.idatendimento = atendimento.getIdatendimento();
        this.idagendamento = atendimento.getAgendamento() != null ? atendimento.getAgendamento().getIdagendamento() : null;
        this.descricao = atendimento.getDescricao();
        this.data = atendimento.getData();
        this.status = atendimento.getStatus();

        // Dados do agendamento
        if (atendimento.getAgendamento() != null) {
            this.dataAgendamento = atendimento.getAgendamento().getDatahorario();
            this.nomeUsuario = atendimento.getAgendamento().getUsuario() != null ?
                    atendimento.getAgendamento().getUsuario().getLogin() : null;
            this.nomeProfissional = atendimento.getAgendamento().getProfissional() != null ?
                    atendimento.getAgendamento().getProfissional().getEspecialidade() : null;
            this.nomeTratamento = atendimento.getAgendamento().getTratamento() != null ?
                    atendimento.getAgendamento().getTratamento().getNometratamento() : null;
            this.descricaoAgendamento = atendimento.getAgendamento().getDescricao();
        }
    }

    // Método para converter DTO para entidade
    public Atendimento toEntity() {
        Atendimento atendimento = new Atendimento();
        atendimento.setIdatendimento(this.idatendimento);
        atendimento.setDescricao(this.descricao);
        atendimento.setData(this.data);
        atendimento.setStatus(this.status);

        // Agendamento será setado no service
        return atendimento;
    }

    // Getters e Setters
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getNomeTratamento() {
        return nomeTratamento;
    }

    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    public String getDescricaoAgendamento() {
        return descricaoAgendamento;
    }

    public void setDescricaoAgendamento(String descricaoAgendamento) {
        this.descricaoAgendamento = descricaoAgendamento;
    }

    @Override
    public String toString() {
        return "AtendimentoResumoDTO{" +
                "idatendimento=" + idatendimento +
                ", idagendamento=" + idagendamento +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", nomeProfissional='" + nomeProfissional + '\'' +
                '}';
    }
}