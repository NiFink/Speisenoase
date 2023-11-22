package mainpackage;




import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class User {



    private String userName;

    private String userEmail;

    private String password;

    //private Grocery[] favourites;

    private String[] favourites;






    public User(String userEmail, String userName, String password) {

        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        favourites = new String[]{};

    }


    public User(String userEmail, String userName, String password, String[] favourites){
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        this.favourites = favourites;

    }

    public User(){

    }







    @Override

    public String toString(){

        return "\n user: \n name: " + userName + "\n email: " + userEmail + "\n password: " + password + "\n favourite Items: " + Arrays.toString(favourites);
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

    public String[] getFavourites() {
        return favourites;
    }

    public void setFavourites(String[] favourites) {
        this.favourites = favourites;
    }
}
