package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class InventoryItemsController implements Initializable {
    ItemInventoryManager itemInventoryManager= new ItemInventoryManager();
    private final Set<String>  serialNumberData= new HashSet<String>();
    private final ObservableList<InventoryItem> inventoryItemsData = FXCollections.observableArrayList();
    private ObservableList<InventoryItem> searchItemsData = FXCollections.observableArrayList();

    @FXML
    private TableView<InventoryItem> itemsTableView;
    @FXML
    private TextField valueTextField;
    @FXML
    private TextField serialNumberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button addButtonToolBar;
    @FXML
    private ChoiceBox<String> choiceBoxToolBar;
    @FXML
    private TextField searchBarText;
    @FXML
    void addItemButtonClicked(ActionEvent event) {

        InventoryItem inventoryItem = new InventoryItem(valueTextField.getText(),serialNumberTextField.getText(),nameTextField.getText());


        if(isSerialNumberUnique(serialNumberTextField.getText())){
            inventoryItemsData.add(inventoryItem);
            itemsTableView.setItems(inventoryItemsData);
            valueTextField.clear();
            serialNumberTextField.clear();
            nameTextField.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entry Error");
            alert.setHeaderText("Unique serial number is required.\n");
            alert.setContentText("The Item added contains existing serial number. ");
            alert.showAndWait();

        }


    }

    private boolean isSerialNumberUnique(String text) {
        if (serialNumberData.contains(text)){
            return false;
        }else{
            serialNumberData.add(text);
            return true;
        }

    }

    @FXML
    void saveAsClicked(ActionEvent event) {
        itemInventoryManager.save(inventoryItemsData);
        System.out.println("save as");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemsTableView.setItems( inventoryItemsData);
        //Creating a graphic (image)
        Image img = new Image(String.valueOf(getClass().getResource("icons/save.png")));
        //img.
        Image imageAdd = new Image(getClass().getResourceAsStream("icons/add2.png"));

        choiceBoxToolBar.getItems().add("Serial Number");
        choiceBoxToolBar.getItems().add("Name");

        addButtonToolBar.setGraphic(new ImageView(imageAdd));
    }

    @FXML
    void SearchButtonClicked(ActionEvent event) {
        String tag = choiceBoxToolBar.getSelectionModel().getSelectedItem();
        System.out.println(tag);
        System.out.println(searchBarText.getText());
       searchItemsData = itemInventoryManager.search(inventoryItemsData, searchBarText.getText(),tag);
       itemsTableView.setItems(searchItemsData);
    }
    @FXML
    void searchKeyTyped(KeyEvent event) {
       // System.out.println(searchBarText.getText());
        //Collections.b
    }


}
