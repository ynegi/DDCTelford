package ddc.telford.app;

import ddc.telford.model.Item;
import ddc.telford.model.Order;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.function.Function;

public class OrderBuilder {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public Order createOrder(List<Item> items)
    {
       return new Order(items);
    }

    public String generateBill(Order order) {

        StringBuilder printReceipt =  new StringBuilder();
        printReceipt.append("===========Cafe XYZ==============")
                .append("\n Order No. : " + order.getOrderNo())
                .append("\n=================================")
                .append("\n" + order.toString())
                .append("\n=================================")
                .append("\nSub Total : " + currencyFormat.format(calcSubTotal(order))).append("\nTotal : ").append(currencyFormat.format(calcTotal(order)));
        return printReceipt.toString();

    }

    public  BigDecimal calcTotal(Order order) {

        return calcSubTotal(order);
    }

    private BigDecimal calcSubTotal(Order order) {
        Function<Item, BigDecimal> totalMapper = item -> item.getUnitPrice().multiply(item.getQuantity());

        BigDecimal subTotal;
        subTotal = order.getOrder().stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return subTotal;
    }

}
