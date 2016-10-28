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
    public void step1() {
        List<Item> orderedItems = menuItems.stream()
                .filter(item -> item.getItemDescription().equals("Cola") || item.getItemDescription().equals("Coffee") || item.getItemDescription().equals("Cheese Sandwich"))
                .collect(Collectors.toList());

        Order order = orderBuilder.createOrder(orderedItems);
        assertEquals(BigDecimal.valueOf(3.50), orderBuilder.calcTotal(order));
        System.out.println(orderBuilder.generateBill(order));

    }
}
