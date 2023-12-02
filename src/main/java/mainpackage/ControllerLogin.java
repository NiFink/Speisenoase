package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerLogin {
    @FXML
    private Label lbLoginfailed;
    @FXML
    private TextField tbUsername;
    @FXML
    private PasswordField pbPassword;

    private UserManager userManager = new UserManager();
    private ControllerMainPage controllerMainPage = new ControllerMainPage();

    private static Logger log = LogManager.getLogger(ItemManager.class);
    @FXML
    protected void checkbtLoginClick(ActionEvent event) throws IOException {
        //TODO: If abfrage, ob das Passwort mit der Datenbank eintrag überein stimmt

        if(tbUsername.getText().isBlank() == false && pbPassword.getText().isBlank() == false && userManager.userLoginCheck(tbUsername.getText(), pbPassword.getText())){
            log.info("User: " + tbUsername.getText() + "has logged in");
            Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
            sceneSwitcher.switchTo("MainPage.fxml", "Mainpage", 860, 550);

            log.debug("Login succesfully");

        }
        else {
            log.info("The user is attempting to log in with incorrect information.");
            lbLoginfailed.setText("Please enter a correct username and password.");
        }


    }
    @FXML
    protected void checkbtRegisterClick(ActionEvent event) throws IOException{

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Register.fxml", "Register", 860, 550);
        log.debug("Register scene has been loaded");
    }
    @FXML
    protected void checkbtExitClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void validateLogin(){
        //TODO: Database connection
    }


}
