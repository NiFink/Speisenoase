package mainpackage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private ItemManager itemManager;
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    private static final Logger log = LogManager.getLogger(ControllerMainPage.class);

    public void initialize() {
        setUserManager(UserManager.getInstance());
        setItemManager(ItemManager.getInstance());
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
        btProfil.setText(userManager.getActiveUser().getUserName());
    }

    @FXML
    protected void search() {
        scrollPaneItems.setContent(itemManager.getItempaneName(tbSearch.getText().toLowerCase()));
    }

    @FXML
    protected void checkBtVegetablesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("vegetable"));
    }

    @FXML
    protected void checkBtFruitClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("fruit"));
    }

    @FXML
    protected void checkBtSweetiesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("sweets"));
    }

    @FXML
    public void checkBtBakeryClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("bakery"));
    }

    @FXML
    protected void checkBtLiquirClick(ActionEvent event) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("beverage"));
    }

    @FXML
    public void checkBtTagClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("favs"));
    }

    @FXML
    protected void checkBtProfilClick(Event event) {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil", 860, 550);
    }

    @FXML
    protected void checkBtHomeClick() {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
    }
    @FXML
    protected void checkBtShoppingCartClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("ShoppingCart.fxml", "ShoppingCart", 860, 550);
        itemManager.updateShoppingCart();
    }

    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }

}
