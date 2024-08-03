package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.transacao.TransacaoConverter;
import com.api.pagamento.domain.dto.response.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoDtoService {

    private final TransacaoModelService transacaoModelService;
    private final TransacaoConverter transacaoConverter;
    private final TransacaoUtilService transacaoUtilService;

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoDTO buscarTransacao(Long id) {
        Transacao transacao = transacaoModelService.buscarTransacao(id);
        return transacaoConverter.modelToDTO(transacao);
    }

    /**
     * Realiza um pagamento
     *
     */
    public List<TransacaoDTO> listarTransacoes() {
        List<Transacao> transacoes  = transacaoModelService.listarTranscacoes();
        return transacaoConverter.modelsToDTOs(transacoes);
    }

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoDTO pagar(SingleTransacaoRequest request) {
        transacaoUtilService.validarTipoPagamentoAoPagar(request);

        TransacaoDTO transacaoDTO = transacaoConverter.requestToDTO(request);

        transacaoDTO.getDescricao().setNsu(transacaoUtilService.obterNsu());
        transacaoDTO.getDescricao().setCodigoAutorizacao(transacaoUtilService.obterCodigoAutorizacao());
        transacaoDTO.getDescricao().setStatus(transacaoUtilService.obterStatusAoPagar());

        Transacao transacaoNaoSalva = transacaoConverter.dtoToModel(transacaoDTO);
        Long id = transacaoModelService.salvarTransacao(transacaoNaoSalva);
        transacaoDTO.setId(id);

        return transacaoDTO;
    }

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoDTO estornar(Long id){
        Transacao transacao = transacaoModelService.buscarTransacao(id);
        transacaoUtilService.validarStatusTransacaoAoEstornar(transacao);

        transacao.getDescricao().setStatus(transacaoUtilService.obterStatusAoEstornar());
        transacaoModelService.salvarTransacao(transacao);

        return transacaoConverter.modelToDTO(transacao);
    }

}
