package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Model to represent Customer information and map to mongoDB
 */
@Document
public class Customer {

    @Id
    private String id;

    @NotNull
    private String code;

    @Indexed
    @NotNull
    private String fullName;

    @Indexed
    private String email;

    @NotNull
    private Date birthDate;

    @NotNull
    private String taxDocument;

    @NotNull
    private ShippingAddress shippingAddress;

    @NotNull
    private Phone phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getTaxDocument() {
        return taxDocument;
    }

    public void setTaxDocument(String taxDocument) {
        this.taxDocument = taxDocument;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
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
            sb.append("code='").append(code).append('\'');
            sb.append(", fullName='").append(fullName).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append(", birthDate=").append(birthDate);
            sb.append(", taxDocument='").append(taxDocument).append('\'');
            sb.append(", shippingAddress=").append(shippingAddress);
            sb.append(", phone=").append(phone);
            sb.append('}');
        return sb.toString();
    }
}
