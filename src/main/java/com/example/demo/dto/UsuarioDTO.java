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

    private Long idpessoa;
    private String cpf;
    private String nomecompleto;
    private LocalDate datanascimento;
    private String sexo;
    private String endereco;
    private String cep;
    private String cidade;
    private String uf;
    private String email;
    private String celular;
    private String contatodeemergencia;
    private String nomecontatodeemergencia;
    private String contatopreferencial;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.idusuario = usuario.getIdusuario();
        this.login = usuario.getLogin();
        this.tipo = usuario.getTipo();
        if (usuario.getPessoa() != null) {
            this.idpessoa = usuario.getPessoa().getIdpessoa();
            this.cpf = usuario.getPessoa().getCpf();
            this.nomecompleto = usuario.getPessoa().getNomecompleto();
            this.datanascimento = usuario.getPessoa().getDatanascimento();
            this.sexo = usuario.getPessoa().getSexo();
            this.endereco = usuario.getPessoa().getEndereco();
            this.cep = usuario.getPessoa().getCep();
            this.cidade = usuario.getPessoa().getCidade();
            this.uf = usuario.getPessoa().getUf();
            this.email = usuario.getPessoa().getEmail();
            this.celular = usuario.getPessoa().getCelular();
            this.contatodeemergencia = usuario.getPessoa().getContatoemergencia();
            this.nomecontatodeemergencia = usuario.getPessoa().getNomecontatoemergencia();
            this.contatopreferencial = usuario.getPessoa().getContatopreferencial();
        }
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

    public Long getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Long idpessoa) {
        this.idpessoa = idpessoa;
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

    public LocalDate getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(LocalDate datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getContatodeemergencia() {
        return contatodeemergencia;
    }

    public void setContatodeemergencia(String contatodeemergencia) {
        this.contatodeemergencia = contatodeemergencia;
    }

    public String getNomecontatodeemergencia() {
        return nomecontatodeemergencia;
    }

    public void setNomecontatodeemergencia(String nomecontatodeemergencia) {
        this.nomecontatodeemergencia = nomecontatodeemergencia;
    }

    public String getContatopreferencial() {
        return contatopreferencial;
    }

    public void setContatopreferencial(String contatopreferencial) {
        this.contatopreferencial = contatopreferencial;
    }
    
}
