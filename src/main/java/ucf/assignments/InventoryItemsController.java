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

    private SceneManager sceneManager = new SceneManager();
    private ItemInventoryManager itemInventoryManager = new ItemInventoryManager();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemsTableView.getItems();

        Image imgDelete = new Image(String.valueOf(getClass().getResource("icons/delete.png")));
        Image imageAdd = new Image(getClass().getResourceAsStream("icons/add2.png"));
        Image imageEdit = new Image(getClass().getResourceAsStream("icons/edit.png"));
        Image imageSearch = new Image(getClass().getResourceAsStream("icons/search.png"));

        addButtonToolBar.setGraphic(new ImageView(imageAdd));
        editButton.setGraphic(new ImageView(imageEdit));
        deleteButton.setGraphic(new ImageView(imgDelete));
        searchButton.setGraphic(new ImageView(imageSearch));

        choiceBoxToolBar.getItems().add("Serial Number");
        choiceBoxToolBar.getItems().add("Name");
    }

    @FXML
    void addItemButtonClicked(ActionEvent event) {
        /*
        InventoryItem inventoryItem = new InventoryItem(valueTextField.getText(), serialNumberTextField.getText(),
                nameTextField.getText());

        if (itemInventoryManager.isSerialNumberUnique(serialNumberTextField.getText())) {
            itemInventoryManager.add(inventoryItem);

            //updateTableView();
            valueTextField.clear();
            serialNumberTextField.clear();
            nameTextField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entry Error");
            alert.setHeaderText("Unique serial number is required.\n");
            alert.setContentText("The Item added contains existing serial number. ");
            alert.showAndWait();

        }
*/

    }

    @FXML
    void saveAsClicked(ActionEvent event) {
        itemInventoryManager.save(itemsData);
        System.out.println("save as");
    }

    @FXML
    void SearchButtonClicked(ActionEvent event) {
        String tag = choiceBoxToolBar.getSelectionModel().getSelectedItem();
        searchItemsData = itemInventoryManager.search(itemsData, searchBarText.getText(), tag);
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
        editItemController.loadData(getSelectedItem());
    }

    public void addItem(InventoryItem item) {
        itemsData.add(item);
        // itemsTableView.setItems(inventoryItemsData);
        updateTableView();
    }

    public void updateTableView() {
        itemsTableView.setItems(itemInventoryManager.getData());
    }

    public InventoryItem getSelectedItem() {
        int index = itemsTableView.getSelectionModel().getSelectedIndex();
        return itemInventoryManager.getData().get(index);
    }

    public int getSelectedIndex() {
        return itemsTableView.getSelectionModel().getSelectedIndex();
    }
}