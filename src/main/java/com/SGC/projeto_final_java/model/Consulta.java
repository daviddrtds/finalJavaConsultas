package com.SGC.projeto_final_java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    @NotNull(message = "O médico é obrigatório")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "O paciente é obrigatório")
    private Paciente paciente;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDateTime dataHoraFim;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private EnumStatusConsulta status = EnumStatusConsulta.PENDENTE;

    public Consulta() {
    }

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EnumStatusConsulta getStatus() {
        return status;
    }

    public void setStatus(EnumStatusConsulta status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", medico=" + medico.getNome() +
                ", paciente=" + paciente.getNome() +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }
}
