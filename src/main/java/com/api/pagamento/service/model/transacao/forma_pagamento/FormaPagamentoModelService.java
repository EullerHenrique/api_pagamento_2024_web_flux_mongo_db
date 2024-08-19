package com.api.pagamento.service.model.transacao.forma_pagamento;

import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.domain.model.transacao.forma_pagamento.FormaPagamentoTransacao;
import com.api.pagamento.domain.repository.transacao.forma_pagamento.FormaPagamentoTransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.api.pagamento.domain.constant.http.message.error.ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA;

/**
 * Serviço responsável por retornar model(s) (se existir) ou  lançar exceção (se não existir)
 *
 * @author Euller Henrique
 */
@Component()
@RequiredArgsConstructor
public class FormaPagamentoModelService {

    private final FormaPagamentoTransacaoRepository formaPagamentoTransacaoRepository;

    /**
     * Busca uma forma de pagamento
     *
     * @param id
     * 		Id da forma de pagamento
     * @return Transacao
     *     Model com os dados da forma de pagamento
     * @author Euller Henrique
     */
    public Mono<FormaPagamentoTransacao> buscarFormaPagamento(String id) {
        return formaPagamentoTransacaoRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(ERRO_404_TRANSACAO_NAO_ENCONTRADA)));
    }

    /**
     * Salva uma forma de pagamento
     *
     * @param formaPagamentoTransacao
     *         Model com os dados da forma de pagamento
     * @return FormaPagamentoTransacao
     *       Model com os dados da forma de pagamento salva
     * @author Euller Henrique
     */
    public Mono<FormaPagamentoTransacao> salvarFormaPagamento(FormaPagamentoTransacao formaPagamentoTransacao) {
        return formaPagamentoTransacaoRepository.save(formaPagamentoTransacao);
    }

}
