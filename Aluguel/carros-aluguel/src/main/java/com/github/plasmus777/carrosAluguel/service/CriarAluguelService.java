package com.github.plasmus777.carrosAluguel.service;

import com.github.plasmus777.carrosAluguel.api.PessoasClient;
import com.github.plasmus777.carrosAluguel.api.VeiculosClient;
import com.github.plasmus777.carrosAluguel.model.Aluguel;
import com.github.plasmus777.carrosAluguel.model.Pessoa;
import com.github.plasmus777.carrosAluguel.model.Veiculo;
import com.github.plasmus777.carrosAluguel.repository.AluguelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CriarAluguelService {
    private final AluguelRepository aluguelRepository;
    private final PessoasClient pessoasClient;
    private final VeiculosClient veiculosClient;

    public CriarAluguelService(AluguelRepository aluguelRepository, PessoasClient pessoasClient, VeiculosClient veiculosClient){
        this.aluguelRepository = aluguelRepository;
        this.pessoasClient = pessoasClient;
        this.veiculosClient = veiculosClient;
    }

    public Optional<Aluguel> criarAluguel(String cpfPessoa, String numeroIdentificacaoVeiculo){
        try {
            Pessoa pessoa = pessoasClient.buscarPessoaPorCpf(cpfPessoa).getBody();
            Veiculo veiculo = veiculosClient.buscarVeiculoPorNumeroIdentificacao(numeroIdentificacaoVeiculo).getBody();

            if (pessoa == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A pessoa a ser cadastrada no aluguel não foi encontrada.");
            }
            if (veiculo == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O veículo a ser cadastrado no aluguel não foi encontrado.");
            }

            if (veiculo.getEstoque().getQuantidadeDisponivel() <= 0 || veiculo.getEstoque().getQuantidadeAtual() <= 0) {
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Não há veículos disponíveis para o aluguel.");
            }

            Aluguel aluguel = new Aluguel();
            aluguel.setCpfPessoa(pessoa.getCpf());
            aluguel.setDataInicio(LocalDateTime.now().toLocalDate());
            aluguel.setDataPrevistaConclusao(LocalDateTime.now().toLocalDate().plusMonths(1));
            aluguel.setNomePessoa(pessoa.getNome());
            aluguel.setNumeroIdentificacao(veiculo.getNumeroIdentificacao());

            veiculosClient.alterarQuantidadeDeVeiculosDisponiveis(veiculo.getModelo(), veiculo.getEstoque().getQuantidadeDisponivel() - 1);

            return Optional.of(aluguelRepository.save(aluguel));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro ao registrar o aluguel.");
        }
    }
}
