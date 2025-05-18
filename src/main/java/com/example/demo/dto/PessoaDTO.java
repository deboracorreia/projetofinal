package com.example.demo.dto;

import java.time.LocalDate;

public class PessoaDTO {
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
    private String contatoemergencia;
    private String nomecontatoemergencia;
    private String contatopreferencial;

    // Getters e Setters

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

    public String getContatopreferencial() {
        return contatopreferencial;
    }

    public void setContatopreferencial(String contatopreferencial) {
        this.contatopreferencial = contatopreferencial;
    }
}
