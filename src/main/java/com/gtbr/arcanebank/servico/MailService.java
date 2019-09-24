package com.gtbr.arcanebank.servico;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Component
public class MailService {

    public void enviarEmail(String emailCliente, String codigoDeConfirmacao){
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("arcaneBankMail@gmail.com",
                                "arcaneLoko123");
                    }
                });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arcaneBankMail@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress.parse(emailCliente);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Email automatico do ArcaneBank");//Assunto
            message.setText("Seu token de confirmação: "+ codigoDeConfirmacao);



            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
