package com.example.controle_contas.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.controle_contas.service.ContaService;

@Component
public class ScheduleTest {

	@Autowired
	private ContaService contaService;
	
	 @Scheduled(fixedRate = 10000)
	 public void reportCurrentTime() {
	    contaService.findAll().forEach(System.out::println);
		 System.out.println();
	 }
}
