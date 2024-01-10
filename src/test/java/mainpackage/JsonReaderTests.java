package mainpackage;

import mainpackage.itempackage.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTests {

    JsonReader jsonReader = JsonReader.getInstance();
    private static final Logger log = LogManager.getLogger(JsonReaderTests.class);
    @Test
    public void ListNotNullTest() {
        jsonReader.start();
        try {
            jsonReader.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        assertNotNull(jsonReader.getItemList());
    }
}
