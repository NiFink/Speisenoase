package mainpackage.itempackage;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import mainpackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemNodeController {

    private ArrayList<Item> items = getItems(15);
    private ArrayList<StackPane> itemNodes = getItemNodes(items);
    private static final Logger log = LogManager.getLogger(ItemManager.class);

    public ArrayList<Item> getItems(int id) {
        items = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.info("List with " + items.size() + " items was created");
        return items;
    }

    public ArrayList<StackPane> getItemNodes(List<Item> items) {
        itemNodes = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemNodes.add(new ItemNode(items.get(i)).getItemNode());
        }
        log.info("List with " + items.size() + " Stackpanes of Items was created");
        return itemNodes;
    }

    public FlowPane getItempaneCategory(String category) {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < items.size(); i++) {
            if (category.equalsIgnoreCase("all") || items.get(i).getCategory().toLowerCase().equals(category)) {
                flowPane.getChildren().add(itemNodes.get(i));
            }
        }
        return flowPane;
    }

    public FlowPane getItempaneName(String name) {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                flowPane.getChildren().add(itemNodes.get(i));
            }
        }
        return flowPane;
    }
}
