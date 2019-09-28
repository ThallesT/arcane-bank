package com.gtbr.arcanebank.repository;

import com.gtbr.arcanebank.entity.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

}
