package com.SGC.projeto_final_java.config;

import com.SGC.projeto_final_java.utils.JwtFilter;
import com.SGC.projeto_final_java.utils.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**", "/paciente/**"))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "*.css", "/login", "/registo", "/h2-console/**")
            .permitAll()
            .requestMatchers("/medico/admin/**").hasRole("ADMIN")
            .requestMatchers("/medico/**").hasRole("MEDICO")
            .requestMatchers("/paciente/**").hasRole("PACIENTE")
            .anyRequest().authenticated())
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll())
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll())
        .headers(headers -> headers.frameOptions().disable());

        // Adiciona o filtro JWT antes do filtro de autenticação padrão
         http.addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        // Opcional: configurar headers para o h2-console funcionar
        http.headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
