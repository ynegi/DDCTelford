package ddc.telford.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    public List<Item> getMenuItems() {
        return menuItems;
    }

    private List<Item> menuItems = new ArrayList<>();

    public Menu() {
        populateMenuItems();
    }

    private void populateMenuItems() {
        menuItems.add(new Item("Cola", Item.Category.DRINK, Item.Temperature.COLD,  new BigDecimal(0.50), new BigDecimal(1)));
        menuItems.add(new Item("Coffee", Item.Category.DRINK, Item.Temperature.HOT ,new BigDecimal(1.00), new BigDecimal(1)));
        menuItems.add(new Item("Cheese Sandwich", Item.Category.FOOD, Item.Temperature.COLD,new BigDecimal(2.00), new BigDecimal(1)));
        menuItems.add(new Item("Steak Sandwich", Item.Category.FOOD, Item.Temperature.HOT, new BigDecimal(4.50), new BigDecimal(1)));
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuItems=" + menuItems +
                '}';
    }
}
