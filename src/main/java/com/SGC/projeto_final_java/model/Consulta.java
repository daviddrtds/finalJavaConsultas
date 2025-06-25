package com.SGC.projeto_final_java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
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

    @NotNull(message = "A data é obrigatória")
    private LocalDate data;

    @NotNull(message = "A hora é obrigatória")
    private LocalTime hora;

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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
                ", data=" + data +
                ", hora=" + hora +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }
}
