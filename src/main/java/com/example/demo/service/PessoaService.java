package com.example.demo.service;

import com.example.demo.dto.PessoaDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Pessoa;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    
     @Autowired
    private UsuarioService usuarioService;
    

    public PessoaDTO buscarPorId(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findByIdWithUsuario(id);
        return optional.map(this::toDTO).orElse(null);
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAllWithUsuario();
    }

    public void excluir(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa salvar(PessoaDTO pessoaDTO) {
        Pessoa pessoa = toEntity(pessoaDTO);
        return pessoaRepository.saveAndFlush(pessoa);
    }

    public Pessoa atualizar(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));

        // Atualizar os campos
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setNomecompleto(pessoaDTO.getNomecompleto());
        pessoa.setDatanascimento(pessoaDTO.getDatanascimento());
        pessoa.setSexo(pessoaDTO.getSexo());
        pessoa.setEndereco(pessoaDTO.getEndereco());
        pessoa.setCep(pessoaDTO.getCep());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setUf(pessoaDTO.getUf());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setCelular(pessoaDTO.getCelular());
        pessoa.setContatoemergencia(pessoaDTO.getContatoemergencia());
        pessoa.setNomecontatoemergencia(pessoaDTO.getNomecontatoemergencia());
        pessoa.setContatopreferencial(pessoaDTO.getContatopreferencial());
        UsuarioDTO usuario = usuarioService.buscarPorId(pessoaDTO.getIdusuario());
        if (usuario != null){
            pessoa.setUsuario(new Usuario(usuario));
        }

        return pessoaRepository.saveAndFlush(pessoa);
    }

    public PessoaDTO toDTO(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();

        dto.setIdpessoa(pessoa.getIdpessoa());
        dto.setCpf(pessoa.getCpf());
        dto.setNomecompleto(pessoa.getNomecompleto());
        dto.setDatanascimento(pessoa.getDatanascimento());
        dto.setSexo(pessoa.getSexo());
        dto.setEndereco(pessoa.getEndereco());
        dto.setCep(pessoa.getCep());
        dto.setCidade(pessoa.getCidade());
        dto.setUf(pessoa.getUf());
        dto.setEmail(pessoa.getEmail());
        dto.setCelular(pessoa.getCelular());
        dto.setContatoemergencia(pessoa.getContatoemergencia());
        dto.setNomecontatoemergencia(pessoa.getNomecontatoemergencia());
        dto.setContatopreferencial(pessoa.getContatopreferencial());
        dto.setIdusuario(pessoa.getUsuario().getIdusuario());
        dto.setUsername(pessoa.getUsuario().getUsername());

        
        return dto;
    }

    public Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();

        pessoa.setIdpessoa(dto.getIdpessoa());
        pessoa.setCpf(dto.getCpf());
        pessoa.setNomecompleto(dto.getNomecompleto());
        pessoa.setDatanascimento(dto.getDatanascimento());
        pessoa.setSexo(dto.getSexo());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setCep(dto.getCep());
        pessoa.setCidade(dto.getCidade());
        pessoa.setUf(dto.getUf());
        pessoa.setEmail(dto.getEmail());
        pessoa.setCelular(dto.getCelular());
        pessoa.setContatoemergencia(dto.getContatoemergencia());
        pessoa.setNomecontatoemergencia(dto.getNomecontatoemergencia());
        pessoa.setContatopreferencial(dto.getContatopreferencial());
        Usuario usuario = new Usuario();
        usuario.setIdusuario(dto.getIdusuario());
        usuario.setLogin(dto.getUsername());

        return pessoa;
    }
}
