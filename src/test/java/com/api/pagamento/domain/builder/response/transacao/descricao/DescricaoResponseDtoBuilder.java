package com.api.pagamento.domain.builder.response.transacao.descricao;

import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoTransacaoResponseDto;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescricaoResponseDtoBuilder {

    @Builder.Default
    private String valor = "500.55";

    @Builder.Default
    private LocalDateTime dataHora = LocalDateTime.of(2021, 10, 1, 10, 10, 10);

    @Builder.Default
    private String estabelecimento = "PetShop Mundo cão";

    @Builder.Default
    private String nsu = "123456";

    @Builder.Default
    private String autorizacao = "654321";

    @Builder.Default
    private StatusTransacaoEnum status = StatusTransacaoEnum.AUTORIZADO;

    public DescricaoTransacaoResponseDto toDescricaoResponseDto() {
        return new DescricaoTransacaoResponseDto(valor, dataHora, estabelecimento, nsu, autorizacao, status);
    }

}
