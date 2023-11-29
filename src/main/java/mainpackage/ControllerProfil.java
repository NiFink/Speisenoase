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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 860, 550);
            Stage stage = Main.getStage();
            stage.setTitle("Mainpage");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            log.debug("MainPage has generated");

            controllerMainPage.initializeMainPage();
            log.debug("MainPage has initialized");
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
