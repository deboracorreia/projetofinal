/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.model.Usuario;

/**
 *
 * @author debora
 */

public class UsuarioLoginResponseDTO {

    private Long idusuario;
    private String login;
    private int tipo;
    private String tipoDescricao;

    public UsuarioLoginResponseDTO() {
    }
    
    public UsuarioLoginResponseDTO(Usuario usuario) {        
        this.idusuario = usuario.getIdusuario();
        this.login = usuario.getLogin();
        this.tipo = usuario.getTipo();
        this.tipoDescricao = usuario.getRoleByTipo();
    }

    public UsuarioLoginResponseDTO(Long idusuario, String login, int tipo) {
        this.idusuario = idusuario;
        this.login = login;
        this.tipo = tipo;
    }    

    public String getTipoDescricao() {
        return tipoDescricao;
    }

    public void setTipoDescricao(String tipoDescricao) {
        this.tipoDescricao = tipoDescricao;
    }
    
    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}
