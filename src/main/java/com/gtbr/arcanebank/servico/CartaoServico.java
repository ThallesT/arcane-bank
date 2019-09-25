package com.gtbr.arcanebank.servico;

import com.gtbr.arcanebank.controller.CartaoController;
import com.gtbr.arcanebank.crud.CartaoCrud;
import com.gtbr.arcanebank.crud.ContaCrud;
import com.gtbr.arcanebank.entity.Cartao;
import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.entity.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

@Component
public class CartaoServico {

    @Autowired
    private CartaoCrud cartaoCrud;
    @Autowired
    private ContaCrud contaCrud;
    @PersistenceContext
    private EntityManager entityManager;

    public void cadastraCartao(Conta conta){
        if(conta.getCartao() == null){
            Cartao cartao = cartaoCrud.insereCartao(geraCartao(conta));
            conta.setIdCartao(cartao.getIdCartao());
            contaCrud.atualizaConta(conta);
        }

    }


    public Cartao geraCartao(Conta conta){
        Cartao cartao = new Cartao();
        String strNumerosCartao = "5220";
        for(int a = 0 ; a < 12; a++){
            Random random = new Random();
            strNumerosCartao += random.nextInt(9);
        }
        String cvv = "";
        for(int a = 0 ; a < 3; a++){
            Random random = new Random();
            int numero = random.nextInt(9);
            if(numero == 0) cvv += numero+1;
            cvv += numero;

        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2026, 11, 10);
        cartao.setCvv(Long.parseLong(cvv));
        cartao.setNomeImpresso(conta.getCliente().getNome() + " " + conta.getCliente().getSobrenome());
        cartao.setDataDaEmissao(new Date());
        cartao.setDataDeVencimento(gregorianCalendar.getTime());
        cartao.setNumero(strNumerosCartao);

        return cartao;
    }

}
