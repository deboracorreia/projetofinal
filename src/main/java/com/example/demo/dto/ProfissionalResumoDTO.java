/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.model.Profissional;

import java.time.LocalDate;

/**
 * @author debora
 */

public class ProfissionalResumoDTO {

    private Long idprofissional;
    private Long idusuario;
    private String loginUsuario; // Adicionado para mostrar na tabela
    private String especialidade;
    private String cro;
    private String estadocro;
    private boolean ativo;
    private LocalDate datacadastro;

    // Construtor vazio
    public ProfissionalResumoDTO() {
    }

    // Construtor completo
    public ProfissionalResumoDTO(Long idprofissional, Long idusuario, String loginUsuario, String especialidade, String cro, String estadocro, boolean ativo, LocalDate datacadastro) {
        this.idprofissional = idprofissional;
        this.idusuario = idusuario;
        this.loginUsuario = loginUsuario;
        this.especialidade = especialidade;
        this.cro = cro;
        this.estadocro = estadocro;
        this.ativo = ativo;
        this.datacadastro = datacadastro;
    }

    // Construtor a partir da entidade Profissional
    public ProfissionalResumoDTO(Profissional profissional) {
        this.idprofissional = profissional.getIdprofissional();
        this.idusuario = profissional.getUsuario() != null ? profissional.getUsuario().getIdusuario() : null;
        this.loginUsuario = profissional.getUsuario() != null ? profissional.getUsuario().getLogin() : null;
        this.especialidade = profissional.getEspecialidade();
        this.cro = profissional.getCro();
        this.estadocro = profissional.getEstadocro();
        this.ativo = profissional.getAtivo(); // Agora usa Boolean diretamente
        this.datacadastro = profissional.getDatacadastro();
    }

    // Método para converter DTO para entidade
    public Profissional toEntity() {
        Profissional profissional = new Profissional();
        profissional.setIdprofissional(this.idprofissional);
        profissional.setEspecialidade(this.especialidade);
        profissional.setCro(this.cro);
        profissional.setEstadocro(this.estadocro);
        profissional.setAtivo(this.ativo); // Agora usa Boolean diretamente
        profissional.setDatacadastro(this.datacadastro);

        // O usuário será setado no service
        return profissional;
    }

    // Getters e Setters
    public Long getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Long idprofissional) {
        this.idprofissional = idprofissional;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(LocalDate datacadastro) {
        this.datacadastro = datacadastro;
    }

    @Override
    public String toString() {
        return "ProfissionalResumoDTO{" +
                "idprofissional=" + idprofissional +
                ", idusuario=" + idusuario +
                ", loginUsuario='" + loginUsuario + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", cro='" + cro + '\'' +
                ", estadocro='" + estadocro + '\'' +
                ", ativo=" + ativo +
                ", datacadastro=" + datacadastro +
                '}';
    }
}