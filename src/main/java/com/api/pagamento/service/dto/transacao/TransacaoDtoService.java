package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
import com.api.pagamento.domain.exception.transacao.InsercaoNaoPermitidaException;
import com.api.pagamento.domain.exception.transacao.TransacaoInexistenteException;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.repository.transacao.descricao.DescricaoRepository;
import com.api.pagamento.repository.transacao.TransacaoRepository;
import com.api.pagamento.service.util.ModelMapperUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TransacaoDtoService {

    private final TransacaoRepository transacaoRepository;
    private final DescricaoRepository descricaoRepository;

    public TransacaoDTO procurarPeloId(Long id) throws TransacaoInexistenteException {
        TransacaoDTO transacaoDTO = (TransacaoDTO) transacaoRepository.findById(id).map(t -> ModelMapperUtilService.convert(t, TransacaoDTO.class)).orElse(null);
        if(transacaoDTO != null){
            return transacaoDTO;
        }else{
            throw new TransacaoInexistenteException();
        }
    }

    public List<TransacaoDTO> procurarTodos() throws TransacaoInexistenteException {
        List<TransacaoDTO> transacaoDTO = transacaoRepository.findAll().stream().map(t -> (TransacaoDTO) ModelMapperUtilService.convert(t, TransacaoDTO.class)).collect(Collectors.toList());
        if(transacaoDTO.size() != 0){
            return transacaoDTO;
        }else{
            throw new TransacaoInexistenteException();
        }
    }

    public TransacaoDTO pagar(SingleTransacaoRequest request) throws InsercaoNaoPermitidaException {

        /*
        if(transacao.getDescricao().getStatus() == null && transacao.getDescricao().getNsu() == null && transacao.getDescricao().getCodigoAutorizacao() == null && transacao.getId() == null && transacao.getDescricao().getId() == null && transacao.getFormaPagamento().getId() == null) {
            transacao.getDescricao().setNsu("1234567890");
            transacao.getDescricao().setCodigoAutorizacao("147258369");
            transacao.getDescricao().setStatus(StatusEnum.AUTORIZADO);
            return (TransacaoDTO) ModelMapperUtilService.convert(transacaoRepository.save(transacao), TransacaoDTO.class);
        }else{
            throw new InsercaoNaoPermitidaException();
        }
         */

        return null;

    }

    public TransacaoDTO estornar(Long id) throws TransacaoInexistenteException {

        try{

            Transacao transacao = (Transacao) ModelMapperUtilService.convert(procurarPeloId(id), Transacao.class);
            transacao.getDescricao().setStatus(StatusEnum.NEGADO);

            descricaoRepository.save(transacao.getDescricao());

            return (TransacaoDTO) ModelMapperUtilService.convert(transacao, TransacaoDTO.class);
        }catch (TransacaoInexistenteException ex){
            throw new TransacaoInexistenteException();
        }

    }

}
