package com.gontijojess.AT_Infnet.service;

import com.gontijojess.AT_Infnet.exception.ResourceNotFoundException;
import com.gontijojess.AT_Infnet.model.Departamento;
import com.gontijojess.AT_Infnet.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento salvar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> listar() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> buscarPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    public void excluir(Long id) {
        if(!departamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Departamento com ID " + id + " não encontrado");
        }
        departamentoRepository.deleteById(id);
    }

    public Departamento atualizar(Long id, Departamento departamentoAlterado) {
        return departamentoRepository.findById(id).map(departamento -> {
            departamento.setNome(departamentoAlterado.getNome());
            departamento.setLocal(departamentoAlterado.getLocal());
            return departamentoRepository.save(departamento);
        }).orElseGet(() -> {
            departamentoAlterado.setId(id);
            return departamentoRepository.save(departamentoAlterado);
        });
    }
}
