package com.github.plasmus777.carrosAluguel.model.builder;

import com.github.plasmus777.carrosAluguel.model.Estoque;
import com.github.plasmus777.carrosAluguel.model.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VeiculoConstrutor implements Construtor<Veiculo>{
    private Veiculo veiculo;

    public VeiculoConstrutor(){
        novo();
    }

    @Override
    public void novo() {
        veiculo = new Veiculo();
        veiculo.setAno(LocalDateTime.now().getYear());
        veiculo.setCor("Cor padrão");
        veiculo.setCategoria("Categoria padrão");
        veiculo.setModelo("Modelo padrão");
        veiculo.setDescricao("Descrição padrão");
        veiculo.setNumeroIdentificacao("123456");
        veiculo.setPreco(new BigDecimal("10000"));
        veiculo.setEstoque(new Estoque(1, 1));
    }

    public VeiculoConstrutor definirAno(int ano){
        veiculo.setAno(ano);
        return this;
    }

    public VeiculoConstrutor definirCor(String cor){
        veiculo.setCor(cor);
        return this;
    }

    public VeiculoConstrutor definirCategoria(String categoria){
        veiculo.setCategoria(categoria);
        return this;
    }

    public VeiculoConstrutor definirModelo(String modelo){
        veiculo.setModelo(modelo);
        return this;
    }

    public VeiculoConstrutor definirDescricao(String descricao){
        veiculo.setDescricao(descricao);
        return this;
    }

    public VeiculoConstrutor definirNumeroIdentificacao(String numeroIdentificacao){
        veiculo.setNumeroIdentificacao(numeroIdentificacao);
        return this;
    }

    public VeiculoConstrutor definirPreco(BigDecimal preco){
        veiculo.setPreco(preco);
        return this;
    }

    public VeiculoConstrutor definirQuantidadeAtual(int quantidadeAtual){
        veiculo.getEstoque().setQuantidadeAtual(quantidadeAtual);
        return this;
    }

    public VeiculoConstrutor definirQuantidadeDisponivel(int quantidadeDisponivel){
        veiculo.getEstoque().setQuantidadeDisponivel(quantidadeDisponivel);
        return this;
    }

    @Override
    public Veiculo construir() {
        return veiculo;
    }
}
