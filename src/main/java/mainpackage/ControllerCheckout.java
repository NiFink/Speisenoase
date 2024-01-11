package mainpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.itempackage.ItemManager;

import java.io.IOException;

public class ControllerCheckout {
    @FXML
    private VBox vboxCosts;
    @FXML
    private Button btProfil;
    @FXML
    private Button btBuy;
    @FXML
    private Label lbTotal;

    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfCity;



    private final UserManager userManager = UserManager.getInstance();
    private final ShoppingCart shoppingCart = ShoppingCart.getInstance();



    public void initialize() {
        btProfil.setText(userManager.getActiveUser().getUserName());
        getUserAddress();
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
    private void checkBtProfilClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil");
    }
    @FXML
    private void checkBtBackClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("ShoppingCart.fxml", "ShoppingCart");
    }
    @FXML
    private void checkBtBuyClick() throws IOException {
        setUserAddress();
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("LastPage.fxml", "LastPage");
    }
    @FXML
    private void btBuyhover() {
        invertColorsOnHover(btBuy);
    }
    private void invertColorsOnHover(Button button) {
        button.setOnMouseEntered(e -> {
            button.setTextFill(Color.web("#022235"));
            button.setStyle("-fx-background-color: white;");
        });

        button.setOnMouseExited(e -> {
            button.setTextFill(Color.web("#ffffff"));
            button.setStyle("-fx-background-color: #022235;");
        });
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



    private void getUserAddress(){
        if(userManager.getActiveUser().getFirstName() != null){
            tfFirstName.setText(userManager.getActiveUser().getFirstName());
            tfLastName.setText(userManager.getActiveUser().getLastName());
            tfAddress.setText(userManager.getActiveUser().getAddress());
            tfCity.setText(userManager.getActiveUser().getCity());
        }
    }
    private void setUserAddress() throws IOException {

        if(!tfFirstName.getText().isBlank() || !tfLastName.getText().isBlank() || !tfAddress.getText().isBlank() || !tfCity.getText().isBlank()){
        userManager.updateDeliveryInfo(userManager.getActiveUser(), tfFirstName.getText(), tfLastName.getText(), tfAddress.getText(), tfCity.getText());
        }
        //TODO: error label

    }
}
