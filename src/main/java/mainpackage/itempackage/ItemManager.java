package mainpackage.itempackage;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ItemManager {

    private static ItemManager instance;
    private List<Item> items;
    private List<ItemNode> itemNodes;
    private List<StackPane> itemPanes;
    private static final Logger log = LogManager.getLogger(ItemManager.class);

    /**
     * If an instance of ItemManager exists, returns this instance, else it returns a new instance
     * @return instance of ItemManager
     */
    public static ItemManager getInstance(){
        if(instance == null){
            instance = new ItemManager();
        }
        log.debug("ItemManager instance is being returned");
        return instance;
    }

    public void loadNodes(int amount){
        setItems(amount);
        setItemNodes(this.items);
        setItemPanes(this.itemNodes);
        log.debug(amount + " item Nodes are loaded");
    }

    /**
     * Creates items with the ItemFactory and sets list of items with an amount of 'id'
     * @param id amount of items
     */
    private void setItems(int id) {
        List<Item> items = IntStream.range(0, id)
                .mapToObj(i -> ItemFactory.createItem(ItemType.GROCERY, i))
                .collect(Collectors.toCollection(ArrayList::new));

        log.debug("List with " + items.size() + " items is created");
        this.items = items;
    }

    /**
     * Creates ItemNodes from a list of Items and sets list of ItemNodes
     * @param items that ItemNodes are created from
     */
    private void setItemNodes(List<Item> items) {
        List<ItemNode> itemNodes = items.stream()
                .map(ItemNode::new)
                .collect(Collectors.toCollection(ArrayList::new));

        log.debug("List with " + items.size() + " Stackpanes of Items is created");
        this.itemNodes = itemNodes;
    }

    /**
     * Creates ItemPanes from a list of ItemNodes and sets list of ItemPanes
     * @param itemNodes that ItemPanes are created from
     */
    private void setItemPanes(List<ItemNode> itemNodes) {
        List<StackPane> itemPanes = itemNodes.stream()
                .map(ItemNode::getItemPane)
                .collect(Collectors.toCollection(ArrayList::new));

        log.debug("List with " + items.size() + " Stackpanes of Items is created");
        this.itemPanes = itemPanes;
    }

    /**
     * Creates and returns a flowpane with Itempanes that are in a given category or are favorites (category = 'favs')
     * @param category of itemNodes that will be in flowpane
     * @return flowpane with itemNodes
     */
    public FlowPane getItempaneCategory(String category) {
        FlowPane flowPane = new FlowPane();

        List<StackPane> filteredPanes = IntStream.range(0, items.size())
                .parallel()
                .filter(i -> (category.equalsIgnoreCase("all") || items.get(i).getCategory().toLowerCase().equals(category)) ||
                             (category.equalsIgnoreCase("favs") && getFavorites().contains(items.get(i).getName())))
                .mapToObj(itemPanes::get)
                .toList();

        flowPane.getChildren().addAll(filteredPanes);

        log.info("FlowPane with ItemNodes, that are in the category '" + category + "', is created with a size of '" + flowPane.getChildren().size() + "'");
        return flowPane;
    }

    /**
     * Creates and returns a flowpane with Itempanes that contain a given name
     * @param name of item
     * @return flowpane with itemNodes that contain name
     */
    public FlowPane getItempaneName(String name) {
        FlowPane flowPane = new FlowPane();

        List<StackPane> filteredPanes = IntStream.range(0, items.size())
                .parallel()
                .filter(i -> items.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                .mapToObj(itemPanes::get)
                .toList();

        flowPane.getChildren().addAll(filteredPanes);

        if(flowPane.getChildren().size() > 0) {
            log.info("FlowPane with ItemNodes, that contain '" + name + "', is created with a size of '" + flowPane.getChildren().size() +  "'");
        } else {
            Label label = new Label("No items found that contain '" + name + "'.");
            label.setFont(Font.font("Yu Gothic UI", FontWeight.MEDIUM, FontPosture.REGULAR, 40));
            label.setTranslateY(20);
            label.setTranslateX(20);
            flowPane.getChildren().add(label);
            log.info("No items were found that contain '" + name + "'");
        }
        return flowPane;
    }

    /**
     * Gets list of current favorites
     * @return list of current favorites
     */
    public List<String> getFavorites() {
        List<String> favorites = IntStream.range(0, items.size())
                .parallel()
                .filter(i -> itemNodes.get(i).isFavorite())
                .mapToObj(i -> items.get(i).getName())
                .collect(Collectors.toList());

        log.info("List of favorites is gathered and returned");
        return favorites;
    }

}
