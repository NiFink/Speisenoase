package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.itempackage.ItemManager;
import mainpackage.itempackage.ItemNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerShoppingCart {

    @FXML
    private VBox vboxPurchase;

    @FXML
    private Button btProfil;
    @FXML
    private ScrollPane spItems;

    private List<Purchase> purchaseList;
    private UserManager userManager;
    private final ItemManager itemManager = ItemManager.getInstance();

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void initialize() {
        setUserManager(UserManager.getInstance());
        btProfil.setText(userManager.getActiveUser().getUserName());

        purchaseList =
                itemManager.getItemsShoppingCart()
                        .stream()
                        .map(x -> new Purchase(x.getName(), x.getPrice(), x.getAmountInCart()))
                                .toList();

        updateVBox();
    }


    @FXML
    protected void checkBtProfilClick(ActionEvent event) {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil", 860, 550);

    }

    @FXML
    protected void checkBtBackClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "MainPage", 860, 550);
    }


    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
        updateVBox();
    }

    private void updateVBox() {
        vboxPurchase.getChildren().clear();

        // Iteriere durch die Elemente und füge sie zur VBox hinzu
        for (Purchase purchase : purchaseList) {
            vboxPurchase.getChildren().add(createPurchaseAPane(purchase));
        }
    }

    // Methode zum Erstellen einer AnchorPane für ein Produkt
    private AnchorPane createPurchaseAPane(Purchase purchase) {
        // Erstelle ImageView
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/groceries/" + purchase.getName() + ".png").toExternalForm()));
        imageView.setFitHeight(124.0);
        imageView.setFitWidth(117.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(17.0);

        // Erstelle Label für Name
        Label nameLabel = new Label("Name: " + purchase.getName());
        nameLabel.setLayoutX(152.0);
        nameLabel.setLayoutY(32.0);
        nameLabel.setPrefHeight(30.0);
        nameLabel.setPrefWidth(246.0);
        nameLabel.setTextFill(Color.web("#022235"));
        nameLabel.setFont(new Font("System Bold", 20.0));

        // Erstelle Label für Preis
        Label priceLabel = new Label("Price: " + String.format("%,.2f", purchase.getPrice()) + "€");
        priceLabel.setLayoutX(152.0);
        priceLabel.setLayoutY(92.0);
        priceLabel.setPrefHeight(30.0);
        priceLabel.setPrefWidth(124.0);
        priceLabel.setTextFill(Color.web("#022235"));
        priceLabel.setFont(new Font("System Bold", 20.0));

        // Erstelle Label für Menge
        Label amountLabel = new Label("Amount: " + purchase.getAmount());
        amountLabel.setLayoutX(300.0);
        amountLabel.setLayoutY(92.0);
        amountLabel.setPrefHeight(30.0);
        amountLabel.setPrefWidth(124.0);
        amountLabel.setTextFill(Color.web("#022235"));
        amountLabel.setFont(new Font("System Bold", 20.0));

        // Erstelle Button zum Löschen
        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(471.0);
        deleteButton.setLayoutY(92.0);
        deleteButton.setPrefHeight(30.0);
        deleteButton.setPrefWidth(65.0);
        deleteButton.setStyle("-fx-background-color: #022235;");
        deleteButton.setTextFill(javafx.scene.paint.Color.WHITE);

        // Erstelle AnchorPane und füge die erstellten Elemente hinzu
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView, nameLabel, priceLabel, amountLabel, deleteButton);

        return anchorPane;
    }
}
