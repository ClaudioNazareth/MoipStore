package br.com.moipstore.service;

import br.com.moipstore.data.OrderData;
import br.com.moipstore.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void whenOrderIsCreatedSuccessfullyTheServiceMustReturnAnPersistedOrderEntity() throws Exception {
        Order order = orderService.createOrder(OrderData.getOrderDomain());

        assertThat(order).as("Order must be created and persisted in database").isNotNull();

        assertThat(order.getAmount()).as("The order has 1 item with two Units, so the amount must be 701.98").isEqualTo(70198);

        assertThat(order.getItems()).as("The Order must have 1 item")
                .hasSize(1)
                .extracting("productCode", "quantity", "price")
                .contains(tuple("Xbox One S 500GB Console", 2, 35099));

        assertThat(order.getCustomer()).as("The customer must be created").isNotNull();

        assertThat(order.getCustomer()).as("The customer data must be created")
                .extracting("fullName", "email","taxDocument")
                .contains("Cláudio Nazareth","chtnazareth@gmail.com","22222222222");

        assertThat(order.getCustomer().getShippingAddress()).as("The Shipping Address must be created")
                .extracting("street", "streetNumber","city", "state", "zipCode")
                .contains("MONTGOMERY ST STE 305","400","SAN FRANCISCO", "CA", "94104-1211");

    }

}