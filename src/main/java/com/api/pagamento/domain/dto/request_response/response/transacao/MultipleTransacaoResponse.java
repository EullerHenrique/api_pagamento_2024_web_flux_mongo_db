package com.api.pagamento.domain.dto.request_response.response.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.response.ResponseMessage;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class MultipleTransacaoResponse extends ResponseMessage {
	private List<TransacaoDTO> data;

	@Builder
	public MultipleTransacaoResponse(int status, String error, String message, List<TransacaoDTO> data) {
		super(status, error, message);
		this.data = data;
	}
}

