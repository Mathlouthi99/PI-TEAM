/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class SuccesHController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
        static boolean answer;
    public static boolean display(String title,String message){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaxWidth(400);
        window.setWidth(300);
        window.setHeight(200);
        Label label=new Label(message);
        Button yesButton=new Button("oui");
        yesButton.setOnAction(e->{
        answer=true;
        window.close();
        });
        Button noButton=new Button("non");
        noButton.setOnAction(e->{
        answer=false;
        window.close();
        });
        
        
        VBox layout=new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
    
}
