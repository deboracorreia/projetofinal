/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 *
 * @author debora
 */

import com.example.demo.model.Atendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Optional<Atendimento> buscarPorId(Long idatendimento) {
        return atendimentoRepository.findById(idatendimento);
    }

    public Atendimento salvar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public Atendimento atualizar(Long idatendimento, Atendimento novoAtendimento) {
        return atendimentoRepository.findById(idatendimento).map(atendimento -> {
            atendimento.setAgendamento(novoAtendimento.getAgendamento());
            atendimento.setProfissional(novoAtendimento.getProfissional());
            atendimento.setDescricao(novoAtendimento.getDescricao());
            atendimento.setData(novoAtendimento.getData());
            return atendimentoRepository.save(atendimento);
        }).orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
    }

    public void deletar(Long idatendimento) {
        atendimentoRepository.deleteById(idatendimento);
    }
}
