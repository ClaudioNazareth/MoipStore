package br.com.moipstore.service;

import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentDomain;


public interface PaymentService {

    Integer calculateAmount(OrderDomain orderDomain);
    Payment createPayment(PaymentDomain paymentRequest);
}
