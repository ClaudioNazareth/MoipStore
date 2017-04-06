package br.com.moipstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Model to represent all product information and map to mongoDB
 */
@Document
public class Product {

    @Id
    private String id;

    @Indexed
    private String code;

    @Indexed
    private String name;

    private String slogan;

    private String description;

    private String info;

    private String dimensions;

    //Simplified model for this test
    private String media;

    //Simplified model for this test
    private String manufacturer;

    private BigDecimal price;

    private List<String> imageLinks = new ArrayList<>();

    public Product(String code, String name, String description, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    Product(){
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
            sb.append("id='").append(id).append('\'');
            sb.append(", code='").append(code).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", slogan='").append(slogan).append('\'');
            sb.append(", description='").append(description).append('\'');
            sb.append(", info='").append(info).append('\'');
            sb.append(", dimensions='").append(dimensions).append('\'');
            sb.append(", media='").append(media).append('\'');
            sb.append(", manufacturer='").append(manufacturer).append('\'');
            sb.append(", price=").append(price);
            sb.append(", imageLinks=").append(imageLinks);
            sb.append('}');
        return sb.toString();
    }
}
