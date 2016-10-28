package ddc.telford.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int  orderNo = 0;
    private List<Item> order = new ArrayList<>();
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public Order(List<Item> order) {
        this.order = order;
        orderNo++;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public List<Item> getOrder() {
        return order;
    }

    @Override
    public String toString() {
        StringBuilder orderString =  new StringBuilder();
        for(Item item :  order){
            orderString.append(item.getItemDescription());
            orderString.append("\t");
            orderString.append(item.getCategory());
            orderString.append("\t");
            orderString.append(item.getQuantity());
            orderString.append("\t");
            orderString.append(currencyFormat.format(item.getUnitPrice()));
            orderString.append("\n");

        }
        return orderString.toString();
    }
}
