package com.api.pagamento.service.util.transacao;

import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.FormaPagamentoEnum;
import com.api.pagamento.domain.exception.http.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants.ERROR_400_PAGAMENTO_AVISTA_MAIS_DE_UMA_PARCELA;

/**
 * Realiza um pagamento
 *
 */
@Service
@RequiredArgsConstructor
public class TransacaoUtilService {

	private static final Random RANDOM = new Random();

	/**
	 * Realiza um pagamento
	 *
	 */
	public String obterNsu() {
		return String.valueOf(RANDOM.nextInt(1000000000));
	}
	/**
	 * Realiza um pagamento
	 *
	 */
	public String obterCodigoAutorizacao() {
		return String.valueOf(RANDOM.nextInt(1000000000));
	}

	/**
	 * Realiza um pagamento
	 *
	 */
	public StatusTransacaoEnum obterStatusAoPagar() {
		return StatusTransacaoEnum.AUTORIZADO;
	}
	/**
	 * Realiza um pagamento
	 *
	 */
	public StatusTransacaoEnum obterStatusAoEstornar() {
		return StatusTransacaoEnum.CANCELADO;
	}

	/**
	 * Realiza um pagamento
	 *
	 */
	public void validarCoerenciaFormaPagamentoParcelas(SingleTransacaoRequest request) {
		FormaPagamentoEnum tipoPagamento = request.getFormaPagamento().getTipo();
		int parcelas = request.getFormaPagamento().getParcelas();

		if (FormaPagamentoEnum.AVISTA.equals(tipoPagamento) && parcelas > 1) {
			throw new BadRequestException(ERROR_400_PAGAMENTO_AVISTA_MAIS_DE_UMA_PARCELA);
		}
	}
}
