package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Model to represent Items information and map to mongoDB
 */
@Document
public class Item {

    @Id
    private String id;

    @Indexed
    @NotNull
    private String productCode;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    public Item(String productCode, Integer quantity, BigDecimal price) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
    }

    Item(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("id='").append(id).append('\'');
        sb.append(", productCode='").append(productCode).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
