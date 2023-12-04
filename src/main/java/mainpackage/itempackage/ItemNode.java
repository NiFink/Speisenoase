package mainpackage.itempackage;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mainpackage.itempackage.Item;

import java.io.IOException;
import java.util.Objects;

public class ItemNode extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane sp = createPane(ItemFactory.createItem(ItemType.GROCERY, 0));
        StackPane sp2 = createPane(ItemFactory.createItem(ItemType.GROCERY, 1));
        FlowPane root = new FlowPane(sp, sp2);

        Scene scene = new Scene(root, 376, 240);

        primaryStage.setTitle("JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final StackPane itemNode;

    public ItemNode (Item item) {
        this.itemNode = createPane(item);
    }

    public StackPane getItemNode(){
        return itemNode;
    }

    public StackPane createPane(Item item) {
        StackPane pane = new StackPane();

        Rectangle border = new Rectangle(183, 235);
        border.setArcWidth(5);
        border.setArcHeight(5);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        border.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        border.setTranslateX(3);
        border.setTranslateY(3);

        BorderPane innerPane = new BorderPane();
        innerPane.setPrefWidth(188);
        innerPane.setPrefHeight(240);

        AnchorPane topAnchorPane = new AnchorPane();
        Label labelName = new Label(item.getName());
        labelName.setStyle("-fx-font-size:20");
        labelName.setFont(new javafx.scene.text.Font("System Bold", 16));
        labelName.setTranslateY(10);

        Label labelPrice = new Label(String.format("%,.2f", item.getPrice()) + "€");
        labelPrice.setStyle("-fx-font-size:15");
        labelPrice.setFont(new javafx.scene.text.Font("System Bold", 16));
        labelPrice.setTranslateY(15);

        topAnchorPane.getChildren().addAll(labelName, labelPrice);
        AnchorPane.setLeftAnchor(labelName, 20.00);
        AnchorPane.setRightAnchor(labelPrice, 15.00);

        innerPane.setTop(topAnchorPane);

        ImageView imgViewItem = null;
        try {
            imgViewItem = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/groceries/" + item.getName() + ".png")).openStream()));
            imgViewItem.setFitHeight(130);
            imgViewItem.setFitWidth(130);

        } catch (IOException ioe){
            ioe.getMessage();
        }
        innerPane.setCenter(imgViewItem);

        AnchorPane bottomAnchorPane = new AnchorPane();

        ImageView imgHeartView = null;
        try {
            imgHeartView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-umriss.png")).openStream()));
            imgHeartView.setFitHeight(30);
            imgHeartView.setFitWidth(30);
            imgHeartView.setLayoutX(16);
        } catch (IOException ioe){
            ioe.getMessage();
        }


        TextField tfValue = new TextField();
        tfValue.setPrefHeight(30);
        tfValue.setPrefWidth(50);

        Button button = new Button("Buy");
        button.setPrefHeight(30);
        button.setPrefWidth(40);

        HBox hbox = new HBox(tfValue, button);
        AnchorPane.setRightAnchor(hbox, 10.0);

        bottomAnchorPane.getChildren().addAll(imgHeartView, hbox);
        bottomAnchorPane.setTranslateY(-10.0);

        innerPane.setBottom(bottomAnchorPane);

        pane.getChildren().addAll(border, innerPane);

        return pane;
    }
}
