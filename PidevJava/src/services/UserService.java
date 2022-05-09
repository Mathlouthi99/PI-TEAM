/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import utils.MyDB;

/**
 *
 * @author macbook
 */
public class UserService {   
     Statement st;
    Connection mc;
    PreparedStatement ste;
    ResultSet rs;

    public UserService() {
        mc = MyDB.getInstance().getConnexion();
    }

     public boolean ajouterUser(Users u){
        String sql ="insert into user(nom,prenom,email,password,repeatpassword,role) Values(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, u.getNom());
            ste.setString(2, u.getPrenom());
            ste.setString(3, u.getEmail());
            ste.setString(4, u.getPassword());
            ste.setString(5, u.getRepeatpassword());
            ste.setString(6, u.getrole());
            ste.executeUpdate();
            System.out.println("Utilisateur Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
        
    }

    
          public List<Users> afficherUser(){
        List<Users> utilisateur = new ArrayList<>();
        String sql="select * from user";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Users u = new Users();
                 u.setId(rs.getInt("id"));
                
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
          
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setRepeatpassword(rs.getString("repeatpassword"));
            u.setrole(rs.getString("role"));
                
            utilisateur.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return utilisateur;
    }  
          
            
     public List<Users> updateUser(Users u ){
        List<Users> utilisateur = new ArrayList<>();
        String sql="UPDATE user SET om=?,prenom=?,email=?,password=?,repeatPassword=?,role=? WHERE id =3";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setString(5, u.getEmail());
            ste.setString(6, u.getPassword());
            ste.setString(7, u.getRepeatpassword());
            ste.setString(8, u.getrole());
            ste.executeUpdate();
            System.out.println("Utilisateur Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return utilisateur;
        
        
      }
            public List<Users> supprimerUser(){
        List<Users> utilisateur = new ArrayList<>();
        String sql="DELETE FROM user WHERE id=3";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Utilisateur Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return utilisateur;
}
                    public int GetIdUserbyEmail(String value) {
        String sql = "select id from user where email = '" + value + "';";

        int id = 0;
        try {
            ste = mc.prepareStatement(sql);
            rs = ste.executeQuery(sql);
            while (rs.next()) {//parcourir le resultset
                id = rs.getInt("id");

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
        public boolean ResetPassword(String password, int id) throws SQLException {
        String sql = "UPDATE user SET password=? WHERE id=?";
        try {
            Users u = new Users();
            ste = mc.prepareStatement(sql);
            ste.setString(1, u.getPassword());
            ste.setInt(2, id);

            ste.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
}
}
