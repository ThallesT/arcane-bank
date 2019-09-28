package com.gtbr.arcanebank.enums;

public enum TipoTransacao {

    E("Entrada"),

    S("Saida");

    private final String tipoTransacao;

    private TipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getValor() {
        return this.toString();
    }

    public String getDescricao() {
        return tipoTransacao;
    }

    public static TipoTransacao[] getValores(){
        return values();
    }
}
