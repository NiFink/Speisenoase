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
    private static Logger log = LogManager.getLogger(ItemManager.class);
    @FXML
    protected void checkbtLoginClick(ActionEvent event) throws IOException {
        //TODO: If abfrage ob das Passwort stimmt mit der Datenbank eintrag überein

        if(tbUsername.getText().isBlank() == false && pbPassword.getText().isBlank() == false && userManager.userLogin(tbUsername.getText(), pbPassword.getText())){
            try {
                log.info("User: " + tbUsername.getText() + " has logged in");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 860, 550);
                Stage stage = Main.getStage();
                stage.setTitle("Mainpage");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        else {
            log.info("The user is attempting to log in with incorrect information.");
            lbLoginfailed.setText("Please enter a correct username and password.");
        }

    }
    @FXML
    protected void checkbtRegisterClick(ActionEvent event) throws IOException{

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 860, 550);
            Stage stage = Main.getStage();
            stage.setTitle("Register");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
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
