package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ItemInventoryManager {

    private static final FileChooser fileChooser = new FileChooser();

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
        } else if (fileExtension.equals("*.csv")) {
            saveAsCSVFile(path, inventoryItemsData);
        }


    }

    private void saveAsCSVFile(String path, ObservableList<InventoryItem> inventoryItemsData) {
        if (!path.equals("")) {
            try {
                FileWriter file = new FileWriter(path);
                for (int i = 0; i < inventoryItemsData.size(); i++) {
                    file.write(inventoryItemsData.get(i).getItemValue() + ",");
                    file.write( inventoryItemsData.get(i).getItemSerialNumber() + ",");

                    file.write(inventoryItemsData.get(i).getItemName() +",");
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
        FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter("JSON file (*.json)", "*.json");
        FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter("Html file (*.Html)", "*.Html");
        FileChooser.ExtensionFilter extensionFilter3 = new FileChooser.ExtensionFilter("csv file (*.csv)", "*.csv");

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
}
