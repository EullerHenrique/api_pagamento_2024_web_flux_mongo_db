package com.api.pagamento.domain.model.transacao.forma_pagamento;

import com.api.pagamento.domain.enumeration.transacao.forma_pagamento.TipoPagamentoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "forma_pagamento_transacao")
public class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_forma_pagamento")
    @SequenceGenerator(name = "seq_forma_pagamento", sequenceName = "seq_forma_pagamento", allocationSize=1)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipo;

    @NotNull
    private Integer parcelas;

}