package com.api.pagamento.domain.dto.model_to_dto.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDTO  {

    @JsonIgnore
    private Long id;
    private TipoEnum tipo;
    private String parcelas;

}
