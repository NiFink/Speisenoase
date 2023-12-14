package mainpackage.itempackage;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader extends Thread {
    private List<Grocery> itemList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LogManager.getLogger(ItemManager.class);
    public static JsonReader instance;
    public static JsonReader getInstance(){
        if(instance == null){
            instance = new JsonReader();
        }
        log.debug("ItemManager instance is being returned");
        return instance;
    }

    @Override
    public void run(){
        createItemlist();
    }

    /**
     * Reads Json File which contains data of items and creates list of groceries with read data
     */
    private void createItemlist(){
        try{
            itemList = objectMapper.readValue(new File("src/main/resources/json/itemData.json"), new TypeReference<List<Grocery>>() {});
            log.debug("ObjectMapper read itemData.json file and created Item List");
        } catch (IOException e) {
            log.error("Json file could not be read. " + e);
        }
    }

    public List<Grocery> getItemList(){
        return itemList;
    }
}
