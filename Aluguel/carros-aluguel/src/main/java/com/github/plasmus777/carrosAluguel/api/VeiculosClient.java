package com.github.plasmus777.carrosAluguel.api;

import com.github.plasmus777.carrosAluguel.model.Veiculo;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("carrosAluguelVeiculos")
public interface VeiculosClient {
    @GetMapping("api/v1/veiculos")
    ResponseEntity<Veiculo> buscarVeiculoPorNumeroIdentificacao(@RequestParam String numeroIdentificacao);

    @PostMapping("api/v1/veiculos")
    ResponseEntity<Veiculo> criarVeiculo(@Valid @RequestBody Veiculo veiculo);

    @PutMapping("api/v1/veiculos/quantidadeUtilizados")
    ResponseEntity<String> alterarQuantidadeDeVeiculosUtilizados(@RequestParam String modelo, @RequestParam int quantidade);

    @PutMapping("api/v1/veiculos/quantidadeDisponiveis")
    ResponseEntity<String> alterarQuantidadeDeVeiculosDisponiveis(@RequestParam String modelo, @RequestParam int quantidade);
}
