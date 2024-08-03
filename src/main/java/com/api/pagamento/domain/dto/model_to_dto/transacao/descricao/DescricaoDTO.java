package com.api.pagamento.domain.dto.model_to_dto.transacao.descricao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.api.pagamento.domain.constant.utils.pattern.PatternConstants.PATTERN_DATA_HORA_PT_BR;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DescricaoDTO {

    private String valor;
    @JsonFormat(pattern = PATTERN_DATA_HORA_PT_BR)
    private LocalDateTime dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusTransacaoEnum status;

}
