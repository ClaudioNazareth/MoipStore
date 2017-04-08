package br.com.moipstore.rest.controller;

import br.com.moipstore.data.OrderData;
import br.com.moipstore.data.PaymentData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentRestControllerTest {

    @Autowired
    private OrderRestController orderRestController;

    @Autowired
    private PaymentRestController paymentRestController;

    @Before
    public void setUp() throws Exception {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Test
    public void whenPaymentIsCreatedSuccessfullyTheServerMustResponde201AndReturnTheLink() throws Exception {
        //Create an order to proceed with the Payment
        ResponseEntity<?> order = orderRestController.createOrder(OrderData.getOrderDomainWithOneInstallments());
        String location = order.getHeaders().get("location").get(0);
        String orderId = location.split("localhost/")[1];

        ResponseEntity<?> paymentReturn = paymentRestController.createPayment(PaymentData.createPaymentDomain(orderId));
        assertThat(paymentReturn).as("Payment must be Created and MoipAPI in SandBox must be online").isNotNull();
        assertThat(paymentReturn.getStatusCode()).as("The Status code must be created").isEqualTo(HttpStatus.CREATED);
        assertThat(paymentReturn.getHeaders().get("location")).as("The new payment location Must be returned").isNotNull();
    }}