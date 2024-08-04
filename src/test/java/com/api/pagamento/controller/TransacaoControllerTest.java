package com.api.pagamento.controller;

import com.api.pagamento.config.gson.LocalDateTimePtbrAdapter;
import com.api.pagamento.controller.transacao.TransacaoController;
import com.api.pagamento.domain.builder.request.transacao.TransacaoRequestDtoBuilder;
import com.api.pagamento.domain.builder.response.transacao.TransacaoResponseDtoBuilder;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
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

import java.time.LocalDateTime;

import static com.api.pagamento.domain.constant.utils.pattern.PatternConstants.FORMATTER_DATA_HORA_PT_BR;
import static java.lang.System.exit;
import static org.hamcrest.core.Is.is;
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
		mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
	}

	@Test
	void QuandoOPagarEhChamadoEleDeveSerExecutado() throws Exception {

		//Dado
		TransacaoRequestDto transacaoRequestDto = TransacaoRequestDtoBuilder.toTransacaoRequestDto();
		TransacaoResponseDto transacaoResponseDto = TransacaoResponseDtoBuilder.toTransacaoResponseDto();

		//Quando
		when(transacaoService.pagar(transacaoRequestDto)).thenReturn(transacaoResponseDto);

		// Então
		String jsonResponse = gson.toJson(transacaoResponseDto);
		mockMvc.perform(post("/transacao/v1/pagar").contentType(MediaType.APPLICATION_JSON).content(jsonResponse))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(transacaoResponseDto.getId())))
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
	/*
    // Quando uma transacao é chamada pelo id e não é encontrada, uma exceção deve ser retornada
    @Test
    void whenTransactionIsCalledByIdAndNotFoundThenAnExceptionIsReturned() throws Exception {

        // Dado

            Long id = 1L;

        //When

            //transacaoService.procurarPeloId(id) -> TransacaoInexistenteException()
            when(transacaoService.procurarPeloId(id))
                    .thenThrow(TransacaoInexistenteException.class);

        // Então

            //perform: Executa o get /transacao/v1/1
            //contentType: Define que o tipo do conteúdo é JSON
            //andExpect: Espera-se que o post retorne o status BadRequest

            mockMvc.perform(get("/transacao/v1/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof TransacaoInexistenteException))
        ;

    }

    // Quando um pagamento não é chamado com todos os campos obrigatórios, uma exceção deve ser retornada
    @Test
    void whenPaymentWithoutAllFieldsIsCalledThenAnExceptionIsReturned() throws Exception {

        // Dado

        //Gera um TransacaoDTO
        TransacaoDTO transacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

        transacaoDTO.setCartao(null);
        transacaoDTO.getDescricao().setEstabelecimento(null);

        //Tranforma o TransacaoDTO em um Transacao
        Transacao transacao = (Transacao) ModelMapperUtilService.convert(transacaoDTO, Transacao.class);

        transacao.setId(null);
        transacao.getDescricao().setId(null);
        transacao.getFormaPagamento().setId(null);

        //When

        //transacao for inválido
        //A anotação @Valid beerDTO presente no metódo createBeer que realiza tal verificação

        // Então

        //perform: Executa o post /transacao/v1/pagamento
        //contentType: Define que o tipo do conteúdo é JSON
        //andExpect: Espera-se que o post retorne o status BadRequest

        mockMvc.perform(post("/transacao/v1/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(transacao)))
                .andExpect(status().isBadRequest());

    }

    // Quando estorno é chamado pelo id, o estorno é retornado
    @Test
    void whenReversalCallByIdThenReversalIsReturned() throws Exception {

        // Dado

        //Gera um TransacaoDTO
        TransacaoDTO transacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

        transacaoDTO.getDescricao().setNsu("1234567890");
        transacaoDTO.getDescricao().setCodigoAutorizacao("147258369");
        transacaoDTO.getDescricao().setStatus(StatusEnum.NEGADO);

        //When

        //transacaoService.estornar() -> transacaoDTO
        when(transacaoService.estornar(1L))
                .thenReturn(transacaoDTO);

        // Então

        //perform: Executa o post /transacao/v1/pagamento
        //contentType: Define que o tipo do conteúdo é JSON
        //content: Define que o conteúdo é o Json de transacaoDTO
        //andExpect: Espera-se que o post retorne o status Ok

        mockMvc.perform(put("/transacao/v1/estorno/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(transacaoDTO.getId()))))
                .andExpect(jsonPath("$.cartao", is(transacaoDTO.getCartao())))
                .andExpect(jsonPath("$.descricao.valor", is(transacaoDTO.getDescricao().getValor())))
                .andExpect(jsonPath("$.descricao.dataHora", is(transacaoDTO.getDescricao().getDataHora())))
                .andExpect(jsonPath("$.descricao.estabelecimento", is(transacaoDTO.getDescricao().getEstabelecimento())))
                .andExpect(jsonPath("$.descricao.nsu", is(transacaoDTO.getDescricao().getNsu())))
                .andExpect(jsonPath("$.descricao.codigoAutorizacao", is(transacaoDTO.getDescricao().getCodigoAutorizacao())))
                .andExpect(jsonPath("$.descricao.status", is(transacaoDTO.getDescricao().getStatus().toString())))
                .andExpect(jsonPath("$.formaPagamento.tipo", is(transacaoDTO.getFormaPagamento().getTipo().toString())))
                .andExpect(jsonPath("$.formaPagamento.parcelas", is(transacaoDTO.getFormaPagamento().getParcelas())));

    }

    //Quando a transacao é chamada pelo id, a transação é retornada
    @Test
    void whenTransactionByIdIsCalledThenIsReturned() throws Exception {

        //Dado

        //Gera um TransacaoDTO
        TransacaoDTO transacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

        transacaoDTO.getDescricao().setNsu("1234567890");
        transacaoDTO.getDescricao().setCodigoAutorizacao("147258369");
        transacaoDTO.getDescricao().setStatus(StatusEnum.AUTORIZADO);

        //Quando

        //transacaoService.procurarPeloId(1) -> transacaoDTO
        when(transacaoService.procurarPeloId(1L))
                .thenReturn(transacaoDTO);

        // Então

        //perform: Executa o post /transacao/v1/pagamento
        //contentType: Define que o tipo do conteúdo é JSON
        //content: Define que o conteúdo é o Json de transacaoDTO
        //andExpect: Espera-se que o post retorne o status OK
        //andExpect: Espera-se que $.id seja igual a transacaoDTO.getId()


        mockMvc.perform(get("/transacao/v1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(transacaoDTO.getId()))))
                .andExpect(jsonPath("$.cartao", is(transacaoDTO.getCartao())))
                .andExpect(jsonPath("$.descricao.valor", is(transacaoDTO.getDescricao().getValor())))
                .andExpect(jsonPath("$.descricao.dataHora", is(transacaoDTO.getDescricao().getDataHora())))
                .andExpect(jsonPath("$.descricao.estabelecimento", is(transacaoDTO.getDescricao().getEstabelecimento())))
                .andExpect(jsonPath("$.descricao.nsu", is(transacaoDTO.getDescricao().getNsu())))
                .andExpect(jsonPath("$.descricao.codigoAutorizacao", is(transacaoDTO.getDescricao().getCodigoAutorizacao())))
                .andExpect(jsonPath("$.descricao.status", is(transacaoDTO.getDescricao().getStatus().toString())))
                .andExpect(jsonPath("$.formaPagamento.tipo", is(transacaoDTO.getFormaPagamento().getTipo().toString())))
                .andExpect(jsonPath("$.formaPagamento.parcelas", is(transacaoDTO.getFormaPagamento().getParcelas())));

    }

    //Quando a transacao é chamada, todas as transações são retornadas
    @Test
    void whenTransactionIsCalledThenAllIsReturned() throws Exception {

        //Dado

            //Gera um TransacaoDTO
            TransacaoDTO transacaoDTO1 = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

            transacaoDTO1.getDescricao().setNsu("1234567890");
            transacaoDTO1.getDescricao().setCodigoAutorizacao("147258369");
            transacaoDTO1.getDescricao().setStatus(StatusEnum.AUTORIZADO);

            //Gera um TransacaoDTO
            TransacaoDTO transacaoDTO2 = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

            transacaoDTO2.getDescricao().setNsu("1234567890");
            transacaoDTO2.getDescricao().setCodigoAutorizacao("147258369");
            transacaoDTO2.getDescricao().setStatus(StatusEnum.AUTORIZADO);

            List<TransacaoDTO> transacaoDTOList = new ArrayList<>();
            transacaoDTOList.add(transacaoDTO1);
            transacaoDTOList.add(transacaoDTO2);

        //Quando

            //transacaoService.procurarTodos() -> transacaoDTOList
            when(transacaoService.procurarTodos())
                    .thenReturn(transacaoDTOList);

        // Então

            //perform: Executa o post /transacao/v1/pagamento
            //contentType: Define que o tipo do conteúdo é JSON
            //content: Define que o conteúdo é o Json de transacaoDTO
            //andExpect: Espera-se que o post retorne o status OK
            //andExpect: Espera-se que $.id seja igual a transacaoDTO.getId()

           mockMvc.perform(get("/transacao/v1")
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].id", is(Math.toIntExact(transacaoDTO1.getId()))))
                    .andExpect(jsonPath("$.[0].cartao", is(transacaoDTO1.getCartao())))
                    .andExpect(jsonPath("$.[0].descricao.valor", is(transacaoDTO1.getDescricao().getValor())))
                    .andExpect(jsonPath("$.[0].descricao.dataHora", is(transacaoDTO1.getDescricao().getDataHora())))
                    .andExpect(jsonPath("$.[0].descricao.estabelecimento", is(transacaoDTO1.getDescricao().getEstabelecimento())))
                    .andExpect(jsonPath("$.[0].descricao.nsu", is(transacaoDTO1.getDescricao().getNsu())))
                    .andExpect(jsonPath("$.[0].descricao.codigoAutorizacao", is(transacaoDTO1.getDescricao().getCodigoAutorizacao())))
                    .andExpect(jsonPath("$.[0].descricao.status", is(transacaoDTO1.getDescricao().getStatus().toString())))
                    .andExpect(jsonPath("$.[0].formaPagamento.tipo", is(transacaoDTO1.getFormaPagamento().getTipo().toString())))
                    .andExpect(jsonPath("$.[0].formaPagamento.parcelas", is(transacaoDTO1.getFormaPagamento().getParcelas())))
                    .andExpect(jsonPath("$.[1].id", is(Math.toIntExact(transacaoDTO2.getId()))))
                    .andExpect(jsonPath("$.[1].cartao", is(transacaoDTO2.getCartao())))
                    .andExpect(jsonPath("$.[1].descricao.valor", is(transacaoDTO2.getDescricao().getValor())))
                    .andExpect(jsonPath("$.[1].descricao.dataHora", is(transacaoDTO2.getDescricao().getDataHora())))
                    .andExpect(jsonPath("$.[1].descricao.estabelecimento", is(transacaoDTO2.getDescricao().getEstabelecimento())))
                    .andExpect(jsonPath("$.[1].descricao.nsu", is(transacaoDTO2.getDescricao().getNsu())))
                    .andExpect(jsonPath("$.[1].descricao.codigoAutorizacao", is(transacaoDTO2.getDescricao().getCodigoAutorizacao())))
                    .andExpect(jsonPath("$.[1].descricao.status", is(transacaoDTO2.getDescricao().getStatus().toString())))
                    .andExpect(jsonPath("$.[1].formaPagamento.tipo", is(transacaoDTO2.getFormaPagamento().getTipo().toString())))
                    .andExpect(jsonPath("$.[1].formaPagamento.parcelas", is(transacaoDTO2.getFormaPagamento().getParcelas())));
    }

	 */
}
