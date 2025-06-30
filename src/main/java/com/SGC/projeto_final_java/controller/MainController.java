package com.SGC.projeto_final_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SGC.projeto_final_java.model.Medico;
import com.SGC.projeto_final_java.model.Paciente;
import com.SGC.projeto_final_java.repository.MedicoRepository;
import com.SGC.projeto_final_java.repository.PacienteRepository;

@Controller
public class MainController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PACIENTE"))) {
            Paciente paciente = pacienteRepository.findByEmail(auth.getName());
            model.addAttribute("paciente", paciente);
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MEDICO"))) {
            Medico medico = medicoRepository.findByEmail(auth.getName());
            model.addAttribute("medico", medico);
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            Medico admin = medicoRepository.findByEmail(auth.getName());
            model.addAttribute("admin", admin);
        }

        // Verificar se o usuário tem **apenas** a role MEDICO
        boolean apenasMedico = auth.getAuthorities().size() == 1 &&
            auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MEDICO"));

        model.addAttribute("apenasMedico", apenasMedico);
        return "home";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    @GetMapping("/erro")
    public String erro() {
        return "erro";
    }
}