package mainpackage;




import java.util.Arrays;
import java.util.Objects;


public class User {



    private String userName;

    private String userEmail;

    private String password;

    private String[] favourites;

    //delivery info:

    private String firstName;
    private String lastName;
    private String address;
    private String city;





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

    public User(){}



    public User(User user) {
        this.userEmail = user.userEmail;
        this.userName = user.userName;
        this.password = user.password;
        this.favourites = user.favourites;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.address = user.address;
        this.city = user.city;
    }

    public User(User user, String firstName, String lastName, String address, String city){
        this.userEmail = user.userEmail;
        this.userName = user.userName;
        this.password = user.password;
        this.favourites = user.favourites;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }


    @Override

    public String toString(){
        if(firstName != null){
            return " user: \n name: " + userName + "\n email: " + userEmail + "\n password: " + password + "\n favourite Items: " + Arrays.toString(favourites) + "\n full name: " + firstName + " " +  lastName + "\n address: " + address + ", " + city;

        }

        return " user: \n name: " + userName + "\n email: " + userEmail + "\n password: " + password + "\n favourite Items: " + Arrays.toString(favourites);
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
        return Objects.requireNonNullElseGet(favourites, () -> new String[0]);
    }

    public void setFavourites(String[] favourites) {
        this.favourites = favourites;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
