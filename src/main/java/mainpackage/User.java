package mainpackage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class User {



    private String userName;

    private String userEmail;

    private String password;

    //private Grocery[] favourites;
    static JSONParser parser = new JSONParser();



    public static void main(String[] args) throws IOException, ParseException {
        User user = new User("Michelle@gmail.com", "Michelle" , "1234");
        registerNewUser(user);
    }


    public User(String userEmail, String userName, String password) throws IOException, ParseException {

        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;

    }

    public static void registerNewUser(User user){

        try {

            JSONObject newUser = new JSONObject();

            newUser.put("userName", user.userName);
            newUser.put("userEmail", user.userEmail);
            newUser.put("password", user.password);



            // TODO: evade doubling userNames


            JSONObject userData = (JSONObject) parser.parse(new FileReader("src/main/resources/userData.json"));
            userData.put(user.userName, newUser);
            FileWriter fileWriter = new FileWriter("src/main/resources/userData.json");
            fileWriter.write(userData.toJSONString() + "\n");
            fileWriter.flush();

            System.out.println("added new user: " + user);


        }
        catch (Exception e){
            System.out.println(e);

        }

    }



    @Override

    public String toString(){

        return "\n user: \n name: " + userName + "\n email: " + userEmail + "\n password: " + password;
    }



}
