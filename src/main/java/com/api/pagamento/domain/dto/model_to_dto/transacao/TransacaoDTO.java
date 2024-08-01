package com.api.pagamento.domain.dto.model_to_dto.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.descricao.DescricaoDTO;
import com.api.pagamento.domain.dto.model_to_dto.transacao.forma_pagamento.FormaPagamentoDTO;
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

public class TransacaoDTO {

    private Long id;
    private String cartao;
    private DescricaoDTO descricao;
    private FormaPagamentoDTO formaPagamento;

}
