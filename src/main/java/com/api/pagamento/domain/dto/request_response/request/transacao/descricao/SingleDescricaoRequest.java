package com.api.pagamento.domain.dto.request_response.request.transacao.descricao;

import com.api.pagamento.domain.exception.handler.json.type.DateTimeHandlerDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
	@Min(value = 0, message = "deve ser maior que 0")
	private Double valor;

	@NotNull(message = "é obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonDeserialize(using = DateTimeHandlerDeserializer.class)
	private LocalDateTime dataHora;

	@NotBlank(message = "é obrigatório!")
	private String estabelecimento;

}
