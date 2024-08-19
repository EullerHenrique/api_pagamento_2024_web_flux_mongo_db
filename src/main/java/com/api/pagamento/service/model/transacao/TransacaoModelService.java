package com.api.pagamento.service.model.transacao;

import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.domain.model.transacao.descricao.DescricaoTransacao;
import com.api.pagamento.domain.model.transacao.forma_pagamento.FormaPagamentoTransacao;
import com.api.pagamento.domain.repository.transacao.TransacaoRepository;
import com.api.pagamento.service.model.transacao.descricao.DescricaoModelService;
import com.api.pagamento.service.model.transacao.forma_pagamento.FormaPagamentoModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.api.pagamento.domain.constant.http.message.error.ErrorConstants.ERRO_404_NENHUMA_TRANSACAO_ENCONTRADA;
import static com.api.pagamento.domain.constant.http.message.error.ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA;

/**
 * Serviço responsável por retornar model(s) (se existir) ou  lançar exceção (se não existir)
 *
 * @author Euller Henrique
 */
@Component()
@RequiredArgsConstructor
public class TransacaoModelService {

    private final TransacaoRepository transacaoRepository;
    private final FormaPagamentoModelService formaPagamentoModelService;
    private final DescricaoModelService descricaoModelService;

    /**
     * Busca uma transação
     *
     * @param id
     * 		Id da transação
     * @return Transacao
     *     Model com os dados da transação
     * @author Euller Henrique
     */
    public Mono<Transacao> buscarTransacao(String id) {
        return transacaoRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(ERRO_404_TRANSACAO_NAO_ENCONTRADA)));
    }

    /**
     * Lista as transacoes
     *
     * @return List<Transacao>
     *     Lista de models com os dados das transações
     * @author Euller Henrique
     */
    public Flux<Transacao> listarTransacoes() {
        return transacaoRepository.findAll()
                .switchIfEmpty(Flux.error(new NotFoundException(ERRO_404_NENHUMA_TRANSACAO_ENCONTRADA)));
    }

    /**
     * Salva uma transação
     *
     * @param transacao
     *         Model com os dados da transação
     * @return Transacao
     *     Model com os dados da transação salva
     *
     * @author Euller Henrique
     */
    public Mono<String> salvarTransacao(Transacao transacao, FormaPagamentoTransacao formaPagamentoTransacao, DescricaoTransacao descricaoTransacao) {
        return formaPagamentoModelService.salvarFormaPagamento(formaPagamentoTransacao)
                .flatMap(formaPagamento -> {
                    transacao.setFormaPagamentoId(formaPagamento.getId());
                    return descricaoModelService.salvarDescricao(descricaoTransacao);
                })
                .flatMap(descricao -> {
                    transacao.setDescricaoId(descricao.getId());
                    return transacaoRepository.save(transacao);
                })
                .map(Transacao::getId);
    }

}
