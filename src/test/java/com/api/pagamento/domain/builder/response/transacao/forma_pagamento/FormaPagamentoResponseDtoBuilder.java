package com.api.pagamento.domain.builder.response.transacao.forma_pagamento;

import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoTransacaoResponseDto;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoTransacaoEnum;
import lombok.Builder;

@Builder
public class FormaPagamentoResponseDtoBuilder {

    @Builder.Default
    private static TipoPagamentoTransacaoEnum tipo = TipoPagamentoTransacaoEnum.AVISTA;

    @Builder.Default
    private static String parcelas = "1";

    public static FormaPagamentoTransacaoResponseDto toFormaPagamentoResponseDto() {
        return new FormaPagamentoTransacaoResponseDto(tipo, parcelas);
    }

}
