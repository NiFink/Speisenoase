package mainpackage.itempackage;

import javafx.scene.layout.*;
import mainpackage.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemManager {

    private static ItemManager instance;
    private ArrayList<Item> items;
    private ArrayList<ItemNode> itemNodes;
    private ArrayList<StackPane> itemPanes;
    private ArrayList<ItemNode> itemsShoppingCart;
    private ArrayList<String> favorites = new ArrayList<String>();
    private static final Logger log = LogManager.getLogger(ItemManager.class);

    public static ItemManager getInstance(){
        if(instance == null){
            instance = new ItemManager();
        }
        return instance;
    }

    public ItemManager(){
        setItems(15);
        setItemNodes(this.items);
        setItemPanes(this.itemNodes);
    }

    private void setItems(int id) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.info("List with " + items.size() + " items was created");
        this.items = items;
    }

    private void setItemNodes(List<Item> items) {
        ArrayList<ItemNode> itemNodes = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemNodes.add(new ItemNode(items.get(i)));
        }
        log.info("List with " + items.size() + " Stackpanes of Items was created");
        this.itemNodes = itemNodes;
    }

    private void setItemPanes(ArrayList<ItemNode> itemNodes) {
        ArrayList<StackPane> itemPanes = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemPanes.add(itemNodes.get(i).getItemPane());
        }
        log.info("List with " + items.size() + " Stackpanes of Items was created");
        this.itemPanes = itemPanes;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<ItemNode> getItemNodes() {
        return itemNodes;
    }

    public ArrayList<StackPane> getItemPanes() {
        return itemPanes;
    }

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

    public ArrayList<ItemNode> getItemsShoppingCart(){
        List<ItemNode> itemsShoppingCart =
                itemNodes
                        .stream()
                        .filter(x -> x.getAmountInCart()>0)
                        .toList();

        return new ArrayList<ItemNode>(itemsShoppingCart);
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
