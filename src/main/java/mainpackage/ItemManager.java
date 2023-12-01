package mainpackage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

import static javafx.scene.input.KeyCode.UP;

public class ItemManager extends Application {

    @FXML
    private Label labelName;
    @FXML
    private Label labelPrice;
    @FXML
    private ImageView imgViewItem;
    private List<Item> items = getItems(15);
    private static final Logger log = LogManager.getLogger(ItemManager.class);

    public List<Item> getItems(int id){
        items = new ArrayList<>();
        for(int i = 0; i < id; i++){
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.info("List with " + items.size() + " items was created");
        return items;
    }

    @FXML
    public Node getItemNode(List<Item> list, int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/item.fxml"));
        fxmlLoader.setController(this);
        Parent parent = fxmlLoader.load();

        Item currentItem = (Item) list.get(id);
        labelName.setText(currentItem.getName());
        labelPrice.setText(String.format("%,.2f", currentItem.getPrice()) + "€");
        Image newItemImage = new Image(Objects.requireNonNull(getClass().getResource("/images/groceries/" + currentItem.getName() + ".png")).openStream());
        imgViewItem.setImage(newItemImage);

        return parent;
    }

    public FlowPane getItempaneCategory(String category) {
        try {
            FlowPane flowPane = new FlowPane();
            for(int i = 0; i < items.size(); i++){
                if(category.toLowerCase().equals("all") || items.get(i).getCategory().toLowerCase().equals(category)){
                    flowPane.getChildren().add(getItemNode(items, i));
                }
            }
            return flowPane;
        } catch (IOException ioe){
            ioe.getMessage();
        }
        return null;
    }

    public FlowPane getItempaneName(String name){
        try {
            FlowPane flowPane = new FlowPane();
            for(int i = 0; i < items.size(); i++){
                if(items.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                    flowPane.getChildren().add(getItemNode(items, i));
                }
            }
            return flowPane;
        } catch (IOException ioe){
            ioe.getMessage();
        }
        return null;
    }

    /*@FXML
    public void heartClicked(ActionEvent event){
        ((Button) flowPane.lookup("#" + buttonId)).setOnAction(actionEvent -> {
            System.out.println(((TextField) flowPane.lookup("#" + textfieldId)).getText());;
        });
    }*/

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
        System.out.println(flowPane.getChildren());

        flowPane.setBackground(new Background(new BackgroundFill(Color.web("#022235"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setFitToWidth(true);
        root.setContent(flowPane);
        primaryStage.setScene(new Scene(root, 785, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //todo: Klasse die Manuell eine Node mit JavaFX erstellt und dann kann man von dieser Klasse Objekte erstellen
}
