package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class EditItemController implements Initializable {
    private SceneManager sceneManager;
    private ItemInventoryManager itemInventoryManager;
    private InventoryItemsController itemsController;


    @FXML
    private TextField valueEditTextField;
    @FXML
    private TextField serialNumberEditTextField;
    @FXML
    private TextField nameEditTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueEditTextField.setText("");
        serialNumberEditTextField.setText("");
        nameEditTextField.setText("");
        System.out.println("test");
    }

    public EditItemController(ItemInventoryManager itemInventoryManager, SceneManager sceneManager, InventoryItemsController inventoryItemsController) {
        this.itemInventoryManager = itemInventoryManager;
        this.sceneManager = sceneManager;
        this.itemsController = inventoryItemsController;
    }

    public EditItemController() {

    }

    @FXML
    void saveButtonClicked(ActionEvent event) {

    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) valueEditTextField.getScene().getWindow();
        stage.close();

        valueEditTextField.clear();
        serialNumberEditTextField.clear();
        nameEditTextField.clear();
    }

    public void setFields(InventoryItem item){


        valueEditTextField.setText(item.getItemValue());
        serialNumberEditTextField.setText(item.getItemSerialNumber());
        nameEditTextField.setText(item.getItemName());
    }
}
