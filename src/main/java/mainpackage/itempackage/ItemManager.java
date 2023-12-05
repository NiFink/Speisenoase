package mainpackage.itempackage;

import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private ArrayList<Item> items = getItems(15);
    private ArrayList<ItemNode> itemNodes = getItemNodes(items);
    private ArrayList<StackPane> itemPanes = getItemPanes(itemNodes);
    private ArrayList<String> favorites = new ArrayList<String>();
    private static final Logger log = LogManager.getLogger(ItemManager.class);

    public ArrayList<Item> getItems(int id) {
        items = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.info("List with " + items.size() + " items was created");
        return items;
    }

    public ArrayList<ItemNode> getItemNodes(List<Item> items) {
        itemNodes = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemNodes.add(new ItemNode(items.get(i)));
        }
        log.info("List with " + items.size() + " Stackpanes of Items was created");
        return itemNodes;
    }

    public ArrayList<StackPane> getItemPanes(ArrayList<ItemNode> itemNodes) {
        itemPanes = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemPanes.add(itemNodes.get(i).getItemNode());
        }
        log.info("List with " + items.size() + " Stackpanes of Items was created");
        return itemPanes;
    }

    public FlowPane getItempaneCategory(String category) {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < items.size(); i++) {
            if (category.equalsIgnoreCase("all") || items.get(i).getCategory().toLowerCase().equals(category)) {
                flowPane.getChildren().add(itemPanes.get(i));
            }
        }
        flowPane.setStyle("-fx-background-color:  #022235");
        return flowPane;
    }

    public FlowPane getItempaneName(String name) {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                flowPane.getChildren().add(itemPanes.get(i));
            }
        }
        flowPane.setStyle("-fx-background-color:  #022235");
        return flowPane;
    }

    public FlowPane getItempaneFavs() {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < items.size(); i++) {
            if (getFavorites().contains(items.get(i).getName())) {
                flowPane.getChildren().add(itemPanes.get(i));
            }
        }
        flowPane.setStyle("-fx-background-color:  #022235");
        return flowPane;
    }

    public ArrayList<String> getFavorites() {
        for(int i = 0; i < items.size(); i++){
            if(itemNodes.get(i).isFavorite()){
                favorites.add(items.get(i).getName());
            }
        }
        return favorites;
    }
}
