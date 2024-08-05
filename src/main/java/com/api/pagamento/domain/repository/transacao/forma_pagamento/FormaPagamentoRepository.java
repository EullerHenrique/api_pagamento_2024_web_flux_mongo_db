package com.api.pagamento.domain.repository.transacao.forma_pagamento;

import com.api.pagamento.domain.model.transacao.forma_pagamento.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> { }
