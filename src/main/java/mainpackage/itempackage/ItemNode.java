package mainpackage.itempackage;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mainpackage.UserManager;
import mainpackage.itempackage.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ItemNode {

    private boolean favorite = false;
    private int amountInCart = 0;
    private final StackPane itemNode;

    public ItemNode(Item item) {
        this.itemNode = createPane(item);
    }

    public StackPane getItemNode() {
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
        labelName.setFont(Font.font("Yu Gothic UI", FontWeight.BOLD, FontPosture.REGULAR, 20));
        labelName.setTranslateY(10);

        Label labelPrice = new Label(String.format("%,.2f", item.getPrice()) + "€");
        labelPrice.setFont(Font.font("Yu Gothic UI", FontWeight.MEDIUM, FontPosture.REGULAR, 15));
        labelPrice.setTranslateY(15);

        topAnchorPane.getChildren().addAll(labelName, labelPrice);
        AnchorPane.setLeftAnchor(labelName, 20.00);
        AnchorPane.setRightAnchor(labelPrice, 15.00);

        innerPane.setTop(topAnchorPane);

        ImageView imgViewItem = null;
        try {
            imgViewItem = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/groceries/" + item.getName() + ".png")).openStream()));
            imgViewItem.setFitHeight(160);
            imgViewItem.setFitWidth(160);

        } catch (IOException ioe) {
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
        } catch (IOException ioe) {
            ioe.getMessage();
        }

        ImageView finalImgHeartView = imgHeartView;
        finalImgHeartView.setOnMouseEntered(e -> {
            try {
                finalImgHeartView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-silhouette.png")).openStream()));
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        });

        finalImgHeartView.setOnMouseExited(e -> {
            // Change image on exit
            try {
                if (!favorite) {
                    finalImgHeartView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-umriss.png")).openStream()));
                } else {
                    finalImgHeartView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-silhouette.png")).openStream()));
                }
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        });

        finalImgHeartView.setOnMouseClicked(e -> {
            try {
                if (favorite) {
                    finalImgHeartView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-umriss.png")).openStream()));
                    favorite = false;
                    System.out.println("Entfavorisiert: " + item.getName());
                } else {
                    finalImgHeartView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-silhouette.png")).openStream()));
                    favorite = true;
                    System.out.println("Favorisiert: " + item.getName());
                }
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        });

        Spinner<Integer> spinner = new Spinner<Integer>(0, 9, 1);
        //spinner.setStyle("-fx-base: #022235");
        spinner.setPrefHeight(30);
        spinner.setPrefWidth(50);

        Button button = new Button("Buy");
        button.setFont(Font.font("Yu Gothic UI", FontWeight.BOLD, FontPosture.REGULAR, 12));;
        button.setStyle("-fx-background-color: #022235; -fx-text-fill: white;");
        button.setPrefHeight(30);
        button.setPrefWidth(40);
        button.setOnAction(actionEvent -> {
            amountInCart += spinner.getValue();
            System.out.println(amountInCart + " " + item.getName() + " currently in shopping cart!");
        });

        HBox hbox = new HBox(spinner, button);
        AnchorPane.setRightAnchor(hbox, 10.0);

        bottomAnchorPane.getChildren().addAll(finalImgHeartView, hbox);
        bottomAnchorPane.setTranslateY(-10.0);

        innerPane.setBottom(bottomAnchorPane);

        pane.getChildren().addAll(border, innerPane);

        return pane;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getAmountInCart() {
        return amountInCart;
    }
}
