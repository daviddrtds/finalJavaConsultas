package com.SGC.projeto_final_java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "medicos")
public class Medico {

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

    @NotBlank(message = "Número de cédula profissional é obrigatório!")
    @Pattern(regexp = "\\d{4,6}", message = "O número de cédula profissional deve conter entre 4 e 6 dígitos numéricos")
    @Column(unique = true)
    private String numeroCarteiraMedica;


    @Size(min = 0, max = 100, message = "A especialidade deve ter entre 0 e 100 caracteres")
    private String especialidade;

    public Medico() {
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

    public String getNumeroCarteiraMedica() {
        return numeroCarteiraMedica;
    }

    public void setNumeroCarteiraMedica(String numeroCarteiraMedica) {
        this.numeroCarteiraMedica = numeroCarteiraMedica;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", idade=" + idade +
                ", numeroCarteiraMedica='" + numeroCarteiraMedica + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
