/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

/**
 *
 * @author debora
 */
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioLoginDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long idusuario;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private int tipo;

    @OneToOne
    @JoinColumn(name = "idpessoa", referencedColumnName = "idpessoa", nullable = true)
    private Pessoa pessoa;
    

    public Usuario() {}

    public Usuario(Long idusuario, String login, String senha, int tipo) {
        this.idusuario = idusuario;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    public Usuario(UsuarioLoginDTO usuarioDto) {
        this.idusuario = usuarioDto.getIdusuario();
        this.login = usuarioDto.getLogin();
        this.senha = usuarioDto.getSenha();
        this.tipo = usuarioDto.getTipo();
    }
    
    public Usuario(UsuarioDTO usuarioDto) {
        this.idusuario = usuarioDto.getIdusuario();
        this.login = usuarioDto.getLogin();
        this.tipo = usuarioDto.getTipo();
        Pessoa pessoa = new Pessoa();
        pessoa.setIdpessoa(usuarioDto.getIdpessoa());
        pessoa.setCpf(usuarioDto.getCpf());
        pessoa.setNomecompleto(usuarioDto.getNomecompleto());
        pessoa.setDatanascimento(usuarioDto.getDatanascimento());
        pessoa.setSexo(usuarioDto.getSexo());
        pessoa.setEndereco(usuarioDto.getEndereco());
        pessoa.setCep(usuarioDto.getCep());
        pessoa.setCidade(usuarioDto.getCidade());
        pessoa.setUf(usuarioDto.getUf());
        pessoa.setEmail(usuarioDto.getEmail());
        pessoa.setCelular(usuarioDto.getCelular());
        pessoa.setContatoemergencia(usuarioDto.getContatodeemergencia());
        pessoa.setNomecontatoemergencia(usuarioDto.getNomecontatodeemergencia());
        pessoa.setContatopreferencial(usuarioDto.getContatopreferencial());
    }

    public Usuario(Long idusuario) {
        this.idusuario = idusuario;
    }
        
    // Getters e Setters

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
   

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = this.getRoleByTipo();
        return List.of(new SimpleGrantedAuthority("ROLE_"+ role));
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
    
    public String getRoleByTipo() {
        if (this.tipo == 0) {
            return "ADMIN";
        }
        return "USER";
    }

    
    /*public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}