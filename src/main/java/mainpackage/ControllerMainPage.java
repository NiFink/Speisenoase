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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private Button  btProfil;
    @FXML
    private Button btShoppingcart;

    private final ItemManager itemManager = new ItemManager();

    public void initialize(){
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
    }

    @FXML
    protected void search(){
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
        scrollPaneItems.setContent(itemManager.getItempaneCategory("all"));
    }
    @FXML
    protected void checkBtProfilClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Profil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 860, 550);
            Stage stage = Main.getStage();
            stage.setTitle("Profil");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.getMessage();
        }

    }
    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }


}
