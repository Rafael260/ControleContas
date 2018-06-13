package com.example.controle_contas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.controle_contas.domain.Aporte;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.exceptions.ResourceNotFoundException;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
import com.example.controle_contas.repository.AporteRepository;

@Service
public class AporteService extends AbstractService<Aporte>{

	@Autowired
	private ContaService contaService;
	
	private AporteRepository aporteRepository;
	
	@Autowired
	public AporteService(AporteRepository aporteRepository) {
		super(aporteRepository);
		this.aporteRepository = aporteRepository;
	}
	
	@Override
	public void onBeforeInsert(Aporte entityToPersist) {
		entityToPersist.setData(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
	}
	
	public Aporte findByCodigo(String codigo) {
		return aporteRepository.findByCodigo(codigo).
				orElseThrow(()-> new ResourceNotFoundException());
	}
	
	private void validarAporte(Conta contaOrigem, ContaMatriz contaDestino, Double valor) throws TransacaoInvalidaException{
		if(valor <= 0.0) {
			throw new TransacaoInvalidaException("O valor deve ser positivo");
		}
		if(contaOrigem.getId().equals(contaDestino.getId())) {
			throw new TransacaoInvalidaException("A transferencia deve ser entre contas distintas");
		}
		if(contaOrigem.getSaldo() < valor) {
			throw new TransacaoInvalidaException("A conta origem não possui saldo para essa transação");
		}
		if(!contaOrigem.estaAtiva() || !contaDestino.estaAtiva()) {
			throw new TransacaoInvalidaException("As duas contas devem estar ativas para enviar o aporte");
		}
	}
	
	//idContaOrigem + "TO" + idContaDestino + _ + (numeroDeAportesAtual + 1)
	//Ex: 5TO6_1
	private String gerarCodigoAporte(Conta contaOrigem, ContaMatriz contaDestino) {
		List<Aporte> aportes = aporteRepository.findByContaEnvolvidaAndContaOrigem(contaDestino, contaOrigem);
		int numeroAportes = aportes == null || aportes.isEmpty() ? 0 : aportes.size();
		String codigo = contaOrigem.getId() + "TO" + contaDestino.getId() + "_" + (numeroAportes+1);
		return codigo;
	}

	public Aporte enviarAporte(Conta contaOrigem, ContaMatriz contaDestino, Double valor) throws TransacaoInvalidaException{
		validarAporte(contaOrigem, contaDestino, valor);
		
		Aporte aporte = new Aporte(contaOrigem,contaDestino,valor, gerarCodigoAporte(contaOrigem, contaDestino));
		insert(aporte);
		contaOrigem.decrementarValor(valor);
		contaDestino.acrescentarValor(valor);
		contaService.update(contaOrigem);
		contaService.update(contaDestino);
		return aporte;
	}

	public Aporte estornarAporte(Aporte aporte) throws TransacaoJaEstornadaException{
		if(aporte.isEstornada()) {
			throw new TransacaoJaEstornadaException("O aporte já está estornado");
		}
		Conta contaOrigem = aporte.getContaOrigem();
		Conta contaDestino = aporte.getContaEnvolvida();
		contaOrigem.acrescentarValor(aporte.getValor());
		contaDestino.decrementarValor(aporte.getValor());
		contaService.update(contaOrigem);
		contaService.update(contaDestino);
		aporte.setEstornada(true);
		return aporte;
	}
}
