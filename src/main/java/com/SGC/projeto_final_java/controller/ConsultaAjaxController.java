package com.SGC.projeto_final_java.controller;

import com.SGC.projeto_final_java.model.HorarioDisponivel;
import com.SGC.projeto_final_java.model.Medico;
import com.SGC.projeto_final_java.repository.HorarioDisponivelRepository;
import com.SGC.projeto_final_java.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class ConsultaAjaxController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private HorarioDisponivelRepository horarioDisponivelRepository;

    @GetMapping("/medicos/{especialidade}")
    public List<Medico> listarMedicosPorEspecialidade(@PathVariable String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    @GetMapping("/horarios/{medicoId}")
    public List<HorarioDisponivel> listarHorariosPorMedico(@PathVariable Long medicoId) {
        return horarioDisponivelRepository.findByMedicoId(medicoId);
    }

    @GetMapping("/especialidades")
    public List<String> listarEspecialidades() {
    return medicoRepository.findEspecialidadesDistintas();
}
}