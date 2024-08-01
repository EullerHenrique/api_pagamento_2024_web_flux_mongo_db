package com.api.pagamento.domain.exception.http;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message) {
        super(message);
    }
}
