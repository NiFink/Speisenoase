package mainpackage;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class UserManager {

     boolean registerNewUser (String username, String userEmail, String userPassword) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(userEmail, username, userPassword );

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
            System.out.println(user.toString() + " \n added successfully");
            return true;
        }
        else
            return false;




    }

    public boolean userLogin(String username, String password) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode userData = (ObjectNode) objectMapper.readTree(new File("src/main/resources/json/userData.json"));
        try{
            JsonNode userNode = userData.get(username);
            User user = objectMapper.treeToValue(userNode, User.class);
            if(user == null){
                System.err.println("wrong username :(");
                return false;
            }
            if(!password.equals(user.getPassword())){

                System.err.println("incorrect password :(");
                return false;
            }

            else {
                System.out.println("successfully logged in as " + username);
                return true;
            }
        }

        catch(Exception e){
            System.out.println(e);
            return false;
        }



    }

    public static void main(String[] args) throws IOException, ParseException {

        UserManager userManager = new UserManager();
        userManager.registerNewUser("Tim", "TimUndStruppi99@gmail.com", "TimUndStruppi99");
        System.out.println(userManager.userLogin("Tim", "TimUndStruppi99"));






    }

    /*** TODO: evade doubling usernames (usernameCheck method?), checking password method for registration?
     *
     * ***/

}
