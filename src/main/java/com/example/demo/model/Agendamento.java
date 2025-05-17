/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author debora
 */
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idagendamento;

    private LocalDateTime datahorario;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

     private String descricao;
     
    @ManyToOne
    @JoinColumn(name = "idTratamento")
    private Tratamento tratamento;
    
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    /*private List<Agendamento> itens = new ArrayList<>();*/
    private LocalDateTime dataHorario;

    public Agendamento() {
    }

    public Agendamento(Long idagendamento, LocalDateTime datahorario, Usuario usuario, String descricao, Tratamento tratamento) {
        this.idagendamento = idagendamento;
        this.dataHorario = datahorario;
        this.usuario = usuario;
        this.descricao = descricao;
        this.tratamento = tratamento;
    }

    public Long getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Long idagendamento) {
        this.idagendamento = idagendamento;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime datahorario) {
        this.dataHorario = datahorario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

   /* public List<Agendamento> getItens() {
        return itens;
    }

    public void setItens(List<Agendamento> itens) {
        this.itens = itens;
    }*/

  
    
}
