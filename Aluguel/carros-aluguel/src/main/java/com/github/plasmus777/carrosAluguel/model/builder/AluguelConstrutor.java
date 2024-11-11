package com.github.plasmus777.carrosAluguel.model.builder;

import com.github.plasmus777.carrosAluguel.model.Aluguel;

import java.time.LocalDate;

public class AluguelConstrutor implements Construtor<Aluguel>{

    private Aluguel aluguel;

    public  AluguelConstrutor(){
        novo();
    }

    @Override
    public void novo() {
        aluguel = new Aluguel();
        aluguel.setCpfPessoa("854.845.370-29");
        aluguel.setDataInicio(LocalDate.now());
        aluguel.setNomePessoa("Pessoa padrão");
        aluguel.setNumeroIdentificacao("123456");
        aluguel.setDataPrevistaConclusao(LocalDate.now().plusDays(7));
    }

    public AluguelConstrutor definirCpfPessoa(String cpfPessoa){
        aluguel.setCpfPessoa(cpfPessoa);
        return this;
    }

    public AluguelConstrutor definirDataInicio(LocalDate dataInicio){
        aluguel.setDataInicio(dataInicio);
        return this;
    }

    public AluguelConstrutor definirNomePessoa(String nomePessoa){
        aluguel.setNomePessoa(nomePessoa);
        return this;
    }

    public AluguelConstrutor definirNumeroIdentificacao(String numeroIdentificacao){
        aluguel.setNumeroIdentificacao(numeroIdentificacao);
        return this;
    }

    public AluguelConstrutor definirDataPrevistaConclusao(LocalDate dataPrevistaConclusao){
        aluguel.setDataPrevistaConclusao(dataPrevistaConclusao);
        return this;
    }

    public AluguelConstrutor definirDataConclusao(LocalDate dataConclusao){
        aluguel.setDataConclusao(dataConclusao);
        return this;
    }

    @Override
    public Aluguel construir() {
        return aluguel;
    }
}
