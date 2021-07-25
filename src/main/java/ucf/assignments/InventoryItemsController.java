package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
/* Icons are used according the the following licence.
https://creativecommons.org/licenses/by/3.0/
 */
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.control.skin.TableViewSkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class InventoryItemsController implements Initializable {

    private SceneManager sceneManager ;
    private ItemInventoryManager itemInventoryManager ;
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
    @FXML
    private Button clearSearchToolBar;
    @FXML
    private SplitMenuButton sortSplitMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemsTableView.getItems();

        Image imgDelete = new Image(String.valueOf(getClass().getResource("icons/delete.png")));
        Image imageAdd = new Image(getClass().getResourceAsStream("icons/add2.png"));
        Image imageEdit = new Image(getClass().getResourceAsStream("icons/edit.png"));
        Image imageSearch = new Image(getClass().getResourceAsStream("icons/search.png"));
        Image imageOpen = new Image(getClass().getResourceAsStream("icons/open.png"));
        Image imageSave = new Image(getClass().getResourceAsStream("icons/save.png"));
        Image imageClear = new Image(getClass().getResourceAsStream("icons/clear.png"));
        Image imageSort = new Image(getClass().getResourceAsStream("icons/sort.png"));

        addButtonToolBar.setGraphic(new ImageView(imageAdd));
        editButton.setGraphic(new ImageView(imageEdit));
        deleteButton.setGraphic(new ImageView(imgDelete));
        searchButton.setGraphic(new ImageView(imageSearch));
        openButton.setGraphic(new ImageView(imageOpen));
        saveButton.setGraphic(new ImageView(imageSave));
        clearSearchToolBar.setGraphic(new ImageView(imageClear));
        sortSplitMenu.setGraphic(new ImageView(imageSort));

        choiceBoxToolBar.getItems().add("Serial Number");
        choiceBoxToolBar.getItems().add("Name");

    }

    @FXML
    void SearchButtonClicked(ActionEvent event) {
        String tag = choiceBoxToolBar.getSelectionModel().getSelectedItem();
        searchItemsData = itemInventoryManager.search(searchBarText.getText(), tag);
        itemsTableView.setItems(searchItemsData);
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
        if (itemInventoryManager.getData()!=null){
            itemInventoryManager.clear(itemInventoryManager.getData());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help Message");
            alert.setHeaderText("Opening a new File will case data loss\n");
            alert.setContentText("Save the before opening a new file.");
        }
        itemInventoryManager.open();
        updateTableView();

    }
    @FXML
    void deleteButtonClicked(ActionEvent event) {
        // call the method delete of the todolistTaskManager
        int index = itemsTableView.getSelectionModel().getSelectedIndex();
        itemsTableView.setItems(itemInventoryManager.delete(itemInventoryManager.getData(), index));

    }
    @FXML
    void clearSearchToolBarClicked(ActionEvent event) {
        searchBarText.clear();
        updateTableView();

    }
    @FXML
    void sortByValueClicked(ActionEvent event) {
        itemInventoryManager.sort("Value");
        updateTableView();

    }
    @FXML
    void sortSerialNumberClicked(ActionEvent event) {
        itemInventoryManager.sort("Serial number");
        updateTableView();
    }
    @FXML
    void sortNameClicked(ActionEvent event) {
        itemInventoryManager.sort("Name");
        updateTableView();
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
        itemInventoryManager.save();
    }
}