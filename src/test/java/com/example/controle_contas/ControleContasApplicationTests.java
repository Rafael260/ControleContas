package com.example.controle_contas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.controle_contas.controller.ContaController;

@RunWith(SpringRunner.class)
@WebMvcTest(ContaController.class)
public class ControleContasApplicationTests {

	@Test
	public void contextLoads() throws Exception {
	}

}
