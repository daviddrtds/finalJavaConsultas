package com.SGC.projeto_final_java.repository;

import com.SGC.projeto_final_java.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByUsername(String username);
}
