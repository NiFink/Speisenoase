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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final int maxCharacters = 20;

    private final UserManager userManager = UserManager.getInstance();
    private static final Logger log = LogManager.getLogger(ControllerRegister.class);
    @FXML
    private void checkRegisterClick(ActionEvent event) throws IOException, ParseException {

        if(!tbUsername.getText().isBlank() && !pbPassword.getText().isBlank() && !pbPasswordcheck.getText().isBlank() && !tbEmail.getText().isBlank()) {
            if(!pbPassword.getText().equals(pbPasswordcheck.getText())) {
                lbRegisterfailed.setText("You have to enter the same Password twice!");
                log.warn("Registration failed, unequaled passwords");
            }
            else if(tbUsername.getText().length() > maxCharacters || pbPassword.getText().length() > maxCharacters || tbUsername.getText().length() > maxCharacters){
                lbRegisterfailed.setText("Your username is to long(max. " + maxCharacters + ")");
                log.warn("Registration failed, too many characters");
            }
            else if(!isEmailValid(tbEmail.getText())){
                lbRegisterfailed.setText("You have to enter a correct format of email");
                log.warn("Registration failed, false email format");
            }
            else if (userManager.registerNewUser(tbUsername.getText(), tbEmail.getText(), pbPassword.getText())){
                Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
                sceneSwitcher.switchTo("MainPage.fxml", "Mainpage");
                log.debug("Registered successfully");
            }
        }
        else{
            lbRegisterfailed.setText("Please fill in all fields.");
            log.warn("Registeration failed, not all fields are filled");
        }
    }


    @FXML
    private void checkBackClick(ActionEvent event) {
        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("Login.fxml", "Login");

    }
    @FXML
    private void checkExitClick(ActionEvent event) {
        System.exit(0);
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
