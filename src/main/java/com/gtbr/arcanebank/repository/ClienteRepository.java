package com.gtbr.arcanebank.repository;

import com.gtbr.arcanebank.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente getByIdCliente(Long idCliente);

    Cliente getClienteByCpf(String cpf);

}
