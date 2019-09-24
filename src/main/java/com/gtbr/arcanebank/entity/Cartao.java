package com.gtbr.arcanebank.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cartao", schema = "public")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long idCartao;
    @Column(name = "numero")
    private String numero;
    @Column(name = "nome_impresso")
    private String nomeImpresso;
    @Column(name = "cvv")
    private Long cvv;
    @Column(name = "data_da_emissao")
    private Date dataDaEmissao;
    @Column(name = "data_de_vencimento")
    private Date dataDeVencimento;

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCvv(Long cvv) {
        this.cvv = cvv;
    }

    public Date getDataDaEmissao() {
        return dataDaEmissao;
    }

    public void setDataDaEmissao(Date dataDaEmissao) {
        this.dataDaEmissao = dataDaEmissao;
    }

    public Date getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(Date dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }
}
