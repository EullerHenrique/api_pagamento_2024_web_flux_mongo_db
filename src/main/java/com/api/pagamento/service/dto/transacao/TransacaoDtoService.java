package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.domain.model.transacao.descricao.DescricaoTransacao;
import com.api.pagamento.domain.model.transacao.forma_pagamento.FormaPagamentoTransacao;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import com.api.pagamento.service.validator.transacao.TransacaoValidatorService;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
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
		return transacaoModelService.buscarTransacao(id).map(transacao -> converter.originToDestiny(transacao, TransacaoResponseDto.class));
	}

	/**
	 * Lista as transacoes
	 *
	 * @return List<TransacaoResponseDto> Lista de dtos com os dados de resposta das transações
	 * @author Euller Henrique
	 */
	public Flux<TransacaoResponseDto> listarTransacoes() {
		return transacaoModelService.listarTransacoes()
				.flatMap(transacao -> Mono.just(converter.originToDestiny(transacao, TransacaoResponseDto.class)));
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
			transacaoValidatorService.validarTipoPagamentoAoPagar(req);
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
	public TransacaoResponseDto estornar(Long id) {
		return null;
		/*
		Transacao transacao = transacaoModelService.buscarTransacao(id);
		transacaoValidatorService.validarStatusTransacaoAoEstornar(transacao);

		transacao.getDescricao().setStatus(transacaoUtilService.obterStatusAoEstornar());
		transacaoModelService.salvarTransacao(transacao);

		return converter.originToDestiny(transacao, TransacaoResponseDto.class);

		 */
	}

}
