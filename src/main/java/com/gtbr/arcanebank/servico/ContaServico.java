package com.gtbr.arcanebank.servico;

import com.gtbr.arcanebank.crud.ContaCrud;
import com.gtbr.arcanebank.entity.Cliente;
import com.gtbr.arcanebank.entity.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Random;

@Component
public class ContaServico {

    @Autowired
    private ContaCrud contaCrud;


    public Conta criaContaCliente(Cliente cliente, String senha) {
        boolean contaVerif = false;
        Long numeroConta = geraConta();
        do{
            numeroConta = geraConta();
            Conta conta = contaCrud.getContaByNumeroConta(numeroConta);
            if(conta == null) contaVerif = true;
        }while (contaVerif = false);

        return contaCrud.insereConta(geraConta(), 0001L, cliente.getIdCliente(), gerarHash(senha));

    }

    /**
     * Metodo que gera a hash e criptografa a senha do usuario
     *
     *
     * @param senha
     * @return
     * @Author https://github.com/rodolfocampos
     */
    public String gerarHash(String senha) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] messageDigest = algorithm.digest(senha.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        senha = hexString.toString();
        return senha;
    }

    public Boolean validaConfirmaSenha(String senha, String confirmaSenha){
        if (senha.equals(confirmaSenha) && !senha.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private static Long geraConta() {
        Long numeroConta = 0l;
        String srtConta = "";
        for(int a = 0 ; a < 10; a++){
            Random random = new Random();
            srtConta += random.nextInt(9);
        }
        numeroConta = Long.parseLong(srtConta);
        return numeroConta;
    }


}
