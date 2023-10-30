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

public class ControllerRegister {
    @FXML
    private Button btBack;
    @FXML
    private Button btRegister;
    @FXML
    private TextField tbUsername;
    @FXML
    private PasswordField pbPassword;
    @FXML
    protected void checkRegisterClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Mainpage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 775, 506);
            Stage stage = Main.getStage();
            stage.setTitle("Mainpage");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public void checkBackClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 775, 506);
            Stage stage = Main.getStage();
            stage.setTitle("Mainpage");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
