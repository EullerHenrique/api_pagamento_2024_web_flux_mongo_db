package com.api.pagamento.repository.transacao.descricao;

import com.api.pagamento.domain.model.transacao.descricao.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public interface DescricaoRepository extends JpaRepository<Descricao, Long> { }
