package mainpackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestItem extends Application {

    @FXML
    Label labelName;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FlowPane root = new FlowPane();
        root.getChildren().addAll(makeItemNode("/fxml/item.fxml"));

        System.out.println(labelName.getText());

        primaryStage.setTitle("FXML Example");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public Node makeItemNode(String url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
