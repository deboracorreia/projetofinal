/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.model.Atendimento;
import com.example.demo.service.AtendimentoService;
import java.util.UUID;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public List<Atendimento> listarTodos() {
        return atendimentoService.listarTodos();
    }

    @GetMapping("/{idtendimento}")
    public ResponseEntity<Atendimento> buscarPorId(@PathVariable Long idatendimento) {
        return atendimentoService.buscarPorId(idatendimento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atendimento> salvar(@RequestBody Atendimento atendimento) {
        return ResponseEntity.ok(atendimentoService.salvar(atendimento));
    }

    @PutMapping("/{idatendimento}")
    public ResponseEntity<Atendimento> atualizar(@PathVariable Long idatendimento, @RequestBody Atendimento atendimento) {
        return ResponseEntity.ok(atendimentoService.atualizar(idatendimento, atendimento));
    }

    @DeleteMapping("/{idatendimento}")
    public ResponseEntity<Void> deletar(@PathVariable Long idatendimento) {
        atendimentoService.deletar(idatendimento);
        return ResponseEntity.noContent().build();
    }
}
