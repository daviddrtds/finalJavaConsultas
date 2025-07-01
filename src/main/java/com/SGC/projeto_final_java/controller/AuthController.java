package com.SGC.projeto_final_java.controller;

import com.SGC.projeto_final_java.dto.AuthRequest;
import com.SGC.projeto_final_java.dto.AuthResponse;
import com.SGC.projeto_final_java.model.Paciente;
import com.SGC.projeto_final_java.repository.PacienteRepository;
import com.SGC.projeto_final_java.utils.CustomUserDetailsService;
import com.SGC.projeto_final_java.utils.JwtUtil;

import jakarta.validation.Valid;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;


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

        @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
