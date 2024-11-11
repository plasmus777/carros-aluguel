package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.model.Veiculo;
import com.github.plasmus777.carrosAluguel.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlterarQuantidadeVeiculosService {
    private final VeiculoRepository veiculoRepository;

    public AlterarQuantidadeVeiculosService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public boolean alterarQuantidadeDeVeiculosUtilizados(String modelo, int quantidade){
        List<Veiculo> lista = veiculoRepository.findVeiculosByModelo(modelo);
        if (lista.isEmpty())return false;
        try{
                lista
                    .forEach(veiculo -> {
                        veiculo.getEstoque().setQuantidadeAtual(quantidade);
                        veiculoRepository.save(veiculo);
                    });
            return true;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "O sistema não pôde alterar a quantidade de veículos utilizados para o modelo definido.");
        }
    }

    public boolean alterarQuantidadeAtualDeVeiculosDisponiveis(String modelo, int quantidade){
        List<Veiculo> lista = veiculoRepository.findVeiculosByModelo(modelo);
        if (lista.isEmpty())return false;
        try{
                lista
                        .forEach(veiculo -> {
                            veiculo.getEstoque().setQuantidadeDisponivel(quantidade);
                            veiculoRepository.save(veiculo);
                        });
            return true;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "O sistema não pôde alterar a quantidade disponível de veículos para o modelo definido.");
        }
    }
}