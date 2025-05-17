/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.model.Agendamento;
import com.example.demo.service.AgendamentoService;
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
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long idatendimento) {
        return agendamentoService.buscarPorId(idatendimento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(agendamentoService.salvar(agendamento));
    }

    @PutMapping("/{idagendamento}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long idagendamento, @RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(agendamentoService.atualizar(idagendamento, agendamento));
    }

    @DeleteMapping("/{idagendamento}")
    public ResponseEntity<Void> deletar(@PathVariable Long idagendamento) {
        agendamentoService.deletar(idagendamento);
        return ResponseEntity.noContent().build();
    }
}
