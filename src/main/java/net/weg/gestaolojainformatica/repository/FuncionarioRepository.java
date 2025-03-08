package net.weg.gestaolojainformatica.repository;

import net.weg.gestaolojainformatica.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
