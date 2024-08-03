package com.api.pagamento.domain.converter.transacao;

import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
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
	public TransacaoResponseDto requestToResponse(TransacaoRequestDto request) {
		return modelMapper.map(request, TransacaoResponseDto.class);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public Transacao responseToModel(TransacaoResponseDto transacaoDTO) {
		return modelMapper.map(transacaoDTO, Transacao.class);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public TransacaoResponseDto modelToResponse(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoResponseDto.class);
	}
	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public List<TransacaoResponseDto> modelsToResponses(List<Transacao> transacoes) {
		return transacoes.stream().map(this::modelToResponse).toList();
	}

}
