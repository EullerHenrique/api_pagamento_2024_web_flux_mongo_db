package com.api.pagamento.service.model.transacao;

import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.repository.transacao.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants.*;

@Service
@RequiredArgsConstructor
public class TransacaoModelService {

    private final TransacaoRepository transacaoRepository;

    /**
     * Realiza um pagamento
     *
     */
    public Transacao buscarTransacao(Long id) {
        return transacaoRepository.findById(id).orElseThrow(() -> new NotFoundException(ERRO_TRANSACAO_NAO_ENCONTRADA));
    }

    /**
     * Realiza um pagamento
     *
     */
    public List<Transacao> listarTranscacoes() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        if (transacoes.isEmpty()) {
            throw new NotFoundException(ERRO_NENUMA_TRANSACAO_ENCONTRADA);
        }
        return transacoes;
    }

    /**
     * Realiza um pagamento
     *
     */
    public Long salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao).getId();
    }

}
