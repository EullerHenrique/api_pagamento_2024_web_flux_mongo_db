package com.api.pagamento.domain.exception.handler.json.transacao;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class TipoHandlerDeserializer extends JsonDeserializer<TipoPagamentoEnum> {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@Override
	public TipoPagamentoEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String value = jsonParser.getText();
		for (TipoPagamentoEnum tipo : TipoPagamentoEnum.values()) {
			if (tipo.name().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		throw new BadRequestException("Tipo de pagamento inválido!");
	}

}