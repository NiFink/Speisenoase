package mainpackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mainpackage.itempackage.ItemNodeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerMainPage {

    @FXML
    private ScrollPane scrollPaneItems;
    @FXML
    private TextField tbSearch;
    @FXML
    private Button btVegetables;
    @FXML
    private Button btFruit;
    @FXML
    private Button btSweeties;
    @FXML
    private Button btProfil;
    @FXML
    private Button btShoppingcart;
    private final ItemNodeController itemNodeController = new ItemNodeController();
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    private static final Logger log = LogManager.getLogger(ItemManager.class);

    public void initialize() {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("all"));
        setUserManager(UserManager.getInstance());
        btProfil.setText(userManager.getActiveUser().getUserName());
    }

    @FXML
    protected void search() {
        scrollPaneItems.setContent(itemNodeController.getItempaneName(tbSearch.getText().toLowerCase()));
    }

    @FXML
    protected void checkBtVegetablesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("vegetable"));
    }

    @FXML
    protected void checkBtFruitClick(ActionEvent event) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("fruit"));
    }

    @FXML
    protected void checkBtSweetiesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("sweets"));
    }

    @FXML
    public void checkBtBakeryClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("bakery"));
    }

    @FXML
    protected void checkBtLiquirClick(ActionEvent event) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("beverage"));
    }

    @FXML
    public void checkBtTagClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("all"));
    }

    @FXML
    protected void checkBtProfilClick(ActionEvent event) {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Profil.fxml", "Profil", 860, 550);
        /*
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Profil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 860, 550);
            Stage stage = Main.getStage();
            stage.setTitle("Profil");
            stage.setResizable(true);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.getMessage();
        }

         */

    }

    @FXML
    protected void checkBtHomeClick(MouseEvent event) {
        scrollPaneItems.setContent(itemNodeController.getItempaneCategory("all"));
    }

    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }


}
