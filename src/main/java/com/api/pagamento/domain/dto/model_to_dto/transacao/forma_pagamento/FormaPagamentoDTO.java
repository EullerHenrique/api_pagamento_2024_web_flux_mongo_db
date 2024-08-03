package com.api.pagamento.domain.dto.model_to_dto.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoFormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDTO  {

    private TipoFormaPagamentoEnum tipo;
    private String parcelas;

}
