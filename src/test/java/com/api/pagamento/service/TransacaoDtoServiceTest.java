package com.api.pagamento.service;

import com.api.pagamento.domain.builder.request.transacao.TransacaoRequestDtoBuilder;
import com.api.pagamento.domain.builder.response.transacao.TransacaoResponseDtoBuilder;
import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.domain.dto.request.transacao.TransacaoRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusTransacaoEnum;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import com.api.pagamento.service.validator.transacao.TransacaoValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoDtoServiceTest {

	private final Converter CONVERTER = new Converter(new ModelMapper());

    @InjectMocks
    private TransacaoDtoService transacaoDtoService;
	@Mock
	private TransacaoValidatorService transacaoValidatorService;
	@Mock
	private TransacaoUtilService transacaoUtilService;
	@Mock
	private Converter converter;
	@Mock
	private TransacaoModelService transacaoModelService;


    @Test
    void QuandoUmaTransacaoEhSolicitadaElaDeveSerRealizada(){

        // Dado
		TransacaoRequestDto transacaoRequestDto = TransacaoRequestDtoBuilder.toTransacaoRequestDto();
		TransacaoResponseDto transacaoResponseDtoEsperada = TransacaoResponseDtoBuilder.toTransacaoResponseDto();
		transacaoResponseDtoEsperada.getDescricao().setStatus(StatusTransacaoEnum.AUTORIZADO);

		Transacao transacaoModelNaoSalva = CONVERTER.originToDestiny(transacaoResponseDtoEsperada, Transacao.class);

		//Quando
		when(converter.originToDestiny(transacaoRequestDto, TransacaoResponseDto.class)).thenReturn(transacaoResponseDtoEsperada);
		when(transacaoUtilService.obterNsu()).thenReturn(transacaoResponseDtoEsperada.getDescricao().getNsu());
		when(transacaoUtilService.obterCodigoAutorizacao()).thenReturn(transacaoResponseDtoEsperada.getDescricao().getCodigoAutorizacao());
		when(transacaoUtilService.obterStatusAoPagar()).thenReturn(transacaoResponseDtoEsperada.getDescricao().getStatus());
		when(converter.originToDestiny(transacaoResponseDtoEsperada, Transacao.class)).thenReturn(transacaoModelNaoSalva);
		when(transacaoModelService.salvarTransacao(transacaoModelNaoSalva)).thenReturn(1L);

        // Então
		TransacaoResponseDto transacaoResponseDtoRetornada  = transacaoDtoService.pagar(transacaoRequestDto);
		assertThat(transacaoResponseDtoRetornada.getId(), is(equalTo(transacaoResponseDtoEsperada.getId())));
		assertThat(transacaoResponseDtoRetornada.getCartao(), is(equalTo(transacaoResponseDtoEsperada.getCartao())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getValor(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getValor())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getDataHora(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getDataHora())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getEstabelecimento(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getEstabelecimento())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getNsu(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getNsu())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getNsu(), is(notNullValue()));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getCodigoAutorizacao(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getCodigoAutorizacao())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getCodigoAutorizacao(), is(notNullValue()));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getStatus(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getStatus())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getStatus(), is(StatusTransacaoEnum.AUTORIZADO));
		assertThat(transacaoResponseDtoRetornada.getFormaPagamento().getTipo(), is(equalTo(transacaoResponseDtoEsperada.getFormaPagamento().getTipo())));
		assertThat(transacaoResponseDtoRetornada.getFormaPagamento().getParcelas(), is(equalTo(transacaoResponseDtoEsperada.getFormaPagamento().getParcelas())));
	}

	@Test
    void QuandoUmaTransacaoEhBuscadaPeloIdENaoEhEncontradaUmaExcecaoDeveSerRetornada()  {
        // Dado
        Long id = 1L;

        //Quando
		when(transacaoModelService.buscarTransacao(id)).thenThrow(NotFoundException.class);

        // Então
        assertThrows(NotFoundException.class, () -> transacaoDtoService.buscarTransacao(id));
    }

	@Test
    void QuandoUmPagamentoEhSolicitadoComTipoAvistaEMaisDeUmaParcelaUmaExcecaoDeveSerRetornada()  {

		// Dado
		TransacaoRequestDto transacaoRequestDto = TransacaoRequestDtoBuilder.toTransacaoRequestDto();

		//Quando
		doThrow(BadRequestException.class).when(transacaoValidatorService).validarTipoPagamentoAoPagar(transacaoRequestDto);

        // Então
 		assertThrows(BadRequestException.class, () -> transacaoDtoService.pagar(transacaoRequestDto));
    }

	@Test
	void QuandoUmaTransacaoEhBuscadaPeloIdElaDeveSerRetornada(){

		// Dado
		Long id = 1L;
		TransacaoResponseDto transacaoResponseDtoEsperada = TransacaoResponseDtoBuilder.toTransacaoResponseDto();
		Transacao transacaoModelSalva = CONVERTER.originToDestiny(transacaoResponseDtoEsperada, Transacao.class);

		//When
		when(transacaoModelService.buscarTransacao(id)).thenReturn(transacaoModelSalva);
		when(converter.originToDestiny(transacaoModelSalva, TransacaoResponseDto.class)).thenReturn(transacaoResponseDtoEsperada);

		// Então
		TransacaoResponseDto transacaoResponseDtoRetornada  = transacaoDtoService.buscarTransacao(id);
		assertThat(transacaoResponseDtoRetornada.getId(), is(equalTo(transacaoResponseDtoEsperada.getId())));
		assertThat(transacaoResponseDtoRetornada.getCartao(), is(equalTo(transacaoResponseDtoEsperada.getCartao())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getValor(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getValor())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getDataHora(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getDataHora())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getEstabelecimento(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getEstabelecimento())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getNsu(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getNsu())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getCodigoAutorizacao(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getCodigoAutorizacao())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getStatus(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getStatus())));
		assertThat(transacaoResponseDtoRetornada.getFormaPagamento().getTipo(), is(equalTo(transacaoResponseDtoEsperada.getFormaPagamento().getTipo())));
		assertThat(transacaoResponseDtoRetornada.getFormaPagamento().getParcelas(), is(equalTo(transacaoResponseDtoEsperada.getFormaPagamento().getParcelas())));

	}

	//Quando a transacao não é informada, todas as transações são retornadas
	@Test
	void QuandoTransacoesSaoBuscadasElasDevemSerRetornadas() {

		//Dado
		List<TransacaoResponseDto> transacoesResponseDtosEsperadas = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			transacoesResponseDtosEsperadas.add(TransacaoResponseDtoBuilder.toTransacaoResponseDto());
		}
		List<Transacao> transacoes = CONVERTER.originToDestiny(transacoesResponseDtosEsperadas, Transacao.class);

		//Quando
		when(transacaoModelService.listarTranscacoes()).thenReturn(transacoes);
		when(converter.originToDestiny(transacoes, TransacaoResponseDto.class)).thenReturn(transacoesResponseDtosEsperadas);

		//Então
		List<TransacaoResponseDto> transacoesResponseDtosRetornadas = transacaoDtoService.listarTransacoes();
		for (int i = 0; i < transacoes.size(); i++) {
			assertThat(transacoesResponseDtosRetornadas.get(i).getId(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getId())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getCartao(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getCartao())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getDescricao().getValor(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getDescricao().getValor())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getDescricao().getDataHora(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getDescricao().getDataHora())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getDescricao().getEstabelecimento(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getDescricao().getEstabelecimento())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getDescricao().getNsu(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getDescricao().getNsu())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getDescricao().getCodigoAutorizacao(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getDescricao().getCodigoAutorizacao())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getDescricao().getStatus(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getDescricao().getStatus())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getFormaPagamento().getTipo(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getFormaPagamento().getTipo())));
			assertThat(transacoesResponseDtosRetornadas.get(i).getFormaPagamento().getParcelas(), is(equalTo(transacoesResponseDtosEsperadas.get(i).getFormaPagamento().getParcelas())));

		}

	}
	@Test
    void QuandoUmEstornoEhSolicitadoEleEhRealizado(){
        // Dado
		Long id = 1L;
		TransacaoResponseDto transacaoResponseDtoEsperada = TransacaoResponseDtoBuilder.toTransacaoResponseDto();
		transacaoResponseDtoEsperada.getDescricao().setStatus(StatusTransacaoEnum.NEGADO);
		Transacao transacaoModelNaoSalva = CONVERTER.originToDestiny(transacaoResponseDtoEsperada, Transacao.class);

		// Quando
		when(transacaoModelService.buscarTransacao(id)).thenReturn(transacaoModelNaoSalva);
		when(converter.originToDestiny(transacaoModelNaoSalva, TransacaoResponseDto.class)).thenReturn(transacaoResponseDtoEsperada);

        // Então
		TransacaoResponseDto transacaoResponseDtoRetornada  = transacaoDtoService.estornar(id);
		assertThat(transacaoResponseDtoRetornada.getId(), is(equalTo(transacaoResponseDtoEsperada.getId())));
		assertThat(transacaoResponseDtoRetornada.getCartao(), is(equalTo(transacaoResponseDtoEsperada.getCartao())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getValor(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getValor())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getDataHora(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getDataHora())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getEstabelecimento(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getEstabelecimento())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getNsu(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getNsu())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getCodigoAutorizacao(), is(equalTo(transacaoResponseDtoEsperada.getDescricao().getCodigoAutorizacao())));
		assertThat(transacaoResponseDtoRetornada.getDescricao().getStatus(), is(StatusTransacaoEnum.NEGADO));
		assertThat(transacaoResponseDtoRetornada.getFormaPagamento().getTipo(), is(equalTo(transacaoResponseDtoEsperada.getFormaPagamento().getTipo())));
		assertThat(transacaoResponseDtoRetornada.getFormaPagamento().getParcelas(), is(equalTo(transacaoResponseDtoEsperada.getFormaPagamento().getParcelas())));
	}

}


