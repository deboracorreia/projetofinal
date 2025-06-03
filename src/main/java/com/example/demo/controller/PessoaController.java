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
import com.example.demo.repository.PessoaRepository;
import com.example.demo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService; // Criar ServicePessoa e substituir esse PessoaRepository pelo PessoaService, não se usa repository no controller

    @GetMapping
    public List<Pessoa> listarTodas() {
        return pessoaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorIdpessoa(id);
    }

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        //return pessoaService.save(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        //return pessoa.findById(id)
        //        .map(pessoa -> {
        //            pessoaAtualizada.setIdpessoa(id);
        ///            return pessoaRepository.save(pessoaAtualizada);
        //        })
        //        .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pessoaService.excluir(id);
    }
}