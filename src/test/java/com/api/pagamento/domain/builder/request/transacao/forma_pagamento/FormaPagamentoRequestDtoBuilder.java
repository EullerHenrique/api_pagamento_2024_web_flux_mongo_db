package com.api.pagamento.domain.builder.request.transacao.forma_pagamento;

import com.api.pagamento.domain.dto.request.transacao.forma_pagamento.FormaPagamentoTransacaoRequestDto;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoTransacaoEnum;
import lombok.Builder;

@Builder
public class FormaPagamentoRequestDtoBuilder {

    @Builder.Default
    private static TipoPagamentoTransacaoEnum tipo = TipoPagamentoTransacaoEnum.AVISTA;

    @Builder.Default
    private static Integer parcelas = 1;

    public static FormaPagamentoTransacaoRequestDto toFormaPagamentoRequestDto() {
        return new FormaPagamentoTransacaoRequestDto(tipo, parcelas);
    }

}
