/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.model.Profissional;
import com.example.demo.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<List<Profissional>> listarProfissional() {
        return ResponseEntity.ok(profissionalService.listarTodos());
    }

    @GetMapping("/{idprofissional}")
    public ResponseEntity<Profissional> buscarProfissional(@PathVariable Long idprofissional) {
        Optional<Profissional> profissional = profissionalService.buscarPorIdprofissional(idprofissional);
        return profissional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profissional> criarProfissional(@RequestBody Profissional profissional) {
        return ResponseEntity.ok(profissionalService.salvar(profissional));
    }

    @PutMapping("/{idprofissional}")
    public ResponseEntity<Profissional> atualizarProfissional(@PathVariable Long idprofissional, @RequestBody Profissional profissional) {
        if (!profissionalService.buscarPorIdprofissional(idprofissional).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profissional.setId(idprofissional);
        return ResponseEntity.ok(profissionalService.salvar(profissional));
    }

    @DeleteMapping("/{idprofissional}")
    public ResponseEntity<Void> excluirProfissional(@PathVariable Long idprofissional) {
        if (!profissionalService.buscarPorIdprofissional(idprofissional).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profissionalService.excluir(idprofissional);
        return ResponseEntity.noContent().build();
    }
}
