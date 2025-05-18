/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import java.time.LocalDate;

/**
 *
 * @author debora
 */
    
public class ProfissionalResumoDTO {
    
    private Long idprofissional;
    private Long idusuario;
    private String especialidade;
    private String cro;
    private String estadocro;
    private boolean ativo;
    private LocalDate datacadastro;    

    public ProfissionalResumoDTO(Long idprofissional, Long idusuario, String especialidade, String cro, String estadocro, boolean ativo, LocalDate datacadastro) {
        this.idprofissional = idprofissional;
        this.idusuario = idusuario;
        this.especialidade = especialidade;
        this.cro = cro;
        this.estadocro = estadocro;
        this.ativo = ativo;
        this.datacadastro = datacadastro;
    }

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
    
}
