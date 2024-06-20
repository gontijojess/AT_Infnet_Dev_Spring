package com.gontijojess.AT_Infnet.controller;

import com.gontijojess.AT_Infnet.model.Usuario;
import com.gontijojess.AT_Infnet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public Usuario create(@RequestBody Usuario usuario) { return usuarioService.salvar(usuario); }

    @GetMapping("/usuarios")
    public List<Usuario> listar() {
        return usuarioService.buscarTodos();
    }

    @GetMapping("usuarios/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable String id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("usuarios/{id}")
    public void remover(@PathVariable String id) {
        usuarioService.excluirPorId(id);
    }

    @PutMapping("usuarios/{id}")
    public Usuario atualizar(@PathVariable String id, @RequestBody Usuario usuario) {
        return usuarioService.atualizar(id, usuario);
    }
}
