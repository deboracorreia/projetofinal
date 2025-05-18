/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

/**
 *
 * @author debora
 */

import com.example.demo.dto.ProfissionalResumoDTO;
import com.example.demo.model.Profissional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findById(Long id);

    @Query("""
           select new com.example.demo.dto.ProfissionalResumoDTO(p.idprofissional, user.idusuario, p.especialidade, p.cro, p.estadocro, p.ativo, p.datacadastro) 
           from Profissional p
           join p.usuario user
           where idprofissional = ?1
           """)
    ProfissionalResumoDTO findProfissionalById(Long id);
}