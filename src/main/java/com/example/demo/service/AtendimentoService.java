/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 * @author debora
 */

import com.example.demo.dto.AtendimentoResumoDTO;
import com.example.demo.model.Agendamento;
import com.example.demo.model.Atendimento;
import com.example.demo.model.Atendimento.StatusAtendimento;
import com.example.demo.repository.AgendamentoRepository;
import com.example.demo.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // ===== MÉTODOS COM DTO =====

    public List<AtendimentoResumoDTO> listarTodosDTO() {
        return atendimentoRepository.findAllByOrderByDataDesc().stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AtendimentoResumoDTO> buscarPorIdDTO(Long idatendimento) {
        return atendimentoRepository.findById(idatendimento)
                .map(AtendimentoResumoDTO::new);
    }

    public AtendimentoResumoDTO salvarDTO(AtendimentoResumoDTO atendimentoDTO) {
        // Converter DTO para entidade
        Atendimento atendimento = atendimentoDTO.toEntity();

        // Validações
        validarAtendimentoDTO(atendimentoDTO);

        // Buscar e setar agendamento
        if (atendimentoDTO.getIdagendamento() != null) {
            Optional<Agendamento> agendamento = agendamentoRepository.findById(atendimentoDTO.getIdagendamento());
            if (!agendamento.isPresent()) {
                throw new RuntimeException("Agendamento não encontrado com ID: " + atendimentoDTO.getIdagendamento());
            }
            atendimento.setAgendamento(agendamento.get());
        }

        // Verificar se já existe atendimento para este agendamento (na criação)
        if (atendimento.getIdatendimento() == null) {
            if (atendimentoRepository.existsByAgendamento_Idagendamento(atendimentoDTO.getIdagendamento())) {
                throw new RuntimeException("Já existe um atendimento para este agendamento");
            }

            // Se for criação, setar data atual se não informada
            if (atendimento.getData() == null) {
                atendimento.setData(LocalDateTime.now());
            }

            // Se for criação, setar status inicial se não informado
            if (atendimento.getStatus() == null) {
                atendimento.setStatus(StatusAtendimento.EM_ANDAMENTO);
            }
        }

        // Salvar e converter de volta para DTO
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResumoDTO(atendimentoSalvo);
    }

    public void excluir(Long idatendimento) {
        if (!atendimentoRepository.existsById(idatendimento)) {
            throw new RuntimeException("Atendimento não encontrado com ID: " + idatendimento);
        }
        atendimentoRepository.deleteById(idatendimento);
    }

    // ===== MÉTODOS DE BUSCA DTO =====

    public List<AtendimentoResumoDTO> buscarPorStatusDTO(StatusAtendimento status) {
        return atendimentoRepository.findByStatusOrderByDataDesc(status).stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResumoDTO> buscarPorUsuarioDTO(Long idusuario) {
        return atendimentoRepository.findByUsuario(idusuario).stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResumoDTO> buscarPorProfissionalDTO(Long idprofissional) {
        return atendimentoRepository.findByProfissional(idprofissional).stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResumoDTO> buscarPorStatusEUsuarioDTO(StatusAtendimento status, Long idusuario) {
        return atendimentoRepository.findByStatusAndUsuario(status, idusuario).stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResumoDTO> buscarPorStatusEProfissionalDTO(StatusAtendimento status, Long idprofissional) {
        return atendimentoRepository.findByStatusAndProfissional(status, idprofissional).stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResumoDTO> buscarAtendimentosHojeDTO() {
        return atendimentoRepository.findAtendimentosHoje().stream()
                .map(AtendimentoResumoDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AtendimentoResumoDTO> buscarPorAgendamentoDTO(Long idagendamento) {
        return atendimentoRepository.findByAgendamento_Idagendamento(idagendamento)
                .map(AtendimentoResumoDTO::new);
    }

    // ===== MÉTODOS DE CONTAGEM =====

    public long contarTodos() {
        return atendimentoRepository.count();
    }

    public long contarPorStatus(StatusAtendimento status) {
        return atendimentoRepository.countByStatus(status);
    }

    public long contarPorUsuario(Long idusuario) {
        return atendimentoRepository.countByUsuario(idusuario);
    }

    public long contarPorProfissional(Long idprofissional) {
        return atendimentoRepository.countByProfissional(idprofissional);
    }

    // ===== MÉTODOS AUXILIARES =====

    public boolean existeAtendimentoParaAgendamento(Long idagendamento) {
        return atendimentoRepository.existsByAgendamento_Idagendamento(idagendamento);
    }

    // ===== MÉTODOS ORIGINAIS (para compatibilidade) =====

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAllByOrderByDataDesc();
    }

    public Optional<Atendimento> buscarPorId(Long idatendimento) {
        return atendimentoRepository.findById(idatendimento);
    }

    public Atendimento salvar(Atendimento atendimento) {
        validarAtendimento(atendimento);
        return atendimentoRepository.save(atendimento);
    }

    // ===== VALIDAÇÕES =====

    private void validarAtendimentoDTO(AtendimentoResumoDTO atendimentoDTO) {
        if (atendimentoDTO.getIdagendamento() == null) {
            throw new RuntimeException("Agendamento é obrigatório");
        }

        if (atendimentoDTO.getStatus() == null) {
            throw new RuntimeException("Status do atendimento é obrigatório");
        }

        if (atendimentoDTO.getDescricao() == null || atendimentoDTO.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("Descrição do atendimento é obrigatória");
        }

        if (atendimentoDTO.getDescricao().length() > 5000) {
            throw new RuntimeException("Descrição do atendimento deve ter no máximo 5000 caracteres");
        }
    }

    private void validarAtendimento(Atendimento atendimento) {
        if (atendimento.getAgendamento() == null) {
            throw new RuntimeException("Agendamento é obrigatório");
        }

        if (atendimento.getStatus() == null) {
            throw new RuntimeException("Status do atendimento é obrigatório");
        }

        if (atendimento.getDescricao() == null || atendimento.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("Descrição do atendimento é obrigatória");
        }

        if (atendimento.getData() == null) {
            throw new RuntimeException("Data do atendimento é obrigatória");
        }
    }
}