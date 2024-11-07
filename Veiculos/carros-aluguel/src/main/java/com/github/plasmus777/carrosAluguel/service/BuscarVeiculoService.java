package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.model.Veiculo;
import com.github.plasmus777.carrosAluguel.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarVeiculoService {
    private final VeiculoRepository veiculoRepository;

    public BuscarVeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public Optional<Veiculo> buscarVeiculoPorModelo(String numeroIdentificacao){
        return veiculoRepository.findById(numeroIdentificacao);
    }

    public Optional<Veiculo> buscarVeiculoPorNumeroIdentificacao(String numeroIdentificacao){
        return veiculoRepository.findById(numeroIdentificacao);
    }

}
