package com.SGC.projeto_final_java.controller;

import com.SGC.projeto_final_java.model.Consulta;
import com.SGC.projeto_final_java.model.Paciente;
import com.SGC.projeto_final_java.model.EnumStatusConsulta;
import com.SGC.projeto_final_java.repository.ConsultaRepository;
import com.SGC.projeto_final_java.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/consultas")
    public String verConsultas(Authentication auth, Model model) {
        Paciente paciente = pacienteRepository.findByEmail(auth.getName());
if (paciente == null) {
    throw new UsernameNotFoundException("Paciente no encontrado");
}

        List<Consulta> consultas = consultaRepository.findByPaciente(paciente);
        model.addAttribute("consultas", consultas);
        return "pacientes/todas-consultas-paciente";
    }

    @GetMapping("/consultas/criar")
    public String criarConsulta(Model model) {
        model.addAttribute("consulta", new Consulta());
        return "pacientes/criar-consulta-paciente";
    }

    @PostMapping("/consultas/criar")
    public String agendarConsulta(@ModelAttribute Consulta consulta, Authentication auth) {
        Paciente paciente = pacienteRepository.findByUsername(auth.getName());
        consulta.setPaciente(paciente);
        consulta.setStatus(EnumStatusConsulta.PENDENTE);
        consultaRepository.save(consulta);
        return "redirect:/paciente/consultas";
    }

    @PostMapping("/consultas/{id}/cancelar")
    public String cancelarConsulta(@PathVariable Long id, Authentication auth) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow();
        // Só pode cancelar se for do próprio paciente (regras de negócio)
        if (consulta.getPaciente().getUsername().equals(auth.getName())) {
            consulta.setStatus(EnumStatusConsulta.CANCELADA);
            consultaRepository.save(consulta);
        }
        return "redirect:/paciente/consultas";
    }
}
