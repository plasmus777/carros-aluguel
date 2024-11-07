package com.github.plasmus777.carrosAluguel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Estoque {
    @Column(nullable = false)
    @NotNull(message = "A quantidade de veículos utilizados é obrigatória!")
    @Min(value = 0, message = "A quantidade de veículos utilizados não pode ser menor do que zero!")
    private int quantidadeAtual;

    @Column(nullable = false)
    @NotNull(message = "A quantidade de veículos disponíveis é obrigatória!")
    @Min(value = 0, message = "A quantidade de veículos disponíveis não pode ser menor do que zero!")
    private int quantidadeDisponivel;
}
