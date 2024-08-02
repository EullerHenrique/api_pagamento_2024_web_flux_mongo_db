package com.api.pagamento.domain.exception.handler.json.type;

import com.api.pagamento.domain.exception.http.BadRequestException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class DateTimeHandlerDeserializer extends JsonDeserializer<LocalDateTime> {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String value = jsonParser.getText();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		try {
			formatter.parse(value);
			return LocalDateTime.parse(value, formatter);
		} catch (Exception e) {
			throw new BadRequestException("Data-Hora inválida! Formato esperado: dd/MM/yyyy HH:mm:ss");
		}

	}

}
