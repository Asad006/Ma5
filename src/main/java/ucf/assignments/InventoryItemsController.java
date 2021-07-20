package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryItemsController {
    ItemInventoryManager itemInventoryManager= new ItemInventoryManager();
    private ObservableSet<InventoryItem> serialNumberData = FXCollections.emptyObservableSet();
    private ObservableList<InventoryItem> inventoryItemsData = FXCollections.observableArrayList();

    @FXML
    private TableView<InventoryItem> itemsTableView;
    @FXML
    private TextField valueTextField;
    @FXML
    private TextField serialNumberTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    void addItemButtonClicked(ActionEvent event) {

        System.out.println(" item" + serialNumberTextField.getText());
        InventoryItem inventoryItem = new InventoryItem(valueTextField.getText(),serialNumberTextField.getText(),nameTextField.getText());
        inventoryItemsData.add(inventoryItem);
        itemsTableView.setItems( inventoryItemsData);

        valueTextField.clear();
        serialNumberTextField.clear();
        nameTextField.clear();

    }
    @FXML
    void saveAsClicked(ActionEvent event) {
        itemInventoryManager.save(inventoryItemsData);
        System.out.println("save as");

    }

}
