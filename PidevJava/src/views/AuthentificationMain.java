package Views;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mdhah
 */
public class AuthentificationMain extends Application {
   

    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Parent parent = FXMLLoader.load(getClass().getResource("AuthentificationUtilisateur.fxml"));
       Scene scene = new Scene(parent);
      // scene.getStylesheets().add(getClass().getResource("ControlPanel.css").toExternalForm());
      // primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setTitle("Authentification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}