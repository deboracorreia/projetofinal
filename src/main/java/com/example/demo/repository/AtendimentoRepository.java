package com.example.demo.repository;

import com.example.demo.model.Atendimento;
import com.example.demo.model.Atendimento.StatusAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    // Buscar por agendamento
    Optional<Atendimento> findByAgendamento_Idagendamento(Long idagendamento);

    // Verificar se já existe atendimento para o agendamento
    boolean existsByAgendamento_Idagendamento(Long idagendamento);

    // Buscar por status
    List<Atendimento> findByStatus(StatusAtendimento status);

    // Buscar por período
    List<Atendimento> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);

    // Buscar por usuário (através do agendamento)
    @Query("SELECT a FROM Atendimento a WHERE a.agendamento.usuario.idusuario = :idusuario")
    List<Atendimento> findByUsuario(@Param("idusuario") Long idusuario);

    // Buscar por profissional (através do agendamento)
    @Query("SELECT a FROM Atendimento a WHERE a.agendamento.profissional.idprofissional = :idprofissional")
    List<Atendimento> findByProfissional(@Param("idprofissional") Long idprofissional);

    // Buscar por status e usuário
    @Query("SELECT a FROM Atendimento a WHERE a.status = :status AND a.agendamento.usuario.idusuario = :idusuario")
    List<Atendimento> findByStatusAndUsuario(@Param("status") StatusAtendimento status, @Param("idusuario") Long idusuario);

    // Buscar por status e profissional
    @Query("SELECT a FROM Atendimento a WHERE a.status = :status AND a.agendamento.profissional.idprofissional = :idprofissional")
    List<Atendimento> findByStatusAndProfissional(@Param("status") StatusAtendimento status, @Param("idprofissional") Long idprofissional);

    // Buscar atendimentos de hoje
    @Query("SELECT a FROM Atendimento a WHERE DATE(a.data) = CURRENT_DATE ORDER BY a.data DESC")
    List<Atendimento> findAtendimentosHoje();

    // Buscar atendimentos em andamento
    List<Atendimento> findByStatusOrderByDataDesc(StatusAtendimento status);

    // Contar por status
    long countByStatus(StatusAtendimento status);

    // Contar por usuário
    @Query("SELECT COUNT(a) FROM Atendimento a WHERE a.agendamento.usuario.idusuario = :idusuario")
    long countByUsuario(@Param("idusuario") Long idusuario);

    // Contar por profissional
    @Query("SELECT COUNT(a) FROM Atendimento a WHERE a.agendamento.profissional.idprofissional = :idprofissional")
    long countByProfissional(@Param("idprofissional") Long idprofissional);

    // Buscar atendimentos recentes (últimos 30 dias)
    @Query("SELECT a FROM Atendimento a WHERE a.data >= :dataInicio ORDER BY a.data DESC")
    List<Atendimento> findAtendimentosRecentes(@Param("dataInicio") LocalDateTime dataInicio);

    // Buscar por descrição
    List<Atendimento> findByDescricaoContainingIgnoreCase(String descricao);

    // Buscar todos ordenados por data
    List<Atendimento> findAllByOrderByDataDesc();

    List<Atendimento> findAllByOrderByDataAsc();
}