package mainpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mainpackage.Exceptions.WrongEntriesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
    private void checkbtLoginClick() {
        validateLogin();
    }

    @FXML
    private void checkbtRegisterClick() {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Register.fxml", "Register");
        log.debug("Register scene has been loaded");
    }

    @FXML
    private void validateLogin() {
        try {
            if (!tbUsername.getText().isBlank() && !pbPassword.getText().isBlank() && userManager.userLoginCheck(tbUsername.getText(), pbPassword.getText())) {
                log.info("User: " + tbUsername.getText() + "has logged in");
                Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
                sceneSwitcher.switchTo("MainPage.fxml", "Mainpage");

                log.debug("Logged in successfully");
            } else {
                log.info("The user is attempting to log in with incorrect information.");
                lbLoginfailed.setText("Please check your entries.");
            }
        } catch (WrongEntriesException wee) {
            log.warn("WrongEntriesException. " + wee.getMessage());
        }
    }
}
