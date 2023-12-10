package mainpackage.itempackage;

import javafx.scene.layout.*;
import mainpackage.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemManager {
    //TODO: Clean Code

    private static ItemManager instance;
    private ArrayList<Item> items;
    private ArrayList<ItemNode> itemNodes;
    private ArrayList<StackPane> itemPanes;
    private ArrayList<ItemNode> itemsShoppingCart;
    private final ArrayList<String> favorites = new ArrayList<>();
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

    /**
     * Constructor of ItemManager
     */
    public ItemManager(){
        setItems(15);
        setItemNodes(this.items);
        setItemPanes(this.itemNodes);
        log.debug("New ItemManager is created");
    }

    /**
     * Creates items with the ItemFactory and sets list of items with a amount of 'id'
     * @param id amount of items
     */
    private void setItems(int id) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.debug("List with " + items.size() + " items is created");
        this.items = items;
    }

    /**
     * Creates ItemNodes from a list of Items and sets list of ItemNodes
     * @param items that ItemNodes are created from
     */
    private void setItemNodes(List<Item> items) {
        ArrayList<ItemNode> itemNodes = new ArrayList<>();
        for (Item item : items) {
            itemNodes.add(new ItemNode(item));
        }
        log.debug("List with " + items.size() + " Stackpanes of Items is created");
        this.itemNodes = itemNodes;
    }

    /**
     * Creates ItemPanes from a list of ItemNodes and sets list of ItemPanes
     * @param itemNodes that ItemPanes are created from
     */
    private void setItemPanes(ArrayList<ItemNode> itemNodes) {
        ArrayList<StackPane> itemPanes = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemPanes.add(itemNodes.get(i).getItemPane());
        }
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
        for (int i = 0; i < items.size(); i++) {
            if (category.equalsIgnoreCase("all") || items.get(i).getCategory().toLowerCase().equals(category)) {
                flowPane.getChildren().add(itemPanes.get(i));
            } else if (category.equalsIgnoreCase("favs")){
                if(getFavorites().contains(items.get(i).getName())){
                    flowPane.getChildren().add(itemPanes.get(i));
                }
            }
        }
        flowPane.setStyle("-fx-background-color:  #022235");
        log.info("FlowPane with ItemNodes, that are in the category '" + category + "', is created with a size of '" + flowPane.getChildren().size() +  "'");
        return flowPane;
    }

    /**
     * Creates and returns a flowpane with Itempanes that contain a given name
     * @param name of item
     * @return flowpane with itemNodes that contain name
     */
    public FlowPane getItempaneName(String name) {
        FlowPane flowPane = new FlowPane();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                flowPane.getChildren().add(itemPanes.get(i));
            }
        }
        flowPane.setStyle("-fx-background-color:  #022235");
        log.info("FlowPane with ItemNodes, that contain '" + name + "', is created with a size of '" + flowPane.getChildren().size() +  "'");
        return flowPane;
    }

    /**
     * Creates and returns list of itemNodes which amount in the shopping cart is greater than 0
     * @return list of ItemNodes with shopping cart amount > 0
     */
    public ArrayList<ItemNode> getItemsShoppingCart(){
        List<ItemNode> itemsShoppingCart =
                itemNodes
                        .stream()
                        .filter(x -> x.getAmountInCart()>0)
                        .toList();

        log.info("List of items in shopping cart is gathered and returned");
        return new ArrayList<ItemNode>(itemsShoppingCart);
    }

    /**
     * Sets list of current favorites
     */
    private void setFavorites(){
        for(int i = 0; i < items.size(); i++){
            if(itemNodes.get(i).isFavorite()){
                favorites.add(items.get(i).getName());
            }
        }
    }

    /**
     * Gets list of current favorites
     * @return list of current favorites
     */
    public ArrayList<String> getFavorites() {
        setFavorites();
        log.info("List of favorites is gathered and returned");
        return favorites;
    }

}
