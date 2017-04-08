package br.com.moipstore.model.request;


public class PaymentDomain {

    private String orderId;
    private String creditCardHash;
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

}
