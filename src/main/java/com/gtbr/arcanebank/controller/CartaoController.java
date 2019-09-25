package com.gtbr.arcanebank.controller;

import com.gtbr.arcanebank.crud.CartaoCrud;
import com.gtbr.arcanebank.crud.ContaCrud;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.servico.CartaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartaoController {

    @Autowired
    private ContaCrud contaCrud;
    @Autowired
    private CartaoCrud cartaoCrud;
    @Autowired
    private CartaoServico cartaoServico;

    @RequestMapping("/solicitar-cartao/{idCliente}")
    public String solicitarCartao(@PathVariable("idCliente")Long idCliente){
        Conta conta = contaCrud.getContaByIdCliente(idCliente);
        cartaoServico.cadastraCartao(conta);

        return "redirect:/area-do-cliente";
    }

}
