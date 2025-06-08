/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 * @author debora
 */

import com.example.demo.dto.ProfissionalResumoDTO;
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
    public ResponseEntity<List<ProfissionalResumoDTO>> listarProfissional() {
        List<ProfissionalResumoDTO> profissionais = profissionalService.listarTodosDTO();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{idprofissional}")
    public ResponseEntity<ProfissionalResumoDTO> buscarProfissional(@PathVariable Long idprofissional) {
        Optional<ProfissionalResumoDTO> profissional = profissionalService.buscarPorIdprofissionalDTO(idprofissional);
        return profissional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarProfissional(@RequestBody ProfissionalResumoDTO profissionalDTO) {
        try {
            ProfissionalResumoDTO novoProfissional = profissionalService.salvarDTO(profissionalDTO);
            return ResponseEntity.ok(novoProfissional);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{idprofissional}")
    public ResponseEntity<?> atualizarProfissional(@PathVariable Long idprofissional, @RequestBody ProfissionalResumoDTO profissionalDTO) {
        try {
            profissionalDTO.setIdprofissional(idprofissional);
            ProfissionalResumoDTO profissionalAtualizado = profissionalService.salvarDTO(profissionalDTO);
            return ResponseEntity.ok(profissionalAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{idprofissional}")
    public ResponseEntity<Void> excluirProfissional(@PathVariable Long idprofissional) {
        try {
            profissionalService.excluir(idprofissional);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Novos endpoints úteis

    @GetMapping("/usuario/{idusuario}")
    public ResponseEntity<ProfissionalResumoDTO> buscarProfissionalPorUsuario(@PathVariable Long idusuario) {
        Optional<ProfissionalResumoDTO> profissional = profissionalService.buscarPorUsuarioDTO(idusuario);
        return profissional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<ProfissionalResumoDTO>> listarPorEspecialidade(@PathVariable String especialidade) {
        List<ProfissionalResumoDTO> profissionais = profissionalService.buscarPorEspecialidadeDTO(especialidade);
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ProfissionalResumoDTO>> listarProfissionaisAtivos() {
        List<ProfissionalResumoDTO> profissionais = profissionalService.buscarAtivosDTO();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ProfissionalResumoDTO>> listarProfissionaisInativos() {
        List<ProfissionalResumoDTO> profissionais = profissionalService.buscarInativosDTO();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProfissionalResumoDTO>> listarPorEstadoCRO(@PathVariable String estado) {
        List<ProfissionalResumoDTO> profissionais = profissionalService.buscarPorEstadoCRODTO(estado);
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/verificar-cro")
    public ResponseEntity<Boolean> verificarCROExistente(
            @RequestParam String cro,
            @RequestParam String estadocro,
            @RequestParam(required = false) Long excludeId) {
        boolean existe = profissionalService.existeCRO(cro, estadocro, excludeId);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarProfissionais() {
        long total = profissionalService.contarTodos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/ativos")
    public ResponseEntity<Long> contarProfissionaisAtivos() {
        long total = profissionalService.contarAtivos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/sem-usuario")
    public ResponseEntity<List<ProfissionalResumoDTO>> listarProfissionaisSemUsuario() {
        List<ProfissionalResumoDTO> profissionais = profissionalService.buscarSemUsuarioDTO();
        return ResponseEntity.ok(profissionais);
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