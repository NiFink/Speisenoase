package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainpackage.Exceptions.WrongEntriesException;
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



    private final UserManager userManager = UserManager.getInstance();
    private static final Logger log = LogManager.getLogger(ControllerLogin.class);



    @FXML
    private void checkbtLoginClick() throws IOException {
        validateLogin();
    }
    @FXML
    private void checkbtRegisterClick() throws IOException{

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Register.fxml", "Register");
        log.debug("Register scene has been loaded");
    }
    @FXML
    private void validateLogin() throws IOException{
        try {
            if(!tbUsername.getText().isBlank() && !pbPassword.getText().isBlank() && userManager.userLoginCheck(tbUsername.getText(), pbPassword.getText())){
                log.info("User: " + tbUsername.getText() + "has logged in");
                Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
                sceneSwitcher.switchTo("MainPage.fxml", "Mainpage");

                log.debug("Logged in successfully");
            }
            else {
                log.info("The user is attempting to log in with incorrect information.");
                lbLoginfailed.setText("Please check your entries.");
                throw new WrongEntriesException();
            }
        } catch (WrongEntriesException wee){
            log.warn("Fehlermeldung: " + wee.getMessage());
        }
    }
}
