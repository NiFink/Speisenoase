package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerProfil {

    private ControllerMainPage controllerMainPage = new ControllerMainPage();
    private static Logger log = LogManager.getLogger(ItemManager.class);


    @FXML
    protected void checkBtHomeClick(ActionEvent event) throws IOException {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "Mainpage", 860, 550);

    }

}
