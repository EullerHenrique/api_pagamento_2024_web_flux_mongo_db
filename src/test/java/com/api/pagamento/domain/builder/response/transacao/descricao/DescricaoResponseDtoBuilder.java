package com.api.pagamento.domain.builder.response.transacao.descricao;

import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoResponseDto;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class DescricaoResponseDtoBuilder {


    @Builder.Default
    private static String valor = "500.5";

    @Builder.Default
    private static LocalDateTime dataHora = LocalDateTime.of(2021, 10, 1, 10, 10, 10);

    @Builder.Default
    private static String estabelecimento = "PetShop Mundo cão";

    @Builder.Default
    private static String nsu = "123456";

    @Builder.Default
    private static String autorizacao = "654321";

    @Builder.Default
    private static StatusTransacaoEnum status = StatusTransacaoEnum.AUTORIZADO;

    public static DescricaoResponseDto toDescricaoResponseDto() {
        return new DescricaoResponseDto(valor, dataHora, estabelecimento, nsu, autorizacao, status);
    }

}
