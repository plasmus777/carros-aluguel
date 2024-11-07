package com.github.plasmus777.carrosAluguel.repository;

import com.github.plasmus777.carrosAluguel.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    public List<Veiculo> findVeiculosByModelo(String modelo);
}
