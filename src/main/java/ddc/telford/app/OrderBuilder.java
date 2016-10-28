package ddc.telford.app;

import ddc.telford.model.Item;
import ddc.telford.model.Order;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.function.Function;

public class OrderBuilder {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    private BigDecimal subTotal = BigDecimal.valueOf(0.0);
    private BigDecimal minServiceTax = BigDecimal.valueOf(0.10);
    private BigDecimal maxServiceTax = BigDecimal.valueOf(0.20);

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
                .append("\nSub Total : " + currencyFormat.format(calcSubTotal(order)))
                .append("\nService Charge : " + currencyFormat.format(calcServiceCharge(order)))
                .append("\nTotal : " + currencyFormat.format(calcTotal(order)));
        return printReceipt.toString();

    }

    public  BigDecimal calcTotal(Order order) {

        return calcSubTotal(order).add(calcServiceCharge(order));
    }

    private BigDecimal calcSubTotal(Order order) {
        Function<Item, BigDecimal> totalMapper = item -> item.getUnitPrice().multiply(item.getQuantity());


        subTotal = order.getOrder().stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return subTotal;
    }

    public BigDecimal calcServiceCharge(Order order) {

        if(isServiceChargeApplicable(order)){

            if(isHotFoodOrdered(order))
            {
                subTotal = subTotal.multiply(maxServiceTax).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(subTotal.compareTo(BigDecimal.valueOf(20)) > 0) {
                    return BigDecimal.valueOf(20).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                else
                    return subTotal;
            }else {
                subTotal = subTotal.multiply(minServiceTax).setScale(2, BigDecimal.ROUND_HALF_UP);
                return subTotal;
            }
        }

        return  BigDecimal.valueOf(0.0);
    }


    private boolean isServiceChargeApplicable(Order order) {

        return order.getOrder().stream().anyMatch(item -> item.getCategory().equals(Item.Category.FOOD));
    }

    private boolean isHotFoodOrdered(Order order) {

        return order.getOrder().stream().anyMatch(item -> item.getCategory().equals(Item.Category.FOOD) && item.getTemperature().equals(Item.Temperature.HOT));
    }

}
