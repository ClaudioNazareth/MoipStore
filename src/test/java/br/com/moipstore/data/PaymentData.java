package br.com.moipstore.data;


import br.com.moipstore.model.request.HolderDomain;
import br.com.moipstore.model.request.PaymentDomain;

public class PaymentData {

    public static PaymentDomain createPaymentDomain(String orderId){
        PaymentDomain paymentDomain = new PaymentDomain();
        paymentDomain.setOrderId(orderId);
        paymentDomain.setCreditCardHash("XLDZ7YBuflYXftg7bkHr9YzHhoSvH5d3uT774C9LjMi26ORvvqA4o7hEHApaqtQ9x6s/Z0NOkhpbiFqz8bian7VjhcnGJoXIdBanvzy/8tZFnVpoKOcfYpV6JLm4hlT1RP1XAKjBgzLxhqqdIJCMww5fCqG6uuEb8AqSg62IVL+rJ71r+OQPgxbgEwy7rzPxqXlF4yA3Tt18rK3MG6HneEpFfI17/HSFn7wWSShjH/CisRgHoy61c0Nu1hlCmw3Kip6dXFWL62Sja254SU66i8h6cleroFwDlKnLsZXcaS4oSa21y6s8D4zukv8PdX+TRrFtEueqvYjZmgLB72hgDg==");
        HolderDomain holderDomain = new HolderDomain();
        holderDomain.setFullName("Claudio Nazareth");
        holderDomain.setBirthDate("1983-10-10");
        holderDomain.setPhoneAreaCode("11");
        holderDomain.setPhoneNumber("968306654");
        holderDomain.setTaxDocument("222.222.222-22");
        paymentDomain.setHolderDomain(holderDomain);
        return paymentDomain;
    }
}
