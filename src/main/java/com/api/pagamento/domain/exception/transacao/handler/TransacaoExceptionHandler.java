package com.api.pagamento.domain.exception.transacao.handler;

import com.api.pagamento.domain.dto.request_response.response.error.ErrorMessageResponse;
import com.api.pagamento.domain.exception.transacao.NotFoundException;
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
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessageResponse> transcaoInexistenteException(NotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getResponseError());

	}

}
