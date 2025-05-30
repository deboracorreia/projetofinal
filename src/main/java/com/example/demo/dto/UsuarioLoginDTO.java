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

public class UsuarioLoginDTO {

    private Long idusuario;
    private String login;
    private String senha;
    private int tipo;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(Long idusuario, String login, String senha, int tipo) {
        this.idusuario = idusuario;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
