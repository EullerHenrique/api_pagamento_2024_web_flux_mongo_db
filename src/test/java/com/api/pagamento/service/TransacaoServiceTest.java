/*
package com.api.pagamento.service;

import com.api.pagamento.builder.TransacaoDTOBuilder;
import com.api.pagamento.domain.dto.model_to_dto.transacao.TransacaoDTO;
import com.api.pagamento.domain.enumeration.transacao.descricao.StatusEnum;
import com.api.pagamento.domain.exception.transacao.InsercaoNaoPermitidaException;
import com.api.pagamento.domain.exception.transacao.TransacaoInexistenteException;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.repository.transacao.descricao.DescricaoRepository;
import com.api.pagamento.repository.transacao.TransacaoRepository;
import com.api.pagamento.service.dto.transacao.TransacaoDtoServiceImp;
import com.api.pagamento.service.util.ModelMapperUtilService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private DescricaoRepository descricaoRepository;

    @InjectMocks
    private TransacaoDtoServiceImp transacaoService;


    @Test
    void whenPaymentInformedThenItShouldBeCreated() throws InsercaoNaoPermitidaException {

        // Dado

            //Gera um TransacaoDTO
            TransacaoDTO expectedTransacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

            //Tranforma o TransacaoDTO em um Transacao
            Transacao transacao = (Transacao) ModelMapperUtilService.convert(expectedTransacaoDTO, Transacao.class);

            transacao.setId(null);
            transacao.getDescricao().setId(null);
            transacao.getFormaPagamento().setId(null);

        //Quando

            //transacaoService.save( -> transacaoRepository.save(expectedTransacao) -> expectedTransacao
            when(transacaoRepository
                    .save(transacao))
                    .thenReturn((Transacao) ModelMapperUtilService.convert(expectedTransacaoDTO,Transacao.class));

        // Então

            //Cria um TransacaoDTO
            TransacaoDTO createdTransacaoDTO  = transacaoService.pagar(transacao);

            //Verifica se o atributo id do createdTransacaoDTO é igual ao atributo id do expectedDTO
            assertThat(createdTransacaoDTO.getId(), is(equalTo(expectedTransacaoDTO.getId())));
            assertThat(createdTransacaoDTO.getCartao(), is(equalTo(expectedTransacaoDTO.getCartao())));
            assertThat(createdTransacaoDTO.getDescricao().getId(), is(equalTo(expectedTransacaoDTO.getDescricao().getId())));
            assertThat(createdTransacaoDTO.getDescricao().getValor(), is(equalTo(expectedTransacaoDTO.getDescricao().getValor())));
            assertThat(createdTransacaoDTO.getDescricao().getDataHora(), is(equalTo(expectedTransacaoDTO.getDescricao().getDataHora())));
            assertThat(createdTransacaoDTO.getDescricao().getEstabelecimento(), is(equalTo(expectedTransacaoDTO.getDescricao().getEstabelecimento())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getId(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getId())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getTipo(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getTipo())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getParcelas(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getParcelas())));

    }

    // Quando o nsu, codigo_pagamento ou o status é informado, uma exceção deve ser lançada
    @Test
    void whenIdsNsuCodPagStatusInformedThenAnExceptionShouldBeThrown() {

        // Dado

            //Gera um TransacaoDTO
            TransacaoDTO transacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

            //Tranforma o TransacaoDTO em um Transacao
            Transacao transacao = (Transacao) ModelMapperUtilService.convert(transacaoDTO, Transacao.class);

            transacao.setId(null);
            transacao.getDescricao().setId(null);
            transacao.getFormaPagamento().setId(null);

        //Quando

            transacao.getDescricao().setNsu("1234567890");
            transacao.getDescricao().setCodigoAutorizacao("147258369");
            transacao.getDescricao().setStatus(StatusEnum.AUTORIZADO);

        // Então

            //Verifica se transacaoService.pagar(transacao) lançou a exceção InsercaoNaoPermitidaException.class
            assertThrows(InsercaoNaoPermitidaException.class, () -> transacaoService.pagar(transacao));

    }

    // Quando uma transacao é informada pelo id e não é encontrada, uma exceção deve ser retornada
    @Test
    void whenTransactionIsInformedByIdAndNotFoundThenAnExceptionIsReturned()  {
        // Dado

        Long id = 1L;

        //Quando

        //transacaoRepository.procurarPeloId(id) -> TransacaoInexistenteException

        // Então

        //Verifica se transacaoService.procurarPeloId(id) lançou a exceção TransacaoInexistenteException.class
        assertThrows(TransacaoInexistenteException.class, () -> transacaoService.procurarPeloId(id));

    }

    // Quando um pagamento não é informado com todos os campos obrigatórios, uma exceção deve ser retornada
    @Test
    void whenPaymentWithoutAllFieldsIsInformedThenAnExceptionIsReturned()  {
        // Dado

            //Gera um TransacaoDTO
            TransacaoDTO transacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

            //Tranforma o TransacaoDTO em um Transacao
            Transacao transacao = (Transacao) ModelMapperUtilService.convert(transacaoDTO, Transacao.class);

            transacao.setId(null);
            transacao.getDescricao().setId(null);
            transacao.getFormaPagamento().setId(null);


        //Quando

        //transacaoRepository.save(transacao) -> ConstraintViolationException
            when(transacaoRepository.save(transacao))
                   .thenThrow(ConstraintViolationException.class);

        // Então

             //Verifica se transacaoService.pagar(transacao) lançou a exceção ConstraintViolationException.class
            assertThrows(ConstraintViolationException.class, () -> transacaoService.pagar(transacao));

    }

    // Quando estorno é chamado pelo id, o estorno é retornado
    @Test
    void whenReversalInformedByIdThenReversalIsReturned() throws Exception {

        // Dado

        Long id = 1L;

        //Gera um TransacaoDTO
        TransacaoDTO expectedTransacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

        expectedTransacaoDTO.getDescricao().setNsu("1234567890");
        expectedTransacaoDTO.getDescricao().setCodigoAutorizacao("147258369");
        expectedTransacaoDTO.getDescricao().setStatus(StatusEnum.NEGADO);

        //Tranforma o TransacaoDTO em um Transacao
        Transacao transacao = (Transacao) ModelMapperUtilService.convert(expectedTransacaoDTO, Transacao.class);

        //When

        //transacaoService.findById(id) -> transacao
        when(transacaoRepository.findById(id))
                .thenReturn(Optional.ofNullable(transacao));

        // Então

          //Cria um TransacaoDTO
            TransacaoDTO createdTransacaoDTO  = transacaoService.estornar(id);

            //Verifica se o atributo id do createdTransacaoDTO é igual ao atributo id do expectedDTO
            assertThat(createdTransacaoDTO.getId(), is(equalTo(expectedTransacaoDTO.getId())));
            assertThat(createdTransacaoDTO.getCartao(), is(equalTo(expectedTransacaoDTO.getCartao())));
            assertThat(createdTransacaoDTO.getDescricao().getId(), is(equalTo(expectedTransacaoDTO.getDescricao().getId())));
            assertThat(createdTransacaoDTO.getDescricao().getValor(), is(equalTo(expectedTransacaoDTO.getDescricao().getValor())));
            assertThat(createdTransacaoDTO.getDescricao().getDataHora(), is(equalTo(expectedTransacaoDTO.getDescricao().getDataHora())));
            assertThat(createdTransacaoDTO.getDescricao().getEstabelecimento(), is(equalTo(expectedTransacaoDTO.getDescricao().getEstabelecimento())));
            assertThat(createdTransacaoDTO.getDescricao().getNsu(), is(equalTo(expectedTransacaoDTO.getDescricao().getNsu())));
            assertThat(createdTransacaoDTO.getDescricao().getCodigoAutorizacao(), is(equalTo(expectedTransacaoDTO.getDescricao().getCodigoAutorizacao())));
            assertThat(createdTransacaoDTO.getDescricao().getStatus(), is(equalTo(expectedTransacaoDTO.getDescricao().getStatus())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getId(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getId())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getTipo(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getTipo())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getParcelas(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getParcelas())));

    }

    //Quando a transacao é informada pelo id, a transação é retornada
    @Test
    void whenTransactionByIdIsInformedThenIsReturned() throws Exception {

        // Dado

        Long id = 1L;

        //Gera um TransacaoDTO
        TransacaoDTO expectedTransacaoDTO = TransacaoDTOBuilder.builder().build().toTransacaoDTO();


        expectedTransacaoDTO.getDescricao().setNsu("1234567890");
        expectedTransacaoDTO.getDescricao().setCodigoAutorizacao("147258369");
        expectedTransacaoDTO.getDescricao().setStatus(StatusEnum.AUTORIZADO);

        //Tranforma o TransacaoDTO em um Transacao
        Transacao transacao = (Transacao) ModelMapperUtilService.convert(expectedTransacaoDTO, Transacao.class);

        //When

        //transacaoService.findById(id) -> transacao
        when(transacaoRepository.findById(id))
                .thenReturn(Optional.ofNullable(transacao));

        // Então

            //Cria um TransacaoDTO
            TransacaoDTO createdTransacaoDTO  = transacaoService.procurarPeloId(id);

            //Verifica se o atributo id do createdTransacaoDTO é igual ao atributo id do expectedDTO
            assertThat(createdTransacaoDTO.getId(), is(equalTo(expectedTransacaoDTO.getId())));
            assertThat(createdTransacaoDTO.getCartao(), is(equalTo(expectedTransacaoDTO.getCartao())));
            assertThat(createdTransacaoDTO.getDescricao().getId(), is(equalTo(expectedTransacaoDTO.getDescricao().getId())));
            assertThat(createdTransacaoDTO.getDescricao().getValor(), is(equalTo(expectedTransacaoDTO.getDescricao().getValor())));
            assertThat(createdTransacaoDTO.getDescricao().getDataHora(), is(equalTo(expectedTransacaoDTO.getDescricao().getDataHora())));
            assertThat(createdTransacaoDTO.getDescricao().getEstabelecimento(), is(equalTo(expectedTransacaoDTO.getDescricao().getEstabelecimento())));
            assertThat(createdTransacaoDTO.getDescricao().getNsu(), is(equalTo(expectedTransacaoDTO.getDescricao().getNsu())));
            assertThat(createdTransacaoDTO.getDescricao().getCodigoAutorizacao(), is(equalTo(expectedTransacaoDTO.getDescricao().getCodigoAutorizacao())));
            assertThat(createdTransacaoDTO.getDescricao().getStatus(), is(equalTo(expectedTransacaoDTO.getDescricao().getStatus())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getId(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getId())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getTipo(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getTipo())));
            assertThat(createdTransacaoDTO.getFormaPagamento().getParcelas(), is(equalTo(expectedTransacaoDTO.getFormaPagamento().getParcelas())));

    }

    //Quando a transacao não é informada, todas as transações são retornadas
    @Test
    void whenTransactionIsCalledThenAllIsReturned() throws Exception {

        //Dado

        //Gera um TransacaoDTO
        TransacaoDTO transacaoDTO1 = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

        transacaoDTO1.getDescricao().setNsu("1234567890");
        transacaoDTO1.getDescricao().setCodigoAutorizacao("147258369");
        transacaoDTO1.getDescricao().setStatus(StatusEnum.AUTORIZADO);

        //Tranforma o TransacaoDTO em um Transacao
        Transacao transacao1 = (Transacao) ModelMapperUtilService.convert(transacaoDTO1, Transacao.class);

        //Gera um TransacaoDTO
        TransacaoDTO transacaoDTO2 = TransacaoDTOBuilder.builder().build().toTransacaoDTO();

        transacaoDTO2.getDescricao().setNsu("1234567890");
        transacaoDTO2.getDescricao().setCodigoAutorizacao("147258369");
        transacaoDTO2.getDescricao().setStatus(StatusEnum.AUTORIZADO);

        //Tranforma o TransacaoDTO em um Transacao
        Transacao transacao2 = (Transacao) ModelMapperUtilService.convert(transacaoDTO2, Transacao.class);

        List<TransacaoDTO> transacaoDTOList = new ArrayList<>();
        transacaoDTOList.add(transacaoDTO1);
        transacaoDTOList.add(transacaoDTO2);

        List<Transacao> transacaoList = new ArrayList<>();
        transacaoList.add(transacao1);
        transacaoList.add(transacao2);

        //Quando

        //transacaoService.procurarTodos() -> transacaoDTOList
        when(transacaoRepository.findAll())
                .thenReturn(transacaoList);


        //Cria um TransacaoDTO
        List<TransacaoDTO> createdTransacaoDTOList  = transacaoService.procurarTodos();

        //Verifica se o atributo id do createdTransacaoDTO é igual ao atributo id do expectedDTO

        for(int i=0; i < transacaoDTOList.size(); i++){
            assertThat(createdTransacaoDTOList.get(i).getId(), is(equalTo(transacaoDTOList.get(i).getId())));
            assertThat(createdTransacaoDTOList.get(i).getCartao(), is(equalTo(transacaoDTOList.get(i).getCartao())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getId(), is(equalTo(transacaoDTOList.get(i).getDescricao().getId())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getValor(), is(equalTo(transacaoDTOList.get(i).getDescricao().getValor())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getDataHora(), is(equalTo(transacaoDTOList.get(i).getDescricao().getDataHora())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getEstabelecimento(), is(equalTo(transacaoDTOList.get(i).getDescricao().getEstabelecimento())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getNsu(), is(equalTo(transacaoDTOList.get(i).getDescricao().getNsu())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getCodigoAutorizacao(), is(equalTo(transacaoDTOList.get(i).getDescricao().getCodigoAutorizacao())));
            assertThat(createdTransacaoDTOList.get(i).getDescricao().getStatus(), is(equalTo(transacaoDTOList.get(i).getDescricao().getStatus())));
            assertThat(createdTransacaoDTOList.get(i).getFormaPagamento().getId(), is(equalTo(transacaoDTOList.get(i).getFormaPagamento().getId())));
            assertThat(createdTransacaoDTOList.get(i).getFormaPagamento().getTipo(), is(equalTo(transacaoDTOList.get(i).getFormaPagamento().getTipo())));
            assertThat(createdTransacaoDTOList.get(i).getFormaPagamento().getParcelas(), is(equalTo(transacaoDTOList.get(i).getFormaPagamento().getParcelas())));

        }

    }

}
*/