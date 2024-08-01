package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.dto.request_response.request.transacao.SingleTransacaoRequest;
import com.api.pagamento.domain.exception.transacao.TransacaoInexistenteException;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transacao/v1")
@RequiredArgsConstructor
public class TransacaoController {
    private final TransacaoDtoService transacaoDtoService;

    @ApiOperation(value = "Procura uma transação pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A transação foi encontrada"),
            @ApiResponse(code = 404, message = "A transação com o id em questão não foi encontrada"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TransacaoDTO> procurarPeloId(@PathVariable Long id) throws TransacaoInexistenteException {

        TransacaoDTO transacaoDTO = transacaoDtoService.procurarPeloId(id);
       return ResponseEntity.ok().body(transacaoDTO);

    }

    @ApiOperation(value = "Procura todas as transações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pelo menos uma transação foi encontrada"),
            @ApiResponse(code = 404, message = "Nenhuma transação foi encontrada"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TransacaoDTO>> listarTranscacoes() throws TransacaoInexistenteException {

        List<TransacaoDTO> transacaoDTOS = transacaoDtoService.listarTranscacoes();
        return ResponseEntity.ok().body(transacaoDTOS);

    }

    @ApiOperation(value = "Realiza um pagamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O pagamento foi realizado"),
            @ApiResponse(code = 404, message = "O código de autorização, o nsu e o status não podem ser inseridos pelo usuário"),
            @ApiResponse(code = 400, message = "Há campos obrigatórios que não foram preenchidos"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/pagamento", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TransacaoDTO> pagar(@RequestBody @Valid SingleTransacaoRequest request) {

        TransacaoDTO transacaoDTO = transacaoDtoService.pagar(request);
        return ResponseEntity.ok().body(transacaoDTO);

    }

    @ApiOperation(value = "Solicita um estorno pelo id da transação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O estorno foi realizado"),
            @ApiResponse(code = 404, message = "Nenhuma transação foi encontrada"),
            @ApiResponse(code = 400, message = "Há campos obrigatórios que não foram preenchidos"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping(value = "/estorno/{id}", produces = "application/json")
    public ResponseEntity<TransacaoDTO> estornar(@PathVariable Long id) throws TransacaoInexistenteException {

        TransacaoDTO transacaoDto = transacaoDtoService.estornar(id);
        return ResponseEntity.ok().body(transacaoDto);
    }

}
