package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FilesExtensionHandlerTest extends TestCase {

    public void testSaveAsJsonFile() {
        // Given
        // create  an object TodoListTableManager
        // expectedData observable  collection
        // create identical actualData Observable Collection with expected.

        // when...
        // call method SaveInJsonFile  to change expectedData

        // then
        //assertEquals(expectedData,actualData);
        ItemInventoryManager itemListManager = new ItemInventoryManager();
        FilesExtensionHandler fHandler = new FilesExtensionHandler();

        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$745.36", "AWQERFAFD8", "Sony");
        InventoryItem item1 = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item2 = new InventoryItem("$599.99", "S40AZBDE47", "Samsung TV");

        actualData.add(item);
        actualData.add(item1);
        actualData.add(item2);

        fHandler.saveAsJsonFile("src/test/resources/TextCaseOpen.json",actualData);

        String actual = "";

        try {
            ClassLoader classLoader = getClass().getClassLoader();

            File file = new File(classLoader.getResource("TextCaseOpen.json").getFile());

            actual = FileUtils.readFileToString(new File(file.getPath()), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = "[{\n" +
                "  \"itemValue\": \"$745.36\",\n" +
                "  \"itemSerialNumber\": \"AWQERFAFD8\",\n" +
                "  \"itemName\": \"Sony\"\n" +
                "},{\n" +
                "  \"itemValue\": \"$399.00\",\n" +
                "  \"itemSerialNumber\": \"AXB124AXY3\",\n" +
                "  \"itemName\": \"Xbox One\"\n" +
                "},{\n" +
                "  \"itemValue\": \"$599.99\",\n" +
                "  \"itemSerialNumber\": \"S40AZBDE47\",\n" +
                "  \"itemName\": \"Samsung TV\"\n" +
                "}]";

        assertEquals(expected, actual);
    }

    public void testSaveAsTSVFile() {
        // Given
        // create  an object TodoListTableManager
        // expectedData observable  collection
        // create identical actualData Observable Collection with expected.

        // when...
        // call method SaveInJsonFile  to change expectedData

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        FilesExtensionHandler fHandler = new FilesExtensionHandler();

        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$745.36", "AWQERFAFD8", "Sony");
        InventoryItem item1 = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item2 = new InventoryItem("$599.99", "S40AZBDE47", "Samsung TV");

        actualData.add(item);
        actualData.add(item1);
        actualData.add(item2);

        fHandler.saveAsTSVFile("src/test/resources/TextCaseOpen.txt",actualData);

        String actual = "";

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TextCaseOpen.txt").getFile());

            actual = FileUtils.readFileToString(new File(file.getPath()), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = "Value\tSerial Number\tName\n" +
                "$745.36\tAWQERFAFD8\tSony\n" +
                "$399.00\tAXB124AXY3\tXbox One\n" +
                "$599.99\tS40AZBDE47\tSamsung TV\n";

        assertEquals(expected, actual);
    }

    public void testSaveInHTMLFile() {
        // Given
        // create  an object TodoListTableManager
        // expectedData observable  collection
        // create identical actualData Observable Collection with expected.

        // when...
        // call method SaveInJsonFile  to change expectedData

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        FilesExtensionHandler fHandler = new FilesExtensionHandler();

        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$745.36", "AWQERFAFD8", "Sony");
        InventoryItem item1 = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item2 = new InventoryItem("$599.99", "S40AZBDE47", "Samsung TV");

        actualData.add(item);
        actualData.add(item1);
        actualData.add(item2);

        fHandler.saveInHTMLFile("src/test/resources/TextCaseOpen.html",actualData);

        String actual = "";

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TextCaseOpen.html").getFile());

            actual = FileUtils.readFileToString(new File(file.getPath()), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected =
                "<!DOCTYPE html><html><body><h2>Inventory Items " +
                "Table</h2><style>table, th, td {border: 0px solid black;border-collapse: collapse;}th, " +
                "td {padding: 5px;text-align: left;}</style> <table><tr><th>Value</th>" +
                "<th>Serial Number</th><th>Name</th></tr><tr><td>$745.36</td><td>AWQERFAFD8</td>" +
                "<td>Sony</td></tr><tr><td>$399.00</td><td>AXB124AXY3</td><td>Xbox One</td></tr><tr><td>$599.99</td>" +
                "<td>S40AZBDE47</td><td>Samsung TV</td></tr></tr></table></body></html>";

        assertEquals(expected, actual);
    }

    public void testProcessHTMLFile() {
        // Given
        // create  an object TodoListTableManager
        // expectedData observable  collection
        // create identical actualData Observable Collection with expected.

        // when...
        // call method ProcessTXTFile  to change expectedData

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        FilesExtensionHandler fHandler = new FilesExtensionHandler();

        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$745.36", "AWQERFAFD8", "Sony");
        InventoryItem item1 = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item2 = new InventoryItem("$599.99", "S40AZBDE47", "Samsung TV");

        itemListManager.add(item);
        itemListManager.add(item1);
        itemListManager.add(item2);

        actualData=itemListManager.getData();
        expectedData = fHandler.processHTMLFile("src/test/resources/TextCaseOpen.html");

        String actual = "";

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TextCaseOpen.html").getFile());

            actual = FileUtils.readFileToString(new File(file.getPath()), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = "<!DOCTYPE html><html><body><h2>Inventory Items " +
                "Table</h2><style>table, th, td {border: 0px solid black;border-collapse: collapse;}th, " +
                "td {padding: 5px;text-align: left;}</style> <table><tr><th>Value</th>" +
                "<th>Serial Number</th><th>Name</th></tr><tr><td>$745.36</td><td>AWQERFAFD8</td>" +
                "<td>Sony</td></tr><tr><td>$399.00</td><td>AXB124AXY3</td><td>Xbox One</td></tr><tr><td>$599.99</td>" +
                "<td>S40AZBDE47</td><td>Samsung TV</td></tr></tr></table></body></html>";

        assertEquals(expected, actual);

    }

    public void testProcessTXTFile() {
        // Given
        // create  an object TodoListTableManager
        // expectedData observable  collection
        // create identical actualData Observable Collection with expected.

        // when...
        // call method ProcessTXTFile  to change expectedData

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        FilesExtensionHandler fHandler = new FilesExtensionHandler();

        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$745.36", "AWQERFAFD8", "Sony");
        InventoryItem item1 = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item2 = new InventoryItem("$599.99", "S40AZBDE47", "Samsung TV");

        itemListManager.add(item);
        itemListManager.add(item1);
        itemListManager.add(item2);

        actualData=itemListManager.getData();
        expectedData = fHandler.processTXTFile("src/test/resources/TextCaseOpen.txt");

        String actual = "";

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TextCaseOpen.txt").getFile());

            actual = FileUtils.readFileToString(new File(file.getPath()), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = "Value\tSerial Number\tName\n" +
                "$745.36\tAWQERFAFD8\tSony\n" +
                "$399.00\tAXB124AXY3\tXbox One\n" +
                "$599.99\tS40AZBDE47\tSamsung TV\n";

        assertEquals(expected, actual);
    }

    public void testProcessJsonFile() {
        // Given
        // create  an object TodoListTableManager
        // expectedData observable  collection
        // create identical actualData Observable Collection with expected.

        // when...
        // call method ProcessTXTFile  to change expectedData

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        FilesExtensionHandler fHandler = new FilesExtensionHandler();

        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$745.36", "AWQERFAFD8", "Sony");
        InventoryItem item1 = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item2 = new InventoryItem("$599.99", "S40AZBDE47", "Samsung TV");

        itemListManager.add(item);
        itemListManager.add(item1);
        itemListManager.add(item2);

        actualData=itemListManager.getData();
        expectedData = fHandler.processJsonFile("src/test/resources/TextCaseOpen.json");

        String actual = "";

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TextCaseOpen.json").getFile());

            actual = FileUtils.readFileToString(new File(file.getPath()), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = "[{\n" +
                "  \"itemValue\": \"$745.36\",\n" +
                "  \"itemSerialNumber\": \"AWQERFAFD8\",\n" +
                "  \"itemName\": \"Sony\"\n" +
                "},{\n" +
                "  \"itemValue\": \"$399.00\",\n" +
                "  \"itemSerialNumber\": \"AXB124AXY3\",\n" +
                "  \"itemName\": \"Xbox One\"\n" +
                "},{\n" +
                "  \"itemValue\": \"$599.99\",\n" +
                "  \"itemSerialNumber\": \"S40AZBDE47\",\n" +
                "  \"itemName\": \"Samsung TV\"\n" +
                "}]";

        assertEquals(expected, actual);

    }
}