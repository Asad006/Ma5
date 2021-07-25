package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

        File file = getFileChooser();
        String path = file.getPath();
        String fileExtension = (fileChooser.getSelectedExtensionFilter().getExtensions()).get(0);

        if (fileExtension.equals("*.json")) {
            filesExtensionHandler.saveAsJsonFile(path, inventoryItemsData);

        } else if (fileExtension.equals("*.html")) {
            filesExtensionHandler.saveInHTMLFile(path, inventoryItemsData);

            saveInHTMLFile(path);
        } else if (fileExtension.equals("*.txt")) {
            filesExtensionHandler.saveAsTSVFile(path, inventoryItemsData);
        }


    }

    private void saveInHTMLFile(String path) {
        if (!path.equals("")) {
            try {
                FileWriter file = new FileWriter(path);
                file.write("<!DOCTYPE html>" +
                        "<html>" +
                        "<body>" +
                        "<h2>Inventory Items Table</h2>" +
                        "<style>" +
                        "table, th, td {" +
                        "border: 0px solid black;" +
                        "border-collapse: collapse;" +
                        "}" +
                        "th, td {" +
                        "padding: 5px;" +
                        "text-align: left;" +
                        "}" +
                        "</style> " +
                        "<table>" +
                        "<tr>" +
                        "<th>Value</th>" +
                        "<th>Serial Number</th>" +
                        "<th>Name</th>" +
                        "</tr>");
                for (int i = 0; i < inventoryItemsData.size(); i++) {

                    file.write("<tr><td>" + inventoryItemsData.get(i).getItemValue() + "</td>");
                    file.write("<td>" + inventoryItemsData.get(i).getItemSerialNumber() + "</td>");
                    file.write("<td>" + inventoryItemsData.get(i).getItemName() + "</td></tr>");
                }


                file.write("</tr>" + "</table>" + "</body>" + "</html>");
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private File getFileChooser() {
        Stage stage = new Stage();
        //FileChooser fileChooser = new FileChooser();

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
        inventoryItemsData.add(inventoryItem);

    }

    public boolean isSerialNumberUnique(String text) {
        if (serialNumberData.contains(text)) {
            return false;
        } else {
            serialNumberData.add(text);
            return true;
        }
    }

    public void editItem(InventoryItem editedItem, int index) {

        inventoryItemsData.get(index).setItemName(editedItem.getItemName());
        inventoryItemsData.get(index).setItemSerialNumber(editedItem.getItemSerialNumber());
        inventoryItemsData.get(index).setItemValue(editedItem.getItemValue());
    }

    public ObservableList<InventoryItem> getData() {
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

        if (file!=null){
            String fileExtension = (fileChooser.getSelectedExtensionFilter().getExtensions()).get(0);
            if (fileExtension.equals("*.json")) {
                dateFile = filesExtensionHandler.processJsonFile(file.getPath());

            } else if (fileExtension.equals("*.html")) {
                dateFile = filesExtensionHandler.processHTMLFile(file.getPath());

            } else {
                dateFile = filesExtensionHandler.processTXTFile(file.getPath());

            }
        }else {
            dateFile=null;
        }

        inventoryItemsData.addAll(dateFile);
    }

    public boolean isNumericValue(String text) {
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
        if (index>=0){
            itemsData.remove(index);
        }

        return itemsData;
    }

    public void clear(ObservableList<InventoryItem> itemsData) {
        int size = itemsData.size();
        while (size > 0) {

            itemsData.remove(size - 1);
            size = itemsData.size();
        }
    }

    public boolean isSerialNumberIsValid(String text) {
        char[] textInChar = text.toCharArray();
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
        // call the built in sort method of the Observable Object.

        if (tag.equals("Value") ){
            Comparator<InventoryItem> compare = Comparator.comparing(InventoryItem::getValueInDouble);
            FXCollections.sort(inventoryItemsData, compare);
        }else if(tag.equals("Serial number")){
            Comparator<InventoryItem> compare = Comparator.comparing(InventoryItem::getItemSerialNumber);
            FXCollections.sort(inventoryItemsData, compare);
        }else if (tag.equals("Name")){
            Comparator<InventoryItem> compare = Comparator.comparing(InventoryItem::getItemName);
            FXCollections.sort(inventoryItemsData, compare);
        }

    }

    public boolean isNameIsValid(String text) {
        if (text.length()>1 && text.length()<267){
            return true;
        }else {
            return false;
        }
    }
}

