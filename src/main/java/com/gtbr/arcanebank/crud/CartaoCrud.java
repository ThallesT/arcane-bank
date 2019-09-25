package com.gtbr.arcanebank.crud;

import com.gtbr.arcanebank.entity.Cartao;
import com.gtbr.arcanebank.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class CartaoCrud {

    @Autowired
    private CartaoRepository cartaoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Cartao insereCartao(Cartao cartao){
        return cartaoRepository.save(cartao);
    }
}
