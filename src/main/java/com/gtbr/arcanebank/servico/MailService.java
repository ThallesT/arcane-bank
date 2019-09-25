package com.gtbr.arcanebank.servico;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.Element;
import java.io.File;
import java.io.IOException;
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
            File arquivo = new File("src\\main\\resources\\templates\\email\\tokenTemplate.html");
            Document doc = Jsoup.parse(arquivo, "UTF-8");
            doc.getElementById("recado").text("Nós do Arcane Bank agradecemos o seu interesse!");
            doc.getElementById("html-token-tag").text("Aqui está seu token: "+ codigoDeConfirmacao);
            String link = "http://gtbr.sa-east-1.elasticbeanstalk.com/cliente/confirmar-token/"+codigoDeConfirmacao;
            doc.getElementById("botao").attr("href", link);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("E-mail automatico do ArcaneBank");//Assunto
            message.setContent(doc.outerHtml(), "text/html");



            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
