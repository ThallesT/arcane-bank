package com.gtbr.arcanebank.controller;

import com.gtbr.arcanebank.crud.TransacaoCrud;
import com.gtbr.arcanebank.dto.ExtratoDetalhado;
import com.gtbr.arcanebank.entity.Transacao;
import com.gtbr.arcanebank.servico.TransacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExtratoController {

    @Autowired
    private TransacaoCrud transacaoCrud;
    @Autowired
    private TransacaoServico transacaoServico;


    @RequestMapping("/extrato")
    public String visualizaExtrato(HttpServletRequest request, Model model){
        Long idCliente = Long.parseLong(request.getSession().getAttribute("usuarioLogado").toString());
        List<ExtratoDetalhado> listaExtratoDetalhado = transacaoServico.getListaExtratoDetalhado(idCliente);

        model.addAttribute("listaExtratoDetalhado", listaExtratoDetalhado);
        return "area-do-cliente/extrato/pagina-inicial-extrato.html";
    }
}
