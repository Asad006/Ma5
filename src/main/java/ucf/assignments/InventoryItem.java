package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {
    SimpleStringProperty itemValue = new SimpleStringProperty();
    SimpleStringProperty itemSerialNumber= new SimpleStringProperty();
    SimpleStringProperty itemName= new SimpleStringProperty();

    public InventoryItem(String itemValue, String itemsSerialNumber, String itemName) {
        this.itemValue.set(itemValue);
        this.itemSerialNumber.set(itemsSerialNumber);
        this.itemName.set(itemName);
    }

    public String getItemValue() {
        return itemValue.get();
    }

    public void setItemValue(String itemValue) {
        this.itemValue.set(itemValue);
    }

    public String getItemSerialNumber() {
        return itemSerialNumber.get();
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber.set(itemSerialNumber);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }
}
