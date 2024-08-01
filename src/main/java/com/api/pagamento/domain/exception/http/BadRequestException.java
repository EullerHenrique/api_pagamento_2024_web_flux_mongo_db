package com.api.pagamento.domain.exception.http;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
