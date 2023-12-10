package mainpackage.itempackage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class ItemNode {
    //TODO: Logging and Exceptions, Clean Code, Doku

    private boolean favorite = false;
    private final String name;
    private final double price;
    private int amountInCart = 0;
    private StackPane itemPane;
    private static final Logger log = LogManager.getLogger(ItemNode.class);

    public ItemNode(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        createPane(item);
        log.debug("ItemNode of '" + item.getName() + "' is created");
    }

    private void createPane(Item item) {
        StackPane stackPane = new StackPane();

        //Border around Pane
        Rectangle border = new Rectangle(183, 235);
        border.setArcWidth(5);
        border.setArcHeight(5);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        border.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        border.setTranslateX(3);
        border.setTranslateY(3);

        //Label for name
        Label labelName = new Label(item.getName());
        labelName.setFont(Font.font("Yu Gothic UI", FontWeight.BOLD, FontPosture.REGULAR, 20));
        labelName.setTranslateY(10);

        //Label for price
        Label labelPrice = new Label(String.format("%,.2f", item.getPrice()) + "€");
        labelPrice.setFont(Font.font("Yu Gothic UI", FontWeight.MEDIUM, FontPosture.REGULAR, 15));
        labelPrice.setTranslateY(15);

        //Layout that contains name and price
        AnchorPane topAnchorPane = new AnchorPane();
        topAnchorPane.getChildren().addAll(labelName, labelPrice);
        AnchorPane.setLeftAnchor(labelName, 20.00);
        AnchorPane.setRightAnchor(labelPrice, 15.00);

        //sets grocery image and heart image on creation
        ImageView imgViewItem = null;
        ImageView imgHeartView = null;
        try {
            imgViewItem = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/groceries/" + item.getName() + ".png")).openStream()));
            imgHeartView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-umriss.png")).openStream()));

            imgViewItem.setFitHeight(160);
            imgViewItem.setFitWidth(160);
            imgHeartView.setFitHeight(30);
            imgHeartView.setFitWidth(30);
            imgHeartView.setLayoutX(16);
        } catch (IOException ioe) {
            ioe.getMessage();
        }

        //changes heartimage when hovered over
        ImageView finalImgHeartView = imgHeartView;
        finalImgHeartView.setOnMouseEntered(e -> {
            try {
                finalImgHeartView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/icons/herzform-silhouette.png")).openStream()));
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        });

        //changes heartimage when not hovered over
        finalImgHeartView.setOnMouseExited(e -> {
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

        //changes heartimage when clicked on
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

        //Layout that contains all information
        BorderPane innerPane = new BorderPane();
        innerPane.setPrefWidth(188);
        innerPane.setPrefHeight(240);
        innerPane.setTop(topAnchorPane);
        innerPane.setCenter(imgViewItem);

        //Spinner to select amount of items
        Spinner<Integer> spinner = new Spinner<>(0, 9, 1);
        spinner.setPrefHeight(30);
        spinner.setPrefWidth(50);

        //Button to add item to shopping cart with amount from Spinner
        Button button = new Button("Buy");
        button.setFont(Font.font("Yu Gothic UI", FontWeight.BOLD, FontPosture.REGULAR, 12));;
        button.setStyle("-fx-background-color: #022235; -fx-text-fill: white;");
        button.setPrefHeight(30);
        button.setPrefWidth(40);
        button.setOnAction(actionEvent -> {
            amountInCart += spinner.getValue();
            System.out.println(amountInCart + " " + item.getName() + " currently in shopping cart!");
        });

        //Layout that contains spinner and buy button
        HBox hbox = new HBox(spinner, button);
        AnchorPane.setRightAnchor(hbox, 10.0);

        //Layout that contains favor 'button' and hbox
        AnchorPane bottomAnchorPane = new AnchorPane();
        bottomAnchorPane.getChildren().addAll(finalImgHeartView, hbox);
        bottomAnchorPane.setTranslateY(-10.0);

        innerPane.setBottom(bottomAnchorPane);
        stackPane.getChildren().addAll(border, innerPane);

        itemPane = stackPane;
    }

    public StackPane getItemPane() {
        return itemPane;
    }
    public boolean isFavorite() {
        return favorite;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getAmountInCart() {
        return amountInCart;
    }
}
