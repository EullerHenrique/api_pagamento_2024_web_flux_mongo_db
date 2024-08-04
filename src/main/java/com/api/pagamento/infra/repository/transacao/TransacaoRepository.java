package com.api.pagamento.infra.repository.transacao;

import com.api.pagamento.domain.model.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public interface TransacaoRepository extends JpaRepository<Transacao, Long> { }
