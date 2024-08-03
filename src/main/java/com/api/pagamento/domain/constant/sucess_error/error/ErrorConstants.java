package com.api.pagamento.domain.constant.sucess_error.error;

import lombok.NoArgsConstructor;

import static com.api.pagamento.domain.constant.sucess_error.error.word.WordErrorConstants.*;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ErrorConstants {

	public static final String ERRO_500_SERVIDOR_INTERNO = "Ocorreu um erro interno no servidor ao tentar realizar a operação";

	public static final String ERRO_404_TRANSACAO_NAO_ENCONTRADA = "Transação não encontrada";
	public static final String ERRO_404_NENHUMA_TRANSACAO_ENCONTRADA = "Nenhuma transação encontrada";

	public static final String ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE = "Há campos preenchidos incorretamente";
	public static final String ERROR_400_PAGAMENTO_AVISTA_MAIS_DE_UMA_PARCELA = "Pagamento à vista não pode ter mais de uma parcela!";

	public static final String ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY = O_CAMPO_XXX + DEVE_SER + DO_TIPO_YYY;
	public static final String ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY_NO_FORMATO = ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY + NO_FORMATO_YYY;
	public static final String ERRO_400_O_CAMPO_XXX_DEVE_SER_UM_DOS_VALORES_YYY = O_CAMPO_XXX + DEVE_SER + UM_DOS_VALORES_YYY;

}
