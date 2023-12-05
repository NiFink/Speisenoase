package mainpackage;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ControllerRegister {
    @FXML
    private Label lbRegisterfailed;
    @FXML
    private TextField tbUsername;
    @FXML
    private TextField tbEmail;
    @FXML
    private PasswordField pbPassword;
    @FXML
    private PasswordField pbPasswordcheck;

    private final UserManager userManager = UserManager.getInstance();
    private static final Logger log = LogManager.getLogger(ControllerRegister.class);
    @FXML
    protected void checkRegisterClick(ActionEvent event) throws IOException, ParseException {

        if(!tbUsername.getText().isBlank() && !pbPassword.getText().isBlank() && !pbPasswordcheck.getText().isBlank() && !tbEmail.getText().isBlank()) {
            if(!pbPassword.getText().equals(pbPasswordcheck.getText())) {
                lbRegisterfailed.setText("You have to enter the same Password twice!");
                log.info("Registration failed");
            }
            else if (userManager.registerNewUser(tbUsername.getText(), tbEmail.getText(), pbPassword.getText())){
                Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
                sceneSwitcher.switchTo("MainPage.fxml", "Mainpage", 860, 550);
                log.debug("Registered successfully");
            }
        }
        else{
            lbRegisterfailed.setText("Please fill in all fields.");
            log.debug("Registeration failed");
        }
    }


    @FXML
    protected void checkBackClick(ActionEvent event) {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Login.fxml", "Login", 860, 550);

    }
    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }

    private void valideRegister(){

    }
}
