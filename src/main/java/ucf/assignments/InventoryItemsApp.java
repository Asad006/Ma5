package ucf.assignments;
/* Icons are used according the the following licence.
https://creativecommons.org/licenses/by/3.0/
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryItemsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager();
        sceneManager.load();
        Scene scene = sceneManager.getScene("Inventory");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Items Manager");
        primaryStage.show();
        /*
        try {
            Parent root = FXMLLoader.load(getClass().getResource("InventoryItems.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Inventory Items Manager");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
