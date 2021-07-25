package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
//  class instance variable
//  Name : SimpleStringProperty
//  serial number : SimpleStringProperty
//  value : SimpleDateFormat
// constructors
//  InventoryItem(String name, String value, String serial number)
// set setters for the instance variables
// get getters for the instance variables

public class InventoryItem {
    SimpleStringProperty itemValue = new SimpleStringProperty();
    SimpleStringProperty itemSerialNumber= new SimpleStringProperty();
    SimpleStringProperty itemName= new SimpleStringProperty();

    public InventoryItem(String itemValue, String itemsSerialNumber, String itemName) {
        this.itemValue.set(itemValue);
        this.itemSerialNumber.set(itemsSerialNumber);
        this.itemName.set(itemName);
    }
    public InventoryItem() {

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

    public double getValueInDouble(){
        //String val =itemValue.get().s;
        return Double.parseDouble( itemValue.get().substring(1));
    }
}
