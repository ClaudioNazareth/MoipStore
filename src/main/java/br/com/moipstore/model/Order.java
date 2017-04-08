package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
/**
 * Model to represent Order information and map to mongoDB
 */
@Document
public class Order {

    @Id
    private String id;

    @NotNull
    private Integer amount;

    @NotNull
    private List<Item> items;

    @NotNull
    private Customer customer;

    public Order(String id, Integer amount, List<Item> items, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.items = items;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
            sb.append("id='").append(id).append('\'');
            sb.append(", amount=").append(amount);
            sb.append(", items=").append(items);
            sb.append(", customer=").append(customer);
            sb.append('}');
        return sb.toString();
    }
}
