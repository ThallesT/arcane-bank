package com.gtbr.arcanebank.entity;

import javax.persistence.*;

@Entity
@Table(name = "conta", schema = "public")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    @Column(name = "foto")
    private Byte[] foto;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "agencia")
    private Long agencia;

    @Column(name = "conta")
    private Long conta;

    @Column(name = "id_cliente")
    private Long idCliente;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_cliente",insertable=false,updatable=false, foreignKey = @ForeignKey(name="fk_conta_id_cliente"))
    private Cliente cliente;

    @Column(name = "id_cartao")
    private Long idCartao;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_cartao",insertable=false,updatable=false, foreignKey = @ForeignKey(name="fk_conta_id_cartao"))
    private Cartao cartao;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "limite")
    private Double limite;




    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Byte[] getFoto() {
        return foto;
    }

    public void setFoto(Byte[] foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }
}
