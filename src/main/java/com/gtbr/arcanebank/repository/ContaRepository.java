package com.gtbr.arcanebank.repository;


import com.gtbr.arcanebank.crud.Crud;
import com.gtbr.arcanebank.entity.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<Conta, Long> {

    Conta getContaByIdCliente(Long idCliente);

}
