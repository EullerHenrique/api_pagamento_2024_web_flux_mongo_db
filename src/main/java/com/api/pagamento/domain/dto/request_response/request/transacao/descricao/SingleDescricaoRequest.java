package com.api.pagamento.domain.dto.request_response.request.transacao.descricao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

import java.time.LocalDateTime;

import static com.api.pagamento.domain.constant.utils.pattern.PatternConstants.PATTERN_DATA_HORA_PT_BR;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleDescricaoRequest {

	@NotNull(message = "é obrigatório!")
	@Min(value = 1, message = "deve ser maior que 0")
	private Double valor;

	@NotNull(message = "é obrigatório!")
	@JsonFormat(pattern = PATTERN_DATA_HORA_PT_BR)
	private LocalDateTime dataHora;

	@NotBlank(message = "é obrigatório!")
	private String estabelecimento;

}
