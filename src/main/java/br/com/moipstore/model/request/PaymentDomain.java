package br.com.moipstore.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PaymentDomain", description="Represents the Pay,emt data that will be received by the server in the Rest API")
public class PaymentDomain {

    @ApiModelProperty(value = "Order identifier",dataType = "string", required = true)
    private String orderId;

    @ApiModelProperty(value = "Hash generated with card data",dataType = "string", required = true)
    private String creditCardHash;

    @ApiModelProperty(value = "Credit Card Holder", dataType = "object", required = true)
    private HolderDomain holderDomain;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreditCardHash() {
        return creditCardHash;
    }

    public void setCreditCardHash(String creditCardHash) {
        this.creditCardHash = creditCardHash;
    }

    public HolderDomain getHolderDomain() {
        return holderDomain;
    }

    public void setHolderDomain(HolderDomain holderDomain) {
        this.holderDomain = holderDomain;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PaymentDomain{");
            sb.append("orderId='").append(orderId).append('\'');
            sb.append(", creditCardHash='").append(creditCardHash).append('\'');
            sb.append(", holderDomain=").append(holderDomain);
            sb.append('}');
        return sb.toString();
    }
}
