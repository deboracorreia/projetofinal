/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.model.Pessoa;
import com.example.demo.dto.PessoaDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @GetMapping("/atual")
    public PessoaDTO obterPessoaAtual(@AuthenticationPrincipal Pessoa pessoa) {
        System.out.println("Pessoa autenticado: " + pessoa); // teste
        return new PessoaDTO(pessoa);
    }
}
