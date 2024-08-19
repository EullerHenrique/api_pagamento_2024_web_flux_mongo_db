package com.api.pagamento.domain.model.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoTransacaoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade responsável por representar a tabela FormaPagamento
 *
 * @author Euller Henrique
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("forma_pagamento_transacao")
public class FormaPagamentoTransacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    private TipoPagamentoTransacaoEnum tipo;

    @NotNull
    private Integer parcelas;

}