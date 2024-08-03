package com.api.pagamento.domain.dto.response.transacao;

import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoResponseDTO;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TransacaoResponseTO {

    private Long id;
    private String cartao;
    private DescricaoResponseDTO descricao;
    private FormaPagamentoResponseDTO formaPagamento;

}
