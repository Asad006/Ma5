package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
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
        // create a reference to sceneManger
        // call load scene  to get the first scene
        // show the scene

        SceneManager sceneManager = new SceneManager();
        sceneManager.load();
        Scene scene = sceneManager.getScene("Inventory");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Items Manager");
        primaryStage.show();

    }

}
