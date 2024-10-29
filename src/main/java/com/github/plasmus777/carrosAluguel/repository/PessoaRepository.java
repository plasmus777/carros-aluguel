package com.github.plasmus777.carrosAluguel.repository;

import com.github.plasmus777.carrosAluguel.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {

}
