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

    private UserManager userManager = new UserManager();
    @FXML
    protected void checkRegisterClick(ActionEvent event) throws IOException, ParseException {

        if(tbUsername.getText().isBlank() == false && pbPassword.getText().isBlank() == false && pbPasswordcheck.getText().isBlank() == false && tbEmail.getText().isBlank() == false ) {
            if(!pbPassword.getText().equals(pbPasswordcheck.getText())) {
                lbRegisterfailed.setText("You have to enter the same Password twice!");
            }
            else{
                Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
                sceneSwitcher.switchTo("MainPage.fxml", "Mainpage", 860, 550);
            }
        }
        else{
            lbRegisterfailed.setText("Please fill in all fields.");
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
