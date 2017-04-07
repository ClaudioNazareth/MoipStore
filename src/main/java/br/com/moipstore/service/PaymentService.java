package br.com.moipstore.service;

import br.com.moipstore.model.request.OrderDomain;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PaymentService {

    BigDecimal calculateAmount(OrderDomain orderDomain);
}
