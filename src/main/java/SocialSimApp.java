import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Logger;

public class SocialSimApp extends Application {

    Logger logger= Logger.getLogger(this.getClass().toString());
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader=new FXMLLoader();
            BorderPane root=(BorderPane) loader.load(getClass().getResource("SocialSim.fxml").openStream());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
        logger.info("Problem by getting the root ");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
