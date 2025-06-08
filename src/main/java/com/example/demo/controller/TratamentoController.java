/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dto.TratamentoResumoDTO;
import com.example.demo.model.Tratamento;
import com.example.demo.service.TratamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller para gerenciamento de Tratamentos
 * @author debora
 */
@RestController
@RequestMapping("/api/tratamentos")
public class TratamentoController {
    
    @Autowired
    private TratamentoService tratamentoService;
    
    /**
     * Lista todos os tratamentos em formato resumido
     * @return Lista de tratamentos resumidos
     */
    @GetMapping
    public ResponseEntity<List<TratamentoResumoDTO>> listarTodos() {
        try {
            List<Tratamento> tratamentos = tratamentoService.listarTodos();
            List<TratamentoResumoDTO> tratamentosResumo = tratamentos.stream()
                .map(t -> new TratamentoResumoDTO(t.getIdtratamento(), t.getNometratamento()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(tratamentosResumo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Lista todos os tratamentos completos (para administração)
     * @return Lista de tratamentos completos
     */
    @GetMapping("/completo")
    public ResponseEntity<List<Tratamento>> listarTodosCompleto() {
        try {
            List<Tratamento> tratamentos = tratamentoService.listarTodos();
            return ResponseEntity.ok(tratamentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Busca tratamento por ID
     * @param idtratamento ID do tratamento
     * @return Tratamento encontrado
     */
    @GetMapping("/{idtratamento}")
    public ResponseEntity<Tratamento> buscarPorId(@PathVariable Long idtratamento) {
        try {
            Optional<Tratamento> tratamento = tratamentoService.buscarPorId(idtratamento);
            return tratamento.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Busca tratamentos por nome em formato resumido
     * @param nome Nome para busca (parcial ou completo)
     * @return Lista de tratamentos resumidos encontrados
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<TratamentoResumoDTO>> buscarPorNome(@PathVariable String nome) {
        try {
            List<Tratamento> tratamentos = tratamentoService.buscarPorNome(nome);
            List<TratamentoResumoDTO> tratamentosResumo = tratamentos.stream()
                .map(t -> new TratamentoResumoDTO(t.getIdtratamento(), t.getNometratamento()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(tratamentosResumo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Lista tratamentos ordenados alfabeticamente em formato resumido
     * @return Lista de tratamentos resumidos ordenados
     */
    @GetMapping("/ordenados")
    public ResponseEntity<List<TratamentoResumoDTO>> listarOrdenados() {
        try {
            List<Tratamento> tratamentos = tratamentoService.listarOrdenados();
            List<TratamentoResumoDTO> tratamentosResumo = tratamentos.stream()
                .map(t -> new TratamentoResumoDTO(t.getIdtratamento(), t.getNometratamento()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(tratamentosResumo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Busca tratamento resumido por ID
     * @param idtratamento ID do tratamento
     * @return Tratamento resumido encontrado
     */
    @GetMapping("/resumo/{idtratamento}")
    public ResponseEntity<TratamentoResumoDTO> buscarResumoPorId(@PathVariable Long idtratamento) {
        try {
            Optional<Tratamento> tratamento = tratamentoService.buscarPorId(idtratamento);
            if (tratamento.isPresent()) {
                TratamentoResumoDTO resumo = new TratamentoResumoDTO(
                    tratamento.get().getIdtratamento(), 
                    tratamento.get().getNometratamento()
                );
                return ResponseEntity.ok(resumo);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Conta o total de tratamentos
     * @return Número total de tratamentos
     */
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> contarTratamentos() {
        try {
            long count = tratamentoService.contarTratamentos();
            Map<String, Long> response = new HashMap<>();
            response.put("total", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Verifica se nome do tratamento já existe
     * @param nome Nome a ser verificado
     * @param excludeId ID a ser excluído da verificação (opcional)
     * @return Resultado da verificação
     */
    @GetMapping("/verificar-nome")
    public ResponseEntity<Map<String, Boolean>> verificarNomeExistente(
            @RequestParam String nome,
            @RequestParam(required = false) Long excludeId) {
        try {
            boolean existe = tratamentoService.verificarNomeExistente(nome, excludeId);
            Map<String, Boolean> response = new HashMap<>();
            response.put("existe", existe);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Cria novo tratamento
     * @param tratamento Dados do tratamento
     * @return Tratamento salvo
     */
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Tratamento tratamento) {
        try {
            // Validação de nome obrigatório
            if (tratamento.getNometratamento() == null || 
                tratamento.getNometratamento().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Nome do tratamento é obrigatório");
                return ResponseEntity.badRequest().body(error);
            }
            
            // Validação de tamanho
            if (tratamento.getNometratamento().trim().length() > 100) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Nome do tratamento deve ter no máximo 100 caracteres");
                return ResponseEntity.badRequest().body(error);
            }
            
            // Verificar se nome já existe
            if (tratamentoService.verificarNomeExistente(tratamento.getNometratamento().trim(), null)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Já existe um tratamento com este nome");
                return ResponseEntity.badRequest().body(error);
            }
            
            Tratamento tratamentoSalvo = tratamentoService.salvar(tratamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(tratamentoSalvo);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    /**
     * Atualiza tratamento existente
     * @param idtratamento ID do tratamento
     * @param tratamento Dados atualizados
     * @return Tratamento atualizado
     */
    @PutMapping("/{idtratamento}")
    public ResponseEntity<?> atualizar(@PathVariable Long idtratamento, 
                @RequestBody Tratamento tratamento) {
        try {
            // Verificar se tratamento existe
            if (!tratamentoService.buscarPorId(idtratamento).isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Tratamento não encontrado");
                return ResponseEntity.notFound().build();
            }
            
            // Validação de nome obrigatório
            if (tratamento.getNometratamento() == null || 
                tratamento.getNometratamento().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Nome do tratamento é obrigatório");
                return ResponseEntity.badRequest().body(error);
            }
            
            // Validação de tamanho
            if (tratamento.getNometratamento().trim().length() > 100) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Nome do tratamento deve ter no máximo 100 caracteres");
                return ResponseEntity.badRequest().body(error);
            }
            
            // Verificar se nome já existe (excluindo o próprio registro)
            if (tratamentoService.verificarNomeExistente(tratamento.getNometratamento().trim(), idtratamento)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Já existe um tratamento com este nome");
                return ResponseEntity.badRequest().body(error);
            }
            
            Tratamento tratamentoAtualizado = tratamentoService.atualizar(idtratamento, tratamento);
            return ResponseEntity.ok(tratamentoAtualizado);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    /**
     * Exclui tratamento
     * @param idtratamento ID do tratamento
     * @return Resposta sem conteúdo
     */
    @DeleteMapping("/{idtratamento}")
    public ResponseEntity<?> deletar(@PathVariable Long idtratamento) {
        try {
            // Verificar se tratamento existe
            if (!tratamentoService.buscarPorId(idtratamento).isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Tratamento não encontrado");
                return ResponseEntity.notFound().build();
            }
            
            tratamentoService.deletar(idtratamento);
            return ResponseEntity.noContent().build();
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Erro ao excluir tratamento. Verifique se não há registros vinculados.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}