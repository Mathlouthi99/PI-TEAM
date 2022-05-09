/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 * @author yassi
 */
public class Users {
    private int id;
    private String nom,prenom,email,password,repeatpassword,role;
    

    public Users() {
    }

    public Users(int id, String nom, String prenom, String email, String password, String repeatpassword, String role) {
        this.id = id;

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.repeatpassword = repeatpassword;
        this.role = role;
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String nom, String prenom, String email, String password, String repeatpassword, String role) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.repeatpassword = repeatpassword;
        this.role = role;
    }

    

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", repeatpassword=" + repeatpassword + ", role=" + role + '}';
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatpassword() {
        return repeatpassword;
    }

    public String getrole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
      public void setPassword(String Password) {
          
        this.password = DigestUtils.shaHex(Password);
    }

    public void setRepeatpassword(String RepeatPassword) {
        this.repeatpassword=DigestUtils.shaHex(RepeatPassword);
    }



    public void setrole(String role) {
        this.role = role;
    }
    
}
