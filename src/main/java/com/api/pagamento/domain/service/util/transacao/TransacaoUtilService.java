package com.api.pagamento.domain.service.util.transacao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;

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
		return StatusTransacaoEnum.values()[RANDOM.nextInt(2)];
	}
	/**
	 * Realiza um pagamento
	 *
	 */
	public StatusTransacaoEnum obterStatusAoEstornar() {
		return StatusTransacaoEnum.CANCELADO;
	}

}
