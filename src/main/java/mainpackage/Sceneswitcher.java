package mainpackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class Sceneswitcher {
    private static Logger log = LogManager.getLogger(ItemManager.class);

    private static Sceneswitcher instance;

     public static Sceneswitcher getInstance(){
        if(instance == null){
            instance = new Sceneswitcher(Main.getStage());
        }
        return instance;
    }

    private final Stage stage;

    private Sceneswitcher(Stage stage) {
        this.stage = stage;
    }

    public void switchTo(String fxmlFileName, String title, double width, double height) {
        try {
            URL fxmlFileUrl = getClass().getClassLoader().getResource(fxmlFileName);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFileUrl);
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, width, height);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
