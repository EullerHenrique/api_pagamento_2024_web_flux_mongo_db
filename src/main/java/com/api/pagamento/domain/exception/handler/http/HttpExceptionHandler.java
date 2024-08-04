package com.api.pagamento.domain.exception.handler.http;

import com.api.pagamento.domain.dto.response.error.MessageErrorResponseDto;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.api.pagamento.domain.constant.utils.divider.DividerConstants.DOIS_PONTOS;

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
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		return MessageErrorResponseDto.obterResponseEntity(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage());
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
		return MessageErrorResponseDto.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				ex.getMessage());
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Object> handleInternalServerException(InternalServerException ex) {
		String[] localizedMessageSplit = ex.getLocalizedMessage().split(DOIS_PONTOS);
		return MessageErrorResponseDto.obterResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), localizedMessageSplit[0],
				localizedMessageSplit[1]);
	}

}
