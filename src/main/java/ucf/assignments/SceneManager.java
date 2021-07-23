package ucf.assignments;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private ItemInventoryManager itemInventoryManager = new ItemInventoryManager();
    private Map<String, Scene> windows = new HashMap<>();


    public void load() {

        InventoryItemsController inventoryItemsController = new InventoryItemsController(itemInventoryManager, this);
        AddItemController addItemController = new AddItemController(itemInventoryManager, this, inventoryItemsController);
        EditItemController editItemController = new EditItemController(itemInventoryManager, this, inventoryItemsController);

        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryItems.fxml"));
        loader.setController(inventoryItemsController);

        try {
            root = loader.load();
            windows.put("Inventory", new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
        loader.setController(addItemController);

        try {
            root = loader.load();
            windows.put("Add", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
        loader.setController(editItemController);

        try {
            root = loader.load();
            windows.put("Edit", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Scene getScene(String name) {
        return windows.get(name);
    }
}
