package com.api.pagamento.domain.exception.transacao;

import com.api.pagamento.domain.dto.request_response.response.ResponseMessage;

public class InsercaoNaoPermitidaException extends Exception{

    public InsercaoNaoPermitidaException( ){}

    public ResponseMessage getResponseError(){
        ResponseMessage rmDTO = new ResponseMessage();
        rmDTO.setStatus(400);
        rmDTO.setError("Bad Request");
        rmDTO.setMessage("O id, o código de autorização, o nsu e o status não podem ser inseridos pelo usuário");
        return rmDTO;
    }

}
