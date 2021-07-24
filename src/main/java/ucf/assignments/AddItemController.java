package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AddItemController implements Initializable {
    private SceneManager sceneManager;
    private ItemInventoryManager itemInventoryManager;
    private InventoryItemsController inventoryItemsController;
    private final Set<String> serialNumberData = new HashSet<String>();

    public AddItemController(ItemInventoryManager itemInventoryManager, SceneManager sceneManager, InventoryItemsController inventoryItemsController) {
        this.itemInventoryManager = itemInventoryManager;
        this.sceneManager = sceneManager;
        this.inventoryItemsController = inventoryItemsController;
    }

    @FXML
    private TextField valueTextField;
    @FXML
    private TextField addSerialNumberTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    void addButtonClicked(ActionEvent event) {
        if (itemInventoryManager.isNumericValue(valueTextField.getText())) {
            Double valueNumber = Double.parseDouble(valueTextField.getText());
            BigDecimal valueBigDecimal = new BigDecimal(valueNumber);
            BigDecimal valueDisplayMoney = valueBigDecimal.setScale(2, RoundingMode.HALF_EVEN);

            InventoryItem inventory = new InventoryItem("$"+valueDisplayMoney.toString(), addSerialNumberTextField.getText(), nameTextField.getText());

            if (itemInventoryManager.isSerialNumberUnique(addSerialNumberTextField.getText())) {
                itemInventoryManager.add(inventory);

                valueTextField.clear();
                addSerialNumberTextField.clear();
                nameTextField.clear();

                Stage stage = (Stage) valueTextField.getScene().getWindow();
                stage.close();
                inventoryItemsController.updateTableView();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Entry Error");
                alert.setHeaderText("Unique serial number is required.\n");
                alert.setContentText("The Item added contains existing serial number. ");
                alert.showAndWait();

            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entry Error");
            alert.setHeaderText("Value Number format is required.\n");
            alert.setContentText("The value of the Item is a number. ");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
