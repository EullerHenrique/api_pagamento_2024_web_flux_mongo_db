package com.api.pagamento.config.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import static com.api.pagamento.domain.constant.util.pattern.PatternConstants.FORMATTER_DATA_HORA_PT_BR;

public class LocalDateTimePtbrAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
	@Override
	public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
		return new JsonPrimitive(localDateTime.format(FORMATTER_DATA_HORA_PT_BR));
	}

	@Override
	public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
			throws JsonParseException {
		return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), FORMATTER_DATA_HORA_PT_BR);
	}

}