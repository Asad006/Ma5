package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hildan.fxgson.FxGson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class FilesExtensionHandler {

    public void saveAsJsonFile(String path, ObservableList<InventoryItem> inventoryItemsData) {
        // create json file
        // the data of the tableView
        // Do serialization of the data to json format
        // write the data in the file
        // close the file
        Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        if (!path.equals("")) {
            try {
                FileWriter file = new FileWriter(path);
                file.write("[");

                for (int i = 0; i < inventoryItemsData.size(); i++) {
                    file.write(gson.toJson(inventoryItemsData.get(i)));

                    if (i < inventoryItemsData.size() - 1) {
                        file.write(",");
                    }
                }

                file.write("]");
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAsTSVFile(String path, ObservableList<InventoryItem> inventoryItemsData) {
        // create tsv file
        // the data of the tableView
        // Do serialization of the data to tsv format
        // write the data in the file
        // close the file
        if (!path.equals("")) {
            try {
                FileWriter file = new FileWriter(path);
                file.write("Value\tSerial Number\tName\n");
                for (int i = 0; i < inventoryItemsData.size(); i++) {
                    file.write(inventoryItemsData.get(i).getItemValue() + "\t");
                    file.write(inventoryItemsData.get(i).getItemSerialNumber() + "\t");

                    file.write(inventoryItemsData.get(i).getItemName() + "\n");
                   /*
                    if (i > inventoryItemsData.size()-1){
                        file.write();
                    }*/
                }

                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void saveInHTMLFile(String path, ObservableList<InventoryItem> inventoryItemsData) {
        // create html file
        // the data of the tableView
        // Do serialization of the data to html format
        // write the data in the file
        // close the file

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

    public ObservableList<InventoryItem> processHTMLFile(String path) {
        //parse HTML file
        //get table
        // get rows
        //get elements of the rows and add them to InventoryItem object
        // add object to the data list
        ObservableList<InventoryItem> dataList = FXCollections.observableArrayList();

        File input = new File(path);
        try {
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements table = doc.select("table");
            Elements tableRows = table.select("tr");

            Iterator<Element> ite = tableRows.select("td").iterator();
            while (ite.hasNext()) {
                InventoryItem item = new InventoryItem();
                String value1 = ite.next().text();
                item.setItemValue(value1);
                String value2 = ite.next().text();
                item.setItemSerialNumber(value2);
                String value3 = ite.next().text();
                item.setItemName(value3);
                dataList.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public ObservableList<InventoryItem> processTXTFile(String path) {
        //parse TXT file
        //get table
        // get rows
        //get elements of the rows and add them to InventoryItem object
        // add object to the data list
        ObservableList<InventoryItem> dataList = FXCollections.observableArrayList();

        try (BufferedReader TSVReader = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");

                InventoryItem item = new InventoryItem();
                if (!lineItems[0].trim().equals("Value")) {
                    item.setItemValue(lineItems[0].trim());
                    item.setItemSerialNumber(lineItems[1].trim());
                    item.setItemName(lineItems[2].trim());
                    dataList.add(item);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public ObservableList<InventoryItem> processJsonFile(String path) {
        //parse json file
        //get table
        // get rows
        //get elements of the rows and add them to InventoryItem object
        // add object to the data list
        ObservableList<InventoryItem> dataList = FXCollections.observableArrayList();
        InventoryItem[] itemDataArray;

        Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        JSONParser jsonParser = new JSONParser();

        try {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(path));

            String userJson = jsonArray.toJSONString();
            itemDataArray = gson.fromJson(userJson, InventoryItem[].class);
            dataList.addAll(Arrays.asList(itemDataArray));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return dataList;
    }


}

