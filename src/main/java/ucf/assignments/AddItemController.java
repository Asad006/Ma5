package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class AddItemController {
    private SceneManager sceneManager;
    private ItemInventoryManager itemInventoryManager;
    private InventoryItemsController itemsController;
    private final Set<String> serialNumberData = new HashSet<String>();

    public AddItemController(ItemInventoryManager itemInventoryManager, SceneManager sceneManager, InventoryItemsController inventoryItemsController) {
        this.itemInventoryManager = itemInventoryManager;
        this.sceneManager = sceneManager;
        this.itemsController = inventoryItemsController;
    }

    @FXML
    private TextField valueTextField;
    @FXML
    private TextField addSerialNumberTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    void addButtonClicked(ActionEvent event) {

        InventoryItem inventory = new InventoryItem(valueTextField.getText(), addSerialNumberTextField.getText(), nameTextField.getText());

        if (itemInventoryManager.isSerialNumberUnique(addSerialNumberTextField.getText())) {
            itemInventoryManager.add(inventory);

            valueTextField.clear();
            addSerialNumberTextField.clear();
            nameTextField.clear();

            Stage stage = (Stage) valueTextField.getScene().getWindow();
            stage.close();
            itemsController.updateTableView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entry Error");
            alert.setHeaderText("Unique serial number is required.\n");
            alert.setContentText("The Item added contains existing serial number. ");
            alert.showAndWait();

        }

    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) valueTextField.getScene().getWindow();
        stage.close();

        valueTextField.clear();
        addSerialNumberTextField.clear();
        nameTextField.clear();
    }
}
