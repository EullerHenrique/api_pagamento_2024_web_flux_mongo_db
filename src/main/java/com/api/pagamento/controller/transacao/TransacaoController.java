package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.constant.sucess_error.error.ErrorConstants;
import com.api.pagamento.domain.constant.sucess_error.sucess.SucessConstants;
import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.dto.request_response.response.error.ResponseMessageError;
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoDTO.class),
			@ApiResponse(code = 404, message = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, response = ResponseMessageError.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = ResponseMessageError.class) })
	@GetMapping(value = "buscar/{id}", produces = "application/json")
	public ResponseEntity<Object> buscarTransacao(@PathVariable Long id) {

		try {
			TransacaoDTO transacaoDTO = transacaoDtoService.buscarTransacao(id);
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoDTO.class),
			@ApiResponse(code = 404, message = ErrorConstants.ERRO_404_NENHUMA_TRANSACAO_ENCONTRADA, response = ResponseMessageError.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = ResponseMessageError.class) })
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<Object> listarTransacoes() {

		try {
			List<TransacaoDTO> transacaoDTOS = transacaoDtoService.listarTransacoes();
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoDTO.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE, response = ResponseMessageError.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = ResponseMessageError.class) })
	@PostMapping(value = "/pagar", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> pagar(@RequestBody @Valid SingleTransacaoRequest request) {

		try {
			TransacaoDTO transacaoDTO = transacaoDtoService.pagar(request);
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, response = TransacaoDTO.class),
			@ApiResponse(code = 404, message = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, response = ResponseMessageError.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE, response = ResponseMessageError.class),
			@ApiResponse(code = 500, message = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, response = ResponseMessageError.class) })
	@PutMapping(value = "/estornar/{id}", produces = "application/json")
	public ResponseEntity<Object> estornar(@PathVariable Long id) {

		try {
			TransacaoDTO transacaoDto = transacaoDtoService.estornar(id);
			return ResponseEntity.ok().body(transacaoDto);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}

	}

}
