package com.api.pagamento.domain.builder.response.transacao;

import com.api.pagamento.domain.builder.response.transacao.descricao.DescricaoResponseDtoBuilder;
import com.api.pagamento.domain.builder.response.transacao.forma_pagamento.FormaPagamentoResponseDtoBuilder;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoTransacaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoTransacaoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponseDtoBuilder {

    @Builder.Default()
    private String id = "1";

    @Builder.Default()
    private String cartao = "4444********1234";

    @Builder.Default()
    private DescricaoTransacaoResponseDto descricao = new DescricaoResponseDtoBuilder().toDescricaoResponseDto();

    @Builder.Default()
    private FormaPagamentoTransacaoResponseDto formaPagamento = new FormaPagamentoResponseDtoBuilder().toFormaPagamentoResponseDto();

    public TransacaoResponseDto toTransacaoResponseDto() {
        return new TransacaoResponseDto(id, cartao, descricao, formaPagamento);
    }

}
