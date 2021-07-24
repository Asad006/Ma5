package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class InventoryItemsController implements Initializable {

    private SceneManager sceneManager ;
    private ItemInventoryManager itemInventoryManager ;
    private  EditItemController editItemController = new EditItemController();


    private ObservableList<InventoryItem> itemsData = FXCollections.observableArrayList();
    private ObservableList<InventoryItem> searchItemsData = FXCollections.observableArrayList();

    public InventoryItemsController(ItemInventoryManager itemInventoryManager, SceneManager sceneManager) {
        this.itemInventoryManager = itemInventoryManager;
        this.sceneManager = sceneManager;

    }

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
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button openButton;
    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemsTableView.getItems();

        Image imgDelete = new Image(String.valueOf(getClass().getResource("icons/delete.png")));
        Image imageAdd = new Image(getClass().getResourceAsStream("icons/add2.png"));
        Image imageEdit = new Image(getClass().getResourceAsStream("icons/edit.png"));
        Image imageSearch = new Image(getClass().getResourceAsStream("icons/search.png"));
        Image imageOpen = new Image(getClass().getResourceAsStream("icons/open.png"));
        Image imageSave = new Image(getClass().getResourceAsStream("icons/save.png"));

        addButtonToolBar.setGraphic(new ImageView(imageAdd));
        editButton.setGraphic(new ImageView(imageEdit));
        deleteButton.setGraphic(new ImageView(imgDelete));
        searchButton.setGraphic(new ImageView(imageSearch));
        openButton.setGraphic(new ImageView(imageOpen));
        saveButton.setGraphic(new ImageView(imageSave));

        choiceBoxToolBar.getItems().add("Serial Number");
        choiceBoxToolBar.getItems().add("Name");
    }

    @FXML
    void saveAsClicked(ActionEvent event) {
        itemInventoryManager.save();

    }

    @FXML
    void SearchButtonClicked(ActionEvent event) {
        String tag = choiceBoxToolBar.getSelectionModel().getSelectedItem();
        searchItemsData = itemInventoryManager.search(searchBarText.getText(), tag);
        itemsTableView.setItems(searchItemsData);
    }

    @FXML
    void searchKeyTyped(KeyEvent event) {
        // System.out.println(searchBarText.getText());
        //Collections.b
    }

    @FXML
    void addItemToolBarClicked(ActionEvent event) {
        Stage primaryStage = new Stage();

        primaryStage.setScene(sceneManager.getScene("Add"));
        primaryStage.setTitle("Add Item");
        primaryStage.show();
    }

    @FXML
    void editButtonClicked(ActionEvent event) {


        Stage primaryStage = new Stage();

        primaryStage.setScene(sceneManager.getScene("Edit"));
        primaryStage.setTitle("Edit Item");
        primaryStage.show();
       //editItemController.loadData(getSelectedItem());
    }

    public void addItem(InventoryItem item) {
        itemsData.add(item);
        // itemsTableView.setItems(inventoryItemsData);
        updateTableView();
    }

    public void updateTableView() {

        itemsTableView.refresh();
        itemsTableView.setItems(itemInventoryManager.getData());
        itemsTableView.refresh();
    }

    public InventoryItem getSelectedItem() {
        int index = -2;
         index = itemsTableView.getSelectionModel().getSelectedIndex();
         if (index>=0){
             return itemInventoryManager.getData().get(index);
         }else{
             return null;
        }

    }

    public int getSelectedIndex() {
        if (getSelectedItem()!=null) {
            return itemsTableView.getSelectionModel().getSelectedIndex();
        } else {
            return -1;
        }
    }
    @FXML
    void openButtonClicked(ActionEvent event) {
        itemInventoryManager.open();
        updateTableView();
    }
}