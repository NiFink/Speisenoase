package mainpackage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainpackage.itempackage.Item;
import mainpackage.itempackage.ItemFactory;
import mainpackage.itempackage.ItemType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemManager extends Application {

    @FXML
    Label labelName;
    @FXML
    ImageView imgViewItem;

    private static final Logger log = LogManager.getLogger(ItemManager.class);

    private List<Item> getItems(int id){
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < id; i++){
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.info("List with " + items.size() + " items was created");
        return items;
    }

    @FXML
    private Node getItemNode(List<Item> list, int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/item.fxml"));
        fxmlLoader.setController(this);
        Parent parent = fxmlLoader.load();

        Item currentItem = (Item) list.get(id);
        labelName.setText(currentItem.getName());
        Image newItemImage = new Image(Objects.requireNonNull(getClass().getResource("/images/groceries/" + currentItem.getName() + ".png")).openStream());
        imgViewItem.setImage(newItemImage);

        return parent;
    }

    private FlowPane getItempaneCategory(String category) throws IOException {
        FlowPane flowPane = new FlowPane();
        List<Item> groceryList = getItems(15);
        for(int i = 0; i < groceryList.size(); i++){
            if(category.equals("all") || groceryList.get(i).getCategory().equals(category)){
                flowPane.getChildren().add(getItemNode(groceryList, i));
            }
        }
        return flowPane;
    }

    private FlowPane getItempaneName(String name) throws IOException {
        FlowPane flowPane = new FlowPane();
        List<Item> groceryList = getItems(15);
        for(int i = 0; i < groceryList.size(); i++){
            if(groceryList.get(i).getName().contains(name)){
                flowPane.getChildren().add(getItemNode(groceryList, i));
            }
        }
        return flowPane;
    }

    @FXML
    public void heartClicked(){

    }

    /*private static void writeItemsToJson(){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(itemJsonFile, groceries);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScrollPane root = new ScrollPane();
        FlowPane flowPane = getItempaneCategory("all");
        //FlowPane flowPane = getItempaneName("apf");
        root.setFitToWidth(true);
        root.setContent(flowPane);
        primaryStage.setScene(new Scene(root, 750, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
