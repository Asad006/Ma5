package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditItemController implements Initializable{
    private SceneManager sceneManager;
    private ItemInventoryManager itemInventoryManager;
    private InventoryItemsController inventoryItemsController;
    String serialNumber;

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
    void cancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) valueEditTextField.getScene().getWindow();
        stage.close();

        valueEditTextField.clear();
        serialNumberEditTextField.clear();
        nameTextEditField.clear();
    }

    @FXML
    void loadDataEditClicked(ActionEvent event) {

        InventoryItem item = inventoryItemsController.getSelectedItem();
        if (item==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection Error");
            alert.setHeaderText("The Selection of the row to edit is required.\n");
            alert.setContentText("Select the row to edit and try again. ");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    Stage stage = (Stage) valueEditTextField.getScene().getWindow();
                    stage.close();

                }
            });

        }else {
            System.out.println(item.itemName.toString());
            valueEditTextField.setText(item.getItemValue());
            serialNumberEditTextField.setText(item.getItemSerialNumber());
            nameTextEditField.setText(item.getItemName());

            serialNumber =serialNumberEditTextField.getText();
        }

    }
    @FXML
    void saveEditButtonClicked(ActionEvent event) {

        InventoryItem editedItem = new InventoryItem(valueEditTextField.getText(),serialNumberEditTextField.getText(),nameTextEditField.getText());

        int index = inventoryItemsController.getSelectedIndex();

        if (itemInventoryManager.isSerialNumberUnique(serialNumberEditTextField.getText()) || serialNumberEditTextField.getText().equals(serialNumber)) {

            itemInventoryManager.editItem(editedItem,index);
            inventoryItemsController.updateTableView();

            Stage stage = (Stage) valueEditTextField.getScene().getWindow();
            stage.close();

            valueEditTextField.clear();
            serialNumberEditTextField.clear();
            nameTextEditField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entry Error");
            alert.setHeaderText("Unique serial number is required.\n");
            alert.setContentText("The Item added contains existing serial number. ");
            alert.showAndWait();

        }




    }
/*
    public void loadData(InventoryItem item){
        item = inventoryItemsController.getSelectedItem();
        System.out.println(item.itemName.toString());
        valueEditTextField.setText(item.getItemName());
        serialNumberEditTextField.setText(item.getItemSerialNumber());
        nameTextEditField.setText(item.getItemValue());
    }*/
}
