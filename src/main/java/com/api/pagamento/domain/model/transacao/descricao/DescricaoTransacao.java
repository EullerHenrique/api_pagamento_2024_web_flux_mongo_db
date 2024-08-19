package com.api.pagamento.domain.model.transacao.descricao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade responsável por representar a tabela Descricao
 *
 * @author Euller Henrique
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("descricao_transacao")
public class DescricaoTransacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private String estabelecimento;

    @NotNull
    private String nsu;

    @NotNull
    private String codigoAutorizacao;

    @NotNull
    private StatusTransacaoEnum status;

}