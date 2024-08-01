package com.api.pagamento.domain.exception.http;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
