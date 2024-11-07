package com.github.plasmus777.carrosAluguel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Veiculo {
    @Id
    @Column(nullable = false, length = 255)
    @NotBlank(message = "O número de identificação do veículo é obrigatório!")
    private String numeroIdentificacao;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "O modelo do veículo é obrigatório!")
    private String modelo;

    @Column(nullable = false)
    @Min(value = 0, message = "O ano do veículo não pode ser menor do que zero!")
    private int ano;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "A cor do veículo é obrigatória!")
    private String cor;

    @Embedded
    @Column(nullable = false, length = 255)
    @NotNull(message = "O veículo registrado deve possuir um estoque no sistema!")
    private Estoque estoque;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "A categoria do veículo é obrigatória!")
    private String categoria;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "A descrição do veículo é obrigatória!")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "O preço do veículo é obrigatório!")
    @Min(value = 0, message = "O preço de aluguel do veículo não pode ser menor do que zero!")
    private BigDecimal preco;
}
