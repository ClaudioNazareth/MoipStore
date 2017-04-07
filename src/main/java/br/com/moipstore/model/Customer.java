package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Model to represent Customer information and map to mongoDB
 */
@Document
public class Customer {

    @Id
    private String id;

    @Indexed
    @NotNull
    private String fullName;

    @Indexed
    private String email;

    @NotNull
    private Instant birthDate;

    @NotNull
    private String taxDocument;

    @NotNull
    private ShippingAddress ShippingAddress;

    @NotNull
    private Phone phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getTaxDocument() {
        return taxDocument;
    }

    public void setTaxDocument(String taxDocument) {
        this.taxDocument = taxDocument;
    }

    public br.com.moipstore.model.ShippingAddress getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(br.com.moipstore.model.ShippingAddress shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
            sb.append("id='").append(id).append('\'');
            sb.append(", fullName='").append(fullName).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append(", birthDate=").append(birthDate);
            sb.append(", taxDocument='").append(taxDocument).append('\'');
            sb.append(", ShippingAddress=").append(ShippingAddress);
            sb.append(", phone=").append(phone);
            sb.append('}');
        return sb.toString();
    }
}
