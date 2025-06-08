/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 * @author debora
 */

import com.example.demo.dto.ProfissionalResumoDTO;
import com.example.demo.model.Profissional;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ProfissionalRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ===== MÉTODOS COM DTO =====

    public List<ProfissionalResumoDTO> listarTodosDTO() {
        return profissionalRepository.findAll().stream()
                .map(ProfissionalResumoDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ProfissionalResumoDTO> buscarPorIdprofissionalDTO(Long idprofissional) {
        return profissionalRepository.findById(idprofissional)
                .map(ProfissionalResumoDTO::new);
    }

    public ProfissionalResumoDTO salvarDTO(ProfissionalResumoDTO profissionalDTO) {
        // Converter DTO para entidade
        Profissional profissional = profissionalDTO.toEntity();

        // Validações
        validarProfissionalDTO(profissionalDTO);

        // Buscar e setar o usuário
        if (profissionalDTO.getIdusuario() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(profissionalDTO.getIdusuario());
            if (!usuario.isPresent()) {
                throw new RuntimeException("Usuário não encontrado com ID: " + profissionalDTO.getIdusuario());
            }
            profissional.setUsuario(usuario.get());
        }

        // Verificar CRO duplicado
        if (existeCRO(profissionalDTO.getCro(), profissionalDTO.getEstadocro(), profissionalDTO.getIdprofissional())) {
            throw new RuntimeException("CRO " + profissionalDTO.getCro() + " já está cadastrado para o estado " + profissionalDTO.getEstadocro());
        }

        // Gerenciar data de cadastro
        if (profissional.getIdprofissional() == null) {
            // Se for criação, setar data atual
            profissional.setDatacadastro(LocalDate.now());
        } else {
            // Se for edição, preservar a data original
            Optional<Profissional> profissionalExistente = profissionalRepository.findById(profissional.getIdprofissional());
            if (profissionalExistente.isPresent()) {
                profissional.setDatacadastro(profissionalExistente.get().getDatacadastro());
            }
        }

        // Salvar e converter de volta para DTO
        Profissional profissionalSalvo = profissionalRepository.save(profissional);
        return new ProfissionalResumoDTO(profissionalSalvo);
    }

    public Optional<ProfissionalResumoDTO> buscarPorUsuarioDTO(Long idusuario) {
        return profissionalRepository.findByUsuario_Idusuario(idusuario)
                .map(ProfissionalResumoDTO::new);
    }

    public List<ProfissionalResumoDTO> buscarPorEspecialidadeDTO(String especialidade) {
        return profissionalRepository.findByEspecialidadeContainingIgnoreCase(especialidade).stream()
                .map(ProfissionalResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProfissionalResumoDTO> buscarAtivosDTO() {
        return profissionalRepository.findByAtivo(true).stream()
                .map(ProfissionalResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProfissionalResumoDTO> buscarInativosDTO() {
        return profissionalRepository.findByAtivo(false).stream()
                .map(ProfissionalResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProfissionalResumoDTO> buscarPorEstadoCRODTO(String estadocro) {
        return profissionalRepository.findByEstadocroIgnoreCase(estadocro).stream()
                .map(ProfissionalResumoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProfissionalResumoDTO> buscarSemUsuarioDTO() {
        return profissionalRepository.findByUsuarioIsNull().stream()
                .map(ProfissionalResumoDTO::new)
                .collect(Collectors.toList());
    }

    // ===== MÉTODOS ORIGINAIS (para compatibilidade) =====

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Optional<Profissional> buscarPorIdprofissional(Long idprofissional) {
        return profissionalRepository.findById(idprofissional);
    }

    public Profissional salvar(Profissional profissional) {
        // Validações antes de salvar
        validarProfissional(profissional);

        // Verificar se o usuário existe
        if (profissional.getUsuario() != null && profissional.getUsuario().getIdusuario() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(profissional.getUsuario().getIdusuario());
            if (!usuario.isPresent()) {
                throw new RuntimeException("Usuário não encontrado com ID: " + profissional.getUsuario().getIdusuario());
            }
            profissional.setUsuario(usuario.get());
        }

        // Verificar se CRO já existe
        if (existeCRO(profissional.getCro(), profissional.getEstadocro(), profissional.getIdprofissional())) {
            throw new RuntimeException("CRO " + profissional.getCro() + " já está cadastrado para o estado " + profissional.getEstadocro());
        }

        return profissionalRepository.save(profissional);
    }

    public void excluir(Long idprofissional) {
        if (!profissionalRepository.existsById(idprofissional)) {
            throw new RuntimeException("Profissional não encontrado com ID: " + idprofissional);
        }
        profissionalRepository.deleteById(idprofissional);
    }

    // ===== MÉTODOS AUXILIARES =====

    public Optional<Profissional> buscarPorUsuario(Long idusuario) {
        return profissionalRepository.findByUsuario_Idusuario(idusuario);
    }

    public List<Profissional> buscarPorEspecialidade(String especialidade) {
        return profissionalRepository.findByEspecialidadeContainingIgnoreCase(especialidade);
    }

    public List<Profissional> buscarAtivos() {
        return profissionalRepository.findByAtivo(true);
    }

    public List<Profissional> buscarInativos() {
        return profissionalRepository.findByAtivo(false);
    }

    public List<Profissional> buscarPorEstadoCRO(String estadocro) {
        return profissionalRepository.findByEstadocroIgnoreCase(estadocro);
    }

    public boolean existeCRO(String cro, String estadocro, Long excludeId) {
        if (excludeId != null) {
            return profissionalRepository.existsByCroAndEstadocroAndIdprofissionalNot(cro, estadocro, excludeId);
        } else {
            return profissionalRepository.existsByCroAndEstadocro(cro, estadocro);
        }
    }

    public long contarTodos() {
        return profissionalRepository.count();
    }

    public long contarAtivos() {
        return profissionalRepository.countByAtivo(true);
    }

    public long contarInativos() {
        return profissionalRepository.countByAtivo(false);
    }

    public List<Profissional> buscarSemUsuario() {
        return profissionalRepository.findByUsuarioIsNull();
    }

    public List<Profissional> buscarPorCRO(String cro) {
        return profissionalRepository.findByCroContainingIgnoreCase(cro);
    }

    public boolean existeProfissionalParaUsuario(Long idusuario, Long excludeId) {
        if (excludeId != null) {
            return profissionalRepository.existsByUsuario_IdusuarioAndIdprofissionalNot(idusuario, excludeId);
        } else {
            return profissionalRepository.existsByUsuario_Idusuario(idusuario);
        }
    }

    // ===== VALIDAÇÕES =====

    private void validarProfissionalDTO(ProfissionalResumoDTO profissionalDTO) {
        if (profissionalDTO.getEspecialidade() == null || profissionalDTO.getEspecialidade().trim().isEmpty()) {
            throw new RuntimeException("Especialidade é obrigatória");
        }

        if (profissionalDTO.getCro() == null || profissionalDTO.getCro().trim().isEmpty()) {
            throw new RuntimeException("CRO é obrigatório");
        }

        if (profissionalDTO.getEstadocro() == null || profissionalDTO.getEstadocro().trim().isEmpty()) {
            throw new RuntimeException("Estado do CRO é obrigatório");
        }

        if (profissionalDTO.getIdusuario() == null) {
            throw new RuntimeException("Usuário é obrigatório");
        }

        // Verificar se o usuário já tem outro profissional vinculado
        if (existeProfissionalParaUsuario(profissionalDTO.getIdusuario(), profissionalDTO.getIdprofissional())) {
            throw new RuntimeException("Este usuário já possui um profissional vinculado");
        }

        // Validar formato do CRO
        if (!profissionalDTO.getCro().matches("\\d+")) {
            throw new RuntimeException("CRO deve conter apenas números");
        }

        // Validar estado
        String[] estadosValidos = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        boolean estadoValido = false;
        for (String estado : estadosValidos) {
            if (estado.equalsIgnoreCase(profissionalDTO.getEstadocro())) {
                estadoValido = true;
                break;
            }
        }
        if (!estadoValido) {
            throw new RuntimeException("Estado do CRO inválido");
        }
    }

    private void validarProfissional(Profissional profissional) {
        if (profissional.getEspecialidade() == null || profissional.getEspecialidade().trim().isEmpty()) {
            throw new RuntimeException("Especialidade é obrigatória");
        }

        if (profissional.getCro() == null || profissional.getCro().trim().isEmpty()) {
            throw new RuntimeException("CRO é obrigatório");
        }

        if (profissional.getEstadocro() == null || profissional.getEstadocro().trim().isEmpty()) {
            throw new RuntimeException("Estado do CRO é obrigatório");
        }

        if (profissional.getUsuario() == null || profissional.getUsuario().getIdusuario() == null) {
            throw new RuntimeException("Usuário é obrigatório");
        }

        // Verificar se o usuário já tem outro profissional vinculado
        if (existeProfissionalParaUsuario(profissional.getUsuario().getIdusuario(), profissional.getIdprofissional())) {
            throw new RuntimeException("Este usuário já possui um profissional vinculado");
        }

        // Validar formato do CRO
        if (!profissional.getCro().matches("\\d+")) {
            throw new RuntimeException("CRO deve conter apenas números");
        }

        // Validar estado
        String[] estadosValidos = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        boolean estadoValido = false;
        for (String estado : estadosValidos) {
            if (estado.equalsIgnoreCase(profissional.getEstadocro())) {
                estadoValido = true;
                break;
            }
        }
        if (!estadoValido) {
            throw new RuntimeException("Estado do CRO inválido");
        }
    }
}