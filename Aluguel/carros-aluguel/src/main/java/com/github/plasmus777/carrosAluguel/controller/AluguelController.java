package com.github.plasmus777.carrosAluguel.controller;

import com.github.plasmus777.carrosAluguel.model.Aluguel;
import com.github.plasmus777.carrosAluguel.service.BuscarAluguelService;
import com.github.plasmus777.carrosAluguel.service.CriarAluguelService;
import com.github.plasmus777.carrosAluguel.service.FinalizarAluguelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/alugueis")
public class AluguelController {
    private final CriarAluguelService criarAluguelService;
    private final FinalizarAluguelService finalizarAluguelService;
    private final BuscarAluguelService buscarAluguelService;

    public AluguelController(CriarAluguelService criarAluguelService, FinalizarAluguelService finalizarAluguelService, BuscarAluguelService buscarAluguelService){
        this.criarAluguelService = criarAluguelService;
        this.finalizarAluguelService = finalizarAluguelService;
        this.buscarAluguelService = buscarAluguelService;
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

    @GetMapping
    public ResponseEntity<List<Aluguel>> buscarAlugueis(){
        List<Aluguel> aluguelLista = buscarAluguelService.buscarAlugueis();

        if(aluguelLista.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(aluguelLista);
        else return ResponseEntity.status(HttpStatus.OK).body(aluguelLista);
    }
}
