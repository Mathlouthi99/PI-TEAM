/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Users;
import java.sql.SQLException;
import services.UserService;
import utils.MailingCode;

import utils.MyDB;


/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
               MyDB m = MyDB.getInstance();

        

       /*Users u=new Users("Yassine", "mathlouthi","yassinemathlouthi@gmailcom","1234","1234","user");
        UserService Us = new UserService();
        Us.ajouterUser(u);
        System.out.println(Us.afficherUser());*/
        
       MailingCode.sendMail("Mathlouthiyassine28@gmail.com");
        
    }
    
}
