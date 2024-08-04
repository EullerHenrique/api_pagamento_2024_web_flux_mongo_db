package com.api.pagamento.controller;

import com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants;
import com.api.pagamento.domain.constant.sucess_error.sucess.SucessConstants;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.error.MessageErrorResponseDto;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.service.dto.TransacaoDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@RestController
@RequestMapping("/transacao/v1")
@RequiredArgsConstructor
public class TransacaoController {
	private final TransacaoDtoService transacaoDtoService;

	/**
	 * Busca uma transação pelo id
	 *
	 * @author Euller Henrique
	 * @param id
	 * 		Id da transação
	 */
	@Operation(summary = "Busca uma transação pelo id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponseDto.class)) }),
			@ApiResponse(responseCode = "404", description = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
			@ApiResponse(responseCode = "500", description = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }) })
	@GetMapping(value = "buscar/{id}", produces = "application/json")
	public ResponseEntity<Object> buscarTransacao(@PathVariable Long id) {

		try {
			TransacaoResponseDto transacaoDTO = transacaoDtoService.buscarTransacao(id);
			return ResponseEntity.ok().body(transacaoDTO);
		} catch (NotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

	/**
	 * Busca todas as transações
	 *
	 * @author Euller Henrique
	 * @return List<TransacaoDTO> Lista de transações
	 */
	@Operation(summary = "Busca todas as transações")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponseDto.class)) }),
			@ApiResponse(responseCode = "404", description = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
			@ApiResponse(responseCode = "500", description = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }) })
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<Object> listarTransacoes() {

		try {
			List<TransacaoResponseDto> transacaoDTOS = transacaoDtoService.listarTransacoes();
			return ResponseEntity.ok().body(transacaoDTOS);
		} catch (NotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

	/**
	 * Realiza um pagamento
	 *
	 * @author Euller Henrique
	 * @param request
	 * 		Objeto que contém os dados da transação
	 */
	@Operation(summary = "Realiza um pagamento")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponseDto.class)) }),
			@ApiResponse(responseCode = "404", description = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = ErrorConstants.ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
			@ApiResponse(responseCode = "500", description = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }) })
	@PostMapping(value = "/pagar", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> pagar(@RequestBody @Valid TransacaoRequestDto request) {

		try {
			TransacaoResponseDto transacaoDTO = transacaoDtoService.pagar(request);
			return ResponseEntity.ok().body(transacaoDTO);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 * @param id
	 * 		Id da transação
	 */
	@Operation(summary = "Realiza um estorno")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponseDto.class)) }),
			@ApiResponse(responseCode = "404", description = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
			@ApiResponse(responseCode = "500", description = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorResponseDto.class)) }) })
	@PutMapping(value = "/estornar/{id}", produces = "application/json")
	public ResponseEntity<Object> estornar(@PathVariable Long id) {

		try {
			TransacaoResponseDto transacaoDto = transacaoDtoService.estornar(id);
			return ResponseEntity.ok().body(transacaoDto);
		} catch (NotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

}
