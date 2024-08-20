package com.api.pagamento.domain.dto.builder.response.transacao;


/**
 * Classe responsável por definir valores padrões para a response da transação
 *
 * @author Euller Henrique

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponseDtoBuilder {

    @Builder.Default()
    private String id = "1";

    @Builder.Default()
    private String cartao = "4444********1234";

    @Builder.Default()
    private DescricaoTransacaoResponseDto descricao = new DescricaoResponseDtoBuilder().obterDescricaoTransacaoResponseDto();

    @Builder.Default()
    private FormaPagamentoTransacaoResponseDto formaPagamento = new FormaPagamentoResponseDtoBuilder().obterFormaPagamentoTransacaoResponseDto();

    public TransacaoResponseDto obterTransacaoResponseDto() {
        return new TransacaoResponseDto(id, cartao, descricao, formaPagamento);
    }

}
 */