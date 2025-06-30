package com.SGC.projeto_final_java.controller;

import com.SGC.projeto_final_java.model.Consulta;
import com.SGC.projeto_final_java.model.Medico;
import com.SGC.projeto_final_java.model.EnumStatusConsulta;
import com.SGC.projeto_final_java.repository.ConsultaRepository;
import com.SGC.projeto_final_java.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/consultas")
        public String verConsultas(Model model, Authentication auth) {
        if (auth == null) return "redirect:/login";

        String email = auth.getName();
        Medico medico = medicoRepository.findByEmail(email);
        if (medico == null) return "redirect:/";

        List<Consulta> consultas = consultaRepository.findByHorarioDisponivel_Medico(medico);
        model.addAttribute("consultas", consultas);
        return "medico/todas-consultas-medico";
    }


    @PostMapping("/consultas/{id}/estado")
    public String alterarEstado(@PathVariable Long id, @RequestParam("estado") EnumStatusConsulta estado) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow();
        consulta.setStatus(estado);
        consultaRepository.save(consulta);
        return "redirect:/medico/consultas";
    }

    @GetMapping("/admin/consultas")
    public String verConsultasAdmin(Model model, Authentication auth) {
        if (!isAdmin(auth))
            return "redirect:/";

        List<Consulta> consultas = consultaRepository.findAll();
        model.addAttribute("consultas", consultas);
        return "admin/todas-consultas-admin";
    }

    @PostMapping("/admin/consultas/{id}/estado")
    public String alterarEstadoAdmin(@PathVariable Long id, @RequestParam("estado") EnumStatusConsulta estado) {
        if(isAdmin(null)) {
            return "redirect:/";
        }
        Consulta consulta = consultaRepository.findById(id).orElseThrow();
        consulta.setStatus(estado);
        consultaRepository.save(consulta);
        return "redirect:/medico/admin/consultas";
    }

    @GetMapping("/admin/adicionar")
    public String mostrarFormAdmin(Model model, Authentication auth) {
        if (!isAdmin(auth))
            return "redirect:/";

        model.addAttribute("medico", new Medico());
        return "adminForm/adminForm";
    }

    // Processa submissão do formulário
    @PostMapping("/admin/adicionar")
    public String adicionarMedico(@ModelAttribute Medico medico, Authentication auth) {
        if (!isAdmin(auth))
            return "redirect:/";

        medico.setPassword(passwordEncoder.encode(medico.getPassword()));
        medicoRepository.save(medico);
        return "redirect:/medico/admin/adicionar?sucesso";
    }

    // Método auxiliar
    private boolean isAdmin(Authentication auth) {
        return auth != null && auth.getName().equals("admin@admin.com");
    }

}
