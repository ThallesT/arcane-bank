package com.gtbr.arcanebank.crud;

import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.repository.ClienteRepository;
import com.gtbr.arcanebank.servico.Servico;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.metamodel.EmbeddableType;
import javax.transaction.Transactional;
import java.nio.channels.ClosedByInterruptException;
import java.util.Date;

@Component
public class ClienteCrud {

    @Autowired
    private ClienteRepository clienteRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private Servico servico;


    public Cliente insereCliente(String nome, String sobrenome, String cpf, String rg, String telefone, String celular, String email, Date dataDeNascimento) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setCelular(celular);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setRg(rg);
        cliente.setEmail(email);
        cliente.setDataDeNascimento(dataDeNascimento);
        cliente.setAutenticado("F");
        cliente.setToken(servico.geraCodigoCliente());

        return clienteRepository.save(cliente);

    }

    @Transactional
    public Cliente confirmaCliente(Cliente cliente, String telefone, String celular, String rg, Date dataDeNascimento){
        entityManager.find(Cliente.class, cliente.getIdCliente());
        cliente.setAutenticado("T");
        cliente.setDataDeNascimento(dataDeNascimento);
        cliente.setRg(rg);
        cliente.setTelefone(telefone);
        cliente.setCelular(celular);

        entityManager.persist(cliente);
        return cliente;

    }


    public Cliente getClienteByToken(String token){

        try{
            Cliente cliente = (Cliente) entityManager.createQuery("SELECT c FROM Cliente c WHERE c.autenticado = 'F' AND c.token LIKE :token").setParameter("token", token).getResultList().get(0);
            return cliente;
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }

    }


    public Cliente getClienteById(Long idCliente){
        return clienteRepository.getByIdCliente(idCliente);

    }

    public Cliente getClienteByCpf(String cpf){
        try{
            Cliente cliente = (Cliente) entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpf LIKE :cpf").setParameter("cpf", cpf).getResultList().get(0);            return cliente;
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
