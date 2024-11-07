package com.github.plasmus777.carrosAluguel.repository;

import com.github.plasmus777.carrosAluguel.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
