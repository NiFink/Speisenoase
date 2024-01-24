package mainpackage;


import mainpackage.Exceptions.WrongEntriesException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;



public class UserManagerTests {
    public UserManager userManager = UserManager.getInstance();

    private final User testUser = new User("email@email.email", "IAmATestUser", "secure");


    @Before
    public void setUp() {
        userManager.registerNewUser("IAmATestUser", "email@email.email", "secure");
    }

    @After
    public void deleteTestUser() {
        userManager.deleteUser(userManager.getActiveUser());

    }

    @Test
    public void testRegistration() {

        assertFalse(userManager.registerNewUser("IAmATestUser", "email@email.email", "secure" ), "user already exists");
        userManager.deleteUser(userManager.getActiveUser());

        assertTrue(userManager.registerNewUser("ThisIsATestUser", "TestEmail", "TestPassword"));
        assertEquals(userManager.getActiveUser().getUserName(),"ThisIsATestUser");
        assertEquals(userManager.getActiveUser().getPassword(),"TestPassword");
        assertEquals(userManager.getActiveUser().getUserEmail(),"TestEmail");


    }

    @Test
    public void testLogin() {
        assertTrue(userManager.userLoginCheck("IAmATestUser", "secure"));
        assertThrows(WrongEntriesException.class, () -> {
            userManager.userLoginCheck("IAmATestUser", "1234");
        });
        assertThrows(WrongEntriesException.class, () -> {
            assertFalse(userManager.userLoginCheck("ThisIsATestUser", "secure"));
        });
        assertEquals(userManager.getActiveUser().getUserName(), "IAmATestUser");
    }

    @Test
    public void testUserUpdates() {

        String[] favorites = new String[]{"Apple", "Pear"};
        userManager.changeUserEmail(testUser, "newEmail");
        userManager.changePassword(testUser, "1234");
        userManager.updateFavorites(testUser, favorites);
        userManager.updateDeliveryInfo(testUser, "Test", "User", "testStreet 12", "testCity");
        userManager.changeUsername(testUser, "TestUsersNewName");

        assertTrue(userManager.userLoginCheck("TestUsersNewName", "1234"), "new username and password");
        assertEquals(userManager.getActiveUser().getUserEmail(), "newEmail");
        assertEquals(userManager.getActiveUser().getFavourites()[0], "Apple");
        assertEquals(userManager.getActiveUser().getAddress(), "testStreet 12");


    }

    @Test
    public void testDeleteUser() {
        assertTrue(userManager.userLoginCheck("IAmATestUser", "secure"));
        userManager.deleteUser(userManager.getActiveUser());
        assertThrows(WrongEntriesException.class, () -> {
            userManager.userLoginCheck("IAmATestUser", "secure");
        });
    }
}
