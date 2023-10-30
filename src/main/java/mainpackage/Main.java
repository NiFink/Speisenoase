package mainpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.stage  = primaryStage;

        URL fxmlFileUrl = getClass().getClassLoader().getResource("Login.fxml");

        Parent root = FXMLLoader.load(Objects.requireNonNull(fxmlFileUrl));
        primaryStage.setTitle("Speisenoase");
        primaryStage.setScene(new Scene(root, 775, 506));
        primaryStage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {

        launch(args);
    }
}
