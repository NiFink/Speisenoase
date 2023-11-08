package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMainpage {
    @FXML
    private Button btVegetables;
    @FXML
    private Button btFruit;
    @FXML
    private Button btSweeties;

/*
<?import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView?>

            <FontAwesomeIconView fill="WHITE" glyphName="USER" layoutX="744.0" layoutY="67.0" size="40" wrappingWidth="24.142857551574707" />
            <FontAwesomeIconView fill="WHITE" glyphName="SEARCH" layoutX="297.0" layoutY="67.0" size="25" wrappingWidth="24.142857551574707" />
            <FontAwesomeIconView fill="WHITE" glyphName="GEAR" layoutX="803.0" layoutY="67.0" size="40" />
 */

    @FXML
    protected void checkBtVegetablesClick(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    protected void checkBtFruitClick(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    protected void checkBtSweetiesClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void checkBtLiquirClick(ActionEvent event) {
        System.exit(0);
    }



    @FXML
    protected void checkExitClick(ActionEvent event) {
        System.exit(0);
    }
}
