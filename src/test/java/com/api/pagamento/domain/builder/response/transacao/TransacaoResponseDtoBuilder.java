package com.api.pagamento.domain.builder.response.transacao;

import com.api.pagamento.domain.builder.response.transacao.descricao.DescricaoResponseDtoBuilder;
import com.api.pagamento.domain.builder.response.transacao.forma_pagamento.FormaPagamentoResponseDtoBuilder;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.request.transacao.descricao.DescricaoRequestDto;
import com.api.pagamento.domain.dto.request.transacao.forma_pagamento.FormaPagamentoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoResponseDto;
import lombok.Builder;

@Builder
public class TransacaoResponseDtoBuilder {

    @Builder.Default()
    private static String id = "1";

    @Builder.Default()
    private static String cartao = "4444********1234";

    @Builder.Default()
    private static DescricaoResponseDto descricao = DescricaoResponseDtoBuilder.toDescricaoResponseDto();

    @Builder.Default()
    private static FormaPagamentoResponseDto formaPagamento = FormaPagamentoResponseDtoBuilder.toFormaPagamentoResponseDto();

    public static TransacaoResponseDto toTransacaoResponseDto() {
        return new TransacaoResponseDto(id, cartao, descricao, formaPagamento);
    }

}
