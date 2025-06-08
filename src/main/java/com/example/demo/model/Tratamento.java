/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.*;

/**
 * Entidade que representa um Tratamento
 * @author debora
 */
@Entity
@Table(name = "tratamento")
public class Tratamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtratamento")
    private Long idtratamento;
    
    @Column(nullable = false, length = 100, unique = true)
    private String nometratamento;
    
    // Construtor padrão
    public Tratamento() {
    }
    
    // Construtor com ID
    public Tratamento(Long idtratamento) {
        this.idtratamento = idtratamento;
    }
    
    // Construtor com nome
    public Tratamento(String nometratamento) {
        this.nometratamento = nometratamento;
    }
    
    // Construtor completo
    public Tratamento(Long idtratamento, String nometratamento) {
        this.idtratamento = idtratamento;
        this.nometratamento = nometratamento;
    }
    
    // Getters e Setters
    public Long getIdtratamento() {
        return idtratamento;
    }
    
    public void setIdtratamento(Long idtratamento) {
        this.idtratamento = idtratamento;
    }
    
    public String getNometratamento() {
        return nometratamento;
    }
    
    public void setNometratamento(String nometratamento) {
        this.nometratamento = nometratamento;
    }
}