package com.api.pagamento.service.model.transacao.descricao;

import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.domain.model.transacao.descricao.DescricaoTransacao;
import com.api.pagamento.domain.repository.transacao.descricao.DescricaoTransacaoRepository;
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
public class DescricaoModelService {

    private final DescricaoTransacaoRepository  descricaoTransacaoRepository;

    /**
     * Busca uma descrição
     *
     * @param id
     * 		Id da descricao
     * @return Transacao
     *     Model com os dados da descricao
     * @author Euller Henrique
     */
    public Mono<DescricaoTransacao> buscarDescricao(String id) {
        return descricaoTransacaoRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(ERRO_404_TRANSACAO_NAO_ENCONTRADA)));
    }

    /**
     * Salva uma descricao
     *
     * @param descricaoTransacao
     *         Model com os dados da descricao
     * @return DescricaoTransacao
     *      Model com os dados da descricao salva
     * @author Euller Henrique
     */
    public Mono<DescricaoTransacao> salvarDescricao(DescricaoTransacao descricaoTransacao) {
        return descricaoTransacaoRepository.save(descricaoTransacao);
    }

}
