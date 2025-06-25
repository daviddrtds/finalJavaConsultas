package com.SGC.projeto_final_java.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
 
@Entity
public class Paciente {
 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 2, max = 100, message = "O nome do utilizador deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório!")
    @Email(message = "Email incorreto")
    private String email;

    @NotNull(message = "A idade é obrigatória!")
    @Min(value=18, message = "Idade inválida, deve ser maior de 18 anos.")
    private int idade;
    
    @NotNull(message = "Número inválido")
    @Min(value=1, message = "Idade inválida, deve ser maior de 18 anos.")
    private int numeroUtente;

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

    public int getNumeroUtente() {
        return numeroUtente;
    }

    public void setNumeroUtente(int numeroUtente) {
        this.numeroUtente = numeroUtente;
    }
    
}
