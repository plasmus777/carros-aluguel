package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.model.Pessoa;
import com.github.plasmus777.carrosAluguel.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarPessoaService {
    private final PessoaRepository pessoaRepository;

    public BuscarPessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Pessoa> buscarPessoa(String cpf){
        return pessoaRepository.findById(cpf);
    }
}
