package com.api.pagamento.controller;

import com.api.pagamento.config.gson.LocalDateTimePtbrAdapter;
import com.api.pagamento.domain.builder.request.transacao.TransacaoRequestDtoBuilder;
import com.api.pagamento.domain.builder.response.transacao.TransacaoResponseDtoBuilder;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import com.api.pagamento.domain.exception.handler.http.HttpExceptionHandler;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.api.pagamento.domain.constant.util.pattern.PatternConstants.FORMATTER_DATA_HORA_PT_BR;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

	private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimePtbrAdapter()).create();

	private MockMvc mockMvc;

	@Mock
	private TransacaoDtoService transacaoService;

	@InjectMocks
	private TransacaoController transacaoController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).setControllerAdvice(HttpExceptionHandler.class).build();
	}

	@Test
	void QuandoUmaTransacaoEhSolicitadaElaDeveSerRealizada() throws Exception {
		//Dado
		TransacaoRequestDto transacaoRequestDto = TransacaoRequestDtoBuilder.toTransacaoRequestDto();
		TransacaoResponseDto transacaoResponseDto = TransacaoResponseDtoBuilder.toTransacaoResponseDto();

		//Quando
		when(transacaoService.pagar(transacaoRequestDto)).thenReturn(transacaoResponseDto);

		// Então
		String jsonResponse = gson.toJson(transacaoResponseDto);
		mockMvc.perform(post("/transacao/v1/pagar").contentType(MediaType.APPLICATION_JSON).content(jsonResponse))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(transacaoResponseDto.getId())))
				.andExpect(jsonPath("$.cartao", is(transacaoResponseDto.getCartao())))
				.andExpect(jsonPath("$.descricao.valor", is(transacaoResponseDto.getDescricao().getValor())))
				.andExpect(jsonPath("$.descricao.dataHora", is(transacaoResponseDto.getDescricao().getDataHora().format(FORMATTER_DATA_HORA_PT_BR))))
				.andExpect(jsonPath("$.descricao.estabelecimento", is(transacaoResponseDto.getDescricao().getEstabelecimento())))
				.andExpect(jsonPath("$.descricao.nsu", is(transacaoResponseDto.getDescricao().getNsu())))
				.andExpect(jsonPath("$.descricao.codigoAutorizacao", is(transacaoResponseDto.getDescricao().getCodigoAutorizacao())))
				.andExpect(jsonPath("$.descricao.status", is(transacaoResponseDto.getDescricao().getStatus().toString())))
				.andExpect(jsonPath("$.formaPagamento.tipo", is(transacaoResponseDto.getFormaPagamento().getTipo().toString())))
				.andExpect(jsonPath("$.formaPagamento.parcelas", is(transacaoResponseDto.getFormaPagamento().getParcelas())));
	}

	@Test
	void QuandoUmaTransacaoEhBuscadaPeloIdENaoEhEncontradaUmaExcecaoDeveSerRetornada() throws Exception {
		//Dado
		Long id = 1L;

		//Quando
		when(transacaoService.buscarTransacao(id)).thenThrow(NotFoundException.class);

		// Então
		mockMvc.perform(get("/transacao/v1/buscar/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}


	@Test
	void QuandoUmPagamentoEhSolicitadoComCamposPreenchidosIncorretamenteUmaExcecaoDeveSerRetornada() throws Exception {

		// Dado
		TransacaoResponseDto transacaoResponseDto = TransacaoResponseDtoBuilder.toTransacaoResponseDto();
		transacaoResponseDto.setCartao(null);
		transacaoResponseDto.getDescricao().setEstabelecimento(null);

		//When
		//A anotação @Valid do Spring não permite que o objeto seja instanciado com campos nulos

		// Então
		String jsonResponse = gson.toJson(transacaoResponseDto);
		mockMvc.perform(post("/transacao/v1/pagar").contentType(MediaType.APPLICATION_JSON).content(jsonResponse))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

	}

	@Test
	void QuandoUmaTransacaoEhBuscadaPeloIdElaDeveSerRetornada() throws Exception {
		//Dado
		Long id = 1L;
		TransacaoResponseDto transacaoResponseDto = TransacaoResponseDtoBuilder.toTransacaoResponseDto();

		//Quando
		when(transacaoService.buscarTransacao(id)).thenReturn(transacaoResponseDto);

		// Então
		String jsonResponse = gson.toJson(transacaoResponseDto);
		mockMvc.perform(get("/transacao/v1/buscar/{id}", id).contentType(MediaType.APPLICATION_JSON).content(jsonResponse))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(transacaoResponseDto.getId())))
				.andExpect(jsonPath("$.cartao", is(transacaoResponseDto.getCartao())))
				.andExpect(jsonPath("$.descricao.valor", is(transacaoResponseDto.getDescricao().getValor()))).andExpect(jsonPath("$.descricao.dataHora", is(transacaoResponseDto.getDescricao().getDataHora().format(FORMATTER_DATA_HORA_PT_BR))))
				.andExpect(jsonPath("$.descricao.estabelecimento", is(transacaoResponseDto.getDescricao().getEstabelecimento())))
				.andExpect(jsonPath("$.descricao.nsu", is(transacaoResponseDto.getDescricao().getNsu())))
				.andExpect(jsonPath("$.descricao.codigoAutorizacao", is(transacaoResponseDto.getDescricao().getCodigoAutorizacao())))
				.andExpect(jsonPath("$.descricao.status", is(transacaoResponseDto.getDescricao().getStatus().toString())))
				.andExpect(jsonPath("$.formaPagamento.tipo", is(transacaoResponseDto.getFormaPagamento().getTipo().toString())))
				.andExpect(jsonPath("$.formaPagamento.parcelas", is(transacaoResponseDto.getFormaPagamento().getParcelas())));
	}

	@Test
	void QuandoTransacoesSaoBuscadasElasDevemSerRetornadas() throws Exception {
		//Dado
		List<TransacaoResponseDto> transacoesResponseDto = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			transacoesResponseDto.add(TransacaoResponseDtoBuilder.toTransacaoResponseDto());
		}

		//Quando
		when(transacaoService.listarTransacoes()).thenReturn(transacoesResponseDto);

		// Então
		String jsonResponse = gson.toJson(transacoesResponseDto);
		mockMvc.perform(get("/transacao/v1/listar").contentType(MediaType.APPLICATION_JSON).content(jsonResponse))
				.andExpect(jsonPath("$[*].id", containsInAnyOrder(transacoesResponseDto.stream().map(TransacaoResponseDto::getId).toArray())))
				.andExpect(jsonPath("$[*].cartao", containsInAnyOrder(transacoesResponseDto.stream().map(TransacaoResponseDto::getCartao).toArray())))
				.andExpect(jsonPath("$[*].descricao.valor", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getDescricao().getValor()).toArray())))
				.andExpect(jsonPath("$[*].descricao.dataHora", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getDescricao().getDataHora().format(FORMATTER_DATA_HORA_PT_BR)).toArray())))
				.andExpect(jsonPath("$[*].descricao.estabelecimento", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getDescricao().getEstabelecimento()).toArray())))
				.andExpect(jsonPath("$[*].descricao.nsu", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getDescricao().getNsu()).toArray())))
				.andExpect(jsonPath("$[*].descricao.codigoAutorizacao", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getDescricao().getCodigoAutorizacao()).toArray())))
				.andExpect(jsonPath("$[*].descricao.status", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getDescricao().getStatus().toString()).toArray())))
				.andExpect(jsonPath("$[*].formaPagamento.tipo", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getFormaPagamento().getTipo().toString()).toArray())))
				.andExpect(jsonPath("$[*].formaPagamento.parcelas", containsInAnyOrder(transacoesResponseDto.stream().map(transacaoResponseDto -> transacaoResponseDto.getFormaPagamento().getParcelas()).toArray())));
	}

	@Test
	void QuandoUmEstornoEhSolicitadoEleEhRealizado() throws Exception {

		// Dado
		Long id = 1L;
		TransacaoResponseDto transacaoResponseDto = TransacaoResponseDtoBuilder.toTransacaoResponseDto();
		transacaoResponseDto.getDescricao().setStatus(StatusTransacaoEnum.CANCELADO);

		//When
		when(transacaoService.estornar(id)).thenReturn(transacaoResponseDto);

		// Então
		String jsonResponse = gson.toJson(transacaoResponseDto);
		mockMvc.perform(put("/transacao/v1/estornar/{id}", id).contentType(MediaType.APPLICATION_JSON).content(jsonResponse))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(transacaoResponseDto.getId())))
				.andExpect(jsonPath("$.cartao", is(transacaoResponseDto.getCartao())))
				.andExpect(jsonPath("$.descricao.valor", is(transacaoResponseDto.getDescricao().getValor()))).andExpect(jsonPath("$.descricao.dataHora", is(transacaoResponseDto.getDescricao().getDataHora().format(FORMATTER_DATA_HORA_PT_BR))))
				.andExpect(jsonPath("$.descricao.estabelecimento", is(transacaoResponseDto.getDescricao().getEstabelecimento())))
				.andExpect(jsonPath("$.descricao.nsu", is(transacaoResponseDto.getDescricao().getNsu())))
				.andExpect(jsonPath("$.descricao.codigoAutorizacao", is(transacaoResponseDto.getDescricao().getCodigoAutorizacao())))
				.andExpect(jsonPath("$.descricao.status", is(StatusTransacaoEnum.CANCELADO.toString())))
				.andExpect(jsonPath("$.formaPagamento.tipo", is(transacaoResponseDto.getFormaPagamento().getTipo().toString())))
				.andExpect(jsonPath("$.formaPagamento.parcelas", is(transacaoResponseDto.getFormaPagamento().getParcelas())));

	}

}

