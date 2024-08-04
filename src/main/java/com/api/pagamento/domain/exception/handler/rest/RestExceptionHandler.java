package com.api.pagamento.domain.exception.handler.rest;

import com.api.pagamento.domain.dto.response.error.MessageErrorResponseDto;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants.*;
import static com.api.pagamento.domain.constant.utils.pattern.PatternConstants.PATTERN_DATA_HORA_PT_BR;
import static com.api.pagamento.domain.constant.utils.divider.DividerConstants.*;
import static com.api.pagamento.domain.constant.sucess_error.error.word.WordErrorConstants.*;

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

		String message = O_CAMPO_XXX.formatted(field) + defaultMessage;

		return MessageErrorResponseDto.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), error, message);
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

			if(TipoPagamentoEnum.class.getSimpleName().equals(typeField)) {
				message = ERRO_400_O_CAMPO_XXX_DEVE_SER_UM_DOS_VALORES_YYY.formatted(field, Arrays.toString(TipoPagamentoEnum.values()));
			} else if(LocalDateTime.class.getSimpleName().equals(typeField)) {
				message = ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY_NO_FORMATO.formatted(field, typeField, PATTERN_DATA_HORA_PT_BR);
			} else {
				message = ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY.formatted(field, typeField);
			}

		}

		return MessageErrorResponseDto.obterResponseEntity(HttpStatus.BAD_REQUEST.value(), error, message);
	}

}


