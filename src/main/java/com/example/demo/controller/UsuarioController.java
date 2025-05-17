/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.model.Usuario;
import com.example.demo.dto.UsuarioDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @GetMapping("/atual")
    public UsuarioDTO obterUsuarioAtual(@AuthenticationPrincipal Usuario usuario) {
        System.out.println("Usuário autenticado: " + usuario); // teste
        return new UsuarioDTO(usuario);
    }
}
