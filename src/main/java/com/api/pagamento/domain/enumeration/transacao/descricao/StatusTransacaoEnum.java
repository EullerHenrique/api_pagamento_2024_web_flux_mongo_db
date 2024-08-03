package com.api.pagamento.domain.enumeration.transacao.descricao;

import lombok.NoArgsConstructor;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum StatusTransacaoEnum {
   AUTORIZADO, NEGADO, CANCELADO;
}
