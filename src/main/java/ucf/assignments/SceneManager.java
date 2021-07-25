package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    // create object of type itemInventoryManager
    //create object of type addItemController
    //create object of type editItemController
    //load the scenes in a new FXMLLoader and store tem in HashMap
    // create method get scene


    private  ItemInventoryManager itemInventoryManager = new ItemInventoryManager();
    private Map<String, Scene> windows = new HashMap<>();

    private InventoryItemsController inventoryItemsController = new InventoryItemsController(itemInventoryManager, this);
    private AddItemController addItemController = new AddItemController(itemInventoryManager, this, inventoryItemsController);
    private EditItemController editItemController = new EditItemController(itemInventoryManager, this, inventoryItemsController);

    public void load() {
    // load the scenes in a new FXMLLoader and store tem in HashMap.

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
        //return selected scene
        return windows.get(name);
    }
}
