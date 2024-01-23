package mainpackage;

import javafx.application.Platform;
import mainpackage.Exceptions.WrongEntriesException;
import mainpackage.itempackage.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTests {

    private static final JsonReader jsonReader = JsonReader.getInstance();
    private static final Logger log = LogManager.getLogger(ExceptionTests.class);

    @BeforeAll
    public static void setUp() {
        Platform.startup(() -> {});
        jsonReader.start();
        try {
            jsonReader.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void wrongEntriesExceptionTest(){
        setUp();
        assertThrows(WrongEntriesException.class, () -> {
            UserManager.getInstance().userLoginCheck("asdf", "a");
        });
        assertThrows(WrongEntriesException.class, () -> {
            UserManager.getInstance().userLoginCheck("Michelle", "456");
        });
        assertThrows(WrongEntriesException.class, () -> {
            UserManager.getInstance().userLoginCheck("Nils", "aaa");
        });
        assertThrows(WrongEntriesException.class, () -> {
            UserManager.getInstance().userLoginCheck("Jan", "passwort");
        });
    }
}
