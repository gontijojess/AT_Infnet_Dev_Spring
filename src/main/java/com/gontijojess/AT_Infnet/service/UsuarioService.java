package com.gontijojess.AT_Infnet.service;

import com.gontijojess.AT_Infnet.exception.ResourceNotFoundException;
import com.gontijojess.AT_Infnet.model.Usuario;
import com.gontijojess.AT_Infnet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public void excluirPorId(String id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(String id, Usuario usuarioAlterado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAlterado.getNome());
            usuario.setEmail(usuarioAlterado.getEmail());
            usuario.setSenha(usuarioAlterado.getSenha());
            usuario.setPapel(usuarioAlterado.getPapel());
            if (usuario.getSenha() != null) {
                String senhaEncriptada = new BCryptPasswordEncoder().encode(usuario.getSenha());
                usuario.setSenha(senhaEncriptada);
            }
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário com ID" + id + " não encontrado"));
    }
}
