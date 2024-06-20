package com.gontijojess.AT_Infnet;

import com.gontijojess.AT_Infnet.model.Departamento;
import com.gontijojess.AT_Infnet.service.DepartamentoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DepartamentoServiceTests {
    @Autowired
    DepartamentoService departamentoService;

    @Test
    @DisplayName("Deve inserir um departamento")
    public void testSalvar(){
        List<Departamento> all = departamentoService.listar();
        int estadoInicial = all.size();
        Departamento departamento = new Departamento();
        departamento.setNome("Marketing");
        departamento.setLocal("São Paulo");
        departamentoService.salvar(departamento);
        all = departamentoService.listar();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @DisplayName("Deve deletar um departamento do banco")
    public void testExcluir(){
        Departamento departamento = new Departamento();
        departamento.setNome("Marketing");
        departamento.setLocal("São Paulo");
        departamentoService.salvar(departamento);
        List<Departamento> all = departamentoService.listar();
        int estadoInicial = all.size();
        Departamento departamento2 = all.get(0);
        departamentoService.excluir(departamento2.getId());
        all = departamentoService.listar();
        int estadoFinal = all.size();
        assertEquals(estadoInicial - 1, estadoFinal);
    }

    @Test
    @DisplayName("Deve retornar um departamento pelo ID")
    public void testBuscarPorId(){
        Departamento departamento = new Departamento();
        departamento.setNome("Marketing");
        departamento.setLocal("São Paulo");
        departamentoService.salvar(departamento);
        List<Departamento> all = departamentoService.listar();
        Departamento departamento2 = all.get(0);
        Optional<Departamento> porId = departamentoService.buscarPorId(departamento2.getId());
        assertTrue(porId.isPresent());

    }
}
