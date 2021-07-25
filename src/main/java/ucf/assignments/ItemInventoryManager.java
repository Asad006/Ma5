package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class ItemInventoryManager {

    private static final FileChooser fileChooser = new FileChooser();
    private FilesExtensionHandler filesExtensionHandler = new FilesExtensionHandler();
    private Set<String> serialNumberData = new HashSet<>();

    private final ObservableList<InventoryItem> inventoryItemsData = FXCollections.observableArrayList();
    private ObservableList<InventoryItem> searchedItemsData = FXCollections.observableArrayList();
    private ObservableList<InventoryItem> sortedItemsData;


    public void save() {
        // open json file
        // the data of the tableView
        // write the data in the file
        // close the file
        File file = getFileChooser();
        String path = file.getPath();
        String fileExtension = (fileChooser.getSelectedExtensionFilter().getExtensions()).get(0);

        if (fileExtension.equals("*.json")) {
            filesExtensionHandler.saveAsJsonFile(path, inventoryItemsData);

        } else if (fileExtension.equals("*.html")) {
            filesExtensionHandler.saveInHTMLFile(path, inventoryItemsData);

        } else if (fileExtension.equals("*.txt")) {
            filesExtensionHandler.saveAsTSVFile(path, inventoryItemsData);
        }


    }

    private File getFileChooser() {
        // create a nes stage
        // set the extensions filter for the chooser
        //show file chooser
        //return the path

        Stage stage = new Stage();

        fileChooser.setTitle("Save File");
        //fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter("jSON file (*.json)", "*.json");
        FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter("html file (*.html)", "*.html");
        FileChooser.ExtensionFilter extensionFilter3 = new FileChooser.ExtensionFilter("txt file (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extensionFilter1);
        fileChooser.getExtensionFilters().add(extensionFilter2);
        fileChooser.getExtensionFilters().add(extensionFilter3);

        File file = fileChooser.showSaveDialog(stage);

        return file;
    }

    public ObservableList<InventoryItem> search(String text, String tag) {
        // traverse the data and check of match if yes add the matched data to sorted data

        ObservableList<InventoryItem> itemsData = FXCollections.observableArrayList();
        for (int i = 0; i < inventoryItemsData.size(); i++) {
            if (tag.equals("Serial Number")) {
                if (inventoryItemsData.get(i).getItemSerialNumber().equals(text)) {
                    itemsData.add(inventoryItemsData.get(i));
                }
            } else {
                if (inventoryItemsData.get(i).getItemName().equals(text)) {
                    itemsData.add(inventoryItemsData.get(i));
                }
            }
        }
        return itemsData;
    }

    public void add(InventoryItem inventoryItem) {
        // add item to the observable list
        inventoryItemsData.add(inventoryItem);

    }

    public boolean isSerialNumberUnique(String text) {
        // check if the set contains the text
        //if not add it to the set and return true
        // else return false
        if (serialNumberData.contains(text)) {
            return false;
        } else {
            serialNumberData.add(text);
            return true;
        }
    }

    public void editItem(InventoryItem editedItem, int index) {
        // set the data in observable list
        inventoryItemsData.get(index).setItemName(editedItem.getItemName());
        inventoryItemsData.get(index).setItemSerialNumber(editedItem.getItemSerialNumber());
        inventoryItemsData.get(index).setItemValue(editedItem.getItemValue());
    }

    public ObservableList<InventoryItem> getData() {
        //return observable list data
        return inventoryItemsData;

    }

    public void open() {
        // open windows dialogue
        // enable the user navigate in folders
        // enable the user to select the file
        // open the Json data file
        // load the new data to the interface
        ObservableList<InventoryItem> dateFile;
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter("JSON file (*.json)", "*.json");
        FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter("Html file (*.html)", "*.html");
        FileChooser.ExtensionFilter extensionFilter3 = new FileChooser.ExtensionFilter("txt file (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(extensionFilter1, extensionFilter2, extensionFilter3);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String fileExtension = (fileChooser.getSelectedExtensionFilter().getExtensions()).get(0);
            if (fileExtension.equals("*.json")) {
                dateFile = filesExtensionHandler.processJsonFile(file.getPath());

            } else if (fileExtension.equals("*.html")) {
                dateFile = filesExtensionHandler.processHTMLFile(file.getPath());

            } else {
                dateFile = filesExtensionHandler.processTXTFile(file.getPath());

            }
        } else {
            dateFile = null;
        }

        inventoryItemsData.addAll(dateFile);
    }

    public boolean isNumericValue(String text) {
        // check if text has numeric value and return true
        // else return false
        if (text == null) {
            return false;
        }
        try {
            double TextDouble = Double.parseDouble(text);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public ObservableList<InventoryItem> delete(ObservableList<InventoryItem> itemsData, int index) {
        // Get Observable Collection data of the list
        // get the selected cell from observable collection
        // create object of the todoTask
        // call remove function of the observable collection
        if (index >= 0) {
            itemsData.remove(index);
        }

        return itemsData;
    }

    public void clear(ObservableList<InventoryItem> itemsData) {
        // traverse the list and remove the elements
        int size = itemsData.size();
        while (size > 0) {

            itemsData.remove(size - 1);
            size = itemsData.size();
        }
    }

    public boolean isSerialNumberIsValid(String text) {
        //traverse the characters of the text and check if there are letters or digits and set valid to true
        //else set valid to false
        // return valid
        int i = 0;
        if (text.length() == 10) {
            boolean valid = true;
            while (valid && i < 10) {
                if (Character.isDigit(text.charAt(i)) || Character.isLetter(text.charAt(i))) {
                    valid = true;
                } else {
                    valid = false;
                }
                i++;
            }
            return valid;

        } else {
            return false;
        }
    }

    public void sort(String tag) {
        //check the tag
        // set comparator related to each tag
        // call the built in sort method of the Observable Object.

        if (tag.equals("Value")) {
            Comparator<InventoryItem> compare = Comparator.comparing(InventoryItem::getValueInDouble);
            FXCollections.sort(inventoryItemsData, compare);
        } else if (tag.equals("Serial number")) {
            Comparator<InventoryItem> compare = Comparator.comparing(InventoryItem::getItemSerialNumber);
            FXCollections.sort(inventoryItemsData, compare);
        } else if (tag.equals("Name")) {
            Comparator<InventoryItem> compare = Comparator.comparing(InventoryItem::getItemName);
            FXCollections.sort(inventoryItemsData, compare);
        }

    }

    public boolean isNameIsValid(String text) {
        // check if the text character are between 2 and 265 and return true
        //else return false
        if (text.length() > 1 && text.length() < 257) {
            return true;
        } else {
            return false;
        }
    }
}

