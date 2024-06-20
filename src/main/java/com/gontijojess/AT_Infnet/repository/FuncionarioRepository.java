package com.gontijojess.AT_Infnet.repository;

import com.gontijojess.AT_Infnet.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByDepartamentoId(Long id);
}
