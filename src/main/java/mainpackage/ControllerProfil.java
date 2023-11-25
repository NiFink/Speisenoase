package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class ControllerProfil {

    @FXML
    private Button btHome;

    @FXML
    protected void checkBtHomeClick(ActionEvent event) {
        System.exit(0);
    }

}
