package com.api.pagamento.builder;
import com.api.pagamento.domain.dto.model_to_dto.transacao.descricao.DescricaoDTO;
import com.api.pagamento.domain.dto.model_to_dto.transacao.forma_pagamento.FormaPagamentoDTO;
import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import lombok.Builder;

@Builder
public class TransacaoDTOBuilder {

    @Builder.Default()
    private Long id = 1L;

    @Builder.Default()
    private String cartao = "4444********1234";

    @Builder.Default()
    private DescricaoDTO descricao = DescricaoDTOBuilder.toDescricaoDTO();


    @Builder.Default()
    private FormaPagamentoDTO formaPagamento = FormaPagamentoDTOBuilder.toFormaPagamento();

    public TransacaoDTO toTransacaoDTO() {
        return new TransacaoDTO(id, cartao, descricao, formaPagamento);
    }


}
