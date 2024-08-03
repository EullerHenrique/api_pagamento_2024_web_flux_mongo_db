package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "O pagamento foi realizado"),
			@ApiResponse(code = 404, message = "O código de autorização, o nsu e o status não podem ser inseridos pelo usuário"),
			@ApiResponse(code = 400, message = "Há campos obrigatórios que não foram preenchidos"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "O pagamento foi realizado"),
			@ApiResponse(code = 404, message = "O código de autorização, o nsu e o status não podem ser inseridos pelo usuário"),
			@ApiResponse(code = 400, message = "Há campos obrigatórios que não foram preenchidos"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "O pagamento foi realizado"),
			@ApiResponse(code = 404, message = "O código de autorização, o nsu e o status não podem ser inseridos pelo usuário"),
			@ApiResponse(code = 400, message = "Há campos obrigatórios que não foram preenchidos"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "O estorno foi realizado"),
			@ApiResponse(code = 404, message = "Nenhuma transação foi encontrada"),
			@ApiResponse(code = 400, message = "Há campos obrigatórios que não foram preenchidos"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
