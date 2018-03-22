package com.example.controle_contas;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.example.controle_contas.controller.ContaController;
import com.example.controle_contas.controller.PessoaController;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.domain.Pessoa;
import com.example.controle_contas.domain.PessoaFisica;
import com.example.controle_contas.domain.PessoaJuridica;

@SpringBootApplication
@ComponentScan({"com.example.controle_contas.service","com.example.controle_contas.controller"})
@EnableJpaRepositories(basePackages = "com.example.controle_contas.repository")
@EntityScan(basePackages = "com.example.controle_contas.domain")
public class ControleContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleContasApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(PessoaController pessoaController, ContaController contaController) {
		return (args) -> {
			Pessoa pessoa1 = new PessoaFisica("111.111.111-11", "Fulano de tal", LocalDateTime.now());
			Pessoa pessoa2 = new PessoaJuridica("11.111.111/0001-11","Razao social", "Nome fantasia");
			pessoaController.insert(pessoa1);
			pessoaController.insert(pessoa2);
			Conta conta1 = new ContaMatriz("nome teste", pessoa1);
			ContaFilial conta2 = new ContaFilial("nome filial", pessoa2);
			conta2.setContaPai(conta1);
			contaController.insert(conta1);
			contaController.insert(conta2);
		};
	}
}
