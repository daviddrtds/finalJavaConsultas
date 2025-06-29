package com.SGC.projeto_final_java;

import com.SGC.projeto_final_java.model.*;
import com.SGC.projeto_final_java.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

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
            paciente1.setNumeroUtente("987654321");
            

        Paciente paciente2 = new Paciente();
        paciente2.setNome("Ana Costa");
        paciente2.setEmail("ana.costa@email.com");
        paciente2.setUsername("anacosta");
        paciente2.setPassword(passwordEncoder.encode("senha123"));
        paciente2.setIdade(28);
        paciente2.setNumeroUtente("123456789");

        Paciente paciente3 = new Paciente();
        paciente3.setNome("Carlos Pereira");
        paciente3.setEmail("carlos.pereira@email.com");
        paciente3.setUsername("carlosp");
        paciente3.setPassword(passwordEncoder.encode("senha123"));
        paciente3.setIdade(45);
        paciente3.setNumeroUtente("555666777");

        pacienteRepository.saveAll(List.of(paciente1, paciente2, paciente3));

            // Criar Médico
            Medico medico1 = new Medico();
            medico1.setNome("Dra. Maria Santos");
            medico1.setEmail("maria.santos@email.com");
            medico1.setUsername("mariasantos");
            medico1.setPassword(passwordEncoder.encode("senhaMedico123"));
            medico1.setIdade(40);
            medico1.setNumeroCarteiraMedica("1234");
            medico1.setEspecialidade("Cardiologia");
            
Medico medico2 = new Medico();
        medico2.setNome("Dr. Pedro Lima");
        medico2.setEmail("pedro.lima@email.com");
        medico2.setUsername("pedrolima");
        medico2.setPassword(passwordEncoder.encode("senhaMedico123"));
        medico2.setIdade(50);
        medico2.setNumeroCarteiraMedica("5678");
        medico2.setEspecialidade("Dermatologia");

        medicoRepository.saveAll(List.of(medico1, medico2));

            // Criar Horário Disponível
            HorarioDisponivel h1 = new HorarioDisponivel();
            h1.setMedico(medico1);
            h1.setDataHoraInicio(LocalDateTime.of(2025, 7, 1, 10, 0));
            h1.setDataHoraFim(LocalDateTime.of(2025, 7, 1, 11, 0));
            horarioRepository.save(h1);

            HorarioDisponivel h2 = new HorarioDisponivel();
        h2.setMedico(medico1);
        h2.setDataHoraInicio(LocalDateTime.of(2025, 7, 1, 11, 0));
        h2.setDataHoraFim(LocalDateTime.of(2025, 7, 1, 12, 0));

        HorarioDisponivel h3 = new HorarioDisponivel();
        h3.setMedico(medico1);
        h3.setDataHoraInicio(LocalDateTime.of(2025, 7, 2, 9, 0));
        h3.setDataHoraFim(LocalDateTime.of(2025, 7, 2, 10, 0));

        HorarioDisponivel h4 = new HorarioDisponivel();
        h4.setMedico(medico1);
        h4.setDataHoraInicio(LocalDateTime.of(2025, 7, 2, 10, 0));
        h4.setDataHoraFim(LocalDateTime.of(2025, 7, 2, 11, 0));

        // Criar Horários para médico 2
        HorarioDisponivel h5 = new HorarioDisponivel();
        h5.setMedico(medico2);
        h5.setDataHoraInicio(LocalDateTime.of(2025, 7, 1, 14, 0));
        h5.setDataHoraFim(LocalDateTime.of(2025, 7, 1, 15, 0));

        HorarioDisponivel h6 = new HorarioDisponivel();
        h6.setMedico(medico2);
        h6.setDataHoraInicio(LocalDateTime.of(2025, 7, 1, 15, 0));
        h6.setDataHoraFim(LocalDateTime.of(2025, 7, 1, 16, 0));

        HorarioDisponivel h7 = new HorarioDisponivel();
        h7.setMedico(medico2);
        h7.setDataHoraInicio(LocalDateTime.of(2025, 7, 2, 14, 0));
        h7.setDataHoraFim(LocalDateTime.of(2025, 7, 2, 15, 0));

        HorarioDisponivel h8 = new HorarioDisponivel();
        h8.setMedico(medico2);
        h8.setDataHoraInicio(LocalDateTime.of(2025, 7, 2, 15, 0));
        h8.setDataHoraFim(LocalDateTime.of(2025, 7, 2, 16, 0));

        horarioRepository.saveAll(List.of(h1, h2, h3, h4, h5, h6, h7, h8));

        // Criar Consultas
        Consulta consulta1 = new Consulta();
        consulta1.setPaciente(paciente1);
        consulta1.setHorarioDisponivel(h1);
        consulta1.setDescricao("Consulta de rotina");
        consulta1.setStatus(EnumStatusConsulta.PENDENTE);

        Consulta consulta2 = new Consulta();
        consulta2.setPaciente(paciente2);
        consulta2.setHorarioDisponivel(h5);
        consulta2.setDescricao("Consulta dermatológica");
        consulta2.setStatus(EnumStatusConsulta.CONCLUIDA);

        Consulta consulta3 = new Consulta();
        consulta3.setPaciente(paciente3);
        consulta3.setHorarioDisponivel(h6);
        consulta3.setDescricao("Revisão de manchas");
        consulta3.setStatus(EnumStatusConsulta.CANCELADA);

        consultaRepository.saveAll(List.of(consulta1, consulta2, consulta3));

            System.out.println("Dados iniciais carregados com sucesso!");
        };
    }
}
