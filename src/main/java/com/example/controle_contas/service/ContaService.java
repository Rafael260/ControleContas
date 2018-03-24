package com.example.controle_contas.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.controle_contas.domain.Carga;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.domain.Transacao;
import com.example.controle_contas.domain.Transferencia;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.repository.ContaRepository;

@Service
public class ContaService extends AbstractService<Conta>{

	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	public ContaService(ContaRepository<Conta> repositorio) {
		super(repositorio);
	}
	
	@Override
	public void onBeforeInsert(Conta entityToPersist) {
		entityToPersist.setDataCriacao(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
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
	
	public void carregarConta(Conta conta, Double valor) throws TransacaoInvalidaException{
		validarCarga(conta, valor);
		
		conta.acrescentarValor(valor);
		update(conta);
		Transacao carga = new Carga();
		carga.setContaEnvolvida(conta);
		carga.setValor(valor);
		transacaoService.insert(carga);
	}
	
	private boolean estaoNaMesmaArvore(Conta contaOrigem, ContaFilial contaDestino) {
		if(contaOrigem instanceof ContaMatriz) {
			return contaOrigem.equals(contaDestino.coletarContaMatriz());
		}
		else {
			return ((ContaFilial) contaOrigem).coletarContaMatriz().equals(contaDestino.coletarContaMatriz());
		}
	}
	
	private void validarTransferencia(Conta contaOrigem, ContaFilial contaDestino, Double valor) throws TransacaoInvalidaException {
		if(valor <= 0) {
			throw new TransacaoInvalidaException("Valor para transferencia deve ser positivo");
		}
		if(contaOrigem.getSaldo() < valor) {
			throw new TransacaoInvalidaException("Conta origem não tem saldo para transferir");
		}
		if(!contaOrigem.estaAtiva() || !contaDestino.estaAtiva()) {
			throw new TransacaoInvalidaException("As duas contas devem estar ativas para a transferencia");
		}
		if(!estaoNaMesmaArvore(contaOrigem, contaDestino)) {
			System.out.println("As contas não estão na mesma árvore!");
			throw new TransacaoInvalidaException("As contas não estão na mesma árvore");
		}
	}
	
	public void transferir(Conta contaOrigem, ContaFilial contaDestino, Double valor) throws TransacaoInvalidaException{
		validarTransferencia(contaOrigem, contaDestino, valor);

		contaOrigem.decrementarValor(valor);
		contaDestino.acrescentarValor(valor);
		update(contaOrigem);
		update(contaDestino);
		Transferencia transferencia = new Transferencia();
		transferencia.setContaEnvolvida(contaDestino);
		transferencia.setContaOrigem(contaOrigem);
		transferencia.setValor(valor);
		transacaoService.insert(transferencia);
	}
	
}
