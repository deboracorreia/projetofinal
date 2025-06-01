/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;


import java.util.Optional;

/**
 *
 * @author debora
 */
import com.example.demo.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("select u from Usuario u left join u.pessoa p where u.idusuario = :id")
    Optional<Usuario> findByIdWithPessoa(@Param("id") Long id);

    Optional<Usuario> findByLogin(String login);
}