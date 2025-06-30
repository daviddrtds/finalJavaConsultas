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
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "O paciente é obrigatório")
    private Paciente paciente;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "horarioDisponivel_id", nullable = false)
    @NotNull(message = "O horário é obrigatório")
    private HorarioDisponivel horarioDisponivel;

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

    public HorarioDisponivel getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(HorarioDisponivel horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
                ", paciente=" + paciente.getNome() +
                ", horarioDisponivel=" + horarioDisponivel +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }
}
