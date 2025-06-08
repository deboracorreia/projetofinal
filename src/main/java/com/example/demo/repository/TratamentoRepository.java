/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository para gerenciamento de Tratamentos
 * @author debora
 */
@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
    
    /**
     * Busca tratamentos por nome (busca parcial, case-insensitive)
     * @param nome Nome para busca
     * @return Lista de tratamentos encontrados
     */
    @Query("SELECT t FROM Tratamento t WHERE UPPER(t.nometratamento) LIKE UPPER(CONCAT('%', :nome, '%'))")
    List<Tratamento> findByNometratamentoContainingIgnoreCase(@Param("nome") String nome);
    
    /**
     * Lista todos os tratamentos ordenados alfabeticamente por nome
     * @return Lista de tratamentos ordenados
     */
    @Query("SELECT t FROM Tratamento t ORDER BY t.nometratamento ASC")
    List<Tratamento> findAllOrderByNometratamentoAsc();
    
    /**
     * Verifica se existe um tratamento com o nome especificado
     * @param nome Nome do tratamento
     * @return true se existe, false caso contrário
     */
    @Query("SELECT COUNT(t) > 0 FROM Tratamento t WHERE UPPER(TRIM(t.nometratamento)) = UPPER(TRIM(:nome))")
    boolean existsByNometratamentoIgnoreCase(@Param("nome") String nome);
    
    /**
     * Verifica se existe um tratamento com o nome especificado, excluindo um ID específico
     * @param nome Nome do tratamento
     * @param excludeId ID a ser excluído da verificação
     * @return true se existe, false caso contrário
     */
    @Query("SELECT COUNT(t) > 0 FROM Tratamento t WHERE UPPER(TRIM(t.nometratamento)) = UPPER(TRIM(:nome)) AND t.idtratamento <> :excludeId")
    boolean existsByNometratamentoIgnoreCaseAndIdNot(@Param("nome") String nome, @Param("excludeId") Long excludeId);
    
    /**
     * Conta o total de tratamentos
     * @return Número total de tratamentos
     */
    @Query("SELECT COUNT(t) FROM Tratamento t")
    long countTratamentos();
}