package com.gontijojess.AT_Infnet.service;

import com.gontijojess.AT_Infnet.exception.ResourceNotFoundException;
import com.gontijojess.AT_Infnet.model.Funcionario;
import com.gontijojess.AT_Infnet.repository.DepartamentoRepository;
import com.gontijojess.AT_Infnet.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Funcionario salvar(Long id, Funcionario funcionario) {
        return departamentoRepository.findById(id).map(departamento -> {
            funcionario.setDepartamento(departamento);
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Departamento não encontrado com o ID " +id));
    }

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso com ID " + id + " não encontrado."));
    }

    public ResponseEntity<?> remover(Long id) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionarioRepository.delete(funcionario);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID " +id));
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAlterado) {
        return funcionarioRepository.findById(id).map(funcionario ->{
            funcionario.setNome(funcionarioAlterado.getNome());
            funcionario.setEndereco(funcionarioAlterado.getEndereco());
            funcionario.setTelefone(funcionarioAlterado.getTelefone());
            funcionario.setEmail(funcionarioAlterado.getEmail());
            funcionario.setDataNascimento(funcionarioAlterado.getDataNascimento());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o id: " + id));
    }

    public ResponseEntity<?> funcionarioPorDepartamento(Long id) {
        List<Funcionario> funcionarios = funcionarioRepository.findByDepartamentoId(id);
        if(funcionarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarios);
    }
}

