package com.api.pagamento.domain.converter.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransacaoConverter {

	private final ModelMapper modelMapper;

	public TransacaoDTO requestToDTO(SingleTransacaoRequest request) {
		TransacaoDTO transacaoDTO = modelMapper.map(request, TransacaoDTO.class);
		transacaoDTO.getDescricao().setNsu("1234567890");
		transacaoDTO.getDescricao().setCodigoAutorizacao("147258369");
		transacaoDTO.getDescricao().setStatus(StatusEnum.AUTORIZADO);
		return transacaoDTO;
	}
	public Transacao dtoToModel(TransacaoDTO transacaoDTO) {
		return modelMapper.map(transacaoDTO, Transacao.class);
	}

	public TransacaoDTO modelToDTO(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoDTO.class);
	}

}
