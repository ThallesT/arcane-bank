package com.gtbr.arcanebank.dto;

import com.gtbr.arcanebank.entity.Cartao;
import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.entity.Transacao;

import java.util.List;

public class ContaDTO {

    private Conta conta;
    private List<Transacao> listaTransacao;



    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Transacao> getListaTransacao() {
        return listaTransacao;
    }

    public void setListaTransacao(List<Transacao> listaTransacao) {
        this.listaTransacao = listaTransacao;
    }
}
