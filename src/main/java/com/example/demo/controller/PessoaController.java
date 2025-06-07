/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.dto.PessoaDTO;
import com.example.demo.model.Pessoa;
import com.example.demo.service.PessoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public PessoaDTO buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    @GetMapping("/atual")
    public PessoaDTO obterPessoaAtual(@AuthenticationPrincipal Pessoa pessoa){
        System.out.println("Pessoa autenticada: " + pessoa);
        return new PessoaDTO(pessoa);
    }

    @GetMapping
    public List<PessoaDTO> buscarTodos() {
        List<Pessoa> pessoas = pessoaService.buscarTodos();
        return pessoas.stream().map(PessoaDTO::new).toList();
    }

    @PostMapping
    public PessoaDTO criar(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.salvar(pessoaDTO);
        return new PessoaDTO(pessoa);
    }

    @PutMapping("/{id}")
    public PessoaDTO atualizar(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        pessoaDTO.setIdpessoa(id);
        Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoaDTO);
        return new PessoaDTO(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
    }
}
