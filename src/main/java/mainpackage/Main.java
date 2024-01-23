package mainpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainpackage.itempackage.ItemManager;
import mainpackage.itempackage.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    private static final Logger log = LogManager.getLogger(Main.class);
    private static Stage stage;
    private final UserManager userManager = UserManager.getInstance();
    private static final JsonReader jsonReader = JsonReader.getInstance();
    @Override
    public void start(Stage primaryStage) {

        Main.stage  = primaryStage;


        URL fxmlFileUrl = getClass().getClassLoader().getResource("Login.fxml");

        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(fxmlFileUrl));
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        }

        primaryStage.setTitle("Speisenoase");
        primaryStage.setScene(new Scene(root, 860, 550));
        primaryStage.setResizable(false);

        //windowsbar hide
        //stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    @Override
    public void stop() {
        String stage = Sceneswitcher.getInstance().getStage();
        if(!Objects.equals(stage, "Speisenoase") && !Objects.equals(stage, "Login") && !Objects.equals(stage, "Register")){
            userManager.updateFavorites(userManager.getActiveUser(), ItemManager.getInstance().getFavorites().toArray(new String[0]));
        }
        try {
            super.stop();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        jsonReader.start();
        try {
            jsonReader.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.debug("JsonReader Thread is done");
        launch(args);
    }
}
