package com.api.pagamento.domain.exception.transacao.handler;

import com.api.pagamento.domain.dto.request_response.response.ResponseMessage;
import com.api.pagamento.domain.exception.transacao.TransacaoInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransacaoExceptionHandler {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(TransacaoInexistenteException.class)
	public ResponseEntity<ResponseMessage> transcaoInexistenteException(TransacaoInexistenteException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getResponseError());

	}

}
