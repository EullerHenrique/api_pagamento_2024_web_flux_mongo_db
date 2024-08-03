package com.api.pagamento.domain.converter.transacao;

import com.api.pagamento.domain.dto.response.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request.transacao.SingleTransacaoRequest;
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
	public TransacaoDTO requestToDTO(SingleTransacaoRequest request) {
		return modelMapper.map(request, TransacaoDTO.class);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public Transacao dtoToModel(TransacaoDTO transacaoDTO) {
		return modelMapper.map(transacaoDTO, Transacao.class);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public TransacaoDTO modelToDTO(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoDTO.class);
	}
	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	public List<TransacaoDTO> modelsToDTOs(List<Transacao> transacoes) {
		return transacoes.stream().map(this::modelToDTO).toList();
	}

}
