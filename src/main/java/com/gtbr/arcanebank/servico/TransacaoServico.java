package com.gtbr.arcanebank.servico;

import com.gtbr.arcanebank.crud.ContaCrud;
import com.gtbr.arcanebank.crud.TransacaoCrud;
import com.gtbr.arcanebank.dto.ExtratoDetalhado;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.entity.Transacao;
import com.gtbr.arcanebank.enums.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransacaoServico {

    @Autowired
    private TransacaoCrud transacaoCrud;
    @Autowired
    private ContaCrud contaCrud;


    public Transacao efetuaTransferencia(Long idCliente, Conta contaDestino, Double valor) {
        Conta conta = contaCrud.getContaByIdCliente(idCliente);
        if(valor <= conta.getSaldo()){
            conta.setSaldo( conta.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);
            contaCrud.atualizaConta(conta);
            contaCrud.atualizaConta(contaDestino);
            Transacao transacao = transacaoCrud.insereTransacao(conta.getIdConta(), valor, contaDestino.getIdConta());
            transacao.setContaDestino(contaDestino);
            transacao.setContaOrigem(conta);
            return transacao;
        }else{
            return null;
        }

    }

    public List<ExtratoDetalhado> getListaExtratoDetalhado(Long idCliente) {
        List<Transacao> listaTransacao = transacaoCrud.getListaTransacaoByidCliente(idCliente);
        Conta conta = contaCrud.getContaByIdCliente(idCliente);
        List<ExtratoDetalhado> listaExtratoDetalhado = new ArrayList<>();
        for(Transacao transacao : listaTransacao){
            ExtratoDetalhado extratoDetalhado = getExtratoDetalhado(transacao, conta.getIdConta());
            listaExtratoDetalhado.add(extratoDetalhado);
        }
        return listaExtratoDetalhado;
    }

    public ExtratoDetalhado getExtratoDetalhado(Transacao transacao, Long idConta){
        ExtratoDetalhado extratoDetalhado = new ExtratoDetalhado();
        extratoDetalhado.setTransacao(transacao);
        Long idContaOrigem = transacao.getIdContaOrigem();
        if(idConta.equals(idContaOrigem)){
            extratoDetalhado.setTipoTransacao(TipoTransacao.S);
        }else{
            extratoDetalhado.setTipoTransacao(TipoTransacao.E);
        }
        return extratoDetalhado;
    }
}
