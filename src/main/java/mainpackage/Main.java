package mainpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mainpackage.itempackage.ItemManager;
import mainpackage.itempackage.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    private static final Logger log = LogManager.getLogger(Main.class);
    private static Stage stage;
    private final UserManager userManager = UserManager.getInstance();
    private static final JsonReader jsonReader = JsonReader.getInstance();
    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.stage  = primaryStage;


        URL fxmlFileUrl = getClass().getClassLoader().getResource("Login.fxml");

        Parent root = FXMLLoader.load(Objects.requireNonNull(fxmlFileUrl));

        primaryStage.setTitle("Speisenoase");
        primaryStage.setScene(new Scene(root, 860, 550));
        primaryStage.setResizable(false);

        //windowsbar hide
        //stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        //FIXME: NullPointerException wenn man sich noch nicht eingeloggt hat und das Fenster geschlossen wird, weil kein ActiveUser existiert
        userManager.updateFavorites(userManager.getActiveUser(), ItemManager.getInstance().getFavorites().toArray(new String[0]));
        super.stop();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        jsonReader.start();
        launch(args);
    }
}
