package com.gtbr.arcanebank.controller;


import com.gtbr.arcanebank.crud.ClienteCrud;
import com.gtbr.arcanebank.crud.ContaCrud;
import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.entity.Conta;
import com.gtbr.arcanebank.servico.ContaServico;
import com.gtbr.arcanebank.servico.MailService;
import com.gtbr.arcanebank.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.net.www.http.HttpClient;

@Controller
public class ClienteController {

    @Autowired
    private ClienteCrud clienteCrud;
    @Autowired
    private Servico servico;
    @Autowired
    private MailService mailService;
    @Autowired
    private ContaServico contaServico;
    @Autowired
    private ContaCrud contaCrud;


    @RequestMapping("/cliente/registrar")
    public String registraCliente(@RequestParam("nome")String nome, @RequestParam("sobrenome")String sobrenome, @RequestParam("email")String email,
                                  @RequestParam("cpf")String cpf, Model model){

        Cliente cliente = clienteCrud.getClienteByCpf(cpf);
        if(cliente == null){
            cliente = clienteCrud.insereCliente(nome, sobrenome, cpf, null, null, null, email, null);
            mailService.enviarEmail(email, cliente.getToken());
            return "confirmacao.html";
        }else{
            model.addAttribute("cliente", cliente);
            return "clienteCadastrado.html";
        }





    }


    @RequestMapping("/cliente/finalizar")
    public String finalizarNovamente(){

        return "confirmacao.html";

    }

    @RequestMapping("/cliente/confirmar-token")
    public String confirmaToken(@RequestParam("token")String token, Model model){


        Cliente cliente = clienteCrud.getClienteByToken(token);

        model.addAttribute("cliente", cliente);
        return "terminoCadastro.html";

    }

    @RequestMapping("/cliente/confirmar-token/{token}")
    public String confirmaTokenDirect(@PathVariable("token")String token, Model model){


        Cliente cliente = clienteCrud.getClienteByToken(token);

        model.addAttribute("cliente", cliente);
        return "terminoCadastro.html";

    }


    @RequestMapping("/cliente/finalizar-cadastro")
    public String registraCliente(@RequestParam("celular")String celular, @RequestParam("telefone")String telefone, @RequestParam("cpf")String cpf,
                                  @RequestParam("rg")String rg, @RequestParam("dataDeNascimento")String dataDeNascimento, @RequestParam("senha") String senha ,@RequestParam("idCliente") Long idCliente, Model model){


        Cliente cliente = clienteCrud.getClienteById(idCliente);
        Cliente cliente2 = clienteCrud.confirmaCliente(cliente, telefone, celular, rg, servico.transformaStringDateEmDate(dataDeNascimento));
        contaServico.criaContaCliente(cliente2, senha);

        model.addAttribute("cliente", cliente2);
        return "conclusao.html";

    }

    @RequestMapping("/logar")
    public String logar(Model model){
        return "login/paginaDeLogin.html";

    }

    @RequestMapping("/validar-login")
    public String validarLogin(@RequestParam("cpf")String cpf, @RequestParam("senha")String senha,  Model model){
        Cliente cliente = clienteCrud.getClienteByCpf(cpf);
        Conta conta = contaCrud.getContaByIdCliente(cliente.getIdCliente());

        if(contaServico.validaConfirmaSenha(conta.getSenha(), contaServico.gerarHash(senha))){
            model.addAttribute("conta", conta);
            return "teste.html";
        }else{
            return "testeE.html";
        }


    }



}
