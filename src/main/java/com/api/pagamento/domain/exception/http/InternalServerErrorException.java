package com.api.pagamento.domain.exception.http;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe que representa uma exceção de erro interno do servidor
 *
 * @author Euller Henrique
 */
@Slf4j
public class InternalServerErrorException extends RuntimeException{

    /**
     * Construtor da exceção
     *
     * @param cause
     *         Causa do erro
     */
    public InternalServerErrorException(Throwable cause) {
        super(cause);
        log.error("Status: 500 - Msg: Erro interno do servidor Error: ", cause);
    }
}
