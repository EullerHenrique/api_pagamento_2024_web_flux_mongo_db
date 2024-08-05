package com.api.pagamento.domain.builder.request.transacao.descricao;

import com.api.pagamento.domain.dto.request.transacao.descricao.DescricaoTransacaoRequestDto;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public class DescricaoRequestDtoBuilder {


    @Builder.Default
    private static Double valor = 500.55;

    @Builder.Default
    private static LocalDateTime dataHora = LocalDateTime.of(2021, 10, 1, 10, 10, 10);

    @Builder.Default
    private static String estabelecimento = "PetShop Mundo cão";

    public static DescricaoTransacaoRequestDto toDescricaoRequestDto() {
        return new DescricaoTransacaoRequestDto(valor, dataHora, estabelecimento);
    }

}
