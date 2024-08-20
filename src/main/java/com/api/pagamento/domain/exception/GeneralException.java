package com.api.pagamento.domain.exception;

import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerErrorException;
import com.api.pagamento.domain.exception.http.NotFoundException;

/**
 * Classe que representa uma exceção de erro geral
 *
 * @author Euller Henrique
 */
public class GeneralException extends RuntimeException{

	/**
	 * Construtor da exceção
	 *
	 * @param cause
	 *         Causa do erro
	 */
	public GeneralException(Throwable cause) {
		super(cause);
		if (cause instanceof NotFoundException) {
			throw new NotFoundException(cause.getMessage());
		}else if (cause instanceof BadRequestException) {
			throw new BadRequestException(cause.getMessage());
		}else{
			throw new InternalServerErrorException(cause);
		}
	}
}
