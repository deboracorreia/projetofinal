package com.example.demo.repository;

import com.example.demo.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Buscar por usuário
    List<Agendamento> findByUsuario_Idusuario(Long idusuario);

    // Buscar por profissional
    List<Agendamento> findByProfissional_Idprofissional(Long idprofissional);

    // Buscar por tratamento
    List<Agendamento> findByTratamento_Idtratamento(Long idtratamento);

    // Buscar por data específica
    @Query("SELECT a FROM Agendamento a WHERE DATE(a.datahorario) = DATE(:data)")
    List<Agendamento> findByData(@Param("data") LocalDateTime data);

    // Buscar por período
    List<Agendamento> findByDatahorarioBetween(LocalDateTime inicio, LocalDateTime fim);

    // Buscar agendamentos futuros
    List<Agendamento> findByDatahorarioAfter(LocalDateTime data);

    // Buscar agendamentos passados
    List<Agendamento> findByDatahorarioBefore(LocalDateTime data);

    // Buscar agendamentos por usuário e período
    List<Agendamento> findByUsuario_IdusuarioAndDatahorarioBetween(Long idusuario, LocalDateTime inicio, LocalDateTime fim);

    // Buscar agendamentos por profissional e período
    List<Agendamento> findByProfissional_IdprofissionalAndDatahorarioBetween(Long idprofissional, LocalDateTime inicio, LocalDateTime fim);

    // Verificar conflito de horário para profissional
    @Query("SELECT COUNT(a) > 0 FROM Agendamento a WHERE a.profissional.idprofissional = :idprofissional " +
            "AND a.datahorario = :datahorario AND (:idagendamento IS NULL OR a.idagendamento != :idagendamento)")
    boolean existeConflitoProfissional(@Param("idprofissional") Long idprofissional,
                                       @Param("datahorario") LocalDateTime datahorario,
                                       @Param("idagendamento") Long idagendamento);

    // Buscar por descrição
    List<Agendamento> findByDescricaoContainingIgnoreCase(String descricao);

    // Contar agendamentos por profissional
    long countByProfissional_Idprofissional(Long idprofissional);

    // Contar agendamentos por usuário
    long countByUsuario_Idusuario(Long idusuario);

    // Buscar próximos agendamentos (limitado)
    @Query("SELECT a FROM Agendamento a WHERE a.datahorario >= :agora ORDER BY a.datahorario ASC")
    List<Agendamento> findProximosAgendamentos(@Param("agora") LocalDateTime agora);

    // Buscar agendamentos de hoje
    @Query("SELECT a FROM Agendamento a WHERE DATE(a.datahorario) = CURRENT_DATE ORDER BY a.datahorario ASC")
    List<Agendamento> findAgendamentosHoje();

    // Buscar agendamentos da semana
    @Query("SELECT a FROM Agendamento a WHERE a.datahorario BETWEEN :inicioSemana AND :fimSemana ORDER BY a.datahorario ASC")
    List<Agendamento> findAgendamentosSemana(@Param("inicioSemana") LocalDateTime inicioSemana,
                                             @Param("fimSemana") LocalDateTime fimSemana);

    // Buscar todos ordenados por data
    List<Agendamento> findAllByOrderByDatahorarioAsc();

    List<Agendamento> findAllByOrderByDatahorarioDesc();
}