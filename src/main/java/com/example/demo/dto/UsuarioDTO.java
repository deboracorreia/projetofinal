/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.model.Usuario;
import com.example.demo.utils.Sexo;
import java.util.Date;

/**
 *
 * @author debora
 */

public class UsuarioDTO {
    private Long idusuario;
    private String login;
    private String cpf;
    private String nomecompleto;
    private Date datanascimento;
    private Sexo sexo;
    private String endereço;
    private String cep;
    private String cidade;
    private String uf;
    private String email;
    private String celular;
    private String contatoemergencia;
    private String nomecontatoemergencia;
    private String senha;
    private String contatopreferencial;

    public UsuarioDTO(Long idusuario, String login, String cpf, String nomecompleto, Date datanascimento, Sexo sexo, String endereço, String cep, String cidade, String uf, String email, String celular, String contatoemergencia, String nomecontatoemergencia, String senha, String contatopreferencial) {
        this.idusuario = idusuario;
        this.login = login;
        this.cpf = cpf;
        this.nomecompleto = nomecompleto;
        this.datanascimento = datanascimento;
        this.sexo = sexo;
        this.endereço = endereço;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.email = email;
        this.celular = celular;
        this.contatoemergencia = contatoemergencia;
        this.nomecontatoemergencia = nomecontatoemergencia;
        this.senha = senha;
        this.contatopreferencial = contatopreferencial;
    }

    public UsuarioDTO(Usuario usuario) {
        this.idusuario = usuario.getIdusuario();
        this.login = usuario.getLogin();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContatoemergencia() {
        return contatoemergencia;
    }

    public void setContatoemergencia(String contatoemergencia) {
        this.contatoemergencia = contatoemergencia;
    }

    public String getNomecontatoemergencia() {
        return nomecontatoemergencia;
    }

    public void setNomecontatoemergencia(String nomecontatoemergencia) {
        this.nomecontatoemergencia = nomecontatoemergencia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getContatopreferencial() {
        return contatopreferencial;
    }

    public void setContatopreferencial(String contatopreferencial) {
        this.contatopreferencial = contatopreferencial;
    }




   /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }*/

    
}
