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

    private String code;

    @NotNull
    private Integer amount;

    @NotNull
    private List<Item> items;

    @NotNull
    private Customer customer;

    public Order(String id, Integer amount, List<Item> items, Customer customer) {
        this.id = id;
        this.code = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Order{" +
                "code='" + code + '\'' +
                ", amount=" + amount +
                ", items=" + items +
                ", customer=" + customer +
                '}';
    }
}
