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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author aroua
 */
public class AuthentificationUtilisateurController implements Initializable {
    @FXML
    private Label loginLabel;
    @FXML
    private Button closeButton ;
//    @FXML
//    private Button btn_admin;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField ;
    
    Connection mc;
    PreparedStatement ste;
    @FXML
    private Button close;
    @FXML
    private ComboBox combo;
    @FXML
    private Hyperlink inscrire;
    @FXML
    private Hyperlink mdpoub;
    
    public void openHome(){
        String nom = usernameTextField.getText();
        String pass = passwordTextField.getText();

    }
      @FXML
    void inscription(ActionEvent event) throws IOException {
    if (usernameTextField.getText().isEmpty()  && passwordTextField.getText().isEmpty()  ){
                  loginLabel.setText("Essayer de connecter");
                           Stage stage = (Stage) inscrire.getScene().getWindow();
                           stage.close();

                           Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("/views/Inscription.fxml"));
                           Scene scene = new Scene(parent);
                                   primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
   
//      Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();

        }else{
                    loginLabel.setText("SVP enter les données");

        }
    }
        private void GotoFXML(String vue, String title,Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/GimmeEmailFXML.fxml"));            
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage =(Stage)((Node) aEvent.getSource()).getScene().getWindow() ;
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("admin","client");
        combo.setItems(list);
    }   
    @FXML
    public void closeButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        
    }
      @FXML
    void choisir(ActionEvent event) {
        String s = combo.getSelectionModel().getSelectedItem().toString();
    }
    @FXML
    public void loginButtonOnAction(ActionEvent e) throws IOException, SQLException{
        if (usernameTextField.getText().isEmpty() == false && passwordTextField.getText().isEmpty() == false && "admin".equals(combo.getValue().toString())){
                  //  loginLabel.setText("Essayer de connecter");
                    validateLogin();
    // Stage stage = (Stage) closeButton.getScene().getWindow();
   //    stage.close();

        } else{
             if (usernameTextField.getText().isEmpty() == false && passwordTextField.getText().isEmpty() == false && "client".equals(combo.getValue().toString())){
             //      loginLabel.setText("Essayer de connecter");
                           Stage stage = (Stage) closeButton.getScene().getWindow();
                           stage.close();

                           Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("/views/gestionutilisateur.fxml"));
                           Scene scene = new Scene(parent);
                           primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
   
                 //Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();

        }else{
                    loginLabel.setText("SVP enter les données");

        }
//        Stage primaryStage = new Stage();
//         Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
//         Scene scene = new Scene(parent);

        
//        
//       Parent parent = FXMLLoader.load(getClass().getResource("gestionEquipeInterface.fxml"));
//       Scene scene = new Scene(parent);

////
//        Parent parent = FXMLLoader.load(getClass().getResource("gestionJoueurInterface.fxml"));
//         Scene scene = new Scene(parent);



      // scene.getStylesheets().add(getClass().getResource("ControlPanel.css").toExternalForm());
      // primaryStage.initStyle(StageStyle.UTILITY);
//        primaryStage.setTitle("Gestion des équipes");

//        primaryStage.setTitle("Dashbord");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
        
        
        
    }
    }
        public void validateLogin() throws SQLException{
            mc=MyDB.getInstance().getConnexion();
                String verifyLogin ="SELECT count(1) FROM user WHERE email ='"+usernameTextField.getText()+"' AND password='"+passwordTextField.getText()+"' AND typeUser='"+combo.getValue().toString()+"'";
                try{
                    Statement statement = mc.createStatement();
                    ResultSet queryResult = statement.executeQuery(verifyLogin);
                    while (queryResult.next()){
                        if (queryResult.getInt(1)== 1){
//                            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
//                            Pattern pattern = Pattern.compile(email_pattern);
//                            Matcher matcher = pattern.matcher(usernameTextField.getText().trim());
                           loginLabel.setText("bienvenue");
                           Stage stage = (Stage) closeButton.getScene().getWindow();
                           stage.close();

                           Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
                           Scene scene = new Scene(parent);
                           primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();

                        }else{
                            loginLabel.setText("Veuillez réessayer ");
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
//                    
//                mc=MaConnexion.getInstance().getCnx();
//        String nom = usernameTextField.getText();
//        String pass = passwordTextField.getText();
//        
//        Users e=new Users(nom,pass);
//             UserController ec = new UserController();
//        List<Users> UserList = new ArrayList<>();
//        
//        PreparedStatement ste = mc.prepareStatement("SELECT * FROM `users` WHERE email= ? AND password= ? AND typeUser= ?");
//        ste.setString(1, nom);
//        ste.setString(2, pass);
//        ste.setString(3, combo.getValue().toString());
//        
//        ResultSet rs = ste.executeQuery();
//        if (rs.next()){
//            JOptionPane.showMessageDialog(null, "email and pwd correct");
//            
//        } else {
//            JOptionPane.showMessageDialog(null, "email and pwd incorrect");
//        }
//        }
//                
        
        }
        }

    @FXML
    private void MdpOublie(ActionEvent event) throws IOException {
             GotoFXML("GimmeEmailFXML", "Bienvenue",event);

    }
        
}
        
    


