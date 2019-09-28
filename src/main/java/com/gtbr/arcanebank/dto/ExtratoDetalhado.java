package com.gtbr.arcanebank.dto;

import com.gtbr.arcanebank.entity.Transacao;
import com.gtbr.arcanebank.enums.TipoTransacao;

import java.util.List;

public class ExtratoDetalhado {

    private Transacao transacao;
    private TipoTransacao tipoTransacao;

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
