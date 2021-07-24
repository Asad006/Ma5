package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;

public class ItemInventoryManagerTest extends TestCase {

    public void testSave() {
    }

    public void testSaveAsJsonFile() {
    }

    public void testSearch() {
    }

    public void testAdd() {
        // Given
        // create  an object ItemInventoryManager
        // expectedData observable  collection object set to empty
        // create actualData Observable Collection
        // when...
        // call method ADD to add new TodoTask object

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        String itemValue = "$399.00";
        String itemSerialNumber = "AXB124AXY3";
        String itemName = "Xbox One";

        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        InventoryItem item = new InventoryItem(itemValue, itemSerialNumber, itemName);

        expectedData.add(item);

        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();
        itemListManager.add(item);
        actualData= itemListManager.getData();

        assertEquals(expectedData.get(0).getItemValue(), actualData.get(0).getItemValue());
        assertEquals(expectedData.get(0).getItemSerialNumber(), actualData.get(0).getItemSerialNumber());
        assertEquals(expectedData.get(0).getItemName(), actualData.get(0).getItemName());


    }

    public void testIsSerialNumberUnique() {
    }

    public void testEditItem() {
        // Given
        // create  an object ItemInventoryManager
        // set an expectedData observable  collection object
        // create actualData Observable Collection
        // when...
        // call method edit with the new ItemInventory data with index of the data to edit

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        String itemValue = "$399.00";
        String itemSerialNumber = "AXB124AXY3";
        String itemName = "Xbox One";

        String itemValue1 = "$599.99";
        String itemSerialNumber1 = "S40AZBDE47";
        String itemName1 = "Samsung TV";

        InventoryItem item = new InventoryItem(itemValue, itemSerialNumber, itemName);
        InventoryItem item2 = new InventoryItem(itemValue1, itemSerialNumber1, itemName1);

        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        expectedData.add(item2);

        itemListManager.add(item);
        itemListManager.editItem(item2,0);

        actualData = itemListManager.getData();

        assertEquals(expectedData.get(0).getItemValue(), actualData.get(0).getItemValue());
        assertEquals(expectedData.get(0).getItemSerialNumber(), actualData.get(0).getItemSerialNumber());
        assertEquals(expectedData.get(0).getItemName(), actualData.get(0).getItemName());
    }

    public void testGetData() {
        // Given
        // create  an object ItemInventoryManager
        // set an expectedData observable  collection object
        // create actualData Observable Collection
        // when...
        // call method getData to actual data

        // then
        //assertEquals(expectedData,actualData);

        ItemInventoryManager itemListManager = new ItemInventoryManager();
        String itemValue = "$399.00";
        String itemSerialNumber = "AXB124AXY3";
        String itemName = "Xbox One";

        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        InventoryItem item = new InventoryItem(itemValue, itemSerialNumber, itemName);

        expectedData.add(item);

        ObservableList<InventoryItem> actualData ;
        itemListManager.add(item);
        actualData= itemListManager.getData();

        assertEquals(expectedData.get(0).getItemValue(), actualData.get(0).getItemValue());
        assertEquals(expectedData.get(0).getItemSerialNumber(), actualData.get(0).getItemSerialNumber());
        assertEquals(expectedData.get(0).getItemName(), actualData.get(0).getItemName());

    }

    public void testOpen() {
    }

    public void testIsNumericValue() {
        // Given
        // create  an object ItemInventoryManager
        // String text yo simulate text entry text

        // when...
        // get the row to be deleted.
        // call method testIsNumericValue()

        // then
        // assertFalse if does not meet requirements
        // assertTrue if does not meet requirements

        ItemInventoryManager itemInventoryManager = new ItemInventoryManager();
        String textTest = "599.99";
        String textTest1 = "59f9.99";

        assertTrue(itemInventoryManager.isNumericValue(textTest));
        assertFalse(itemInventoryManager.isNumericValue(textTest1));

    }

    public void testDelete() {
        // Given
        // create  an object ItemInventoryManager
        // expectedData observable  collection object
        // add test objects to expectedData
        // create actualData Observable Collection with the deleted object

        // when...
        //
        // call method deleted  to delete TodoTask object

        // then
        //assertEquals(expectedData,actualData);
        ItemInventoryManager itemInventoryManager = new ItemInventoryManager();
        String itemValue = "$399.00";
        String itemSerialNumber = "AXB124AXY3";
        String itemName = "Xbox One";


        ObservableList<InventoryItem> expectedData = FXCollections.observableArrayList();
        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem(itemValue, itemSerialNumber, itemName);
        actualData.add(item);
        int index = 0;

        itemInventoryManager.delete(actualData, index);

        assertEquals(expectedData, actualData);
    }

    public void testClear() {
        // Given
        // create  an object itemInventoryManager
        // expectedData observable  collection object
        // add test objects to expectedData
        // create actualData Observable Collection with the deleted object

        // when...
        // call method  clear

        // then
        //assertEquals(expectedData,actualData);
        // or assertNull(actual)
        ItemInventoryManager itemListManager = new ItemInventoryManager();

        ObservableList<InventoryItem> actualData = FXCollections.observableArrayList();

        InventoryItem item = new InventoryItem("$399.00", "AXB124AXY3", "Xbox One");
        InventoryItem item1 = new InventoryItem("$599.99 ", "S40AZBDE47", "Samsung TV");

        itemListManager.add(item);
        itemListManager.add(item1);

        itemListManager.clear(itemListManager.getData());
        actualData= itemListManager.getData();

        int actualSize = actualData.size();
        int expectedSize = 0;

        assertNotNull(actualData);
        assertEquals(expectedSize, actualSize);
    }

    public void testIsSerialNumberIsValid() {
        // Given
        // create  an object ItemInventoryManager
        // String text yo simulate text entry text

        // when...
        // call method testIsSerialNumberIsValid()

        // then
        // assertFalse if does not meet requirements
        // assertTrue if does not meet requirements

    }
}