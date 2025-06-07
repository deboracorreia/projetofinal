/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.example.demo.repository;

import com.example.demo.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);
 
    @Query("select p from Pessoa p join p.usuario u where p.idpessoa = ?1")
    public Optional<Pessoa> findByIdWithUsuario(Long id);
    
    
    @Query("select p from Pessoa p left join fetch p.usuario")
    public List<Pessoa> findAllWithUsuario();
    
}
