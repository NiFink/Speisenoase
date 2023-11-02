package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {
    @FXML
    private Button btLogin;
    @FXML
    private Button btRegister;
    @FXML
    private TextField tbUsername;
    @FXML
    private PasswordField pbPassword;
    @FXML
    protected void checkLoginClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Mainpage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 550);
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
    protected void checkRegisterClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Register.fxml"));
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


}
