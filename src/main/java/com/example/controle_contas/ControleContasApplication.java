package com.example.controle_contas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.controle_contas.repository")
@EntityScan(basePackages = "com.example.controle_contas.domain")
public class ControleContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleContasApplication.class, args);
	}
}
