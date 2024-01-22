package mainpackage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.Arrays;

public class UserManager {

   private User activeUser;
   private static UserManager instance;

    private static final Logger logger = LogManager.getLogger(UserManager.class);

    public static UserManager getInstance(){

        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

     boolean registerNewUser(String username, String userEmail, String userPassword) {

        User user = new User(userEmail, username, userPassword);

         ObjectMapper objectMapper = new ObjectMapper();

         try {

             //getting current userData from json file:

             ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));

             if (userData.get(username) == null) {

                 // adding updated user as node:

                 JsonNode newUser = objectMapper.valueToTree(user);

                 userData.set(user.getUserName(), newUser);

                 JsonNode updatedUserData = userData;

                 //writing new data node to json file:
                 PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
                 objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
                 logger.debug("added " + user + "\n successfully");
                 setActiveUser(user);
                 return true;


             } else {
                 logger.debug("Failed registration - username is not available");

             }
         }
         catch(IOException e){
             logger.error("IOException: " + e.getMessage());
         }

         return false;

    }

    public boolean userLoginCheck(String username, String password) {

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode userData = objectMapper.readTree(new File("src/main/resources/json/userData.json"));
            JsonNode userNode = userData.get(username);
            User user = objectMapper.treeToValue(userNode, User.class);
            if(user == null){
                logger.debug("wrong username");
                return false;
            }
            if(!password.equals(user.getPassword())){

                logger.debug("incorrect password");
                return false;
            }

            else {
                logger.debug("successfully logged in as " + username);
                setActiveUser(user);
                return true;
            }
        }

        catch(IOException e){
            logger.error("IOException." + e.getMessage());
            return false;
        }



    }

    public boolean updateUserData(User oldUser, User newUser) {

         ObjectMapper objectMapper = new ObjectMapper();

        //getting current userData from json file:
        try {

            ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));


            if (newUser.getUserName().equals(oldUser.getUserName()) || userData.get(newUser.getUserName()) == null) {

                userData.set(newUser.getUserName(), objectMapper.valueToTree(newUser));

                // if updating username:
                if (newUser.getUserName() != oldUser.getUserName()) {
                    userData.remove(oldUser.getUserName());
                }

                JsonNode updatedUserData = userData;

                //writing new data node to json file:
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
                this.activeUser = newUser;
                logger.debug("updated userdata successfully");
                return true;
            }
        }

            catch(IOException e){
            logger.error("IOException: " + e.getMessage());
            return false;
            }

            logger.debug("update failed - username exists already");
            return false;


    }

    void changePassword(User user, String newpassword) {


             User newUser = new User(user);
             newUser.setPassword(newpassword);

             if(updateUserData(user, newUser));
             logger.debug(user.getUserName() + " 's password: " + user.getPassword() + " updated to : " + newUser.getPassword());
             user.setPassword(newpassword);

    }

    boolean changeUsername(User user, String newUserName) {

         User newUser = new User(user);
         newUser.setUserName(newUserName);
         if(updateUserData(user, newUser)){
             logger.debug(user.getUserName() + " 's name updated to : " + newUser.getUserName());
             user.setUserName(newUserName);
             return true;
         }
         return false;
    }

    void changeUserEmail(User user, String newEmail) {

        User newUser = new User(user);
        newUser.setUserEmail(newEmail);
        if(updateUserData(user, newUser)){
            logger.debug(user.getUserName() + " 's email: " + user.getUserEmail() + " updated to : " + newUser.getUserEmail());
            user.setUserEmail(newEmail);
        }
    }

    public void updateFavorites(User user, String[] favourites) {

         User newUser = new User(user);
         newUser.setFavourites(favourites);
         if(updateUserData(user, newUser)) {
             logger.debug(user.getUserName() + " 's favourites: " + Arrays.toString(user.getFavourites()) + " updated to : " + Arrays.toString(newUser.getFavourites()));
             user.setFavourites(favourites);
         }

    }

    void updateDeliveryInfo(User user, String firstName, String lastName, String address, String city) {
        User newUser = new User(user, firstName, lastName, address, city);
        if(updateUserData(user, newUser)) {
            logger.debug((user.getUserName() + " 's delivery data has been updated to: " + firstName + " " + lastName + ", address: " + address + ", " + city + "."));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setCity(city);
        }

    }

    void deleteUser(User user) {

         ObjectMapper objectMapper = new ObjectMapper();
         try {
             ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));
             userData.remove(user.getUserName());
             JsonNode updatedUserData = userData;
             PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
             objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
             logger.debug("following user is no longer in userData: \n" + user);
         }
         catch(IOException e){
             logger.error("IOException: " + e.getMessage());
         }
         }





    public User getActiveUser() {
        return new User(activeUser);
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }


}
