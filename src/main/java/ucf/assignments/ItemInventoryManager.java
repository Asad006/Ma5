package ucf.assignments;

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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ItemInventoryManager {

    private static final FileChooser fileChooser = new FileChooser();
    private Set<String> serialNumberData = new HashSet<>();

    private final ObservableList<InventoryItem> inventoryItemsData = FXCollections.observableArrayList();
    private ObservableList<InventoryItem> searchedItemsData = FXCollections.observableArrayList();


    public void save(ObservableList<InventoryItem> inventoryItemsData) {

        File file = getFileChooser();
        String path = file.getPath();
        String fileExtension = (fileChooser.getSelectedExtensionFilter().getExtensions()).get(0);

        System.out.println("ext " + fileExtension);

        System.out.println(file.getName());
        if (fileExtension.equals("*.json")) {
            System.out.println("we are here");
            saveAsJsonFile(path, inventoryItemsData);
        } else if (fileExtension.equals("*.Html")) {
            saveInHTMLFile(path, inventoryItemsData);
        } else if (fileExtension.equals("*.txt")) {
            saveAsTSVFile(path, inventoryItemsData);
        }


    }

    private void saveAsTSVFile(String path, ObservableList<InventoryItem> inventoryItemsData) {
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

    private void saveInHTMLFile(String path, ObservableList<InventoryItem> inventoryItemsData) {
        if (!path.equals("")) {
            try {
                FileWriter file = new FileWriter(path);
                file.write("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h2>Inventory Items Table</h2>\n" +
                        "\n" +
                        "<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "  border-collapse: collapse;\n" +
                        "}\n" +
                        "th, td {\n" +
                        "  padding: 5px;\n" +
                        "  text-align: left;    \n" +
                        "}\n" +
                        "</style> " +
                        "<table>\n" +
                        "  <tr>\n" +
                        "    <th>Value</th>\n" +
                        "    <th>Serial Number</th> \n" +
                        "    <th>Name</th>\n" +
                        "  </tr>");
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

    public void saveAsJsonFile(String path, ObservableList<InventoryItem> dataList) {
        Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        if (!path.equals("")) {
            try {
                FileWriter file = new FileWriter(path);
                file.write("[");

                for (int i = 0; i < dataList.size(); i++) {
                    file.write(gson.toJson(dataList.get(i)));

                    if (i < dataList.size() - 1) {
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
        System.out.println("");
        //
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
        System.out.println("");
    }

    public ObservableList<InventoryItem> getData() {
        System.out.println("");
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

        String fileExtension = (fileChooser.getSelectedExtensionFilter().getExtensions()).get(0);

        System.out.println("ext " + fileExtension);


        if (fileExtension.equals("*.json")) {
            System.out.println("we are here");
            dateFile = processJsonFile(file.getPath());
        } else if (fileExtension.equals("*.html")) {
             dateFile = processHTMLFile(file.getPath());
        } else  {
            dateFile = processTXTFile(file.getPath());
        }


        //return ;

        inventoryItemsData.addAll(dateFile);

    }

    private ObservableList<InventoryItem> processHTMLFile(String path) {
        ObservableList<InventoryItem> dataList = FXCollections.observableArrayList();
        File input = new File(path);
        try {
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements table = doc.select("table");
            Elements tableRows = table.select("tr");
            for (int i = 0; i < tableRows.size(); i++) {
                Element row = tableRows.get(i);
                Elements tds = row.select("td");
                for (int j = 0; j < 3; j++) {
                    System.out.println(tds.get(j).text());
                }

                Elements cols = row.select("td");
                System.out.println(" ghjd");
            }
/*
            for (Element row : table.select("tr")) {
                    Elements tds = row.select("td");

                }
*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private ObservableList<InventoryItem> processTXTFile(String path) {
        ObservableList<InventoryItem> dataList = FXCollections.observableArrayList();

        return dataList;
    }

    private ObservableList<InventoryItem> processJsonFile(String path) {
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
