/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Tratamento;
import com.example.demo.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service para gerenciamento de Tratamentos
 * @author debora
 */
@Service
@Transactional
public class TratamentoService {
    
    @Autowired
    private TratamentoRepository tratamentoRepository;
    
    /**
     * Lista todos os tratamentos
     * @return Lista de tratamentos
     */
    @Transactional(readOnly = true)
    public List<Tratamento> listarTodos() {
        return tratamentoRepository.findAll();
    }
    
    /**
     * Busca tratamento por ID
     * @param idtratamento ID do tratamento
     * @return Optional contendo o tratamento se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Tratamento> buscarPorId(Long idtratamento) {
        if (idtratamento == null) {
            return Optional.empty();
        }
        return tratamentoRepository.findById(idtratamento);
    }
    
    /**
     * Busca tratamentos por nome (busca parcial)
     * @param nome Nome para busca
     * @return Lista de tratamentos encontrados
     */
    @Transactional(readOnly = true)
    public List<Tratamento> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return List.of(); // Retorna lista vazia se nome for nulo ou vazio
        }
        return tratamentoRepository.findByNometratamentoContainingIgnoreCase(nome.trim());
    }
    
    /**
     * Lista tratamentos ordenados alfabeticamente
     * @return Lista de tratamentos ordenados
     */
    @Transactional(readOnly = true)
    public List<Tratamento> listarOrdenados() {
        return tratamentoRepository.findAllOrderByNometratamentoAsc();
    }
    
    /**
     * Conta o total de tratamentos
     * @return Número total de tratamentos
     */
    @Transactional(readOnly = true)
    public long contarTratamentos() {
        return tratamentoRepository.countTratamentos();
    }
    
    /**
     * Verifica se nome do tratamento já existe
     * @param nome Nome a ser verificado
     * @param excludeId ID a ser excluído da verificação (pode ser null)
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean verificarNomeExistente(String nome, Long excludeId) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        
        if (excludeId == null) {
            return tratamentoRepository.existsByNometratamentoIgnoreCase(nome.trim());
        } else {
            return tratamentoRepository.existsByNometratamentoIgnoreCaseAndIdNot(nome.trim(), excludeId);
        }
    }
    
    /**
     * Salva novo tratamento
     * @param tratamento Tratamento a ser salvo
     * @return Tratamento salvo
     * @throws IllegalArgumentException se dados inválidos
     * @throws DataIntegrityViolationException se violação de integridade
     */
    public Tratamento salvar(Tratamento tratamento) {
        // Validações básicas
        validarTratamento(tratamento);
        
        // Limpar e normalizar o nome
        tratamento.setNometratamento(tratamento.getNometratamento().trim());
        
        // Garantir que não está setando ID para novo registro
        tratamento.setIdtratamento(null);
        
        try {
            return tratamentoRepository.save(tratamento);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar tratamento. Verifique se os dados estão corretos.", e);
        }
    }
    
    /**
     * Atualiza tratamento existente
     * @param idtratamento ID do tratamento
     * @param tratamento Dados atualizados
     * @return Tratamento atualizado
     * @throws IllegalArgumentException se dados inválidos
     * @throws RuntimeException se tratamento não encontrado
     */
    public Tratamento atualizar(Long idtratamento, Tratamento tratamento) {
        // Verificar se o tratamento existe
        Optional<Tratamento> tratamentoExistente = buscarPorId(idtratamento);
        if (!tratamentoExistente.isPresent()) {
            throw new RuntimeException("Tratamento não encontrado com ID: " + idtratamento);
        }
        
        // Validações básicas
        validarTratamento(tratamento);
        
        // Definir o ID para garantir que está atualizando o registro correto
        tratamento.setIdtratamento(idtratamento);
        
        // Limpar e normalizar o nome
        tratamento.setNometratamento(tratamento.getNometratamento().trim());
        
        try {
            return tratamentoRepository.save(tratamento);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao atualizar tratamento. Verifique se os dados estão corretos.", e);
        }
    }
    
    /**
     * Exclui tratamento
     * @param idtratamento ID do tratamento
     * @throws RuntimeException se tratamento não encontrado
     * @throws DataIntegrityViolationException se há registros vinculados
     */
    public void deletar(Long idtratamento) {
        // Verificar se o tratamento existe
        Optional<Tratamento> tratamento = buscarPorId(idtratamento);
        if (!tratamento.isPresent()) {
            throw new RuntimeException("Tratamento não encontrado com ID: " + idtratamento);
        }
        
        try {
            tratamentoRepository.deleteById(idtratamento);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir o tratamento. Existem registros vinculados a ele.", e);
        }
    }
    
    /**
     * Valida os dados do tratamento
     * @param tratamento Tratamento a ser validado
     * @throws IllegalArgumentException se dados inválidos
     */
    private void validarTratamento(Tratamento tratamento) {
        if (tratamento == null) {
            throw new IllegalArgumentException("Tratamento não pode ser nulo");
        }
        
        if (tratamento.getNometratamento() == null || tratamento.getNometratamento().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do tratamento é obrigatório");
        }
        
        if (tratamento.getNometratamento().trim().length() > 100) {
            throw new IllegalArgumentException("Nome do tratamento deve ter no máximo 100 caracteres");
        }
    }
    
    /**
     * Verifica se um tratamento existe pelo ID
     * @param idtratamento ID do tratamento
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existePorId(Long idtratamento) {
        if (idtratamento == null) {
            return false;
        }
        return tratamentoRepository.existsById(idtratamento);
    }
}