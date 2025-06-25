package com.SGC.projeto_final_java.repository;

import com.SGC.projeto_final_java.model.Consulta;
import com.SGC.projeto_final_java.model.Medico;
import com.SGC.projeto_final_java.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPaciente(Paciente paciente);

    List<Consulta> findByMedico(Medico medico);

    List<Consulta> findByData(LocalDate data);

    List<Consulta> findByStatus(String status);
}
