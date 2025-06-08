package com.example.demo.repository;

import com.example.demo.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    // Buscar por usuário
    Optional<Profissional> findByUsuario_Idusuario(Long idusuario);

    // Verificar se usuário já tem profissional
    boolean existsByUsuario_Idusuario(Long idusuario);

    boolean existsByUsuario_IdusuarioAndIdprofissionalNot(Long idusuario, Long idprofissional);

    // Buscar por especialidade
    List<Profissional> findByEspecialidadeContainingIgnoreCase(String especialidade);

    // Buscar por status (ativo/inativo)
    List<Profissional> findByAtivo(Boolean ativo);

    // Contar por status
    long countByAtivo(Boolean ativo);

    // Buscar por estado do CRO
    List<Profissional> findByEstadocroIgnoreCase(String estadocro);

    // Verificar CRO existente
    boolean existsByCroAndEstadocro(String cro, String estadocro);

    boolean existsByCroAndEstadocroAndIdprofissionalNot(String cro, String estadocro, Long idprofissional);

    // Buscar por CRO
    List<Profissional> findByCroContainingIgnoreCase(String cro);

    // Buscar profissionais sem usuário
    List<Profissional> findByUsuarioIsNull();

    // Buscar todos ordenados por especialidade
    List<Profissional> findAllByOrderByEspecialidadeAsc();

    // Buscar ativos ordenados por especialidade
    List<Profissional> findByAtivoOrderByEspecialidadeAsc(Boolean ativo);
}