package com.example.controle_contas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.controle_contas.service","com.example.controle_contas.controller"})
@EnableJpaRepositories(basePackages = "com.example.controle_contas.repository")
@EntityScan(basePackages = "com.example.controle_contas.domain")
public class ControleContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleContasApplication.class, args);
	}
}
