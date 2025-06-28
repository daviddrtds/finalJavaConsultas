package com.SGC.projeto_final_java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SGC.projeto_final_java.utils.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/", "/registo", "/erro", "/css/**", "/js/**").permitAll()
    // .requestMatchers("/paciente/**").hasRole("PACIENTE")
    // .requestMatchers("/medico/**").hasRole("MEDICO")
    // .anyRequest().authenticated())
    // .formLogin(form -> form
    // .loginPage("/login")
    // .defaultSuccessUrl("/", true)
    // .permitAll())
    // .logout(logout -> logout.permitAll());

    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "*.css", "/login", "/registo", "/h2-console/**")
                        .permitAll()
                        .requestMatchers("/medico/**").hasRole("MEDICO")
                        .requestMatchers("/paciente/**").hasRole("PACIENTE")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()) // necessário
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()) // necessário
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers
                        .frameOptions().disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public AuthenticationManager authenticationManager(HttpSecurity http) throws
    // Exception {
    // return http.getSharedObject(AuthenticationManagerBuilder.class)
    // .userDetailsService(customUserDetailsService)
    // .passwordEncoder(passwordEncoder())
    // .and()
    // .build();
    // }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}