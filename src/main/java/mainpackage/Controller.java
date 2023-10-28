package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button btLogin;
    @FXML
    private Button btRegister;
    @FXML
    private TextField tbUsername;
    @FXML
    private PasswordField pbPassword;
    @FXML
    private void sayHello(ActionEvent event) {

        System.out.println("Hello!");
    }
}
