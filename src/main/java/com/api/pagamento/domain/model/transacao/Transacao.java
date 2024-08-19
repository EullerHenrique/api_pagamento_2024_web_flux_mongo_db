package com.api.pagamento.domain.model.transacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade responsável por representar a tabela Transacao
 *
 * @author Euller Henrique
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("transacao")
public class Transacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    private String cartao;

    @NotNull
    private String descricaoId;

    @NotNull
    private String formaPagamentoId;

}