package br.com.moipstore.data;


import br.com.moipstore.model.request.ItemDomain;
import br.com.moipstore.model.request.OrderDomain;

public class OrderData {

    public static OrderDomain getOrderDomainWithOneInstallments() {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setCustomerId("CUST-1000");
        ItemDomain itemDomain = new ItemDomain();
        itemDomain.setProductCode("PROD-1030");
        itemDomain.setQuantity(2);
        orderDomain.getItems().add(itemDomain);
        orderDomain.setNumberOfInstallments(1);
        orderDomain.setCoupon("COUP-15721");
        return orderDomain;
    }

    public static OrderDomain getOrderDomainWithMoreThanOneInstallments() {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setCustomerId("CUST-1000");
        ItemDomain itemDomain = new ItemDomain();
        itemDomain.setProductCode("PROD-1030");
        itemDomain.setQuantity(2);
        orderDomain.getItems().add(itemDomain);
        orderDomain.setNumberOfInstallments(5);
        orderDomain.setCoupon("COUP-15721");
        return orderDomain;
    }
}
