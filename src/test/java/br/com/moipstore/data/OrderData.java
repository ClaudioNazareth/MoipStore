package br.com.moipstore.data;


import br.com.moipstore.model.request.ItemDomain;
import br.com.moipstore.model.request.OrderDomain;

public class OrderData {

    public static OrderDomain getOrderDomain() {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setCustomerId("CUST-1000");
        ItemDomain itemDomain = new ItemDomain();
        itemDomain.setProductCode("PROD-1030");
        itemDomain.setQuantity(2);
        orderDomain.getItems().add(itemDomain);
        return orderDomain;
    }
}
