package com.gtbr.arcanebank.controller;

import com.gtbr.arcanebank.crud.ClienteCrud;
import com.gtbr.arcanebank.crud.ContaCrud;
import com.gtbr.arcanebank.crud.TransacaoCrud;
import com.gtbr.arcanebank.dto.ContaDTO;
import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.entity.Transacao;
import com.gtbr.arcanebank.servico.TransacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TransacaoController {

    @Autowired
    private TransacaoCrud transacaoCrud;
    @Autowired
    private TransacaoServico transacaoServico;
    @Autowired
    private ContaCrud contaCrud;
    @Autowired
    private ClienteCrud clienteCrud;

    @RequestMapping("/pagamento")
    public String visualizaPaginaPagamento(HttpServletRequest request, Model model){
        Long idCliente = Long.parseLong(request.getSession().getAttribute("usuarioLogado").toString());
        Conta conta = contaCrud.getContaByIdCliente(idCliente);

        model.addAttribute("conta", conta);
        return "area-do-cliente/pagamento/pagina-inicial.html";
    }

    @RequestMapping("/pagamento/buscar-destinatario")
    public String buscaDestinatario(HttpServletRequest request,@RequestParam("cpf") String cpf, Model model){
        Cliente clienteOrigem = clienteCrud.getClienteById(Long.parseLong(request.getSession().getAttribute("usuarioLogado").toString()));
        Cliente cliente = clienteCrud.getClienteByCpf(cpf);
        if(clienteOrigem.getIdCliente() == cliente.getIdCliente()) return "/area-do-cliente/pagamento/naoAutorizado.html";
        if(cliente == null){
            return "/area-do-cliente/pagamento/clienteNaoEncontrado.html";
        }else{
            Conta conta = contaCrud.getContaByIdCliente(cliente.getIdCliente());
            if(conta == null) return "/area-do-cliente/pagamento/clienteNaoEncontrado.html";
            else {
                model.addAttribute("contaDestino", conta);
                return "/area-do-cliente/pagamento/formTransferencia.html";
            }
        }

    }

    @RequestMapping("/pagamento/transferir")
    public String efetuaTransferencia(HttpServletRequest request, @RequestParam("cpf")String cpf, @RequestParam("quantidade")String quantidade, Model model){
        Long idCliente = Long.parseLong(request.getSession().getAttribute("usuarioLogado").toString());
        Cliente cliente = clienteCrud.getClienteByCpf(cpf);
        if(cliente == null) return "/area-do-cliente/pagamento/clienteNaoEncontrado.html";
        if(cliente.getIdCliente() == idCliente) return "/area-do-cliente/pagamento/naoAutorizado.html";
        Conta contaDestino = contaCrud.getContaByIdCliente(cliente.getIdCliente());
        if(contaDestino == null) return "/area-do-cliente/pagamento/clienteNaoEncontrado.html";
        Double valor = Double.parseDouble(quantidade.replaceAll("\\.", "").replaceAll(",", "\\."));
        Transacao transacao = transacaoServico.efetuaTransferencia(idCliente , contaDestino, valor);
        if(transacao == null) return "/area-do-cliente/pagamento/saldoInsuficiente.html";

        model.addAttribute("transacao", transacao);

        return "/area-do-cliente/pagamento/transferenciaBemSucedida.html";

    }


}
