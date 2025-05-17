/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 *
 * @author debora
 */

import com.example.demo.model.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarPorId(Long idagendamento) {
        return agendamentoRepository.findById(idagendamento);
    }

    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Long idagendamento, Agendamento novoAgendamento) {
        return agendamentoRepository.findById(idagendamento).map(agendamento -> {
            agendamento.setDescricao(novoAgendamento.getDescricao());
            agendamento.setDataHorario(novoAgendamento.getDataHorario());
            agendamento.setUsuario(novoAgendamento.getUsuario());
            agendamento.setTratamento(novoAgendamento.getTratamento());
            return agendamentoRepository.save(agendamento);
        }).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void deletar(Long idagendamento) {
        agendamentoRepository.deleteById(idagendamento);
    }
}
