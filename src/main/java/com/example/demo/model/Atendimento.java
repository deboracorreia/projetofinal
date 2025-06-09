/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author debora
 */
@Entity
@Table(name = "atendimento")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idatendimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idagendamento", nullable = false)
    private Agendamento agendamento;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAtendimento status;

    // Enum para status do atendimento
    public enum StatusAtendimento {
        EM_ANDAMENTO("Em Andamento"),
        CANCELADO("Cancelado"),
        CONCLUIDO("Concluído");

        private final String descricao;

        StatusAtendimento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Construtores
    public Atendimento() {
    }

    public Atendimento(Agendamento agendamento, String descricao, LocalDateTime data, StatusAtendimento status) {
        this.agendamento = agendamento;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
    }

    // Getters e Setters
    public Long getIdatendimento() {
        return idatendimento;
    }

    public void setIdatendimento(Long idatendimento) {
        this.idatendimento = idatendimento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
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

    @Override
    public String toString() {
        return "Atendimento{" +
                "idatendimento=" + idatendimento +
                ", agendamento=" + (agendamento != null ? agendamento.getIdagendamento() : null) +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}