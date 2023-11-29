package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMainPage {
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


    @FXML
    protected void checkBtVegetablesClick(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    protected void checkBtFruitClick(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    protected void checkBtSweetiesClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void checkBtLiquirClick(ActionEvent event) {
        System.exit(0);
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
