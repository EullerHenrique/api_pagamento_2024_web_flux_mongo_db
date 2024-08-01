package com.api.pagamento.domain.dto.request_response.request.transacao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleTransacaoRequest {

	@NotBlank(message = "é obrigatório!")
	private String cartao;

	@NotBlank(message = "é obrigatório!")
	private String valor;

	@NotNull(message = "é obrigatório!")
	private String dataHora;

	@NotBlank(message = "é obrigatório!")
	private String estabelecimento;

	private String nsu;

	private String codigoAutorizacao;

	private StatusEnum status;

	@NotNull(message = "é obrigatório!")
	private TipoEnum tipo;

	@NotBlank(message = "é obrigatório!")
	private String parcelas;

}
