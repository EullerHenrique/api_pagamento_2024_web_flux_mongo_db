package com.api.pagamento.domain.dto.request_response.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse {

    private int status;
    private String error;
    private String message;

}
