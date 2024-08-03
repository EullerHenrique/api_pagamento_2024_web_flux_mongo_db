package com.api.pagamento.domain.dto.request_response.request.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleFormaPagamentoRequest {

	@NotNull(message = "é obrigatório!")
	private FormaPagamentoEnum tipo;

	@NotNull(message = "é obrigatório!")
	@Min(value = 1, message = "deve ser maior que 0")
	private Integer parcelas;
}
