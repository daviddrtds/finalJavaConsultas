package com.SGC.projeto_final_java.utils;

import com.SGC.projeto_final_java.model.Medico;
import com.SGC.projeto_final_java.model.Paciente;
import com.SGC.projeto_final_java.repository.PacienteRepository;
import com.SGC.projeto_final_java.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Paciente paciente = pacienteRepository.findByEmail(email);
        if (paciente != null) {
            UserDetails user = User.builder()
                    .username(paciente.getEmail())
                    .password(paciente.getPassword())
                    .roles("PACIENTE")
                    .build();

            System.out.println("Login Paciente: " + paciente.getUsername() + ", Roles: " + user.getAuthorities());
            return user;
        }

        Medico medico = medicoRepository.findByEmail(email);
        if (medico != null) {
            UserDetails user;

            if (medico.getEmail().equals("admin@admin.com")) {
                user = User.builder()
                        .username(medico.getEmail())
                        .password(medico.getPassword())
                        .roles("MEDICO", "ADMIN")
                        .build();
            } else {
                user = User.builder()
                        .username(medico.getEmail())
                        .password(medico.getPassword())
                        .roles("MEDICO")
                        .build();
            }

            System.out.println("Login Médico: " + medico.getEmail() + ", Roles: " + user.getAuthorities());
            return user;
        }

        throw new UsernameNotFoundException("Utilizador não encontrado com o email: " + email);
    }
}
