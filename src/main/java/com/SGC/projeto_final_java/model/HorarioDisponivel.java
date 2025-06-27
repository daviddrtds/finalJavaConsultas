package com.SGC.projeto_final_java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "horarios_disponiveis")
public class HorarioDisponivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @NotNull(message = "Data e hora de início é obrigatória")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "Data e hora de fim é obrigatória")
    private LocalDateTime dataHoraFim;

    public HorarioDisponivel() {}

    public HorarioDisponivel(Medico medico, LocalDateTime inicio, LocalDateTime fim) {
        this.medico = medico;
        this.dataHoraInicio = inicio;
        this.dataHoraFim = fim;
    }

    // getters e setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    @Override
    public String toString() {
        return "HorarioDisponivel{" +
                "id=" + id +
                ", medico=" + medico.getNome() +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                '}';
    }
}
