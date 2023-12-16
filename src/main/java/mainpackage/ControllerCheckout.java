package mainpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.itempackage.ItemManager;

import java.util.List;

public class ControllerCheckout {
    @FXML
    private VBox vboxCosts;
    @FXML
    private Button btProfil;
    @FXML
    private Label lbTotal;

    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfAdress;
    @FXML
    private TextField tfCity;


    private final UserManager userManager = UserManager.getInstance();
    private final ShoppingCart shoppingCart = ShoppingCart.getInstance();
    private final ItemManager itemManager = ItemManager.getInstance();

    public void initialize() {
        btProfil.setText(userManager.getActiveUser().getUserName());
        updateVBox();
    }

    private void updateVBox() {
        vboxCosts.getChildren().clear();

        for (Purchase purchase : shoppingCart.getPurchaseList()) {
            if (purchase.getAmount() > 0) {
                vboxCosts.getChildren().add(createCostAPane(purchase));
            }
        }

        lbTotal.setText("Total: " + String.format("%.2f", shoppingCart.getTotal()) + "€");
    }

    @FXML
    protected void checkBtProfilClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil");
    }

    @FXML
    protected void checkBtBackClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("ShoppingCart.fxml", "ShoppingCart");
    }
    @FXML
    protected void checkBtBuyClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("LastPage.fxml", "LastPage");
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

        return anchorPane;
    }

    private void setUserAdress(){
    //TODO: Update user
        //userManager.setActiveUser() = tfFirstName.getText();
        //userManager.setActiveUser() = tfLastName.getText();
        //userManager.setActiveUser() = tfAdress.getText();
        //userManager.setActiveUser() = tfCity.getText();
    }
}
