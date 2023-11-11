package mainpackage;




import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class User {



    private String userName;

    private String userEmail;

    private String password;

    //private Grocery[] favourites;






    public User(String userEmail, String userName, String password) {

        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;

    }

    public User(){

    }







    @Override

    public String toString(){

        return "\n user: \n name: " + userName + "\n email: " + userEmail + "\n password: " + password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
