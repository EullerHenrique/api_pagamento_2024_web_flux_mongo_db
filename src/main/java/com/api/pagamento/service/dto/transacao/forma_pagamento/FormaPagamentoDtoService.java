package com.api.pagamento.service.dto.transacao.forma_pagamento;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoTransacaoResponseDto;
import com.api.pagamento.service.model.transacao.forma_pagamento.FormaPagamentoModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Serviço responsável por retornar model(s) (se existir) ou  lançar exceção (se não existir)
 *
 * @author Euller Henrique
 */
@Component()
@RequiredArgsConstructor
public class FormaPagamentoDtoService {

    private final FormaPagamentoModelService formaPagamentoModelService;
    private final Converter converter;

    /**
     * Busca uma forma de pagamento
     *
     * @param id
     * 		Id da forma de pagamento
     * @return Transacao
     *     Model com os dados da forma de pagamento
     * @author Euller Henrique
     */
    public Mono<FormaPagamentoTransacaoResponseDto> buscarFormaPagamento(String id) {
        return formaPagamentoModelService.buscarFormaPagamento(id)
                .flatMap(formaPagamentoTransacao ->
                        Mono.just(converter.originToDestiny(formaPagamentoTransacao, FormaPagamentoTransacaoResponseDto.class))
                );
    }

}
