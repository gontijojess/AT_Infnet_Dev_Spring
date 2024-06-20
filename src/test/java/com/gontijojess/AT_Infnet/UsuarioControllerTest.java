package com.gontijojess.AT_Infnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gontijojess.AT_Infnet.controller.UsuarioController;
import com.gontijojess.AT_Infnet.model.Usuario;
import com.gontijojess.AT_Infnet.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void salvarUsuarioTest() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setId("123abcd");
        usuario.setNome("Ana");
        usuario.setEmail("ana@gmail.com");
        usuario.setSenha("123456");
        usuario.setPapel("ADMIN");

        given(usuarioService.salvar(any(Usuario.class))).willReturn(usuario);

        mockMvc.perform(post("/api/public/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Ana"));
    }

    @Test
    public void removerUsuarioTest() throws Exception {
        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listarUsuarioTest() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setId("1");
        usuario1.setNome("João");
        usuario1.setEmail("joao@gmail.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId("2");
        usuario2.setNome("Maria");
        usuario2.setEmail("maria@gmail.com");

        given(usuarioService.buscarTodos()).willReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }

    @Test
    public void buscarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setNome("João");
        usuario.setEmail("joao@gmail.com");

        given(usuarioService.buscarPorId(anyString())).willReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("João"));
    }
    
    @Test
    public void atualizarUsuarioTest() throws Exception {
        Usuario usuarioAlterado = new Usuario();
        usuarioAlterado.setId("1");
        usuarioAlterado.setNome("João Atualizado");
        usuarioAlterado.setEmail("joao_atualizado@gmail.com");

        given(usuarioService.atualizar(anyString(), any(Usuario.class))).willReturn(usuarioAlterado);

        mockMvc.perform(put("/usuarios/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(usuarioAlterado)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("João Atualizado"));

    }
}