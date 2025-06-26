package com.SGC.projeto_final_java.utils;

import com.SGC.projeto_final_java.model.Paciente;
import com.SGC.projeto_final_java.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Paciente paciente = pacienteRepository.findByEmail(email);
        if (paciente == null) {
            throw new UsernameNotFoundException("Paciente não encontrado com o email: " + email);
        }

        return User.builder()
                .username(paciente.getEmail())
                .password(paciente.getPassword())
                .roles("PACIENTE") // ou usa paciente.getRole() se tiveres um campo "role"
                .build();
    }
}
