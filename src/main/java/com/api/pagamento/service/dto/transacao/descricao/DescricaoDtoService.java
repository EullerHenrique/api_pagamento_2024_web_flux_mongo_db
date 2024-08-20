package com.api.pagamento.service.dto.transacao.descricao;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoTransacaoResponseDto;
import com.api.pagamento.service.model.transacao.descricao.DescricaoModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Serviço responsável por retornar dto(s) ou lançar exceção (Se não existir ou se alguma validação falhar)
 *
 * @author Euller Henrique
 */
@Component()
@RequiredArgsConstructor
public class DescricaoDtoService {

    private final DescricaoModelService descricaoModelService;
    private final Converter converter;

    /**
     * Busca uma descrição
     *
     * @param id
     * 		Id da descricao
     * @return Transacao
     *     Model com os dados da descricao
     * @author Euller Henrique
     */
    public Mono<DescricaoTransacaoResponseDto> buscarDescricao(String id) {
        return descricaoModelService.buscarDescricao(id)
                .flatMap(descricaoTransacao ->
                        Mono.just(converter.originToDestiny(descricaoTransacao, DescricaoTransacaoResponseDto.class))
                );
    }

}
