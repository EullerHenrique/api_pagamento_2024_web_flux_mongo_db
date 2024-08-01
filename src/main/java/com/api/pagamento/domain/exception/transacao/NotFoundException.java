package com.api.pagamento.domain.exception.transacao;

import com.api.pagamento.domain.dto.request_response.response.error.ErrorMessageResponse;

public class NotFoundException extends Exception{
    public NotFoundException() {}

    /**
     * Realiza um estorno
     *
     * @author Euller Henrique
     */
    public ErrorMessageResponse getResponseError(){
        ErrorMessageResponse rmDTO = new ErrorMessageResponse();
        rmDTO.setStatus(404);
        rmDTO.setError("Not Found");
        rmDTO.setMessage("Transação(ões) inexistente(s)");
        return rmDTO;
    }
}
