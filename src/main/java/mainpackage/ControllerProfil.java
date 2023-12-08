package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerProfil {

    @FXML
    private Label lbUsername;
    @FXML
    private Label lbUserEmail;
    @FXML
    private Label lbErrorUsername;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorPassword;
    @FXML
    private TextField tbUsername;
    @FXML
    private TextField tbEmail;
    @FXML
    private PasswordField pbPassword;
    @FXML
    private PasswordField pbNewPassword;
    @FXML
    private PasswordField pbNewPasswordCheck;
    @FXML
    private VBox passwordChange;





    private final ControllerMainPage controllerMainPage = new ControllerMainPage();
    private static final Logger log = LogManager.getLogger(ControllerProfil.class);
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }



    public void initialize(){
        setUserManager(UserManager.getInstance());
        User user = userManager.getActiveUser();
        lbUsername.setText(user.getUserName());
        lbUserEmail.setText(user.getUserEmail());
        tbUsername.setText(user.getUserName());
        tbEmail.setText(user.getUserEmail());
        lbUsername.requestFocus();

    }

    @FXML
    protected void checkbtnConfirmUsernameClick(MouseEvent event) throws IOException {
        if(tbUsername.getText().isBlank()){
            lbErrorUsername.setText("Username can't vanish into thin air!");
            lbErrorUsername.setVisible(true);


        }
       else if(tbUsername.getText().equals(userManager.getActiveUser().getUserName())){
            lbErrorUsername.setText("Seems like you wanna keep your old username...");
            lbErrorUsername.setVisible(true);
        }
       else if(userManager.changeUsername(userManager.getActiveUser(), tbUsername.getText())){
           lbErrorUsername.setVisible(false);
           initialize();

        }
       else{
            lbErrorUsername.setText("Sorry, but that name's already been taken.");
            lbErrorUsername.setVisible(true);

        }

    }

    @FXML
    protected void changeUserPassword(){}

    @FXML
    protected void enablePasswordChange(){}

    @FXML
    protected void enableAccountDeletion(){}

    @FXML
    protected void disablePasswordChange(){}

    @FXML
    protected void changePassword(){}






    @FXML
    protected void checkBtHomeClick(ActionEvent event) throws IOException {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "Mainpage", 860, 550);

    }

}
