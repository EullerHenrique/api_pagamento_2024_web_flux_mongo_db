package com.api.pagamento.domain.dto.request_response.request.transacao.descricao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleDescricaoRequest {

	@NotBlank(message = "é obrigatório!")
	private String valor;

	@NotNull(message = "é obrigatório!")
	private String dataHora;

	@NotBlank(message = "é obrigatório!")
	private String estabelecimento;

}
