package br.com.moipstore.service;

import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.ItemDomain;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentDomain;

import java.util.List;


public interface PaymentService {

    /**
     * Create a Payment in Moip Api and also in a moipstore and store in the database
     * @param paymentRequest
     * @return Payment
     */
    Payment createPayment(PaymentDomain paymentRequest);

    /**
     * Calculates the Amount  based on the items (product price and quantity) quantity of installments and if there is any discount coupon
     * @param items
     * @param numberOfInstallments
     * @param shouldApplyCoupon
     * @return Integer
     */
    Integer calculateAmount(List<ItemDomain> items, int numberOfInstallments, boolean shouldApplyCoupon);

}
