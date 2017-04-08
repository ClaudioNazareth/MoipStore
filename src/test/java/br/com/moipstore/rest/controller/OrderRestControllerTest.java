package br.com.moipstore.rest.controller;

import br.com.moipstore.data.OrderData;
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
public class OrderRestControllerTest {

    @Autowired
    private OrderRestController orderRestController;

    @Before
    public void setUp() throws Exception {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Test
    public void whenOrderIsCreated201StatusCodeMustReturn() throws Exception {
        ResponseEntity<?> order = orderRestController.createOrder(OrderData.getOrderDomainWithOneInstallments());
        assertThat(order).as("Order must be Created and MoipAPI in SandBox must be online").isNotNull();
        assertThat(order.getStatusCode()).as("The Status code must be created").isEqualTo(HttpStatus.CREATED);
        assertThat(order.getHeaders().get("location")).as("The new order location Must be returned").isNotNull();
    }

}