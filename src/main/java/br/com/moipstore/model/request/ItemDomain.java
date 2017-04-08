package br.com.moipstore.model.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ItemDomain", description="Represents the Item data that will be received by the server in the Rest API")
public class ItemDomain {

    @ApiModelProperty(value = "Product code",dataType = "string", required = true)
    private String productCode;

    @ApiModelProperty(value = "Number of items purchased",dataType = "number", required = true)
    private int quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemDomain{");
        sb.append("productCode='").append(productCode).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
