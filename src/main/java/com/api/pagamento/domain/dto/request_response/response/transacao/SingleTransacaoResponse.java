package com.api.pagamento.domain.dto.request_response.response.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.response.ResponseMessage;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class SingleTransacaoResponse extends ResponseMessage {
	private TransacaoDTO data;

	@Builder
	public SingleTransacaoResponse(int status, String error, String message, TransacaoDTO data) {
		super(status, error, message);
		this.data = data;
	}
}
