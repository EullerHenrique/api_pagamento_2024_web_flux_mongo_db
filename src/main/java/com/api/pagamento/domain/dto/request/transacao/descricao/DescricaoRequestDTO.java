package com.api.pagamento.domain.dto.request.transacao.descricao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

import java.time.LocalDateTime;

import static com.api.pagamento.domain.constant.utils.pattern.PatternConstants.PATTERN_DATA_HORA_PT_BR;
import static com.api.pagamento.domain.constant.sucess_error.error.word.WordErrorConstants.*;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescricaoRequestDTO {

	@NotNull(message = EH_OBRIGATORIO)
	@Min(value = 1, message = DEVE_SER_MAIOR_QUE + 0)
	private Double valor;

	@NotNull(message = EH_OBRIGATORIO)
	@JsonFormat(pattern = PATTERN_DATA_HORA_PT_BR)
	private LocalDateTime dataHora;

	@NotBlank(message = EH_OBRIGATORIO)
	private String estabelecimento;

}
