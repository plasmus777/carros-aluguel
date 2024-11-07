package com.github.plasmus777.carrosAluguel.controller;

import com.github.plasmus777.carrosAluguel.model.Pessoa;
import com.github.plasmus777.carrosAluguel.service.BuscarPessoaService;
import com.github.plasmus777.carrosAluguel.service.CriarPessoaService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

    private final CriarPessoaService criarPessoaService;
    private final BuscarPessoaService buscarPessoaService;

    public PessoaController(CriarPessoaService criarPessoaService, BuscarPessoaService buscarPessoaService){
        this.criarPessoaService = criarPessoaService;
        this.buscarPessoaService = buscarPessoaService;
    }

    @GetMapping
    public ResponseEntity<Pessoa> buscarPessoaPorCpf(@CPF @RequestParam String cpf){
        Optional<Pessoa> pessoaOpt = buscarPessoaService.buscarPessoa(cpf);
        return pessoaOpt.map(valor -> ResponseEntity.status(HttpStatus.FOUND).body(valor))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa){
        Optional<Pessoa> pessoaOpt = criarPessoaService.criarPessoa(pessoa);
        return pessoaOpt.map(valor -> ResponseEntity.status(HttpStatus.CREATED).body(valor))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
