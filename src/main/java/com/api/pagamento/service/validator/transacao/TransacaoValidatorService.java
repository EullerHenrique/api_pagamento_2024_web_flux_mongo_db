package com.api.pagamento.service.validator.transacao;

import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants.*;

/**
 * Realiza um pagamento
 *
 */
@Service
@RequiredArgsConstructor
public class TransacaoValidatorService {

	/**
	 * Realiza um pagamento
	 *
	 */
	public void validarTipoPagamentoAoPagar(TransacaoRequestDto request) {
		TipoPagamentoEnum tipoPagamento = request.getFormaPagamento().getTipo();
		int parcelas = request.getFormaPagamento().getParcelas();

		if (TipoPagamentoEnum.AVISTA.equals(tipoPagamento) && parcelas > 1) {
			throw new BadRequestException(ERROR_400_PAGAMENTO_AVISTA_MAIS_DE_UMA_PARCELA);
		}
	}

	/**
	 * Realiza um pagamento
	 *
	 */
	public void validarStatusTransacaoAoEstornar(Transacao transacao) {
		StatusTransacaoEnum status = transacao.getDescricao().getStatus();

		if (StatusTransacaoEnum.CANCELADO.equals(status)) {
			throw new BadRequestException(ERROR_400_TRANSACAO_JA_FOI_ESTORNADA);
		}else if (StatusTransacaoEnum.NEGADO.equals(status)) {
			throw new BadRequestException(ERROR_400_TRANSACAO_NEGADA_NAO_PODE_SER_ESTORNADA);
		}
	}
}
