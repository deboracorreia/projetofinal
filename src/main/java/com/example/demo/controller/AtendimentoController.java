/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 * @author debora
 */

import com.example.demo.dto.AtendimentoResumoDTO;
import com.example.demo.model.Atendimento.StatusAtendimento;
import com.example.demo.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<List<AtendimentoResumoDTO>> listarAtendimentos() {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.listarTodosDTO();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{idatendimento}")
    public ResponseEntity<AtendimentoResumoDTO> buscarAtendimento(@PathVariable Long idatendimento) {
        Optional<AtendimentoResumoDTO> atendimento = atendimentoService.buscarPorIdDTO(idatendimento);
        return atendimento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarAtendimento(@RequestBody AtendimentoResumoDTO atendimentoDTO) {
        try {
            AtendimentoResumoDTO novoAtendimento = atendimentoService.salvarDTO(atendimentoDTO);
            return ResponseEntity.ok(novoAtendimento);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{idatendimento}")
    public ResponseEntity<?> atualizarAtendimento(@PathVariable Long idatendimento, @RequestBody AtendimentoResumoDTO atendimentoDTO) {
        try {
            atendimentoDTO.setIdatendimento(idatendimento);
            AtendimentoResumoDTO atendimentoAtualizado = atendimentoService.salvarDTO(atendimentoDTO);
            return ResponseEntity.ok(atendimentoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{idatendimento}")
    public ResponseEntity<Void> excluirAtendimento(@PathVariable Long idatendimento) {
        try {
            atendimentoService.excluir(idatendimento);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoints específicos

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AtendimentoResumoDTO>> buscarPorStatus(@PathVariable StatusAtendimento status) {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.buscarPorStatusDTO(status);
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/usuario/{idusuario}")
    public ResponseEntity<List<AtendimentoResumoDTO>> buscarPorUsuario(@PathVariable Long idusuario) {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.buscarPorUsuarioDTO(idusuario);
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/profissional/{idprofissional}")
    public ResponseEntity<List<AtendimentoResumoDTO>> buscarPorProfissional(@PathVariable Long idprofissional) {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.buscarPorProfissionalDTO(idprofissional);
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/status/{status}/usuario/{idusuario}")
    public ResponseEntity<List<AtendimentoResumoDTO>> buscarPorStatusEUsuario(
            @PathVariable StatusAtendimento status,
            @PathVariable Long idusuario) {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.buscarPorStatusEUsuarioDTO(status, idusuario);
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/status/{status}/profissional/{idprofissional}")
    public ResponseEntity<List<AtendimentoResumoDTO>> buscarPorStatusEProfissional(
            @PathVariable StatusAtendimento status,
            @PathVariable Long idprofissional) {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.buscarPorStatusEProfissionalDTO(status, idprofissional);
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/agendamento/{idagendamento}")
    public ResponseEntity<AtendimentoResumoDTO> buscarPorAgendamento(@PathVariable Long idagendamento) {
        Optional<AtendimentoResumoDTO> atendimento = atendimentoService.buscarPorAgendamentoDTO(idagendamento);
        return atendimento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hoje")
    public ResponseEntity<List<AtendimentoResumoDTO>> buscarAtendimentosHoje() {
        List<AtendimentoResumoDTO> atendimentos = atendimentoService.buscarAtendimentosHojeDTO();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/existe-para-agendamento/{idagendamento}")
    public ResponseEntity<Boolean> existeAtendimentoParaAgendamento(@PathVariable Long idagendamento) {
        boolean existe = atendimentoService.existeAtendimentoParaAgendamento(idagendamento);
        return ResponseEntity.ok(existe);
    }

    // Endpoints de contagem

    @GetMapping("/count")
    public ResponseEntity<Long> contarAtendimentos() {
        long total = atendimentoService.contarTodos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> contarPorStatus(@PathVariable StatusAtendimento status) {
        long total = atendimentoService.contarPorStatus(status);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/usuario/{idusuario}")
    public ResponseEntity<Long> contarPorUsuario(@PathVariable Long idusuario) {
        long total = atendimentoService.contarPorUsuario(idusuario);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/profissional/{idprofissional}")
    public ResponseEntity<Long> contarPorProfissional(@PathVariable Long idprofissional) {
        long total = atendimentoService.contarPorProfissional(idprofissional);
        return ResponseEntity.ok(total);
    }

    // Classe interna para respostas de erro
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}