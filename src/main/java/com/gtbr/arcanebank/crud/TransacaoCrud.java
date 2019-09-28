package com.gtbr.arcanebank.crud;

import com.gtbr.arcanebank.entity.Transacao;
import com.gtbr.arcanebank.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Component
public class TransacaoCrud {


    @Autowired
    private TransacaoRepository transacaoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Transacao insereTransacao(Long idConta, Double valor, Long idConta1) {
        Transacao transacao = new Transacao();
        transacao.setIdContaOrigem(idConta);
        transacao.setIdContaDestino(idConta1);
        transacao.setData(new Date());
        transacao.setValor(valor);

        return transacaoRepository.save(transacao);

    }

    public List<Transacao> getListaTransacaoByidCliente(Long idCliente){
        List<Transacao> listaTransacao = entityManager.createQuery("SELECT t FROM Transacao t WHERE t.contaOrigem.idCliente = :idCliente OR t.contaDestino.idCliente = :idCliente ORDER BY t.idTransacao DESC").setParameter("idCliente", idCliente).getResultList();
        return listaTransacao;
    }
}
