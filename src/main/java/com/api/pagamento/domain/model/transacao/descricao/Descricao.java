package com.api.pagamento.domain.model.transacao.descricao;

import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "descricao")
public class Descricao implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_descricao")
    @SequenceGenerator(name = "seq_descricao", sequenceName = "seq_descricao", allocationSize=1)
    private Long id;

    @NotBlank
    private String valor;

    @NotBlank
    private String dataHora;

    @NotBlank
    private String estabelecimento;

    private String nsu;

    private String codigoAutorizacao;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

}