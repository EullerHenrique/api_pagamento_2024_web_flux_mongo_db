package com.api.pagamento.domain.constant.utils.pattern;

import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PatternConstants {
	public static final String PATTERN_DATA_HORA_PT_BR = "dd/MM/yyyy HH:mm:ss";
	public static final DateTimeFormatter FORMATTER_DATA_HORA_PT_BR = DateTimeFormatter.ofPattern(PATTERN_DATA_HORA_PT_BR);
}
