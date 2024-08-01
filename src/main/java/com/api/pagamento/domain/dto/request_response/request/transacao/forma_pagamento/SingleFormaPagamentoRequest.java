package com.api.pagamento.domain.dto.request_response.request.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleFormaPagamentoRequest {

	@NotNull(message = "é obrigatório!")
	private TipoEnum tipo;

	@NotBlank(message = "é obrigatório!")
	private String parcelas;
}
