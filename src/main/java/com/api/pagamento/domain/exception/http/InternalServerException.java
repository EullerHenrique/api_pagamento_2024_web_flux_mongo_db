package com.api.pagamento.domain.exception.http;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class InternalServerException extends RuntimeException{
    /**
     * Realiza um estorno
     *
     * @author Euller Henrique
     */
    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
