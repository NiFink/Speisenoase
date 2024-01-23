package mainpackage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.itempackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerMainPage{

    @FXML
    private ScrollPane scrollPaneItems;
    @FXML
    private TextField tbSearch;
    @FXML
    private Button btProfil;
    private final ItemManager itemManager = ItemManager.getInstance();
    private final UserManager userManager = UserManager.getInstance();
    private static final Logger log = LogManager.getLogger(ControllerMainPage.class);


    public void initialize() {
        itemManager.loadNodes(15);
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
        btProfil.setText(userManager.getActiveUser().getUserName());
        log.debug("All Items loaded succesfully in MainPage");
    }



    @FXML
    private void search() {
        scrollPaneItems.setContent(itemManager.getItempaneName(tbSearch.getText().toLowerCase()));
        log.debug("Searched loaded succesfully in MainPage");
    }
    @FXML
    private void checkBtVegetablesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("vegetable"));
    }
    @FXML
    private void checkBtFruitClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("fruit"));
    }
    @FXML
    private void checkBtSweetiesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("sweets"));
    }
    @FXML
    private void checkBtBakeryClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("bakery"));
    }
    @FXML
    private void checkBtLiquirClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("beverage"));
    }
    @FXML
    private void checkBtTagClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("favs"));
    }
    @FXML
    private void checkBtProfilClick(Event event) {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil");
    }
    @FXML
    private void checkBtHomeClick() {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
    }
    @FXML
    private void checkBtShoppingCartClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("ShoppingCart.fxml", "ShoppingCart");
    }
}
