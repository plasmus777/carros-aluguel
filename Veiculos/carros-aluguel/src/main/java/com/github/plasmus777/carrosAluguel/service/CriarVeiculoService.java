package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.model.Veiculo;
import com.github.plasmus777.carrosAluguel.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CriarVeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final AlterarQuantidadeVeiculosService alterarQuantidadeVeiculosService;

    public CriarVeiculoService(VeiculoRepository veiculoRepository, AlterarQuantidadeVeiculosService alterarQuantidadeVeiculosService){
        this.veiculoRepository = veiculoRepository;
        this.alterarQuantidadeVeiculosService = alterarQuantidadeVeiculosService;
    }

    public Optional<Veiculo> criarVeiculo(Veiculo veiculo){
        if(veiculo == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O veículo a ser cadastrado deve ser válido.");

        Optional<Veiculo> veiculoOpt = Optional.of(veiculoRepository.save(veiculo));

        alterarQuantidadeVeiculosService.alterarQuantidadeDeVeiculosUtilizados(veiculo.getModelo(), veiculoRepository.findVeiculosByModelo(veiculo.getModelo()).size());
        alterarQuantidadeVeiculosService.alterarQuantidadeAtualDeVeiculosDisponiveis(veiculo.getModelo(), veiculo.getEstoque().getQuantidadeDisponivel() + 1);

        return veiculoOpt;
    }
}
