/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author debora
 */
@Entity
@Table(name = "atendimento")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idatendimento;


    @ManyToOne
    @JoinColumn(name = "idagendamento")
    private Agendamento agendamento;
     
    @ManyToOne
    @JoinColumn(name = "idprofissional")
    private Profissional profissional;
    
    private String descricao;
    
    private LocalDate data;
    
    //@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true)
    /*private List<Agendamento> itens = new ArrayList<>();*/
    private LocalDate Data;

    public Atendimento() {
    }

    public Atendimento(Long idatendimento,  Agendamento agendamento, Profissional profissional, String descricao, LocalDate data) {
        this.idatendimento = idatendimento;
        this.agendamento = agendamento;
        this.profissional = profissional;
        this.descricao = descricao;
        this.data = data;

    }

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

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
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
