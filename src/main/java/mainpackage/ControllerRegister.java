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
                try {
                    userManager.registerNewUser(tbUsername.getText(), tbEmail.getText(), pbPassword.getText());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Mainpage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 860, 550);
                    Stage stage = Main.getStage();
                    stage.setTitle("Speisenoase");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.getMessage();
                }
            }


            //TODO: Alle Fehlerquellen durchgehen



        }

        else{
            lbRegisterfailed.setText("Please fill in all fields.");
        }
    }


    @FXML
    protected void checkBackClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
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
    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }

    private void valideRegister(){

    }
}
