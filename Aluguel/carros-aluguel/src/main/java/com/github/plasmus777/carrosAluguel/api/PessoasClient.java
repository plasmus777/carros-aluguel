package com.github.plasmus777.carrosAluguel.api;

import com.github.plasmus777.carrosAluguel.model.Pessoa;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("carrosAluguelPessoas")
public interface PessoasClient {
    @GetMapping("api/v1/pessoas")
    ResponseEntity<Pessoa> buscarPessoaPorCpf(@CPF @RequestParam String cpf);

    @PostMapping("api/v1/pessoas")
    ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa);
}
