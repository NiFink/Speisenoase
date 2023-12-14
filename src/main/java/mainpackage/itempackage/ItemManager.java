package mainpackage.itempackage;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import mainpackage.ShoppingCart.Purchase;
import mainpackage.ShoppingCart.ShoppingCart;
import mainpackage.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemManager {
    //TODO: ist public Flowpane Clean Code?

    private static ItemManager instance;
    private List<Item> items;
    private List<ItemNode> itemNodes;
    private List<StackPane> itemPanes;
    private List<ItemNode> itemsShoppingCart;
    private List<String> favorites;
    private static final Logger log = LogManager.getLogger(ItemManager.class);
    private ShoppingCart shoppingCart;

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
        setShoppingCart(ShoppingCart.getInstance());
        setItems(15);
        setItemNodes(this.items);
        setItemPanes(this.itemNodes);
        setFavorites(UserManager.getInstance().getActiveUser().getFavourites());
        log.debug("New ItemManager is created");
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
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
    private void setItemPanes(List<ItemNode> itemNodes) {
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
        //flowPane.setStyle("-fx-background-color:  #022235");
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
     * Sets list of current favorites to favorites of activeUser
     */
    private void setFavorites(String[] favorites){
        this.favorites = new ArrayList<>(Arrays.stream(favorites).toList());
    }

    /**
     * Gets list of current favorites
     * @return list of current favorites
     */
    public List<String> getFavorites() {
        List<String> favorites = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(itemNodes.get(i).isFavorite()){
                favorites.add(items.get(i).getName());
            }
        }
        log.info("List of favorites is gathered and returned");
        return favorites;
    }

}
