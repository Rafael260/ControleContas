package com.example.controle_contas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.controle_contas.client_rest.ClientRest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControleContasApplicationTests {

	ClientRest client = new ClientRest();
	
	@Test
	public void contextLoads() {
		client.imprimirContas();
	}

}
