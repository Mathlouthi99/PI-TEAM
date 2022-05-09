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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class GestionutilisateurController implements Initializable  {

    

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_mdp;
    @FXML
    private TextField txt_mdpc;
    @FXML
    private TextField txt_type;
    @FXML
    private TableView<Users> usertable;
    @FXML
    private TableColumn<Users, Integer> col_id;
    @FXML
    private TableColumn<Users, Integer> col_cin;
    @FXML
    private TableColumn<Users, String> col_nom;
    @FXML
    private TableColumn<Users, String> col_prenom;
    @FXML
    private TableColumn<Users, Integer> col_tel;
    @FXML
    private TableColumn<Users, String> col_email;
    @FXML
    private TableColumn<Users, String> col_mdp;
    @FXML
    private TableColumn<Users, String> col_mdpc;
    @FXML
    private TableColumn<Users, String> col_type;
    @FXML
    private TextField filterField;

    ObservableList<Users>userList;
    Connection mc;
    PreparedStatement ste;
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mc=MyDB.getInstance().getConnexion();

        userList = FXCollections.observableArrayList();
        
        String sql="SELECT * FROM user ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Users e = new Users();
                e.setId(rs.getInt("id"));
                
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                
                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
                e.setRepeatpassword(rs.getString("repeatPassword"));
                e.setrole(rs.getString("role"));
                
                
                userList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
        col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));

        col_nom.setCellValueFactory(new PropertyValueFactory<Users,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Users,String>("prenom"));
       
        col_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        col_mdp.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        col_mdpc.setCellValueFactory(new PropertyValueFactory<Users, String>("repeatpassword"));
        col_type.setCellValueFactory(new PropertyValueFactory<Users,String>("typeuser"));




        
        usertable.setItems(userList);


        search();
        
       
    }    

    

    @FXML
    private void adduser(ActionEvent event) throws IOException, Exception{
        
       
                
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmer votre choix !");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
         String nom = txt_nom.getText();
         String prenom = txt_prenom.getText();
         String email = txt_email.getText();
         String typeuser = txt_type.getText();
         String password = txt_mdp.getText();
         String rpassword = txt_mdpc.getText();

         
         
         
        

        
           
         String sql="select * from user where email='"+email+"'";
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            if(rs.next() == true){
                
                  Alert alert1 = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Eamil deja utilisée");
             alert.showAndWait();
             
              txt_id.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
      
        txt_email.setText(null);
        txt_mdp.setText(null);
        txt_mdpc.setText(null);
        txt_type.setText(null);  
             
             
            }else{

        
        if ( testSaisie()&&nom.isEmpty()){
            // Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
             
         }if(testSaisie()){
             
             Users e=new Users(1,nom,prenom,email,password,rpassword ,typeuser);
             
             UserService ec = new UserService();
             
             ec.ajouterUser(e);
              utils.Mailing.sendMail(e);
        //  Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("utilisateur Ajoutée!");
                alert.showAndWait();             
        
        
         }
        Notifications notificationBuilder = Notifications.create().title("Alert").text(" utilisateur ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER).onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event){
                       System.out.println("clicked on");
                   } 
                });
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        
        refresh();
            }
    }else{
         txt_id.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_email.setText(null);
        txt_mdp.setText(null);
        txt_mdpc.setText(null);
        txt_type.setText(null);   
        }
    }
    @FXML
    private void updateUser(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmer votre choix !");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        Users clickedUser = usertable.getSelectionModel().getSelectedItem();
        
        
        try{
             mc=MyDB.getInstance().getConnexion();
             String value1 = txt_id.getText();
             String value3 = txt_nom.getText();
             String value4 = txt_prenom.getText();
             String value6 = txt_email.getText();
             String value7 = txt_mdp.getText();
             String value8 = txt_mdpc.getText();
             String value9 = txt_type.getText();
             
             String sql = "update user set id = '"+value1+"', nom='"+value3+"',prenom='"+value4+",email='"+value6+"',password='"+value7+"',repeatPassword='"+value8+"',typeUser='"+value9+"' where id ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "utilisateur modifié");
        }catch(SQLException e){
               JOptionPane.showMessageDialog(null,e);
               System.out.println(e.getMessage());
        }
        
      testSaisie();
        refresh();
        refreshChamp();
    }else{
         txt_id.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_email.setText(null);
        txt_mdp.setText(null);
        txt_mdpc.setText(null);
        txt_type.setText(null);   
        
    }
    }
    @FXML
    private void deleteUser(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmer votre choix !");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        mc=MyDB.getInstance().getConnexion();
         String sql = "DELETE FROM user WHERE id =?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, txt_id.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Utilisateur supprimé" );
        
            refresh();
            refreshChamp();
    }else{
             txt_id.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_email.setText(null);
        txt_mdp.setText(null);
        txt_mdpc.setText(null);
        txt_type.setText(null);
            
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Users clickedUser = usertable.getSelectionModel().getSelectedItem();
        //System.out.println("aaaa"+String.valueOf(clickedUser.getId()));
                System.out.println(clickedUser.getId());

         txt_id.setText(String.valueOf(clickedUser.getId()));
        txt_nom.setText(String.valueOf(clickedUser.getNom()));
        txt_prenom.setText(String.valueOf(clickedUser.getPrenom()));
        
        txt_email.setText(String.valueOf(clickedUser.getEmail()));
        txt_mdp.setText(String.valueOf(clickedUser.getPassword()));
        txt_mdpc.setText(String.valueOf(clickedUser.getRepeatpassword()));
        txt_type.setText(String.valueOf(clickedUser.getrole()));
    }
  public void refresh(){
        
         userList.clear();
       
          
          mc=MyDB.getInstance().getConnexion();

        userList = FXCollections.observableArrayList();
        
        String sql="select * from user";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Users e = new Users();
                e.setId(rs.getInt("id"));
                
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                
                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
                e.setRepeatpassword(rs.getString("repeatPassword"));
                e.setrole(rs.getString("role"));
                
                userList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         usertable.setItems(userList);
        
        
        
    }
    @FXML
    private void refreshChamp() {
        
        
        txt_id.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_email.setText(null);
        txt_mdp.setText(null);
        txt_mdpc.setText(null);
        txt_type.setText(null);
        
    }
    private void search() {      
        
        FilteredList<Users>filteredData = new FilteredList<>(userList, b->true);
        filterField.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(Users->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
               
                 if(String.valueOf(Users).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Users>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(usertable.comparatorProperty());
        usertable.setItems(sortedData);
    }
//     private void verifDataEntered(KeyEvent event) {
//        
//        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
//            Pattern pattern = Pattern.compile(email_pattern);
//            Matcher matcher = pattern.matcher(txt_email.getText());
//
//            if (matcher.matches()) {       //if   matcher ne contient pas la format   
//                txt_email.setVisible(false);
//                txt_email.setText("Email valide !");
//                verificationUserEmail = true;
//
//            } else {
//                txt_email.setVisible(true);
//                txt_email.setText("Email Format invalide !");
//                // JOptionPane.showMessageDialog(null, "Email Format invalide");
//                verificationUserEmail = false;
//
//            }
//        

       

     private Boolean testSaisie() {
        String erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
      
         if (!testMdp()) {
            erreur = erreur + ("Les mots de passes doit etre compatibles \n");
        }
        if (!testMail() ||  !testMdp() )
{
          Notifications n = Notifications.create()
                .title("Erreur de format")
                  .text(erreur)
                    .graphic(null)
                  .position(Pos.TOP_CENTER)
                   .hideAfter(Duration.seconds(6));
        n.showInformation();}

        return testMail() &&  testMdp();
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
            

  } else {

       }
      return true;

   }

    @FXML
    private Boolean testMdp() {
        if (txt_mdp.getText().trim().length() == txt_mdpc.getText().trim().length()) 
            return true;
            
        

            
        else if(txt_mdp.getText().trim().length() != txt_mdpc.getText().trim().length()) {

                return false;

            }
        return true;
    }
   }


