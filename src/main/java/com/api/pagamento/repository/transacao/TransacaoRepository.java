package com.api.pagamento.repository.transacao;

import com.api.pagamento.domain.model.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> { }
