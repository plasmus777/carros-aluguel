package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.model.Aluguel;
import com.github.plasmus777.carrosAluguel.repository.AluguelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarAluguelService {
    private final AluguelRepository aluguelRepository;

    public BuscarAluguelService(AluguelRepository aluguelRepository){
        this.aluguelRepository = aluguelRepository;
    }

    public List<Aluguel> buscarAlugueis(){
        return aluguelRepository.findAll();
    }
}
