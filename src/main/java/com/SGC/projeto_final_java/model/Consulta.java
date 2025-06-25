package com.SGC.projeto_final_java.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Medico medico;
    private Paciente paciente;
    private LocalDate data;
    private LocalTime hora;
    private String descricao;

    // @ManyToOne
    // private User paciente;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

}
