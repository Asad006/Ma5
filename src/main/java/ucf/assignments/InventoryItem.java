package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {
    SimpleStringProperty itemValue;
    SimpleStringProperty itemSerialNumber;
    SimpleStringProperty itemName;

    public InventoryItem(SimpleStringProperty itemValue, SimpleStringProperty itemsSerialNumber, SimpleStringProperty itemName) {
        this.itemValue = itemValue;
        this.itemSerialNumber = itemsSerialNumber;
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue.get();
    }

    public SimpleStringProperty itemValueProperty() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue.set(itemValue);
    }

    public String getItemSerialNumber() {
        return itemSerialNumber.get();
    }

    public SimpleStringProperty itemSerialNumberProperty() {
        return itemSerialNumber;
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber.set(itemSerialNumber);
    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }
}
