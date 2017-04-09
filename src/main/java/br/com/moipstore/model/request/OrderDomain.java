package br.com.moipstore.model.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="OrderDomain", description="Represents the Order data that will be received by the server in the Rest API")
public class OrderDomain {

    @ApiModelProperty(value = "CustomerId in the dataBase",dataType = "string", required = true)
    private String customerId;

    @ApiModelProperty(value = "List of items that will be purchased", required = true)
    private List<ItemDomain> items = new ArrayList<>();

    private int numberOfInstallments;
    private String coupon;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemDomain> getItems() {
        return items;
    }

    public void setItems(List<ItemDomain> items) {
        this.items = items;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDomain{");
            sb.append("customerId='").append(customerId).append('\'');
            sb.append(", items=").append(items);
            sb.append(", numberOfInstallments=").append(numberOfInstallments);
            sb.append(", coupon='").append(coupon).append('\'');
            sb.append('}');
        return sb.toString();
    }
}
