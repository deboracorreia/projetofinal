/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.model.Usuario;
import com.example.demo.utils.Sexo;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author debora
 */

public class UsuarioDTO {

    private Long idusuario;
    private String login;
    private int tipo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.idusuario = usuario.getIdusuario();
        this.login = usuario.getLogin();
        this.tipo = usuario.getTipo();
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
