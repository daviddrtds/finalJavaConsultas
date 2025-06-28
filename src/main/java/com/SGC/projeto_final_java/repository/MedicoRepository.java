package com.SGC.projeto_final_java.repository;

import com.SGC.projeto_final_java.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByEmail(String email);
    Medico findByUsername(String username);
    List<Medico> findByEspecialidade(String especialidade);

    @Query("SELECT DISTINCT m.especialidade FROM Medico m WHERE LOWER(m.especialidade) <> 'administrador'")
    List<String> findEspecialidadesDistintas();

}
