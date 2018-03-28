package com.example.controle_contas.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.domain.Transferencia;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
import com.example.controle_contas.repository.TransferenciaRepository;

@Service
public class TransferenciaService extends AbstractService<Transferencia>{

	@Autowired
	private ContaService contaService;
	
	@Autowired
	public TransferenciaService(TransferenciaRepository transferenciaRepository) {
		super(transferenciaRepository);
	}
	
	private boolean estaoNaMesmaArvore(Conta contaOrigem, ContaFilial contaDestino) {
		if(contaOrigem instanceof ContaMatriz) {
			return contaOrigem.equals(contaDestino.coletarContaMatriz());
		}
		else {
			return ((ContaFilial) contaOrigem).coletarContaMatriz().equals(contaDestino.coletarContaMatriz());
		}
	}
	
	@Override
	public void onBeforeInsert(Transferencia entityToPersist) {
		entityToPersist.setData(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
	}
	
	private void validarTransferencia(Conta contaOrigem, ContaFilial contaDestino, Double valor) throws TransacaoInvalidaException {
		if(valor <= 0) {
			throw new TransacaoInvalidaException("Valor para transferencia deve ser positivo");
		}
		if(contaOrigem.getId().equals(contaDestino.getId())) {
			throw new TransacaoInvalidaException("A transferencia deve ser entre contas distintas");
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
	
	public Transferencia transferir(Conta contaOrigem, ContaFilial contaDestino, Double valor) throws TransacaoInvalidaException{
		validarTransferencia(contaOrigem, contaDestino, valor);

		contaOrigem.decrementarValor(valor);
		contaDestino.acrescentarValor(valor);
		contaService.update(contaOrigem);
		contaService.update(contaDestino);
		Transferencia transferencia = new Transferencia();
		transferencia.setContaEnvolvida(contaDestino);
		transferencia.setContaOrigem(contaOrigem);
		transferencia.setValor(valor);
		insert(transferencia);
		return transferencia;
	}

	public Transferencia estornarTransferencia(Transferencia transferencia) throws TransacaoJaEstornadaException{
		if(transferencia.isEstornada()) {
			throw new TransacaoJaEstornadaException("A transferencia já foi estornada");
		}
		
		Conta contaOrigem = transferencia.getContaOrigem();
		Conta contaDestino = transferencia.getContaEnvolvida();
		transferencia.setEstornada(true);
		contaOrigem.acrescentarValor(transferencia.getValor());
		contaDestino.decrementarValor(transferencia.getValor());
		contaService.update(contaOrigem);
		contaService.update(contaDestino);
		update(transferencia);
		return transferencia;
	}

}
