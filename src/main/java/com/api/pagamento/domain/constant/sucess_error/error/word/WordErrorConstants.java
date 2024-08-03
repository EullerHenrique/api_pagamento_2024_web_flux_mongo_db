package com.api.pagamento.domain.constant.sucess_error.error.word;

import lombok.NoArgsConstructor;

import static com.api.pagamento.domain.constant.utils.txt.TxtConstants.ESPACO;
import static com.api.pagamento.domain.constant.utils.txt.TxtConstants.PORCENTAGEM_STRING;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class WordErrorConstants {

	public static final String DEVE_SER = "deve ser";
	public static final String DEVE_TER = "deve ter";
	public static final String MAIOR_QUE = "maior que";

	public static final String EH_OBRIGATORIO = "é obrigatório!";
	public static final String CARACTERES = "caracteres!";

	public static final String O_CAMPO_XXX = "O campo" + PORCENTAGEM_STRING;
	public static final String NO_FORMATO_YYY = "no formato" + PORCENTAGEM_STRING;
	public static final String DO_TIPO_YYY = "do tipo" + PORCENTAGEM_STRING;
	public static final String UM_DOS_VALORES_YYY = "um dos valores:" + PORCENTAGEM_STRING;
	public static final String DEVE_SER_MAIOR_QUE = DEVE_SER + ESPACO + MAIOR_QUE;


}
