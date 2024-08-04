package com.api.pagamento.domain.builder.response.transacao.forma_pagamento;

import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoResponseDto;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import lombok.Builder;

@Builder
public class FormaPagamentoResponseDtoBuilder {

    @Builder.Default
    private static TipoPagamentoEnum tipo = TipoPagamentoEnum.AVISTA;

    @Builder.Default
    private static String parcelas = "1";

    public static FormaPagamentoResponseDto toFormaPagamentoResponseDto() {
        return new FormaPagamentoResponseDto(tipo, parcelas);
    }

}
