package com.github.plasmus777.carrosAluguel.controller;

import com.github.plasmus777.carrosAluguel.model.Veiculo;
import com.github.plasmus777.carrosAluguel.service.AlterarQuantidadeVeiculosService;
import com.github.plasmus777.carrosAluguel.service.BuscarVeiculoService;
import com.github.plasmus777.carrosAluguel.service.CriarVeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/veiculos")
public class VeiculoController {
    private final CriarVeiculoService criarVeiculoService;
    private final BuscarVeiculoService buscarVeiculoService;
    private final AlterarQuantidadeVeiculosService alterarQuantidadeVeiculosService;

    public VeiculoController(CriarVeiculoService criarVeiculoService, BuscarVeiculoService buscarVeiculoService, AlterarQuantidadeVeiculosService alterarQuantidadeVeiculosService){
        this.criarVeiculoService = criarVeiculoService;
        this.buscarVeiculoService = buscarVeiculoService;
        this.alterarQuantidadeVeiculosService = alterarQuantidadeVeiculosService;
    }

    @GetMapping
    public ResponseEntity<Veiculo> buscarVeiculoPorNumeroIdentificacao(@RequestParam String numeroIdentificacao){
        Optional<Veiculo> veiculoOpt = buscarVeiculoService.buscarVeiculoPorNumeroIdentificacao(numeroIdentificacao);
        return veiculoOpt.map(valor -> ResponseEntity.status(HttpStatus.FOUND).body(valor))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@Valid @RequestBody Veiculo veiculo){
        Optional<Veiculo> veiculoOpt = criarVeiculoService.criarVeiculo(veiculo);
        return veiculoOpt.map(valor -> ResponseEntity.status(HttpStatus.CREATED).body(valor))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/quantidadeUtilizados")
        public ResponseEntity<String> alterarQuantidadeDeVeiculosUtilizados(@RequestParam String modelo, @RequestParam int quantidade){
        try {
            if(alterarQuantidadeVeiculosService.alterarQuantidadeDeVeiculosUtilizados(modelo, quantidade)){
                return ResponseEntity.status(HttpStatus.OK).body("Valores alterados para o modelo \"" + modelo + "\" com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Os valores não foram alterados.");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno ao alterar os valores.");
        }
    }

    @PutMapping("/quantidadeDisponiveis")
    public ResponseEntity<String> alterarQuantidadeDeVeiculosDisponiveis(@RequestParam String modelo, @RequestParam int quantidade){
        try {
            if(alterarQuantidadeVeiculosService.alterarQuantidadeAtualDeVeiculosDisponiveis(modelo, quantidade)){
                return ResponseEntity.status(HttpStatus.OK).body("Valores alterados para o modelo \"" + modelo + "\" com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Os valores não foram alterados.");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno ao alterar os valores.");
        }
    }
}
