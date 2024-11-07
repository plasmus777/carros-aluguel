package com.github.plasmus777.carrosAluguel.controller;

import com.github.plasmus777.carrosAluguel.model.Aluguel;
import com.github.plasmus777.carrosAluguel.service.CriarAluguelService;
import com.github.plasmus777.carrosAluguel.service.FinalizarAluguelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/alugueis")
public class AluguelController {
    private final CriarAluguelService criarAluguelService;
    private final FinalizarAluguelService finalizarAluguelService;

    public AluguelController(CriarAluguelService criarAluguelService, FinalizarAluguelService finalizarAluguelService){
        this.criarAluguelService = criarAluguelService;
        this.finalizarAluguelService = finalizarAluguelService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Aluguel> criarAluguel(@Valid @RequestBody Aluguel aluguel){
        Optional<Aluguel> aluguelOpt = criarAluguelService.criarAluguel(aluguel.getCpfPessoa(), aluguel.getNumeroIdentificacao());
        return aluguelOpt.map(valor -> ResponseEntity.status(HttpStatus.CREATED).body(valor))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarAluguel(@RequestParam Long id){
        try {
            if(finalizarAluguelService.finalizarAluguel(id)){
                return ResponseEntity.status(HttpStatus.OK).body("Aluguel finalizado com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("O aluguel não foi finalizado com sucesso.");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno ao finalizar o aluguel.");
        }
    }
}
