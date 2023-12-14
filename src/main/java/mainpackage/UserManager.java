package mainpackage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

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

     boolean registerNewUser(String username, String userEmail, String userPassword) throws IOException, ParseException {

        User user = new User(userEmail, username, userPassword);

         ObjectMapper objectMapper = new ObjectMapper();

         //getting current userData from json file:

         ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));

         if(userData.get(username) == null){

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


         }

        else {
            logger.debug("Failed registration - username is not available");
            return false;
        }


    }

    public boolean userLoginCheck(String username, String password) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode userData = objectMapper.readTree(new File("src/main/resources/json/userData.json"));
        try{
            JsonNode userNode = userData.get(username);
            User user = objectMapper.treeToValue(userNode, User.class);
            if(user == null){
                logger.debug("wrong username :(");
                return false;
            }
            if(!password.equals(user.getPassword())){

                logger.debug("incorrect password :(");
                return false;
            }

            else {
                logger.debug("successfully logged in as " + username);
                setActiveUser(user);
                return true;
            }
        }

        catch(Exception e){
            System.out.println(e);
            return false;
        }



    }

    public boolean updateUserData(User oldUser, User newUser) throws IOException {

         ObjectMapper objectMapper = new ObjectMapper();

        //getting current userData from json file:

        ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));


            if(newUser.getUserName().equals(oldUser.getUserName()) || userData.get(newUser.getUserName()) == null) {

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
            logger.debug("update failed - username exists already");
            return false;


    }

    void changePassword(User user, String newpassword) throws IOException {


             User newUser = new User(user);
             newUser.setPassword(newpassword);

             if(updateUserData(user, newUser));
             logger.debug(user.getUserName() + " 's password: " + user.getPassword() + " updated to : " + newUser.getPassword());
             user.setPassword(newpassword);

    }

    boolean changeUsername(User user, String newUserName) throws IOException {

         User newUser = new User(user);
         newUser.setUserName(newUserName);
         if(updateUserData(user, newUser)){
             logger.debug(user.getUserName() + " 's name updated to : " + newUser.getUserName());
             user.setUserName(newUserName);
             return true;
         }
         return false;
    }

    void changeUserEmail(User user, String newEmail) throws IOException {

        User newUser = new User(user);
        newUser.setUserEmail(newEmail);
        if(updateUserData(user, newUser)){
            logger.debug(user.getUserName() + " 's email: " + user.getUserEmail() + " updated to : " + newUser.getUserEmail());
            user.setUserEmail(newEmail);
        }
    }

    private void updateFavorites(User user, String[] favourites) throws IOException {

         User newUser = new User(user);
         newUser.setFavourites(favourites);
         if(updateUserData(user, newUser)) {
             logger.debug(user.getUserName() + " 's favourites: " + Arrays.toString(user.getFavourites()) + " updated to : " + Arrays.toString(newUser.getFavourites()));
             user.setFavourites(favourites);
         }

    }

    void deleteUser(User user) throws IOException {

         ObjectMapper objectMapper = new ObjectMapper();
         ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));
         userData.remove(user.getUserName());
         JsonNode updatedUserData = userData;
         PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
         objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
         logger.debug("successfully deleted: " + user);
    }




    public User getActiveUser() {
        return new User(activeUser);
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }




}
