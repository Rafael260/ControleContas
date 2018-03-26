package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Carga;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
import com.example.controle_contas.repository.CargaRepository;

@Service
public class CargaService extends AbstractService<Carga>{

	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	public CargaService(CargaRepository cargaRepository) {
		super(cargaRepository);
	}

	private void validarCarga(Conta conta, Double valor) throws TransacaoInvalidaException {
		if(valor <= 0.0) {
			throw new TransacaoInvalidaException("Valor a ser carregado na conta deve ser positivo!");
		}
		//Transacoes so podem ser feitas para contas ativas
		if(!conta.estaAtiva()) {
			throw new TransacaoInvalidaException("Conta a ser carregada nao está ativa!");
		}
	}
	
	public Carga carregarConta(Conta conta, Double valor) throws TransacaoInvalidaException{
		validarCarga(conta, valor);
		
		conta.acrescentarValor(valor);
		contaService.update(conta);
		Carga carga = new Carga();
		carga.setContaEnvolvida(conta);
		carga.setValor(valor);
		transacaoService.insert(carga);
		return carga;
	}
	
	public Carga estornarCarga(Carga carga) throws TransacaoJaEstornadaException{
		if(carga.isEstornada()) {
			throw new TransacaoJaEstornadaException("A carga já foi estornada");
		}
		carga.setEstornada(true);
		Conta conta = carga.getContaEnvolvida();
		conta.decrementarValor(carga.getValor());
		contaService.update(conta);
		update(carga);
		return carga;
	}
}
