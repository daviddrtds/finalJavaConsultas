package com.SGC.projeto_final_java.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 2, max = 100, message = "O nome do utilizador deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório!")
    @Email(message = "Email incorreto")
    private String email;

    @NotBlank(message = "Password obrigatória!")
    private String password;

    @NotNull(message = "A idade é obrigatória!")
    @Min(value = 18, message = "Idade inválida, deve ser maior de 18 anos.")
    private int idade;

    @NotNull(message = "Número de carteira profissional inválido!")
    @Min(value = 1, message = "Número de carteira profissional inválido!")
    private int numeroCarteiraMedica;

    @NotBlank(message = "A especialidade é obrigatória!")
    @Size(min = 1, max = 100, message = "O nome da especialidaee deve ter entre 2 e 100 caracteres")
    private String especialidade;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getNumeroCarteiraMedica() {
        return numeroCarteiraMedica;
    }

    public void setNumeroCarteiraMedica(int numeroCarteiraMedica) {
        this.numeroCarteiraMedica = numeroCarteiraMedica;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
