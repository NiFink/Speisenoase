package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mainpackage.itempackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerMainPage{

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
    private final ItemManager itemController = new ItemManager();
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    private static final Logger log = LogManager.getLogger(ControllerMainPage.class);

    public void initialize() {
        scrollPaneItems.setContent(itemController.getItempaneCategory("all"));
        setUserManager(UserManager.getInstance());
        btProfil.setText(userManager.getActiveUser().getUserName());
    }

    @FXML
    protected void search() {
        scrollPaneItems.setContent(itemController.getItempaneName(tbSearch.getText().toLowerCase()));
    }

    @FXML
    protected void checkBtVegetablesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemController.getItempaneCategory("vegetable"));
    }

    @FXML
    protected void checkBtFruitClick(ActionEvent event) {
        scrollPaneItems.setContent(itemController.getItempaneCategory("fruit"));
    }

    @FXML
    protected void checkBtSweetiesClick(ActionEvent event) {
        scrollPaneItems.setContent(itemController.getItempaneCategory("sweets"));
    }

    @FXML
    public void checkBtBakeryClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemController.getItempaneCategory("bakery"));
    }

    @FXML
    protected void checkBtLiquirClick(ActionEvent event) {
        scrollPaneItems.setContent(itemController.getItempaneCategory("beverage"));
    }

    @FXML
    public void checkBtTagClick(ActionEvent actionEvent) {
        scrollPaneItems.setContent(itemController.getItempaneFavs());
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
        scrollPaneItems.setContent(itemController.getItempaneCategory("all"));
    }

    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }

}
