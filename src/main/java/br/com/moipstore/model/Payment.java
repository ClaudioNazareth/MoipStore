package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Model to represent Payment information and map to mongoDB
 */
@Document
public class Payment {

    @Id
    private String id;

    private String code;
    //Simplified model for testes purpose
    @NotNull
    private String status;
    @NotNull
    private Integer amount;

    public Payment(String id, String status, Integer amount) {
        this.id = id;
        this.code = id;
        this.status = status;
        this.amount = amount;
    }

    Payment(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Payment{");
            sb.append("id='").append(id).append('\'');
            sb.append(", status='").append(status).append('\'');
            sb.append(", amount=").append(amount);
            sb.append('}');
        return sb.toString();
    }
}
