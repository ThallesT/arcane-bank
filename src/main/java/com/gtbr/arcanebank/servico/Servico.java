package com.gtbr.arcanebank.servico;

import com.gtbr.arcanebank.crud.ClienteCrud;
import com.gtbr.arcanebank.entity.Cliente;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class Servico {

    @Autowired
    private ClienteCrud clienteCrud;


    public Date transformaStringDateEmDate(String strData){
        String[] datas = strData.split("/");
        int dia = Integer.parseInt(datas[0]);
        int mes = Integer.parseInt(datas[1]);
        int ano = Integer.parseInt(datas[2]);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(ano, mes - 1, dia);
        return gregorianCalendar.getTime();


    }

    public String geraCodigoCliente(){
        boolean verificador = false;
        String token;
        do {
            int n = 30;
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());
                sb.append(AlphaNumericString
                        .charAt(index));
            }
            token = sb.toString();
            Cliente cliente = clienteCrud.getClienteByToken(token);
            if(cliente == null){
                verificador = true;
            }

        }while (verificador == false);

        return token;
    }

}
