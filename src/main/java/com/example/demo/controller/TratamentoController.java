/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author debora
 */

import com.example.demo.model.Tratamento;
import com.example.demo.service.TratamentoService;
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
@RequestMapping("/api/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;

    @GetMapping
    public List<Tratamento> listarTodos() {
        return tratamentoService.listarTodos();
    }

    @GetMapping("/{idtratamento}")
    public ResponseEntity<Tratamento> buscarPorId(@PathVariable Long idtratamento) {
        return tratamentoService.buscarPorId(idtratamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tratamento> salvar(@RequestBody Tratamento tratamento) {
        return ResponseEntity.ok(tratamentoService.salvar(tratamento));
    }

    @PutMapping("/{idtratamento}")
    public ResponseEntity<Tratamento> atualizar(@PathVariable Long idtratamento, @RequestBody Tratamento tratamento) {
        return ResponseEntity.ok(tratamentoService.atualizar(idtratamento, tratamento));
    }

    @DeleteMapping("/{idtratamento}")
    public ResponseEntity<Void> deletar(@PathVariable Long idtratamento) {
        tratamentoService.deletar(idtratamento);
        return ResponseEntity.noContent().build();
    }
    
    
}
