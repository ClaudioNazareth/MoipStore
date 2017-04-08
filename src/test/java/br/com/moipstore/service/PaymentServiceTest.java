package br.com.moipstore.service;

import br.com.moipstore.data.OrderData;
import br.com.moipstore.data.PaymentData;
import br.com.moipstore.model.Order;
import br.com.moipstore.model.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;


    @Test
    public void createPayment() throws Exception {
        Order order = orderService.createOrder(OrderData.getOrderDomain());

        Payment payment = paymentService.createPayment(PaymentData.createPaymentDomain(order.getCode()));
        assertThat(payment).as("Payment must not be null");
    }

    @Test
    public void calculateAmount() throws Exception {
    }

}