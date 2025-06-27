package com.SGC.projeto_final_java.controller;

import com.SGC.projeto_final_java.model.HorarioDisponivel;
import com.SGC.projeto_final_java.repository.HorarioDisponivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios-disponiveis")
public class HorarioDisponivelController {

    @Autowired
    private HorarioDisponivelRepository horarioDisponivelRepository;

    // Listar todos os horários
    @GetMapping
    public List<HorarioDisponivel> listarTodos() {
        return horarioDisponivelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDisponivel> findPorId(@PathVariable Long id) {
        Optional<HorarioDisponivel> horario = horarioDisponivelRepository.findById(id);
        return horario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo horário
    @PostMapping
    public ResponseEntity<HorarioDisponivel> criar(@RequestBody HorarioDisponivel horarioDisponivel) {
        HorarioDisponivel salvo = horarioDisponivelRepository.save(horarioDisponivel);
        return ResponseEntity.ok(salvo);
    }

    // Atualizar horário existente
    @PutMapping("/{id}")
    public ResponseEntity<HorarioDisponivel> atualizar(@PathVariable Long id, @RequestBody HorarioDisponivel atualizado) {
        return horarioDisponivelRepository.findById(id)
                .map(horario -> {
                    horario.setMedico(atualizado.getMedico());
                    horario.setDataHoraInicio(atualizado.getDataHoraInicio());
                    horario.setDataHoraFim(atualizado.getDataHoraFim());
                    horarioDisponivelRepository.save(horario);
                    return ResponseEntity.ok(horario);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar horário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (horarioDisponivelRepository.existsById(id)) {
            horarioDisponivelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
