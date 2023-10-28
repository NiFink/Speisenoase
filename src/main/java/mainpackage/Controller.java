package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button btLogin;
    @FXML
    private void sayHello(ActionEvent event) {

        System.out.println("Hello!");
    }
}
