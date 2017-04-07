package br.com.moipstore.service;

import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PaymentService {

    BigDecimal calculateAmount(OrderDomain orderDomain);
    Payment createPayment(PaymentRequest paymentRequest);
}
