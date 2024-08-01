package com.api.pagamento.repository.transacao.descricao;

import com.api.pagamento.domain.model.transacao.descricao.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescricaoRepository extends JpaRepository<Descricao, Long> { }
