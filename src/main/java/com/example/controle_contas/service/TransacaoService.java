package com.example.controle_contas.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Transacao;
import com.example.controle_contas.domain.Transferencia;
import com.example.controle_contas.repository.TransacaoRepository;
import com.example.controle_contas.repository.TransferenciaRepository;

@Service
public class TransacaoService extends AbstractService<Transacao> {

	TransacaoRepository transacaoRepository;
	TransferenciaRepository transferenciaRepository;
	
	@Autowired
	public TransacaoService(TransacaoRepository transacaoRepository, TransferenciaRepository transferenciaRepository) {
		super(transacaoRepository);
		this.transacaoRepository = transacaoRepository;
		this.transferenciaRepository = transferenciaRepository;
	}
	
	@Override
	public void onBeforeInsert(Transacao entityToPersist) {
		entityToPersist.setData(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
	}
	
	public List<Transacao> coletarHistoricoDaConta(Conta conta){
		//Recupera as transacoes em que a conta eh a beneficiada
		List<Transacao> transacoesComoEnvolvido = transacaoRepository.findByContaEnvolvida(conta);
		//Recupera as transferencias/aportes em que a conta em questao envia o valor
		List<Transferencia> transacoesComoOrigem = transferenciaRepository.findByContaOrigem(conta);
		//Adiciona todas as transacoes em uma lista
		transacoesComoEnvolvido.addAll(transacoesComoOrigem);
		Collections.sort(transacoesComoEnvolvido);
		return transacoesComoEnvolvido;
	}
}
