package com.SGC.projeto_final_java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório!")
    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "O nome de utilizador é obrigatório!")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password obrigatória!")
    @Size(min = 6, message = "A password deve ter pelo menos 6 caracteres")
    private String password;

    @NotNull(message = "A idade é obrigatória!")
    @Min(value = 18, message = "Deve ter pelo menos 18 anos")
    private Integer idade;

    @NotBlank(message = "Número de utente é obrigatório!")
    @Pattern(regexp = "\\d{9}", message = "O número de utente deve conter exatamente 9 dígitos numéricos")
    @Column(unique = true)
    private String numeroUtente;


    public Paciente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNumeroUtente() {
        return numeroUtente;
    }

    public void setNumeroUtente(String numeroUtente) {
        this.numeroUtente = numeroUtente;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", idade=" + idade +
                ", numeroUtente='" + numeroUtente + '\'' +
                '}';
    }
}
