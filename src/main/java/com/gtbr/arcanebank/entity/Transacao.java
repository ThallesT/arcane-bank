package com.gtbr.arcanebank.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transacao", schema = "public")
public class Transacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idTransacao;

    @Column(name = "id_conta_origem")
    private Long idContaOrigem;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_conta_origem",insertable=false,updatable=false, foreignKey = @ForeignKey(name="fk_transacao_id_conta_origem"))
    private Conta contaOrigem;

    @Column(name = "id_conta_destino")
    private Long idContaDestino;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_conta_destino",insertable=false,updatable=false, foreignKey = @ForeignKey(name="fk_transacao_id_conta_destino"))
    private Conta contaDestino;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private Date data;


    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(Long idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public Long getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(Long idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
