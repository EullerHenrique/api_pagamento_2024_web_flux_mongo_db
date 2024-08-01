package com.api.pagamento.domain.model.transacao;

import com.api.pagamento.domain.model.transacao.descricao.Descricao;
import com.api.pagamento.domain.model.transacao.forma_pagamento.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transacao")
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_transacao")
    @SequenceGenerator(name = "seq_transacao", sequenceName = "seq_transacao", allocationSize=1)

    private Long id;

    @NotBlank
    private String cartao;

    @Valid
    @NotNull
    @OneToOne(cascade= CascadeType.PERSIST)
    private Descricao descricao;

    @Valid
    @NotNull
    @OneToOne(cascade=CascadeType.PERSIST)
    private FormaPagamento formaPagamento;

}