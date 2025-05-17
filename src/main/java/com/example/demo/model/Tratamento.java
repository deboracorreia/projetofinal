/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.*;

/**
 *
 * @author debor
 */

@Entity
@Table(name = "tratamento")
public class Tratamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nometratamento;

    public Tratamento(Long id, String nometratamento) {
        this.id = id;
        this.nometratamento = nometratamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNometratamento() {
        return nometratamento;
    }

    public void setNometratamento(String nometratamento) {
        this.nometratamento = nometratamento;
    }
    
}
