package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import com.api.pagamento.service.validator.transacao.TransacaoValidatorService;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoDtoService {

    private final TransacaoModelService transacaoModelService;
    private final TransacaoUtilService transacaoUtilService;
    private final TransacaoValidatorService transacaoValidatorService;
    private final Converter converter;

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoResponseDto buscarTransacao(Long id) {
        Transacao transacao = transacaoModelService.buscarTransacao(id);
        return converter.originToDestiny(transacao, TransacaoResponseDto.class);
    }

    /**
     * Realiza um pagamento
     *
     */
    public List<TransacaoResponseDto> listarTransacoes() {
        List<Transacao> transacoes  = transacaoModelService.listarTranscacoes();
        return converter.originToDestiny(transacoes, TransacaoResponseDto.class);
    }

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoResponseDto pagar(TransacaoRequestDto request) {
        transacaoValidatorService.validarTipoPagamentoAoPagar(request);

        TransacaoResponseDto transacaoResponseDto = converter.originToDestiny(request, TransacaoResponseDto.class);

        transacaoResponseDto.getDescricao().setNsu(transacaoUtilService.obterNsu());
        transacaoResponseDto.getDescricao().setCodigoAutorizacao(transacaoUtilService.obterCodigoAutorizacao());
        transacaoResponseDto.getDescricao().setStatus(transacaoUtilService.obterStatusAoPagar());

        Transacao transacaoNaoSalva = converter.originToDestiny(transacaoResponseDto, Transacao.class);
        transacaoResponseDto.setId(transacaoModelService.salvarTransacao(transacaoNaoSalva).toString());

        return transacaoResponseDto;
    }

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoResponseDto estornar(Long id){
        Transacao transacao = transacaoModelService.buscarTransacao(id);
        transacaoValidatorService.validarStatusTransacaoAoEstornar(transacao);

        transacao.getDescricao().setStatus(transacaoUtilService.obterStatusAoEstornar());
        transacaoModelService.salvarTransacao(transacao);

        return converter.originToDestiny(transacao, TransacaoResponseDto.class);
    }

}
