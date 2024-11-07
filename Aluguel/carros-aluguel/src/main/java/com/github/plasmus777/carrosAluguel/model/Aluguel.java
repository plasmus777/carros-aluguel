package com.github.plasmus777.carrosAluguel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Alugueis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "O id não pode estar vazio!")
    private Long id;

    @CPF(message = "O CPF da pessoa deve ser válido!")
    @NotBlank(message = "O CPF da pessoa não pode estar vazio!")
    private String cpfPessoa;

    @NotBlank(message = "O nome da pessoa não pode estar vazio!")
    private String nomePessoa;

    @NotBlank(message = "O número de identificação do veículo não pode estar vazio!")
    private String numeroIdentificacao;

    @NotNull(message = "A data de início não pode estar vazia!")
    private LocalDate dataInicio;

    @NotNull(message = "A data prevista de conclusão não pode estar vazia!")
    private LocalDate dataPrevistaConclusao;

    private LocalDate dataConclusao;

    @Transient
    public BigDecimal obterValor(){
        if(dataInicio == null){
            return BigDecimal.ZERO;
        } else if (dataConclusao == null){
            return new BigDecimal(Period.between(dataInicio, LocalDate.now()).getDays() * 170);
        } else {
            return new BigDecimal(Period.between(dataInicio, dataConclusao).getDays() * 170);
        }
    }
}
