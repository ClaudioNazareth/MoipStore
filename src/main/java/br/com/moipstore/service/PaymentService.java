package br.com.moipstore.service;

import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.ItemDomain;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentDomain;

import java.util.List;


public interface PaymentService {

    Integer calculateAmount(List<ItemDomain> items, int numberOfInstallments, boolean shouldApplyCoupon);
    Payment createPayment(PaymentDomain paymentRequest);
}
