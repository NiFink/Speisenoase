package mainpackage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.*;

public class UserManager {

   private User activeUser;


    private static Logger logger = LogManager.getLogger(UserManager.class);

     boolean registerNewUser(String username, String userEmail, String userPassword) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(userEmail, username, userPassword);

        //getting current userData from json file

        ObjectNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));

        if(userData.get(username) == null) {

            // adding new user as node

            JsonNode newUser = objectMapper.valueToTree(user);

            userData.set(username, newUser);

            JsonNode updatedUserData = userData;

            //writing new data node to json file

            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/json/userData.json")));
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, updatedUserData);
            logger.debug(user.toString() + " \n added successfully");
            activeUser = user;
            return true;
        }
        else
            logger.debug("Failed registration - username is not available");
            return false;




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

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public static void main(String[] args) throws IOException, ParseException {

        UserManager userManager = new UserManager();
        userManager.registerNewUser("Tom", "TimUndStruppi99@gmail.com", "TimUndStruppi99");
        System.out.println(userManager.userLoginCheck("Tom", "TimUndStruppi99"));






    }




}
