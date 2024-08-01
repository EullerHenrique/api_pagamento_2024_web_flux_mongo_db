package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.exception.transacao.NotFoundException;
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A transação foi encontrada"),
			@ApiResponse(code = 404, message = "A transação com o id em questão não foi encontrada"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "buscar/{id}", produces = "application/json")
	public ResponseEntity<TransacaoDTO> buscarTransacaoPeloId(@PathVariable Long id) throws NotFoundException {

		TransacaoDTO transacaoDTO = transacaoDtoService.buscarTransacaoPeloId(id);
		return ResponseEntity.ok().body(transacaoDTO);

	}

	/**
	 * Busca todas as transações
	 *
	 * @author Euller Henrique
	 * @return List<TransacaoDTO>
	 *     Lista de transações
	 */
	@ApiOperation(value = "Busca todas as transações")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pelo menos uma transação foi encontrada"),
			@ApiResponse(code = 404, message = "Nenhuma transação foi encontrada"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/listar", produces = "application/json")
	public ResponseEntity<List<TransacaoDTO>> listarTranscacoes() throws NotFoundException {

		List<TransacaoDTO> transacaoDTOS = transacaoDtoService.listarTranscacoes();
		return ResponseEntity.ok().body(transacaoDTOS);

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
	public ResponseEntity<TransacaoDTO> pagar(@RequestBody @Valid SingleTransacaoRequest request) {

		TransacaoDTO transacaoDTO = transacaoDtoService.pagar(request);
		return ResponseEntity.ok().body(transacaoDTO);

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
	public ResponseEntity<TransacaoDTO> estornar(@PathVariable Long id) throws NotFoundException {

		TransacaoDTO transacaoDto = transacaoDtoService.estornar(id);
		return ResponseEntity.ok().body(transacaoDto);
	}

}
