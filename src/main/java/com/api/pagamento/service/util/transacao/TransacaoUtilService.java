package com.api.pagamento.service.util.transacao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
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
	public StatusEnum obterStatusAoPagar() {
		return StatusEnum.AUTORIZADO;
	}
	/**
	 * Realiza um pagamento
	 *
	 */
	public StatusEnum obterStatusAoEstornar() {
		return StatusEnum.CANCELADO;
	}

}
