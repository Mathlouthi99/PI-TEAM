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
import javax.mail.internet.*; 

/**
 *
 * @author yassi
 */
public class Mailing {
    
    
    
    public static void sendMail(Users c) throws Exception {
        Properties prop = new Properties();
        final String moncompteEmail = "Mathlouthiyassine28@gmail.com";
        final String psw = "Yassin20568767";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

            
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(moncompteEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getEmail()));
            msg.setSubject("ForU");
            msg.setText("Bonjour Mr/Mme "+ c.getNom()+", votre inscription est validé ! " );
            
            
            
           

            Transport.send(msg);
            System.out.println(c.getEmail());
                        System.out.println("mail sent");


        } catch (MessagingException ex) {
            Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }

         }   


}