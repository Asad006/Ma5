package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditItemController implements Initializable{
    private SceneManager sceneManager;
    private ItemInventoryManager itemInventoryManager;
    private InventoryItemsController inventoryItemsController;

    @FXML
    private TextField valueEditTextField;

    @FXML
    private TextField serialNumberEditTextField;

    @FXML
    private TextField nameTextEditField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextField valueEditTextField = new TextField();
        TextField serialNumberEditTextField= new TextField();
        TextField nameEditTextField = new TextField();

        valueEditTextField.setText("");
        serialNumberEditTextField.setText("");
        nameEditTextField.setText("");
        System.out.println("test");

    }

    public EditItemController(ItemInventoryManager itemInventoryManager, SceneManager sceneManager, InventoryItemsController inventoryItemsController) {
        this.itemInventoryManager = itemInventoryManager;
        this.sceneManager = sceneManager;
        this.inventoryItemsController = inventoryItemsController;
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
        nameTextEditField.clear();
    }

    public void setFields(InventoryItem item){


    }
    @FXML
    void loadDataEditClicked(ActionEvent event) {
        /*
        TextField valueEditTextField = new TextField();
        TextField serialNumberEditTextField= new TextField();
        TextField nameEditTextField = new TextField();

        valueEditTextField.setText("");
        serialNumberEditTextField.setText("");
        nameEditTextField.setText("");
        System.out.println("test");*/
        InventoryItem item = inventoryItemsController.getSelectedItem();
        System.out.println(item.itemName.toString());
        valueEditTextField.setText(item.getItemName());
        serialNumberEditTextField.setText(item.getItemSerialNumber());
        nameTextEditField.setText(item.getItemValue());

    }
    @FXML
    void saveEditButtonClicked(ActionEvent event) {
        InventoryItem editedItem = new InventoryItem(valueEditTextField.getText(),serialNumberEditTextField.getText(),nameTextEditField.getText());
        int index = inventoryItemsController.getSelectedIndex();
        itemInventoryManager.editItem(editedItem,index);

        Stage stage = (Stage) valueEditTextField.getScene().getWindow();
        stage.close();

        valueEditTextField.clear();
        serialNumberEditTextField.clear();
        nameTextEditField.clear();
        inventoryItemsController.updateTableView();

    }

    public void loadData(InventoryItem item){
        item = inventoryItemsController.getSelectedItem();
        System.out.println(item.itemName.toString());
        valueEditTextField.setText(item.getItemName());
        serialNumberEditTextField.setText(item.getItemSerialNumber());
        nameTextEditField.setText(item.getItemValue());
    }
}
