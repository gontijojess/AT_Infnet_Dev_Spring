package com.gontijojess.AT_Infnet.controller;

import com.gontijojess.AT_Infnet.model.Funcionario;
import com.gontijojess.AT_Infnet.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("funcionarios/departamento/{id}")
    public Funcionario criar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.salvar(id, funcionario);
    }

    @GetMapping("/funcionarios")
    public List<Funcionario> listar() {
        return funcionarioService.listar();
    }

    @GetMapping("funcionarios/{id}")
    public Funcionario buscar(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id);
    }

    @DeleteMapping("funcionarios/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return funcionarioService.remover(id);
    }

    @PutMapping("funcionarios/{id}")
    public Funcionario atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.atualizar(id, funcionario);
    }

    @GetMapping("funcionarios/departamento/{id}")
    public ResponseEntity<?> departamentoFuncionarios(@PathVariable Long id) {
        return funcionarioService.funcionarioPorDepartamento(id);
    }
}
