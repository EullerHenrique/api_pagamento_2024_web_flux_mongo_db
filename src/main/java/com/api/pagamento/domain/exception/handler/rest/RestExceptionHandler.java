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

import static com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants.ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY;
import static com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants.ERRO_400_O_CAMPO_XXX_DEVE_SER_UM_DOS_VALORES_YYY;
import static com.api.pagamento.domain.constant.utils.pattern.PatternConstants.PATTERN_DATA_HORA_PT_BR;
import static com.api.pagamento.domain.constant.utils.txt.TxtConstants.*;
import static com.api.pagamento.domain.constant.utils.word.WordConstants.*;

@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String error = ex.toString().split(DOIS_PONTOS)[0];
		String field = ex.getBindingResult().getFieldErrors().get(0).getField();
		String defaultMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

		String message = O_CAMPO + field + ESPACO + defaultMessage;

		return ResponseMessageError.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), error, message);
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(HttpMessageNotReadableException ex) {
		String error = ex.toString().split(DOIS_PONTOS)[0];
		String message = ex.getCause().getMessage();

		if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
			error = invalidFormatException.toString().split(DOIS_PONTOS)[0];

			List<JsonMappingException.Reference> path = invalidFormatException.getPath();
			String field = path.stream().map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining(PONTO));
			String typeField = invalidFormatException.getTargetType().getSimpleName();

			message = switch (typeField) {
				case "FormaPagamentoEnum" ->
						ERRO_400_O_CAMPO_XXX_DEVE_SER_UM_DOS_VALORES_YYY.formatted(field, Arrays.toString(FormaPagamentoEnum.values()));
				case "LocalDateTime" ->
						ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY.formatted(field, typeField) + NO_FORMATO.formatted(PATTERN_DATA_HORA_PT_BR);
				default -> ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY.formatted(field, typeField);
			};

		}

		return ResponseMessageError.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), error, message);
	}

}


