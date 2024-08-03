package com.api.pagamento.domain.enumeration.transacao.forma_pagamento;

import lombok.NoArgsConstructor;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum TipoPagamentoEnum {
    AVISTA, PARCELADO_LOJA, PARCELADO_EMISSOR;
}
