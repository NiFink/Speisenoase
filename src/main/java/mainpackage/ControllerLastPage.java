package mainpackage;

import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.itempackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class ControllerLastPage{

    private boolean selectPurchase = false;
    @FXML
    private VBox vboxPurchase;
    @FXML
    private VBox vboxPurchaseIntern;
    @FXML
    private Button btHome;
    @FXML
    private Button btPurchase;
    @FXML
    private Button btProfil;
    @FXML
    private Label lbAddress;
    @FXML
    private Label lbAddressData;


    private Paint originalTextFill = Color.web("#ffffff");
    private Paint originalBackgroundColor  = Color.web("#022235");


    private final UserManager userManager = UserManager.getInstance();
    private final ShoppingCart shoppingCart = ShoppingCart.getInstance();
    private static final Logger log = LogManager.getLogger(ItemManager.class);



    public void initialize() {
        btProfil.setText(userManager.getActiveUser().getUserName());

        setLbAddressData();

        vboxPurchase.setTranslateX(350);
        vboxPurchase.setVisible(false);
        btPurchase.setTranslateX(350);
        lbAddressData.setTranslateX(209);
        lbAddress.setTranslateX(209);

        for (Purchase purchase : shoppingCart.getPurchaseList()) {
            if (purchase.getAmount() > 0) {
                vboxPurchaseIntern.getChildren().add(createCostAPane(purchase));
            }
        }
        log.debug("Purchases are loaded in vboxPurchase");
    }


    @FXML
    private void checkBtProfilClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil");
    }
    @FXML
    private void checkBtBackClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Checkout.fxml", "Checkout");
    }
    @FXML
    private void checkBtHomeClick() {
        ShoppingCart.getInstance().clearShoppingCart();
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "MainPage");
    }
    @FXML
    private void checkBtPurchasesClick() {

        TranslateTransition t1 = new TranslateTransition();
        TranslateTransition t2 = new TranslateTransition();
        TranslateTransition t3 = new TranslateTransition();
        TranslateTransition t4 = new TranslateTransition();
        t1.setNode(vboxPurchase);
        t2.setNode(btPurchase);
        t3.setNode(lbAddress);
        t4.setNode(lbAddressData);
        t1.setDuration(Duration.millis(1000));
        t2.setDuration(Duration.millis(1000));
        t3.setDuration(Duration.millis(1000));
        t4.setDuration(Duration.millis(1000));
        if(selectPurchase == false){
            selectPurchase = true;
            vboxPurchase.setVisible(true);
            t1.setByX(-350);
            t2.setByX(-350);
            t3.setByX(-209);
            t4.setByX(-209);

        }
        else {
            selectPurchase = false;
            t1.setByX(350);
            t2.setByX(350);
            t3.setByX(209);
            t4.setByX(209);
        }
        t1.play();
        t2.play();
        t3.play();
        t4.play();
    }


    @FXML
    private void hoverbtHome() {
        invertColorsOnHover(btHome);
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
        nameLabel.setLayoutX(72.0);
        nameLabel.setLayoutY(9.0);
        nameLabel.setPrefHeight(25.0);
        nameLabel.setPrefWidth(149.0);
        nameLabel.setTextFill(Color.web("#022235"));
        nameLabel.setFont(new Font("System Bold", 16.0));

        Label amountLabel = new Label(String.valueOf(purchase.getAmount()) + "x");
        amountLabel.setLayoutX(221.0);
        amountLabel.setLayoutY(9.0);
        amountLabel.setPrefHeight(25.0);
        amountLabel.setPrefWidth(39.0);
        amountLabel.setTextFill(Color.web("#022235"));
        amountLabel.setFont(new Font("System Bold", 16.0));

        Label priceLabel = new Label(String.format("%.2f€", purchase.getPrice()));
        priceLabel.setLayoutX(273.0);
        priceLabel.setLayoutY(9.0);
        priceLabel.setPrefHeight(25.0);
        priceLabel.setPrefWidth(63.0);
        priceLabel.setTextFill(Color.web("#022235"));
        priceLabel.setFont(new Font("System Bold", 16.0));

        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/groceries/" + purchase.getName() + ".png").toExternalForm()));
        imageView.setFitHeight(34.0);
        imageView.setFitWidth(39.0);
        imageView.setLayoutX(6.0);
        imageView.setLayoutY(5.0);
        imageView.setPreserveRatio(true);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(nameLabel, amountLabel, priceLabel, imageView);

        return anchorPane;
    }



    private void setLbAddressData(){
        lbAddressData.setText("Name:" + userManager.getActiveUser().getFirstName() + " " +  userManager.getActiveUser().getLastName() + "\n" +
                "Address:" + userManager.getActiveUser().getAddress() + "\n" +
                "City:" + userManager.getActiveUser().getCity());
    }
}
