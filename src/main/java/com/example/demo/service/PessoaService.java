/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 *
 * @author debora
 */

import com.example.demo.model.Pessoa;
import com.example.demo.model.Profissional;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorIdpessoa(Long idpessoa) {
        return pessoaRepository.findById(idpessoa);
    }

    public Pessoa salvar(Pessoa pessoa) {//Utilizar PessoaDTO
        //Adicionar esse código depois de criar o ServicePessoa (que ainda não existe
        Pessoa newPessoa = pessoaRepository.save(pessoa);//quando receber o dto vai precisar de um new Pessoa(pessoaD)
        //if (pessoaDto.getIdUsuario()) {
        //    Usuario usuario = usuarioRepository.findById(pessoaDto.getIdUsuario());
        //    usuario.setPessoa(newPessoa);
        //    usuarioRepository.save()
        //}
        return newPessoa;
    }

    public void excluir(Long idpessoa) {
        pessoaRepository.deleteById(idpessoa);
    }
}
