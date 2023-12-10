package mainpackage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
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
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    private static final Logger log = LogManager.getLogger(ControllerMainPage.class);

    public void initialize() {
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
        setUserManager(UserManager.getInstance());
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
    }

    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }

}
