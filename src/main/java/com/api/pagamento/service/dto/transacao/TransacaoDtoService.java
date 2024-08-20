package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.domain.dto.response.transacao.descricao.DescricaoTransacaoResponseDto;
import com.api.pagamento.domain.dto.response.transacao.forma_pagamento.FormaPagamentoTransacaoResponseDto;
import com.api.pagamento.domain.model.transacao.descricao.DescricaoTransacao;
import com.api.pagamento.domain.model.transacao.forma_pagamento.FormaPagamentoTransacao;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.model.transacao.descricao.DescricaoModelService;
import com.api.pagamento.service.model.transacao.forma_pagamento.FormaPagamentoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import com.api.pagamento.service.validator.transacao.TransacaoValidatorService;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Serviço responsável por retornar dto(s) ou lançar exceção (Se não existir ou se alguma validação falhar)
 *
 * @author Euller Henrique
 */
@Service
@RequiredArgsConstructor
public class TransacaoDtoService {

	private final TransacaoModelService transacaoModelService;
	private final DescricaoModelService descricaoModelService;
	private final FormaPagamentoModelService formaPagamentoModelService;
	private final TransacaoUtilService transacaoUtilService;
	private final TransacaoValidatorService transacaoValidatorService;
	private final Converter converter;

	/**
	 * Busca uma transação
	 *
	 * @param id
	 * 		Id da transação
	 * @return TransacaoResponseDto Dto com os dados de resposta da transação
	 * @author Euller Henrique
	 */
	public Mono<TransacaoResponseDto> buscarTransacao(String id) {
		return transacaoModelService.buscarTransacao(id)
				.flatMap(this::obterTransacaoMonoComRelacionamentos);
	}

	/**
	 * Lista as transacoes
	 *
	 * @return List<TransacaoResponseDto> Lista de dtos com os dados de resposta das transações
	 * @author Euller Henrique
	 */
	public Flux<TransacaoResponseDto> listarTransacoes() {
		return transacaoModelService.listarTransacoes()
				.flatMap(this::obterTransacaoFluxComRelacionamentos);
	}

	private Mono<? extends TransacaoResponseDto> obterTransacaoMonoComRelacionamentos(Transacao transacao) {
		String descricaoTransacaoId = transacao.getDescricaoId();
		String formaPagamentoTransacaoId = transacao.getFormaPagamentoId();

		return Mono.zip(
						descricaoModelService.buscarDescricao(descricaoTransacaoId),
						formaPagamentoModelService.buscarFormaPagamento(formaPagamentoTransacaoId)
				)
				.map(tuple -> {
					DescricaoTransacao descricaoTransacao = tuple.getT1();
					FormaPagamentoTransacao formaPagamentoTransacao = tuple.getT2();

					DescricaoTransacaoResponseDto descricaoTransacaoResponseDto = converter.originToDestiny(descricaoTransacao,
							DescricaoTransacaoResponseDto.class);
					FormaPagamentoTransacaoResponseDto formaPagamentoTransacaoResponseDto = converter.originToDestiny(formaPagamentoTransacao,
							FormaPagamentoTransacaoResponseDto.class);

					return TransacaoResponseDto.builder()
							.id(transacao.getId())
							.cartao(transacao.getCartao())
							.descricao(descricaoTransacaoResponseDto)
							.formaPagamento(formaPagamentoTransacaoResponseDto)
							.build();
				});
	}

	private Publisher<? extends TransacaoResponseDto> obterTransacaoFluxComRelacionamentos(Transacao transacao) {
		return obterTransacaoMonoComRelacionamentos(transacao);
	}

	/**
	 * Realiza um pagamento
	 *
	 * @param request
	 * 		Dto com os dados de requisição da transação
	 * @return TransacaoResponseDto Dto com os dados da resposta da transação
	 * @author Euller Henrique
	 */
	public Mono<TransacaoResponseDto> pagar(TransacaoRequestDto request) {
		return Mono.just(request).flatMap(req -> {
			transacaoValidatorService.validarTipoPagamentoAoPagar(req.getFormaPagamento().getTipo(), req.getFormaPagamento().getParcelas());
			TransacaoResponseDto transacaoResponseDto = converter.originToDestiny(req, TransacaoResponseDto.class);
			transacaoResponseDto.getDescricao().setNsu(transacaoUtilService.obterNsu());
			transacaoResponseDto.getDescricao().setCodigoAutorizacao(transacaoUtilService.obterCodigoAutorizacao());
			transacaoResponseDto.getDescricao().setStatus(transacaoUtilService.obterStatusAoPagar());

			Transacao transacaoNaoSalva = converter.originToDestiny(transacaoResponseDto, Transacao.class);
			FormaPagamentoTransacao formaPagamentoNaoSalva = converter.originToDestiny(transacaoResponseDto.getFormaPagamento(),
					FormaPagamentoTransacao.class);
			DescricaoTransacao descricaoTransacaoNaoSalva = converter.originToDestiny(transacaoResponseDto.getDescricao(),
					DescricaoTransacao.class);

			return transacaoModelService.salvarTransacao(transacaoNaoSalva, formaPagamentoNaoSalva, descricaoTransacaoNaoSalva)
					.map(transacaoId -> {
						transacaoResponseDto.setId(transacaoId);
						return transacaoResponseDto;
					});
		});
	}

	/**
	 * Estorna a transação
	 *
	 * @param id
	 * 		Id da transação
	 * @return TransacaoResponseDto Dto com os dados de resposta da transação
	 * @author Euller Henrique
	 */
	public Mono<TransacaoResponseDto> estornar(String id) {
		return buscarTransacao(id).flatMap(transacaoResponseDto -> {
			transacaoValidatorService.validarStatusTransacaoAoEstornar(transacaoResponseDto.getDescricao().getStatus());
			transacaoResponseDto.getDescricao().setStatus(transacaoUtilService.obterStatusAoEstornar());

			FormaPagamentoTransacao formaPagamentoNaoSalva = converter.originToDestiny(transacaoResponseDto.getFormaPagamento(),
					FormaPagamentoTransacao.class);
			DescricaoTransacao descricaoTransacaoNaoSalva = converter.originToDestiny(transacaoResponseDto.getDescricao(),
					DescricaoTransacao.class);

			return Mono.zip(
					descricaoModelService.salvarDescricao(descricaoTransacaoNaoSalva),
					formaPagamentoModelService.salvarFormaPagamento(formaPagamentoNaoSalva))
					.thenReturn(transacaoResponseDto);
		});
	}

}
