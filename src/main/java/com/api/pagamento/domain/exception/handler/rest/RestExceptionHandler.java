package com.api.pagamento.domain.exception.handler.rest;

import com.api.pagamento.domain.dto.request_response.response.error.ResponseMessageError;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.FormaPagamentoEnum;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String error = ex.toString().split(":")[0];
		String field = ex.getBindingResult().getFieldErrors().get(0).getField();
		String defaultMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

		String message = "O campo " + field + " " + defaultMessage;

		return ResponseMessageError.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), error, message);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(HttpMessageNotReadableException ex) {
		String error = ex.toString().split(":")[0];
		String message = ex.getCause().getMessage();

		if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
			error = invalidFormatException.toString().split(":")[0];

			List<JsonMappingException.Reference> path = invalidFormatException.getPath();
			String field = path.stream().map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining("."));
			String typeField = invalidFormatException.getTargetType().getSimpleName();

			message = switch (typeField) {
				case "FormaPagamentoEnum" ->
						"O campo " + field + " deve ser um dos valores: " + Arrays.toString(FormaPagamentoEnum.values());
				case "LocalDateTime" -> "O campo " + field + " deve ser do tipo " + typeField + " no formato dd/MM/yyyy HH:mm:ss";
				default -> "O campo " + field + " deve ser do tipo " + typeField;
			};

		}

		return ResponseMessageError.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), error, message);
	}

}


