package com.SGC.projeto_final_java.repository;

import com.SGC.projeto_final_java.model.HorarioDisponivel;
import com.SGC.projeto_final_java.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long> {

    // Método para encontrar horários disponíveis por médico
    List<HorarioDisponivel> findByMedicoId(Long medicoId);

    // Método para encontrar horários disponíveis por data e hora
    List<HorarioDisponivel> findByDataHoraInicioBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("""
        SELECT h FROM HorarioDisponivel h
        WHERE h.medico.id = :medicoId
        AND h.dataHoraInicio >= CURRENT_TIMESTAMP
        AND h.id NOT IN (
            SELECT c.horarioDisponivel.id
            FROM Consulta c
            WHERE c.status = 'PENDENTE'
        )
        ORDER BY h.dataHoraInicio
    """)
    List<HorarioDisponivel> findHorariosDisponiveisPorMedico(Long medicoId);

    boolean existsByMedicoAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(Medico medico, LocalDateTime fimNovo,
            LocalDateTime inicioNovo);
    
}
