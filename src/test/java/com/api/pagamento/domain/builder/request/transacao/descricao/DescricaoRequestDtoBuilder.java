package com.api.pagamento.domain.builder.request.transacao.descricao;

import com.api.pagamento.domain.dto.request.transacao.descricao.DescricaoTransacaoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescricaoRequestDtoBuilder {

    @Builder.Default
    private Double valor = 500.55;

    @Builder.Default
    private LocalDateTime dataHora = LocalDateTime.of(2021, 10, 1, 10, 10, 10);

    @Builder.Default
    private String estabelecimento = "PetShop Mundo cão";

    public DescricaoTransacaoRequestDto toDescricaoRequestDto() {
        return new DescricaoTransacaoRequestDto(valor, dataHora, estabelecimento);
    }

}
