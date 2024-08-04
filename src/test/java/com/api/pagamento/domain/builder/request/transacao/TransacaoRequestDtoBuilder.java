package com.api.pagamento.domain.builder.request.transacao;

import com.api.pagamento.domain.builder.request.transacao.descricao.DescricaoRequestDtoBuilder;
import com.api.pagamento.domain.builder.request.transacao.forma_pagamento.FormaPagamentoRequestDtoBuilder;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.request.transacao.descricao.DescricaoRequestDto;
import com.api.pagamento.domain.dto.request.transacao.forma_pagamento.FormaPagamentoRequestDto;
import lombok.Builder;

@Builder
public class TransacaoRequestDtoBuilder {

    @Builder.Default()
    private static String cartao = "4444********1234";

    @Builder.Default()
    private static DescricaoRequestDto descricao = DescricaoRequestDtoBuilder.toDescricaoRequestDto();

    @Builder.Default()
    private static FormaPagamentoRequestDto formaPagamento = FormaPagamentoRequestDtoBuilder.toFormaPagamentoRequestDto();

    public static TransacaoRequestDto toTransacaoRequestDto() {
        return new TransacaoRequestDto(cartao, descricao, formaPagamento);
    }

}
