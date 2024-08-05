package com.api.pagamento.domain.builder.response.transacao;

import com.api.pagamento.domain.builder.response.transacao.descricao.DescricaoResponseDtoBuilder;
import com.api.pagamento.domain.builder.response.transacao.forma_pagamento.FormaPagamentoResponseDtoBuilder;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoTransacaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoTransacaoResponseDto;
import lombok.Builder;

@Builder
public class TransacaoResponseDtoBuilder {

    @Builder.Default()
    private static String id = "1";

    @Builder.Default()
    private static String cartao = "4444********1234";

    @Builder.Default()
    private static DescricaoTransacaoResponseDto descricao = DescricaoResponseDtoBuilder.toDescricaoResponseDto();

    @Builder.Default()
    private static FormaPagamentoTransacaoResponseDto formaPagamento = FormaPagamentoResponseDtoBuilder.toFormaPagamentoResponseDto();

    public static TransacaoResponseDto toTransacaoResponseDto() {
        return new TransacaoResponseDto(id, cartao, descricao, formaPagamento);
    }

}
