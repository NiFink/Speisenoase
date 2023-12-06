package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import mainpackage.itempackage.ItemManager;

public class ControllerShoppingCart {
    @FXML
    private Button btProfil;
    @FXML
    private ScrollPane spItems;
    private final ItemManager itemController = new ItemManager();

    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void initialize() {
        setUserManager(UserManager.getInstance());
        btProfil.setText(userManager.getActiveUser().getUserName());
        spItems.setContent(itemController.getItempaneFavs());
    }
    @FXML
    protected void checkBtProfilClick(ActionEvent event) {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil", 860, 550);

    }

    @FXML
    protected void checkBtBackClick(MouseEvent event) {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "MainPage", 860, 550);
    }
}
