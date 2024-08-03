package com.api.pagamento.domain.dto.request_response.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@AllArgsConstructor
public class ResponseMessageError {
    private int status;
    private String error;
    private String message;

    /**
     * Realiza um estorno
     *
     * @author Euller Henrique
     */
    public static ResponseEntity<Object> obterResponseEntity(int status, String error, String message) {
        ResponseMessageError errorMessageResponse = new ResponseMessageError(status, error, message);
        return ResponseEntity.status(status).body(errorMessageResponse);
    }

}
