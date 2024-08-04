package com.api.pagamento.domain.builder.request.transacao.forma_pagamento;

import com.api.pagamento.domain.dto.request.transacao.forma_pagamento.FormaPagamentoRequestDto;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import lombok.Builder;

@Builder
public class FormaPagamentoRequestDtoBuilder {

    @Builder.Default
    private static TipoPagamentoEnum tipo = TipoPagamentoEnum.AVISTA;

    @Builder.Default
    private static Integer parcelas = 1;

    public static FormaPagamentoRequestDto toFormaPagamentoRequestDto() {
        return new FormaPagamentoRequestDto(tipo, parcelas);
    }

}
