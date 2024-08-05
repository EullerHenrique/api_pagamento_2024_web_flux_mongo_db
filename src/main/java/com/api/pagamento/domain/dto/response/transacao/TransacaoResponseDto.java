package com.api.pagamento.domain.dto.response.transacao;

import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto responsável por armazenar os dados de resposta da uma transação
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponseDto {

    private String id;
    private String cartao;
    private DescricaoResponseDto descricao;
    private FormaPagamentoResponseDto formaPagamento;

}
