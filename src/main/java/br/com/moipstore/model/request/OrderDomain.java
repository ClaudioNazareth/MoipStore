package br.com.moipstore.model.request;


import java.util.ArrayList;
import java.util.List;

public class OrderDomain {

    private String customerId;
    private final List<ItemDomain> items = new ArrayList<>();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemDomain> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDomain{");
        sb.append("items=").append(items);
        sb.append(", customerId='").append(customerId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
