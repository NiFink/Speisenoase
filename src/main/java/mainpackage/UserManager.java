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


    private static Logger logger = LogManager.getLogger(UserManager.class);

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
             logger.debug("added " + user.toString() + "\n successfully");
             activeUser = user;
             return true;


         }

        else {
            logger.debug("Failed registration - username is not available");
            return false;
        }


    }

    public boolean userLoginCheck(String username, String password) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));
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
                activeUser = user;
                return true;
            }
        }

        catch(Exception e){
            System.out.println(e);
            return false;
        }



    }

    private boolean updateUserData(User oldUser, User newUser) throws IOException {

         ObjectMapper objectMapper = new ObjectMapper();

        //getting current userData from json file:

        ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));


            if(newUser.getUserName() == oldUser.getUserName() || userData.get(newUser.getUserName()) == null) {

                userData.set(newUser.getUserName(), (JsonNode) objectMapper.valueToTree(newUser));

                // if updating username:
                if (newUser.getUserName() != oldUser.getUserName()) {
                    userData.remove(oldUser.getUserName());
                }

                JsonNode updatedUserData = userData;

                //writing new data node to json file:
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
                logger.debug("updated userdata successfully");
                return true;
            }
            logger.debug("update failed - username exists already");
            return false;


    }

    private boolean changePassword(User user, String oldPassword, String newpassword) throws IOException {

         // TODO: check if old password equals new password in Userpage Controller

         if(userLoginCheck(user.getUserName(), oldPassword)){

             User newUser = new User(user);
             newUser.setPassword(newpassword);

             if(updateUserData(user, newUser));
             logger.debug(user.getUserName() + " 's password: " + user.getPassword() + " updated to : " + newUser.getPassword());
             user.setPassword(newpassword);
             return true;

         }
         return false;

    }

    private boolean changeUserName(User user, String newUserName) throws IOException {

         // TODO: check if new user name is actually new in userpage controller

         User newUser = new User(user);
         newUser.setUserName(newUserName);
         if(updateUserData(user, newUser)){
             logger.debug(user.getUserName() + " 's name updated to : " + newUser.getUserName());
             user.setUserName(newUserName);
             return true;
         }
         return false;
    }

    private void changeUserEmail(User user, String newEmail) throws IOException {

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

    private void deleteUser(User user) throws IOException {

         ObjectMapper objectMapper = new ObjectMapper();
         ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));
         userData.remove(user.getUserName());
         JsonNode updatedUserData = userData;
         PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
         objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
         logger.debug("successfully deleted: " + user.toString());
    }




    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public static void main(String[] args) throws IOException, ParseException {

        UserManager userManager = new UserManager();

        System.out.println(userManager.userLoginCheck("Nils", "derBeste?"));
        String[] favoriten = {"Tofu", "Seitan", "Soja"};
        userManager.updateFavorites(userManager.getActiveUser(), favoriten);

        //userManager.userLoginCheck("jan", "test123");
        //userManager.deleteUser(userManager.getActiveUser());






    }




}
