package com.gontijojess.AT_Infnet.controller;

import com.gontijojess.AT_Infnet.model.Departamento;
import com.gontijojess.AT_Infnet.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping("/departamentos")
    public Departamento adicionar(@RequestBody Departamento departamento) {
        return departamentoService.salvar(departamento);
    }

    @GetMapping("/departamentos")
    public List<Departamento> listar() {
        return departamentoService.listar();
    }

    @GetMapping("/departamentos/{id}")
    public Optional<Departamento> buscar(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    @DeleteMapping("/departamentos/{id}")
    public void remover(@PathVariable Long id) {
        departamentoService.excluir(id);
    }

    @PutMapping("/departamentos/{id}")
    public Departamento atualizar(@PathVariable Long id, @RequestBody Departamento departamento) {
        return departamentoService.atualizar(id, departamento);
    }
}
