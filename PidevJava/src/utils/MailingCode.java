/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Users;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

/**
 *
 * @author yassi
 */
public class MailingCode {
    
public static void sendMail(String email) throws Exception {


          Properties prop = new Properties();
                  String moncompteEmail = "letstravel48@gmail.com";
        String psw = "travel12345";
       
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress("moncompteEmail"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject("LETSTRAVEL");
            msg.setText("Bonjour Mr/Mme , Le Code de récupération est le suivant : 5569112"
                    + "               Merci de le bien écrire dans l'interface pour Reinialiser votre mot de passe!! " );
            

            Transport.send(msg);
            System.out.println(email);
            System.out.println("mail sent");
        } catch (MessagingException ex) {
            Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }

         }   
}

