package mainpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.itempackage.ItemManager;

import java.util.List;

public class ControllerCheckout {
    @FXML
    private VBox vboxCosts;

    @FXML
    private Button btProfil;

    @FXML
    private Label lbTotal;

    double total = 0.0;
    private List<Purchase> purchaseList;
    private UserManager userManager;

    private final ItemManager itemManager = ItemManager.getInstance();
    private final ControllerShoppingCart shoppingCart = ControllerShoppingCart.getInstance();

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

    private void updateVBox() {
        vboxCosts.getChildren().clear();

        for (Purchase purchase : purchaseList) {
            if(purchase.getAmount() > 0){
                vboxCosts.getChildren().add(createCostAPane(purchase));
            }

        }
        lbTotal.setText("Total: " + String.format("%.2f", shoppingCart.getPurchaseTotal())+ "€");
    }

    @FXML
    protected void checkBtProfilClick() {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil", 860, 550);

    }

    @FXML
    protected void checkBtBackClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("ShoppingCart.fxml", "ShoppingCart", 860, 550);
    }

    private AnchorPane createCostAPane(Purchase purchase) {
        Label nameLabel = new Label(purchase.getName());
        nameLabel.setLayoutX(14.0);
        nameLabel.setLayoutY(9.0);
        nameLabel.setPrefHeight(25.0);
        nameLabel.setPrefWidth(149.0);
        nameLabel.setTextFill(Color.web("#022235"));
        nameLabel.setFont(new Font("System Bold", 16.0));

        Label amountLabel = new Label(String.format("%d",purchase.getAmount()) + "x");
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

        return anchorPane;
    }
}
