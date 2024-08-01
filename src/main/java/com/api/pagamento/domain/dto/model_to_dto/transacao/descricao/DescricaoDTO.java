package com.api.pagamento.domain.dto.model_to_dto.transacao.descricao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DescricaoDTO {

    @JsonIgnore
    private Long id;
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;

}
