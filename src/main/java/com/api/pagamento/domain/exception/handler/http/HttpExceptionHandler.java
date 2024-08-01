package com.api.pagamento.domain.exception.handler.http;

import com.api.pagamento.domain.dto.request_response.response.error.ResponseMessageError;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerErrorException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@ControllerAdvice
public class HttpExceptionHandler {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseMessageError> handleNotFoundException(NotFoundException ex) {
		return obterResponseMessageError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseMessageError> handleBadRequestException(BadRequestException ex) {
		return obterResponseMessageError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ResponseMessageError> handleBadRequestException(InternalServerErrorException ex) {
		return obterResponseMessageError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	private ResponseEntity<ResponseMessageError> obterResponseMessageError(int status, String error, String message) {

		ResponseMessageError errorMessageResponse = new ResponseMessageError();
		errorMessageResponse.setStatus(status);
		errorMessageResponse.setError(error);
		errorMessageResponse.setMessage(message);

		return ResponseEntity.status(status).body(errorMessageResponse);
	}

}
