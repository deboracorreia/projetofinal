/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 * @author debora
 */

import com.example.demo.dto.AgendamentoResumoDTO;
import com.example.demo.model.Agendamento;
import com.example.demo.model.Profissional;
import com.example.demo.model.Tratamento;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AgendamentoRepository;
import com.example.demo.repository.ProfissionalRepository;
import com.example.demo.repository.TratamentoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    // ===== MÉTODOS COM DTO =====

    public List<AgendamentoResumoDTO> listarTodosDTO() {
        return agendamentoRepository.findAllByOrderByDatahorarioAsc().stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AgendamentoResumoDTO> buscarPorIdDTO(Long idagendamento) {
        return agendamentoRepository.findById(idagendamento)
                .map(AgendamentoResumoDTO::new);
    }

    public AgendamentoResumoDTO salvarDTO(AgendamentoResumoDTO agendamentoDTO) {
        // Converter DTO para entidade
        Agendamento agendamento = agendamentoDTO.toEntity();

        // Validações
        validarAgendamentoDTO(agendamentoDTO);

        // Buscar e setar usuário
        if (agendamentoDTO.getIdusuario() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(agendamentoDTO.getIdusuario());
            if (!usuario.isPresent()) {
                throw new RuntimeException("Usuário não encontrado com ID: " + agendamentoDTO.getIdusuario());
            }
            agendamento.setUsuario(usuario.get());
        }

        // Buscar e setar profissional
        if (agendamentoDTO.getIdprofissional() != null) {
            Optional<Profissional> profissional = profissionalRepository.findById(agendamentoDTO.getIdprofissional());
            if (!profissional.isPresent()) {
                throw new RuntimeException("Profissional não encontrado com ID: " + agendamentoDTO.getIdprofissional());
            }
            agendamento.setProfissional(profissional.get());
        }

        // Buscar e setar tratamento (opcional)
        if (agendamentoDTO.getIdtratamento() != null) {
            Optional<Tratamento> tratamento = tratamentoRepository.findById(agendamentoDTO.getIdtratamento());
            if (!tratamento.isPresent()) {
                throw new RuntimeException("Tratamento não encontrado com ID: " + agendamentoDTO.getIdtratamento());
            }
            agendamento.setTratamento(tratamento.get());
        }

        // Verificar conflito de horário
        if (existeConflitoProfissional(agendamentoDTO.getIdprofissional(), agendamentoDTO.getDatahorario(), agendamentoDTO.getIdagendamento())) {
            throw new RuntimeException("Já existe um agendamento para este profissional no horário selecionado");
        }

        // Salvar e converter de volta para DTO
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
        return new AgendamentoResumoDTO(agendamentoSalvo);
    }

    public void excluir(Long idagendamento) {
        if (!agendamentoRepository.existsById(idagendamento)) {
            throw new RuntimeException("Agendamento não encontrado com ID: " + idagendamento);
        }
        agendamentoRepository.deleteById(idagendamento);
    }

    // ===== MÉTODOS DE BUSCA DTO =====

    public List<AgendamentoResumoDTO> buscarPorUsuarioDTO(Long idusuario) {
        return agendamentoRepository.findByUsuario_Idusuario(idusuario).stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResumoDTO> buscarPorProfissionalDTO(Long idprofissional) {
        return agendamentoRepository.findByProfissional_Idprofissional(idprofissional).stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResumoDTO> buscarPorTratamentoDTO(Long idtratamento) {
        return agendamentoRepository.findByTratamento_Idtratamento(idtratamento).stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResumoDTO> buscarPorPeriodoDTO(LocalDateTime inicio, LocalDateTime fim) {
        return agendamentoRepository.findByDatahorarioBetween(inicio, fim).stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResumoDTO> buscarAgendamentosHojeDTO() {
        return agendamentoRepository.findAgendamentosHoje().stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResumoDTO> buscarProximosAgendamentosDTO() {
        return agendamentoRepository.findProximosAgendamentos(LocalDateTime.now()).stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResumoDTO> buscarAgendamentosSemanaDTO(LocalDate data) {
        LocalDateTime inicioSemana = data.atStartOfDay();
        LocalDateTime fimSemana = data.plusDays(6).atTime(23, 59, 59);

        return agendamentoRepository.findAgendamentosSemana(inicioSemana, fimSemana).stream()
                .map(AgendamentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    // ===== MÉTODOS AUXILIARES =====

    public boolean existeConflitoProfissional(Long idprofissional, LocalDateTime datahorario, Long idagendamento) {
        return agendamentoRepository.existeConflitoProfissional(idprofissional, datahorario, idagendamento);
    }

    public long contarPorProfissional(Long idprofissional) {
        return agendamentoRepository.countByProfissional_Idprofissional(idprofissional);
    }

    public long contarPorUsuario(Long idusuario) {
        return agendamentoRepository.countByUsuario_Idusuario(idusuario);
    }

    public long contarTodos() {
        return agendamentoRepository.count();
    }

    // ===== MÉTODOS ORIGINAIS (para compatibilidade) =====

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAllByOrderByDatahorarioAsc();
    }

    public Optional<Agendamento> buscarPorId(Long idagendamento) {
        return agendamentoRepository.findById(idagendamento);
    }

    public Agendamento salvar(Agendamento agendamento) {
        validarAgendamento(agendamento);
        return agendamentoRepository.save(agendamento);
    }

    // ===== VALIDAÇÕES =====

    private void validarAgendamentoDTO(AgendamentoResumoDTO agendamentoDTO) {
        if (agendamentoDTO.getDatahorario() == null) {
            throw new RuntimeException("Data e horário são obrigatórios");
        }

        if (agendamentoDTO.getDatahorario().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar para datas passadas");
        }

        if (agendamentoDTO.getIdusuario() == null) {
            throw new RuntimeException("Usuário é obrigatório");
        }

        if (agendamentoDTO.getIdprofissional() == null) {
            throw new RuntimeException("Profissional é obrigatório");
        }

        // Validar horário comercial (8h às 18h)
        int hora = agendamentoDTO.getDatahorario().getHour();
        if (hora < 8 || hora >= 18) {
            throw new RuntimeException("Agendamentos só podem ser feitos entre 8h e 18h");
        }

        // Validar dias da semana (segunda a sexta)
        int diaSemana = agendamentoDTO.getDatahorario().getDayOfWeek().getValue();
        if (diaSemana > 5) { // 6 = sábado, 7 = domingo
            throw new RuntimeException("Agendamentos só podem ser feitos de segunda a sexta-feira");
        }
    }

    private void validarAgendamento(Agendamento agendamento) {
        if (agendamento.getDatahorario() == null) {
            throw new RuntimeException("Data e horário são obrigatórios");
        }

        if (agendamento.getDatahorario().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar para datas passadas");
        }

        if (agendamento.getUsuario() == null) {
            throw new RuntimeException("Usuário é obrigatório");
        }

        if (agendamento.getProfissional() == null) {
            throw new RuntimeException("Profissional é obrigatório");
        }
    }
}