package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.model.Pessoa;
import com.github.plasmus777.carrosAluguel.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CriarPessoaService {
    private final PessoaRepository pessoaRepository;

    public CriarPessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Pessoa> criarPessoa(Pessoa pessoa){
        if(pessoa == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A pessoa a ser cadastrada deve ser válida.");

        if(Period.between(pessoa.getDataNascimento(), LocalDate.now()).getYears() < 18)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A pessoa a ser cadastrada é menor de idade.");

        return Optional.of(pessoaRepository.save(pessoa));
    }
}
