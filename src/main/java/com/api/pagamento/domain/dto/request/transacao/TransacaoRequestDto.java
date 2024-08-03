package com.api.pagamento.domain.dto.request.transacao;

import com.api.pagamento.domain.dto.request.transacao.descricao.DescricaoRequestDto;
import com.api.pagamento.domain.dto.request.transacao.forma_pagamento.FormaPagamentoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.api.pagamento.domain.constant.sucess_error.error.word.WordErrorConstants.*;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequestDto {

	@NotBlank(message = EH_OBRIGATORIO)
	@Size(min = 16, max = 16, message = DEVE_TER + 16 + CARACTERES)
	private String cartao;

	@Valid
	private DescricaoRequestDto descricao;

	@Valid
	private FormaPagamentoRequestDto formaPagamento;

}
