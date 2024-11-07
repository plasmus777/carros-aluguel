package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.api.PessoasClient;
import com.github.plasmus777.carrosAluguel.api.VeiculosClient;
import com.github.plasmus777.carrosAluguel.model.Aluguel;
import com.github.plasmus777.carrosAluguel.model.Veiculo;
import com.github.plasmus777.carrosAluguel.repository.AluguelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FinalizarAluguelService {
    private final AluguelRepository aluguelRepository;
    private final PessoasClient pessoasClient;
    private final VeiculosClient veiculosClient;

    public FinalizarAluguelService(AluguelRepository aluguelRepository, PessoasClient pessoasClient, VeiculosClient veiculosClient){
        this.aluguelRepository = aluguelRepository;
        this.pessoasClient = pessoasClient;
        this.veiculosClient = veiculosClient;
    }

    public boolean finalizarAluguel(Long id){
        try {
            Optional<Aluguel> aluguel = aluguelRepository.findById(id);

            if(aluguel.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O aluguel a ser finalizado não foi encontrado.");
            }

            Veiculo veiculo = veiculosClient.buscarVeiculoPorNumeroIdentificacao(aluguel.get().getNumeroIdentificacao()).getBody();

            if (veiculo == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O veículo cadastrado no aluguel não foi encontrado.");
            }

            aluguel.get().setDataConclusao(LocalDate.now());
            veiculosClient.alterarQuantidadeDeVeiculosDisponiveis(veiculo.getModelo(), veiculo.getEstoque().getQuantidadeDisponivel() + 1);

            aluguelRepository.save(aluguel.get());

            return true;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro ao finalizar o aluguel.");
        }
    }
}
