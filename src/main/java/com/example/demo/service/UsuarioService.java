/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.dto.AgendamentoResumoDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioLoginDTO;
import com.example.demo.dto.UsuarioLoginResponseDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author debora
 */

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AgendamentoService agendamentoService;

    public UsuarioLoginResponseDTO cadastrar(UsuarioLoginDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByLogin(usuarioDTO.getLogin());

        if (usuarioExistente.isPresent()) {
            throw new IllegalStateException("Login já existe: " + usuarioDTO.getLogin());
        }

        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioLoginResponseDTO(usuarioSalvo);
    }

    // Busca usuário por login (usado na autenticação)
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByLogin(username);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        return optional.map(this::toDTO).orElse(null);
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setIdusuario(usuario.getIdusuario());
        dto.setLogin(usuario.getLogin());
        dto.setTipo(usuario.getTipo());


        return dto;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não existe."));
        List<AgendamentoResumoDTO> agendamentos = agendamentoService.buscarPorUsuarioDTO(usuario.getIdusuario());
        if (!agendamentos.isEmpty()) {
            throw new RuntimeException("Usuário não pode ser removido pois há agendamentos vinculados.");
        }
        usuarioRepository.delete(usuario);
    }

    public Usuario salvar(UsuarioLoginDTO usuarioLoginDto) {
        return usuarioRepository.saveAndFlush(new Usuario(usuarioLoginDto));
    }

    public Usuario atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setTipo(usuarioDTO.getTipo());
        return usuarioRepository.saveAndFlush(usuario);
    }

}