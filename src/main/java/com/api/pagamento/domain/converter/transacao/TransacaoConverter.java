package com.api.pagamento.domain.converter.transacao;

import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseTO;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDTO;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Component
@RequiredArgsConstructor
public class TransacaoConverter {

	private final ModelMapper modelMapper;

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public TransacaoResponseTO requestToResponse(TransacaoRequestDTO request) {
		return modelMapper.map(request, TransacaoResponseTO.class);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public Transacao responseToModel(TransacaoResponseTO transacaoDTO) {
		return modelMapper.map(transacaoDTO, Transacao.class);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public TransacaoResponseTO modelToResponse(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoResponseTO.class);
	}
	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public List<TransacaoResponseTO> modelsToResponses(List<Transacao> transacoes) {
		return transacoes.stream().map(this::modelToResponse).toList();
	}

}
