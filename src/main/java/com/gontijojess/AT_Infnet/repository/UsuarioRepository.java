package com.gontijojess.AT_Infnet.repository;

import com.gontijojess.AT_Infnet.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findUsuarioByNome(String nome);
}
