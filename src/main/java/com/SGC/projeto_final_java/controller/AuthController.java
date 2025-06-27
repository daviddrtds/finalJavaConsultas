package com.SGC.projeto_final_java.controller;

import com.SGC.projeto_final_java.model.Paciente;
import com.SGC.projeto_final_java.repository.PacienteRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/registo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "registo";
    }

    @PostMapping("/registo")
    public String registarPaciente(@Valid @ModelAttribute("paciente") Paciente paciente,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "registo";
        }

        if (pacienteRepository.findByEmail(paciente.getEmail()) != null) {
            model.addAttribute("erroEmail", "Já existe um utilizador com esse email.");
            return "registo";
        }

        if (pacienteRepository.findByNumeroUtente(paciente.getNumeroUtente()) != null) {
            model.addAttribute("erroNumeroUtente", "Já existe um utilizador com esse número de utente.");
            return "registo";
        }

        paciente.setPassword(passwordEncoder.encode(paciente.getPassword()));
        pacienteRepository.save(paciente);
        return "redirect:/login";
    }
}
