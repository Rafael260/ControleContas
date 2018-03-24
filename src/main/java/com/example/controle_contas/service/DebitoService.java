package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Debito;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.repository.DebitoRepository;

@Service
public class DebitoService extends AbstractService<Debito>{

	@Autowired
	private ContaService contaService;
	
	@Autowired
	public DebitoService(DebitoRepository debitoRepository) {
		super(debitoRepository);
	}
	
	private void validarDebito(Conta conta, Double valor) throws TransacaoInvalidaException {
		if(valor <= 0.0) {
			throw new TransacaoInvalidaException("Valor a ser debitado na conta deve ser positivo!");
		}
		//Transacoes so podem ser feitas para contas ativas
		if(!conta.estaAtiva()) {
			throw new TransacaoInvalidaException("Conta a ser debitada nao está ativa!");
		}
		if(conta.getSaldo() < valor) {
			throw new TransacaoInvalidaException("Conta não possui saldo suficiente para debitar");
		}
	}
	
	public Debito debitarConta(Conta conta, Double valor) throws TransacaoInvalidaException{
		validarDebito(conta, valor);
		
		conta.decrementarValor(valor);
		contaService.update(conta);
		Debito debito = new Debito();
		debito.setContaEnvolvida(conta);
		debito.setValor(valor);
		insert(debito);
		return debito;
	}

}
