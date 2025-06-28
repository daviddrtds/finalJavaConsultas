package com.SGC.projeto_final_java;

import com.SGC.projeto_final_java.model.*;
import com.SGC.projeto_final_java.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository,
            HorarioDisponivelRepository horarioRepository,
            ConsultaRepository consultaRepository,
            PasswordEncoder passwordEncoder     
    ) {
        return args -> {

            // Criar Paciente
            Paciente paciente1 = new Paciente();
            paciente1.setNome("João Silva");
            paciente1.setEmail("joao.silva@email.com");
            paciente1.setUsername("joaosilva");
            paciente1.setPassword(passwordEncoder.encode("senha123"));
            paciente1.setIdade(30);
            paciente1.setNumeroUtente("UT123456");
            pacienteRepository.save(paciente1);

            // Criar Médico
            Medico medico1 = new Medico();
            medico1.setNome("Dra. Maria Santos");
            medico1.setEmail("maria.santos@email.com");
            medico1.setUsername("mariasantos");
            medico1.setPassword(passwordEncoder.encode("senhaMedico123"));
            medico1.setIdade(40);
            medico1.setNumeroCarteiraMedica("CRM123456");
            medico1.setEspecialidade("Cardiologia");
            medicoRepository.save(medico1);

            // Criar Horário Disponível
            HorarioDisponivel horario1 = new HorarioDisponivel();
            horario1.setMedico(medico1);
            horario1.setDataHoraInicio(LocalDateTime.of(2025, 7, 1, 10, 0));
            horario1.setDataHoraFim(LocalDateTime.of(2025, 7, 1, 11, 0));
            horarioRepository.save(horario1);

            // Criar Consulta
            Consulta consulta1 = new Consulta();
            consulta1.setPaciente(paciente1);
            consulta1.setHorarioDisponivel(horario1);
            consulta1.setDescricao("Consulta de rotina");
            consulta1.setStatus(EnumStatusConsulta.PENDENTE);
            consultaRepository.save(consulta1);

            System.out.println("Dados iniciais carregados com sucesso!");
        };
    }
}
