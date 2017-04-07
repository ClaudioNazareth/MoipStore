package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Model to represent  ShippingAddress information and map to mongoDB
 */
@Document
public class ShippingAddress {

    @Id
    private String id;

    @Indexed
    @NotNull
    private String street;
    private String streetNumber;
    private String complement;
    private String district;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ShippingAddress{");
            sb.append("street='").append(street).append('\'');
            sb.append(", streetNumber='").append(streetNumber).append('\'');
            sb.append(", complement='").append(complement).append('\'');
            sb.append(", district='").append(district).append('\'');
            sb.append(", city='").append(city).append('\'');
            sb.append(", state='").append(state).append('\'');
            sb.append(", country='").append(country).append('\'');
            sb.append(", zipCode='").append(zipCode).append('\'');
            sb.append('}');
        return sb.toString();
    }
}
