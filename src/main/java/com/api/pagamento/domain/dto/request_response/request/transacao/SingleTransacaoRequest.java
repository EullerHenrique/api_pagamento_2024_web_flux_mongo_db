package com.api.pagamento.domain.dto.request_response.request.transacao;

import com.api.pagamento.domain.dto.request_response.request.transacao.descricao.SingleDescricaoRequest;
import com.api.pagamento.domain.dto.request_response.request.transacao.forma_pagamento.SingleFormaPagamentoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleTransacaoRequest {

	@NotBlank(message = "é obrigatório!")
	@Size(min = 16, max = 16, message = "deve ter 16 caracteres!")
	private String cartao;

	@Valid
	private SingleDescricaoRequest descricao;

	@Valid
	private SingleFormaPagamentoRequest formaPagamento;

}
