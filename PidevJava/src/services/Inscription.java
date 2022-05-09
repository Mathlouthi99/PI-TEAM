/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import services.UserService;
import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;


//import org.controlsfx.control.Notifications;


import utils.MyDB;

/**
 *
 * @author yassi
 */
public class Inscription {

    @FXML
    private TextField txt_c;
    @FXML
    private PasswordField txt_mdpcc;
    @FXML
    private Button btn_ins;
    @FXML
    private Button close;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField txt_pre;
    @FXML
    private TextField txt_num;
    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField tct_mdpp;
    @FXML
    private TextField txt_n;
Connection mc;
    PreparedStatement ste;
     @FXML
    private FontAwesomeIconView fermer;

    @FXML
    private TextField txt_type;
    ObservableList<Users>userList;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        
      
    }    
     public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
//      private void GotoFXML(String vue, String title, Event aEvent) {
//        try {
//            Event event;
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
//            stage.setTitle(title);
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


    @FXML
    void inscription(ActionEvent event) throws Exception {
        mc=MyDB.getInstance().getConnexion();

        String nom = txt_n.getText();
        String prenom = txt_pre.getText();
        String email = txt_email.getText();
         
        String password = tct_mdpp.getText();
        String rpassword = txt_mdpcc.getText();
        
        
        String typeuser = "client";
        
        
         String sql="select * from user where email='"+email+"'";
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            if(rs.next() == true){
                
                  Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Email deja utilisée");
             alert.showAndWait();
             
               txt_n.setText(null);
          txt_pre.setText(null);
        txt_email.setText(null);
         tct_mdpp.setText(null);
          txt_mdpcc.setText(null);
           
            // txt_pre.setText(null);
                
            }
        
                   Users e=new Users(1,nom,prenom,email,password,rpassword ,typeuser);

   
       if (testSaisie()) {
                  UserService us = new UserService();


                us.ajouterUser(e);
                      

                AlertWindow("Lets Travel", "Bienvenue " + prenom, Alert.AlertType.INFORMATION);
            } else {
          utils.Mailing.sendMail(e);
                AlertWindow("Lets Travel", "erreur " + prenom, Alert.AlertType.INFORMATION);
            
            }
            
        Stage stage = (Stage) btn_ins.getScene().getWindow();
                       stage.close();

                        Stage primaryStage = new Stage();
                   Parent parent = FXMLLoader.load(getClass().getResource("AuthentificationUtilisateur.fxml"));
                           Scene scene = new Scene(parent);
                       primaryStage.initStyle(StageStyle.UNDECORATED);

                          
                         primaryStage.setScene(scene);
                         primaryStage.show();
     //     Stage stage = (Stage) fermer.getScene().getWindow();
           stage.close();
          //GotoFXML("LoginFXML", "ForU", event);
        }
            
    
//    @FXML
//    private void handleCloseButtonAction(ActionEvent event) throws IOException {
//         Stage stage = (Stage) btn_ins.getScene().getWindow();
//                           stage.close();
//
//                           Stage primaryStage = new Stage();
//                           Parent parent = FXMLLoader.load(getClass().getResource("AuthentificationUtilisateur.fxml"));
//                           Scene scene = new Scene(parent);
//                           primaryStage.initStyle(StageStyle.UNDECORATED);
//
//                           
//                           primaryStage.setScene(scene);
//                           primaryStage.show();
//    }
//    private void closeButtonOnAction(ActionEvent event) throws IOException {
//        Stage stage = (Stage) fermer.getScene().getWindow();
//                           stage.close();
//    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
                           stage.close();
                           Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("/views/AuthentificationUtilisateur.fxml"));
                           Scene scene = new Scene(parent);
                           primaryStage.initStyle(StageStyle.UNDECORATED);

                           
                           primaryStage.setScene(scene);
                           primaryStage.show();
    }
 private Boolean testSaisie() {
        String erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }

         if (!testMdp()) {
            erreur = erreur + ("Les mots de passes doit etre compatibles \n");
        }
        if (!testMail() || !testMdp() )
{
            Notifications n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
        n.showInformation();}

        return testMail() && testMdp();
    }
    
    @FXML
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (txt_email.getText() == null) {
            return false;
        }

        if (pat.matcher(txt_email.getText()).matches() == false) {

            return false;
//            

        } else {

        }
        return true;

    }
   
    @FXML
    private Boolean testMdp() {
        if (tct_mdpp.getText().trim().length() == txt_mdpcc.getText().trim().length()) 
            return true;
            
        

            
        else if(tct_mdpp.getText().trim().length() != txt_mdpcc.getText().trim().length()) {

                return false;

            }
        return true;
    }
}