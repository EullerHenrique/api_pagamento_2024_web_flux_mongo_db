package com.api.pagamento.domain.exception.transacao;

import com.api.pagamento.domain.dto.request_response.response.ResponseMessage;

public class TransacaoInexistenteException extends Exception{
    public TransacaoInexistenteException() {}

    /**
     * Realiza um estorno
     *
     * @author Euller Henrique
     */
    public ResponseMessage getResponseError(){
        ResponseMessage rmDTO = new ResponseMessage();
        rmDTO.setStatus(404);
        rmDTO.setError("Not Found");
        rmDTO.setMessage("Transação(ões) inexistente(s)");
        return rmDTO;
    }
}
