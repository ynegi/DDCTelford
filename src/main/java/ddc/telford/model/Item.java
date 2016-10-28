package ddc.telford.model;

import java.math.BigDecimal;

public class Item {

    public enum Temperature {
        HOT, COLD
    }

    public enum Category{
        FOOD, DRINK
    }

    private String itemDescription;
    private Category category;
    private Temperature temperature;
    private BigDecimal unitPrice;
    private BigDecimal quantity;

    public Item(String itemDescription, Category category, Temperature temperature, BigDecimal unitPrice, BigDecimal quantity) {
        this.itemDescription = itemDescription;
        this.category = category;
        this.temperature = temperature;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemDescription='" + itemDescription + '\'' +
                ", category=" + category +
                ", temperature=" + temperature +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
