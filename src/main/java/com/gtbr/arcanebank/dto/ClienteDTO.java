package com.gtbr.arcanebank.dto;

import com.gtbr.arcanebank.entity.Cartao;
import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.entity.Transacao;

import java.util.List;

public class ClienteDTO {

    private Cliente cliente;
    private Conta conta;
    private Cartao cartao;
    private List<Transacao> listaTransacao;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public List<Transacao> getListaTransacao() {
        return listaTransacao;
    }

    public void setListaTransacao(List<Transacao> listaTransacao) {
        this.listaTransacao = listaTransacao;
    }
}
