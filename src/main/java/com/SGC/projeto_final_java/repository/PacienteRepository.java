package com.SGC.projeto_final_java.repository;

import com.SGC.projeto_final_java.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByUsername(String username);

    Paciente findByEmail(String email);
}
