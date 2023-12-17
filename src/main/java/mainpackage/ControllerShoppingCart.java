package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.itempackage.ItemManager;
import mainpackage.itempackage.ItemNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerShoppingCart {
    @FXML
    private VBox vboxPurchase;
    @FXML
    private VBox vboxCosts;
    @FXML
    private Button btProfil;
    @FXML
    private Label lbTotal;



    private final UserManager userManager = UserManager.getInstance();
    private final ShoppingCart shoppingCart = ShoppingCart.getInstance();
    private static final Logger log = LogManager.getLogger(ItemManager.class);



    public void initialize() {
        btProfil.setText(userManager.getActiveUser().getUserName());
        updateVBox();

        log.debug("Shoppingcart loaded succesfully, with selected purchases");
    }



    @FXML
    private void checkBtProfilClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil");
    }
    @FXML
    private void checkBtBackClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "MainPage");
    }
    @FXML
    private void checkBtCheckoutClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Checkout.fxml", "Checkout");
    }



    private void updateVBox() {
        vboxPurchase.getChildren().clear();
        vboxCosts.getChildren().clear();

        for (Purchase purchase : shoppingCart.getPurchaseList()) {
            if (purchase.getAmount() > 0) {
                vboxPurchase.getChildren().add(createPurchaseAPane(purchase));
                vboxCosts.getChildren().add(createCostAPane(purchase));
            }
        }

        lbTotal.setText("Total: " + String.format("%.2f", shoppingCart.getTotal()) + "€");

        log.debug("PurchasesAPane and CostAPane updated, because User change something");
    }
    private AnchorPane createPurchaseAPane(Purchase purchase) {
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/groceries/" + purchase.getName() + ".png").toExternalForm()));
        imageView.setFitHeight(124.0);
        imageView.setFitWidth(117.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(17.0);

        Label nameLabel = new Label(purchase.getName());
        nameLabel.setLayoutX(152.0);
        nameLabel.setLayoutY(32.0);
        nameLabel.setPrefHeight(30.0);
        nameLabel.setPrefWidth(246.0);
        nameLabel.setTextFill(Color.web("#022235"));
        nameLabel.setFont(new Font("System Bold", 18.0));

        Label priceLabel = new Label("Price: " + String.format("%,.2f", (purchase.getPrice()) * purchase.getAmount()) + "€");
        priceLabel.setLayoutX(152.0);
        priceLabel.setLayoutY(92.0);
        priceLabel.setPrefHeight(30.0);
        priceLabel.setPrefWidth(124.0);
        priceLabel.setTextFill(Color.web("#022235"));
        priceLabel.setFont(new Font("System Bold", 18.0));

        Label amountLabel = new Label("Amount: ");
        amountLabel.setLayoutX(300.0);
        amountLabel.setLayoutY(92.0);
        amountLabel.setPrefHeight(30.0);
        amountLabel.setPrefWidth(80.0);
        amountLabel.setTextFill(Color.web("#022235"));
        amountLabel.setFont(new Font("System Bold", 18.0));

        Spinner<Integer> amountSpinner = new Spinner<Integer>(0, 9, purchase.getAmount());
        amountSpinner.setLayoutX(400.0);
        amountSpinner.setLayoutY(92.0);
        amountSpinner.setPrefHeight(30.0);
        amountSpinner.setPrefWidth(50.0);
        amountSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateAmount(purchase, newValue);
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(460.0);
        deleteButton.setLayoutY(92.0);
        deleteButton.setPrefHeight(30.0);
        deleteButton.setPrefWidth(65.0);
        deleteButton.setStyle("-fx-background-color: #022235;");
        deleteButton.setTextFill(javafx.scene.paint.Color.WHITE);
        deleteButton.setOnAction(actionEvent -> {
            updateAmount(purchase, 0);
        });

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView, nameLabel, priceLabel, amountLabel, amountSpinner, deleteButton);

        log.debug("PurchaseAPane element loaded succesfully");

        return anchorPane;

    }
    private void updateAmount(Purchase purchase, Integer newAmount) {
        if (purchase.getAmount() != newAmount) {
            purchase.setAmount(newAmount);
            updateVBox();
        }
    }
    private AnchorPane createCostAPane(Purchase purchase) {
        Label nameLabel = new Label(purchase.getName());
        nameLabel.setLayoutX(14.0);
        nameLabel.setLayoutY(9.0);
        nameLabel.setPrefHeight(25.0);
        nameLabel.setPrefWidth(149.0);
        nameLabel.setTextFill(Color.web("#022235"));
        nameLabel.setFont(new Font("System Bold", 16.0));

        Label amountLabel = new Label(String.format("%d", purchase.getAmount()) + "x");
        amountLabel.setLayoutX(163.0);
        amountLabel.setLayoutY(9.0);
        amountLabel.setPrefHeight(25.0);
        amountLabel.setPrefWidth(39.0);
        amountLabel.setTextFill(Color.web("#022235"));
        amountLabel.setFont(new Font("System Bold", 18.0));

        Label priceLabel = new Label(String.format("%,.2f", (purchase.getPrice()) * purchase.getAmount()) + "€");
        priceLabel.setLayoutX(215.0);
        priceLabel.setLayoutY(9.0);
        priceLabel.setPrefHeight(25.0);
        priceLabel.setPrefWidth(63.0);
        priceLabel.setTextFill(Color.web("#022235"));
        priceLabel.setFont(new Font("System Bold", 16.0));

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(nameLabel, priceLabel, amountLabel);

        log.debug("CostAPane element loaded succesfully");

        return anchorPane;
    }
}
