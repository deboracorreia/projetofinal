/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

/**
 * @author debora
 */


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprofissional")
    private Long idprofissional;
    private String especialidade;
    private String cro;
    private String estadocro;
    private Boolean ativo;
    @ManyToOne

    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @Column(name = "datacadastro")
    private LocalDate datacadastro;


    public Profissional() {
    }

    public Profissional(Long idprofissional, String especialidade, String cro, String estadocro, Boolean ativo, Usuario usuario, LocalDate datacadastro) {
        this.idprofissional = idprofissional;
        this.especialidade = especialidade;
        this.cro = cro;
        this.estadocro = estadocro;
        this.ativo = ativo;
        this.usuario = usuario;
        this.datacadastro = datacadastro;
    }


    public Long getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Long idprofissional) {
        this.idprofissional = idprofissional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEstadocro() {
        return estadocro;
    }

    public void setEstadocro(String estadocro) {
        this.estadocro = estadocro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(LocalDate dataCadastro) {
        this.datacadastro = dataCadastro;
    }


}
