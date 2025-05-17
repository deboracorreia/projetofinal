/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

/**
 *
 * @author debora
 */

import com.example.demo.model.Tratamento;
import com.example.demo.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.repository.AgendamentoRepository;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    public List<Tratamento> listarTodos() {
        return tratamentoRepository.findAll();
    }

    public Optional<Tratamento> buscarPorId(Long id) {
        return tratamentoRepository.findById(id);
    }

    public Tratamento salvar(Tratamento tratamento) {
        return tratamentoRepository.save(tratamento);
    }

    public Tratamento atualizar(Long id, Tratamento novoTratamento) {
        return tratamentoRepository.findById(id).map(tratamento -> {
            tratamento.setId(novoTratamento.getId());
            tratamento.setNometratamento(novoTratamento.getNometratamento());
            return tratamentoRepository.save(tratamento);
        }).orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));
    }

    public void deletar(Long id) {
        tratamentoRepository.deleteById(id);
    }
}
