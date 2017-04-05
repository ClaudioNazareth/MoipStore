package br.com.moipstore.rest.request;


import java.util.ArrayList;
import java.util.List;

public class OrderRequest {

    private String customerId;
    private String ShippingAddressId;
    private final List<ItemRequest> items = new ArrayList<>();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShippingAddressId() {
        return ShippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        ShippingAddressId = shippingAddressId;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderRequest{");
        sb.append("items=").append(items);
        sb.append(", customerId='").append(customerId).append('\'');
        sb.append(", ShippingAddressId='").append(ShippingAddressId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
