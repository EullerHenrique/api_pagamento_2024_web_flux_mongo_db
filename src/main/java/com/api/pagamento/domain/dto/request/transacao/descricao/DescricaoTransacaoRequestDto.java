package com.api.pagamento.domain.dto.request.transacao.descricao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.api.pagamento.domain.constant.http.message.error.word.WordErrorConstants.DEVE_SER_MAIOR_QUE;
import static com.api.pagamento.domain.constant.http.message.error.word.WordErrorConstants.EH_OBRIGATORIO;
import static com.api.pagamento.domain.constant.pattern.PatternConstants.PATTERN_DATA_HORA_PT_BR;

/**
 * Dto responsável por armazenar os dados de requisição da descrição de uma transação
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescricaoTransacaoRequestDto {

	@NotNull(message = EH_OBRIGATORIO)
	@Min(value = 1, message = DEVE_SER_MAIOR_QUE + 0)
	private BigDecimal valor;

	@NotNull(message = EH_OBRIGATORIO)
	@JsonFormat(pattern = PATTERN_DATA_HORA_PT_BR)
	private LocalDateTime dataHora;

	@NotBlank(message = EH_OBRIGATORIO)
	private String estabelecimento;

}
