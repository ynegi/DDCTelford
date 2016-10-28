package ddc.telford.test;

import ddc.telford.app.OrderBuilder;
import ddc.telford.model.Item;
import ddc.telford.model.Menu;
import ddc.telford.model.Order;
import org.junit.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class CafeMenuTest {

    private static List<Item> menuItems;
    private OrderBuilder orderBuilder =  new OrderBuilder();

    @BeforeClass
    public static void setUpClass() {
        Menu menu = new Menu();
        menuItems = menu.getMenuItems();
    }

    /*
        Generate a bill from a list of purchased orderedItems
        e.g. [“Cola”, “Coffee”, “Cheese Sandwich”] returns 3.5
    */
    @Test
    public void step1Modified() {
        List<Item> orderedItems = menuItems.stream()
                .filter(item -> item.getItemDescription().equals("Cola") || item.getItemDescription().equals("Coffee") || item.getItemDescription().equals("Cheese Sandwich"))
                .collect(Collectors.toList());

        Order order = orderBuilder.createOrder(orderedItems);
        assertEquals(BigDecimal.valueOf(3.85).setScale(2,BigDecimal.ROUND_HALF_EVEN), orderBuilder.calcTotal(order));
        System.out.println(orderBuilder.generateBill(order) + "\n");

    }

    /*
    step 2
     */
    @Test
    public void whenOrderedItemsOnlyDrink() {
        List<Item> orderedItems = menuItems.stream()
                .filter(item -> item.getItemDescription().equals("Cola") || item.getItemDescription().equals("Coffee"))
                .collect(Collectors.toList());
        Order order = orderBuilder.createOrder(orderedItems);
        assertEquals(BigDecimal.valueOf(0.0), orderBuilder.calcServiceCharge(order));
        System.out.println(orderBuilder.generateBill(order)  + "\n");

    }

    @Test
    public void calculateServiceChargeWhenAnyFoodOrdered(){
        List<Item> orderedItems = menuItems.stream()
                .filter(item -> item.getItemDescription().equals("Cola") || item.getItemDescription().equals("Coffee") || item.getItemDescription().equals("Cheese Sandwich"))
                .collect(Collectors.toList());

        Order order = orderBuilder.createOrder(orderedItems);
        assertEquals(BigDecimal.valueOf(3.85).setScale(2,BigDecimal.ROUND_HALF_EVEN), orderBuilder.calcTotal(order));
        System.out.println(orderBuilder.generateBill(order)  + "\n");
    }

    @Test
    public void calculateServiceChargeWhenAnyHotFoodOrdered(){
        List<Item> orderedItems = menuItems.stream()
                .filter(item -> item.getItemDescription().equals("Cola") || item.getItemDescription().equals("Coffee")
                        || item.getItemDescription().equals("Cheese Sandwich") || item.getItemDescription().equals("Steak Sandwich"))
                .collect(Collectors.toList());

        Order order = orderBuilder.createOrder(orderedItems);
        assertEquals(new BigDecimal(9.60).setScale(2,BigDecimal.ROUND_HALF_EVEN), orderBuilder.calcTotal(order));
        System.out.println(orderBuilder.generateBill(order)  + "\n");
    }

    @Test
    public void calculateServiceChargeWhenAnyHotFoodOrderedMoreThanTwenty(){
        menuItems.get(3).setQuantity(new BigDecimal(50));
        List<Item> orderedItems = menuItems.stream()
                .filter(item -> item.getItemDescription().equals("Cola") || item.getItemDescription().equals("Coffee")
                        || item.getItemDescription().equals("Cheese Sandwich") || item.getItemDescription().equals("Steak Sandwich"))
                .collect(Collectors.toList());

        Order order = orderBuilder.createOrder(orderedItems);
        assertEquals(new BigDecimal(248.50).setScale(2,BigDecimal.ROUND_HALF_EVEN), orderBuilder.calcTotal(order));
        System.out.println(orderBuilder.generateBill(order)  + "\n");
    }

}
