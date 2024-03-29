package mainpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.itempackage.Item;
import mainpackage.itempackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private VBox vbAccountDeletion;
    @FXML
    private VBox vbDefault;

    private final int maxCharacters = 20;




    private static final Logger log = LogManager.getLogger(ControllerProfil.class);
    private UserManager userManager;
    private ItemManager itemManager = ItemManager.getInstance();

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
        tbUsername.setFocusTraversable(false);

    }

    @FXML
    private void confirmUsername(MouseEvent event) {
        if(tbUsername.getText().isBlank()){
            lbErrorUsername.setText("Username can't vanish into thin air!");
            lbErrorUsername.setVisible(true);

        }
        else if(tbUsername.getText().length() > maxCharacters){
            lbErrorUsername.setText("Username can only have up to " + maxCharacters + " characters.");
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
    private void changeUserEmail() {
        if(tbEmail.getText().isBlank()){
            lbErrorEmail.setText("Please enter your email address.");
            lbErrorEmail.setVisible(true);

        }
        else if(tbEmail.getText().length() > maxCharacters) {
            lbErrorEmail.setText("Email-address can only have up to " + maxCharacters + " characters.");
            lbErrorEmail.setVisible(true);
        }
        else if(tbEmail.getText().equals(userManager.getActiveUser().getUserEmail())){
            lbErrorEmail.setText("That's your current address.");
            lbErrorEmail.setVisible(true);
        }
        else if (isEmailValid(tbEmail.getText())){
            userManager.changeUserEmail(userManager.getActiveUser(), tbEmail.getText());
            lbErrorEmail.setVisible(false);
            initialize();
        }
        else {
            lbErrorEmail.setText("That's not the right email format.");
            lbErrorEmail.setVisible(true);
        }
    }


    @FXML
    private void enableAccountDeletion(){
        vbDefault.setVisible(false);
        vbDefault.setDisable(true);
        vbAccountDeletion.setDisable(false);
        vbAccountDeletion.setVisible(true);
    }
    @FXML
    private void disableAccountDeletion(){
        vbAccountDeletion.setDisable(true);
        vbAccountDeletion.setVisible(false);
        vbDefault.setVisible(true);
        vbDefault.setDisable(false);

    }
    @FXML
    private  void deleteAccount() {
        userManager.deleteUser(userManager.getActiveUser());
        Sceneswitcher sceneswitcher = Sceneswitcher.getInstance();
        sceneswitcher.switchTo("Login.fxml", "Login");
        userManager.setActiveUser(null);

    }
    @FXML
    private void enablePasswordChange(){
        vbDefault.setVisible(false);
        vbDefault.setDisable(true);
        passwordChange.setDisable(false);
        passwordChange.setVisible(true);
    }

    @FXML
    private void disablePasswordChange(){
        passwordChange.setDisable(true);
        passwordChange.setVisible(false);
        vbDefault.setVisible(true);
        vbDefault.setDisable(false);
    }

    @FXML
    private void changePassword() {

        if(pbPassword.getText().isBlank() || pbNewPassword.getText().isBlank()){
            lbErrorPassword.setText("Please fill in all fields!");
            lbErrorPassword.setVisible(true);
        }
        else if(pbNewPassword.getText().length() > maxCharacters) {
            lbErrorPassword.setText("Password can only have up to " + maxCharacters + " characters.");
            lbErrorPassword.setVisible(true);
        }
        else if(!pbPassword.getText().equals(userManager.getActiveUser().getPassword())){
            lbErrorPassword.setText("Please enter the right password!");
            lbErrorPassword.setVisible(true);
        }
        else if(!pbNewPassword.getText().equals(pbNewPasswordCheck.getText())){
            lbErrorPassword.setText("Please enter the same password twice!");
            lbErrorPassword.setVisible(true);
        }
        else{
            userManager.changePassword(userManager.getActiveUser(), pbNewPassword.getText());
            passwordChange.setDisable(true);
            passwordChange.setVisible(false);
            vbDefault.setDisable(false);
            vbDefault.setVisible(true);
        }
    }

    @FXML
    private void logOut() {
        userManager.updateFavorites(userManager.getActiveUser(), ItemManager.getInstance().getFavorites().toArray(new String[0]));
        ShoppingCart.getInstance().clearShoppingCart();
        Sceneswitcher sceneswitcher = Sceneswitcher.getInstance();
        sceneswitcher.switchTo("Login.fxml", "Login");
        userManager.setActiveUser(null);
    }


    @FXML
    private void checkBtHomeClick(ActionEvent event) throws IOException {

        Sceneswitcher sceneSwitcher = Sceneswitcher.getInstance();
        sceneSwitcher.switchTo("MainPage.fxml", "Mainpage");

    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
