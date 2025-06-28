package com.SGC.projeto_final_java.utils;

import com.SGC.projeto_final_java.model.Medico;
import com.SGC.projeto_final_java.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultAdminInitializer implements CommandLineRunner {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@admin.com";

        if (medicoRepository.findByEmail(adminEmail) == null) {
            Medico admin = new Medico();
            admin.setNome("Administrador do Sistema");
            admin.setEmail(adminEmail);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Password segura e encriptada
            admin.setIdade(40);
            admin.setNumeroCarteiraMedica("ADM-0000");
            admin.setEspecialidade("Administrador");

            medicoRepository.save(admin);
            System.out.println("✔ Admin criado com sucesso (email: " + adminEmail + ")");
        } else {
            System.out.println("ℹ Admin já existe (email: " + adminEmail + ")");
        }
    }
}
