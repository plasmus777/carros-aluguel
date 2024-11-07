package com.github.plasmus777.carrosAluguel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
* Projeto: Criar o backend de uma locadora de veículos. Aonde vamos ter um cliente, que vamos ter que cadastrar, que vai poder alugar carros, também devidamente cadastrados, por um período de tempo e será cobrado de acordo com esse período.

Requisitos:
Java
GitHub
Banco de dados - a escolha
Spring Boot - 1 projeto único
Não precisa de testes ( opcional )

Atividades:
Criar repositório base da aplicação contendo Spring Boot
- Usar o site ou algum outro método de criar o repositório base pra ganharmos tempo

Criar cadastro de pessoa
- Nome, CPF, dataNascimento, numeroHabilitacao
-- Endereco*
-- Validar CPF usando as annotations do Validator ( @CPF ), Somente para maiores de 18 anos, numero de habilitação não ser vazio

Criar consulta de pessoa
- por CPF

Criar cadastro de veículos
- Modelo, ano, cor, categoria, estoque
Estoque, quantidade existente, quantidade disponível
categoria, numero_identificao, descrição, preço

Criar endpoint para atualizar quantidade de veículos de um determinado modelo estão disponíveis
- alterar a quantidade disponível ou existente

Criar endpoint para criar um aluguel
- cpf da pessoa - obrig
-  carro - obrig
- Data de inicio - obrig
- Data prevista de final - obrig
- Data de conclusão
- valor - não armazena, mas devolve calculado

Criar endpoint para finalizar um aluguel
- cpf da pessoa - obrig
-  carro - obrig
- Data de inicio - obrig
- Data prevista de final
- Data de conclusão - obrig
- valor - não armazena, mas devolve calculado
* */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CarrosAluguelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrosAluguelApplication.class, args);
	}

}