/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 * @author debora
 */

import com.example.demo.dto.AgendamentoResumoDTO;
import com.example.demo.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoResumoDTO>> listarAgendamentos() {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.listarTodosDTO();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{idagendamento}")
    public ResponseEntity<AgendamentoResumoDTO> buscarAgendamento(@PathVariable Long idagendamento) {
        Optional<AgendamentoResumoDTO> agendamento = agendamentoService.buscarPorIdDTO(idagendamento);
        return agendamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarAgendamento(@RequestBody AgendamentoResumoDTO agendamentoDTO) {
        try {
            AgendamentoResumoDTO novoAgendamento = agendamentoService.salvarDTO(agendamentoDTO);
            return ResponseEntity.ok(novoAgendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{idagendamento}")
    public ResponseEntity<?> atualizarAgendamento(@PathVariable Long idagendamento, @RequestBody AgendamentoResumoDTO agendamentoDTO) {
        try {
            agendamentoDTO.setIdagendamento(idagendamento);
            AgendamentoResumoDTO agendamentoAtualizado = agendamentoService.salvarDTO(agendamentoDTO);
            return ResponseEntity.ok(agendamentoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{idagendamento}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long idagendamento) {
        try {
            agendamentoService.excluir(idagendamento);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoints específicos

    @GetMapping("/usuario/{idusuario}")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarPorUsuario(@PathVariable Long idusuario) {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarPorUsuarioDTO(idusuario);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/profissional/{idprofissional}")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarPorProfissional(@PathVariable Long idprofissional) {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarPorProfissionalDTO(idprofissional);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/tratamento/{idtratamento}")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarPorTratamento(@PathVariable Long idtratamento) {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarPorTratamentoDTO(idtratamento);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarPorPeriodoDTO(inicio, fim);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/hoje")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarAgendamentosHoje() {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarAgendamentosHojeDTO();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/proximos")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarProximosAgendamentos() {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarProximosAgendamentosDTO();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/semana")
    public ResponseEntity<List<AgendamentoResumoDTO>> buscarAgendamentosSemana(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarAgendamentosSemanaDTO(data);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/verificar-conflito")
    public ResponseEntity<Boolean> verificarConflitoProfissional(
            @RequestParam Long idprofissional,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datahorario,
            @RequestParam(required = false) Long idagendamento) {
        boolean conflito = agendamentoService.existeConflitoProfissional(idprofissional, datahorario, idagendamento);
        return ResponseEntity.ok(conflito);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarAgendamentos() {
        long total = agendamentoService.contarTodos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/usuario/{idusuario}")
    public ResponseEntity<Long> contarPorUsuario(@PathVariable Long idusuario) {
        long total = agendamentoService.contarPorUsuario(idusuario);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/profissional/{idprofissional}")
    public ResponseEntity<Long> contarPorProfissional(@PathVariable Long idprofissional) {
        long total = agendamentoService.contarPorProfissional(idprofissional);
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