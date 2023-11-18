package mainpackage;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class TestItem extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        BorderPane root = new BorderPane();

        HBox hBox = new HBox();

        Scene scene = new Scene(root, 300, 300);

        Label name = new Label("Banana");

        File file = new File("src/main/resources/images/groceries/Banane.png");
        Image productImg = new Image(file.toURI().toURL().toExternalForm(), 200.0, 200.0, true, true);
        ImageView productImgView = new ImageView(productImg);
        Button favor = new Button();
        TextField amount = new TextField("1");
        File file2 = new File("src/main/resources/images/icons/herzform-umriss.png");
        Image heart = new Image(file2.toURI().toURL().toExternalForm(), 20, 20, true, true);
        ImageView heartView = new ImageView(heart);
        Button buy = new Button("buy");

        favor.setGraphic(heartView);

        name.setFont(new Font(40));
        name.setTranslateY(20);
        amount.setPrefSize(30,20);

        hBox.getChildren().addAll(favor, amount, heartView, buy);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(productImgView, Pos.CENTER);
        BorderPane.setAlignment(name, Pos.CENTER);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        root.setTop(name);
        root.setCenter(productImgView);
        root.setBottom(hBox);

        stage.setTitle("Product");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
