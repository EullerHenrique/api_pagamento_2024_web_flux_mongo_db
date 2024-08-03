package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants;
import com.api.pagamento.domain.constant.sucess_error.sucess.SucessConstants;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.error.MessageErrorResponseDto;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
	 * @param id
	 * 		Id da transação
	 * @author Euller Henrique
	 */
	@ApiOperation(value = "Busca uma transação pelo id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoResponseDto.class),
			@ApiResponse(code = 404, message = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, response = MessageErrorResponseDto.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = MessageErrorResponseDto.class) })
	@GetMapping(value = "buscar/{id}", produces = "application/json")
	public ResponseEntity<Object> buscarTransacao(@PathVariable Long id) {

		try {
			TransacaoResponseDto transacaoDTO = transacaoDtoService.buscarTransacao(id);
			return ResponseEntity.ok().body(transacaoDTO);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}
	}

	/**
	 * Busca todas as transações
	 *
	 * @return List<TransacaoDTO> Lista de transações
	 * @author Euller Henrique
	 */
	@ApiOperation(value = "Busca todas as transações")
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoResponseDto.class),
			@ApiResponse(code = 404, message = ErrorConstants.ERRO_404_NENHUMA_TRANSACAO_ENCONTRADA, response = MessageErrorResponseDto.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = MessageErrorResponseDto.class) })
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<Object> listarTransacoes() {

		try {
			List<TransacaoResponseDto> transacaoDTOS = transacaoDtoService.listarTransacoes();
			return ResponseEntity.ok().body(transacaoDTOS);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

	/**
	 * Realiza um pagamento
	 *
	 * @param request
	 * 		Objeto que contém os dados da transação
	 * @author Euller Henrique
	 */
	@ApiOperation(value = "Realiza um pagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoResponseDto.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE, response = MessageErrorResponseDto.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = MessageErrorResponseDto.class) })
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
	 * @param id
	 * 		Id da transação
	 * @author Euller Henrique
	 */
	@ApiOperation(value = "Realiza um estorno")
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoResponseDto.class),
			@ApiResponse(code = 404, message = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, response = MessageErrorResponseDto.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE, response = MessageErrorResponseDto.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = MessageErrorResponseDto.class) })
	@PutMapping(value = "/estornar/{id}", produces = "application/json")
	public ResponseEntity<Object> estornar(@PathVariable Long id) {

		try {
			TransacaoResponseDto transacaoDto = transacaoDtoService.estornar(id);
			return ResponseEntity.ok().body(transacaoDto);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

}
