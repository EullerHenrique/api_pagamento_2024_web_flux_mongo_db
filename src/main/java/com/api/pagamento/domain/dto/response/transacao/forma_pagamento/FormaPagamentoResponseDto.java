package com.api.pagamento.domain.dto.response.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para receber os dados de resposta da forma de pagamento de uma transação
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoResponseDto {

    private TipoPagamentoEnum tipo;
    private String parcelas;

}
