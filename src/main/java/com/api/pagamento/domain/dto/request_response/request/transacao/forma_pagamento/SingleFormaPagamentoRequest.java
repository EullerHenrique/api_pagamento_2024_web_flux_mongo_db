package com.api.pagamento.domain.dto.request_response.request.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoEnum;
import com.api.pagamento.domain.exception.handler.json.transacao.TipoHandlerDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
	@JsonDeserialize(using = TipoHandlerDeserializer.class)
	private TipoEnum tipo;

	@NotNull(message = "é obrigatório!")
	@Min(value = 0, message = "deve ser maior que 0")
	private Integer parcelas;
}
