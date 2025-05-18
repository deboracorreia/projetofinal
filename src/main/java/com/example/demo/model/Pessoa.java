package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpessoa")
    private Long idpessoa;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(length = 100)
    private String nomecompleto;

    @Column
    private LocalDate datanascimento;

    @Column(columnDefinition = "ENUM('Masculino','Feminino','Outro')")
    private String sexo;

    @Column(length = 255)
    private String endereco;

    @Column(length = 10)
    private String cep;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String celular;

    @Column(length = 15)
    private String contatoemergencia;

    @Column(length = 100)
    private String nomecontatoemergencia;

    @Column(length = 12)
    private String contatopreferencial;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private int tipo;

    public Pessoa() {
    }

    public Pessoa(Long idpessoa, String login, String senha, int tipo) {
        this.idpessoa = idpessoa;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    // Métodos do UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
