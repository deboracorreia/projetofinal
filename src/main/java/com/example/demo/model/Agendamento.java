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
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idagendamento;

    @Column(name = "datahorario", nullable = false)
    private LocalDateTime datahorario;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtratamento")
    private Tratamento tratamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprofissional", nullable = false)
    private Profissional profissional;

    // Construtores
    public Agendamento() {
    }

    public Agendamento(LocalDateTime datahorario, String descricao, Usuario usuario, Tratamento tratamento, Profissional profissional) {
        this.datahorario = datahorario;
        this.descricao = descricao;
        this.usuario = usuario;
        this.tratamento = tratamento;
        this.profissional = profissional;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "idagendamento=" + idagendamento +
                ", datahorario=" + datahorario +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + (usuario != null ? usuario.getIdusuario() : null) +
                ", tratamento=" + (tratamento != null ? tratamento.getIdtratamento() : null) +
                ", profissional=" + (profissional != null ? profissional.getIdprofissional() : null) +
                '}';
    }
}