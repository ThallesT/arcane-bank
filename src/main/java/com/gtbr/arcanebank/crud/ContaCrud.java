package com.gtbr.arcanebank.crud;

import com.gtbr.arcanebank.entity.Cartao;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ContaCrud {

    @Autowired
    private ContaRepository contaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Conta insereConta(Long numeroConta, Long agencia, Long idCliente, String senha){
        try {
            Conta conta = (Conta) entityManager.createQuery("SELECT c FROM Conta c WHERE c.conta = :conta").setParameter("conta", numeroConta).getResultList().get(0);
            return conta;
        }catch(IndexOutOfBoundsException e){
            Conta conta = new Conta();
            conta.setAgencia(agencia);
            conta.setSaldo(0.0);
            conta.setIdCliente(idCliente);
            conta.setSenha(senha);
            conta.setIdConta(numeroConta);
            conta.setFoto(null);
            conta.setIdCartao(null);

            return contaRepository.save(conta);

        }catch (Exception e){
            return null;
        }
    }

    public Conta getContaByNumeroConta(Long numeroConta){
        try {
            Conta conta = (Conta) entityManager.createQuery("SELECT c FROM Conta c WHERE c.conta = :conta").setParameter("conta", numeroConta).getResultList().get(0);
            return conta;
        }catch(Exception e){
            return null;
        }
    }

    public Conta getContaByIdCliente(Long idCliente) {
        return contaRepository.getContaByIdCliente(idCliente);

    }
}
