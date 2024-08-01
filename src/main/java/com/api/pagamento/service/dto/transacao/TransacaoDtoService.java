package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.transacao.TransacaoConverter;
import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.exception.transacao.NotFoundException;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.repository.transacao.descricao.DescricaoRepository;
import com.api.pagamento.repository.transacao.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransacaoDtoService {

    private final TransacaoRepository transacaoRepository;
    private final DescricaoRepository descricaoRepository;
    private final TransacaoConverter transacaoConverter;

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoDTO buscarTransacaoPeloId(Long id) throws NotFoundException {
        /*
        TransacaoDTO transacaoDTO = (TransacaoDTO) transacaoRepository.findById(id).map(t -> ModelMapperUtilService.convert(t, TransacaoDTO.class)).orElse(null);
        if(transacaoDTO != null){
            return transacaoDTO;
        }else{
            throw new TransacaoInexistenteException();
        }
        */
        return null;
    }

    /**
     * Realiza um pagamento
     *
     */
    public List<TransacaoDTO> listarTranscacoes() throws NotFoundException {
        /*
        List<TransacaoDTO> transacaoDTO = transacaoRepository.findAll().stream().map(t -> (TransacaoDTO) ModelMapperUtilService.convert(t, TransacaoDTO.class)).collect(Collectors.toList());
        if(transacaoDTO.size() != 0){
            return transacaoDTO;
        }else{
            throw new TransacaoInexistenteException();
        }
         */
        return null;
    }

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoDTO pagar(SingleTransacaoRequest request) {

        TransacaoDTO transacaoDTO = transacaoConverter.requestToDTO(request);
        Transacao transacao = transacaoConverter.dtoToModel(transacaoDTO);

        Long id = transacaoRepository.save(transacao).getId();
        transacaoDTO.setId(id);

        return transacaoDTO;

    }

    /**
     * Realiza um pagamento
     *
     */
    public TransacaoDTO estornar(Long id) throws NotFoundException {
        /*
        try{

            Transacao transacao = (Transacao) ModelMapperUtilService.convert(procurarPeloId(id), Transacao.class);
            transacao.getDescricao().setStatus(StatusEnum.NEGADO);

            descricaoRepository.save(transacao.getDescricao());

            return (TransacaoDTO) ModelMapperUtilService.convert(transacao, TransacaoDTO.class);
        }catch (TransacaoInexistenteException ex){
            throw new TransacaoInexistenteException();
        }
         */
        return null;
    }

}
