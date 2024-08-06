package com.api.pagamento.domain.builder.request.transacao.forma_pagamento;

import com.api.pagamento.domain.dto.request.transacao.forma_pagamento.FormaPagamentoTransacaoRequestDto;
import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoRequestDtoBuilder {

    @Builder.Default
    private TipoPagamentoTransacaoEnum tipo = TipoPagamentoTransacaoEnum.AVISTA;

    @Builder.Default
    private Integer parcelas = 1;

    public FormaPagamentoTransacaoRequestDto toFormaPagamentoRequestDto() {
        return new FormaPagamentoTransacaoRequestDto(tipo, parcelas);
    }

}
