package com.github.plasmus777.carrosAluguel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "Pessoas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Pessoa {
    @Id
    @CPF
    @Column(name = "cpf", nullable = false, length = 255)
    @NotNull(message = "O cpf é obrigatório!")
    private String cpf;

    @Column(name = "nome", nullable = false, length = 255)
    @NotNull(message = "O nome é obrigatório!")
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    @NotNull(message = "A data de nascimento é obrigatória!")
    private LocalDate dataNascimento;

    @Column(name = "num_habilitacao", nullable = false, length = 255)
    @NotEmpty(message = "O número de habilitação é obrigatório e não pode ser vazio!")
    private String numeroHabilitacao;

    @Embedded
    @NotNull(message = "O endereço é obrigatório!")
    Endereco endereco;
}