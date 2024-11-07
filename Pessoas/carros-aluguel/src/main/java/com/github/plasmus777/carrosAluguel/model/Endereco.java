package com.github.plasmus777.carrosAluguel.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Endereco {
    @NotBlank(message = "O CEP é obrigatório!")
    private String cep;

    @NotBlank(message = "A rua é obrigatória!")
    private String rua;

    @NotNull(message = "O número é obrigatório!")
    private int numero;

    @NotBlank(message = "O bairro é obrigatório!")
    private String bairro;

    private String complemento;
}
