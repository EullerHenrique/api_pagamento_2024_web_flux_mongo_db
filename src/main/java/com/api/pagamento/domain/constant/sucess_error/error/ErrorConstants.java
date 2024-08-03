package com.api.pagamento.domain.constant.sucess_error.error;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
public class ErrorConstants {

	public static final String ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE = "Há campos preenchidos incorretamente";
	public static final String ERRO_404_TRANSACAO_NAO_ENCONTRADA = "Transação não encontrada";
	public static final String ERRO_404_NENUMA_TRANSACAO_ENCONTRADA = "Nenhuma transação encontrada";
	public static final String ERRO_500_SERVIDOR_INTERNO = "Ocorreu um erro interno no servidor ao tentar realizar a operação";

	/**
	 * Construtor privado para impedir a criação de instâncias
	 *
	 * @author Euller Henrique
	 */
	private ErrorConstants() {}


}
