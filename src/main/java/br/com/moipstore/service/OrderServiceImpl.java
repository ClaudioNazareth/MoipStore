package br.com.moipstore.service;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moipstore.model.Customer;
import br.com.moipstore.model.Item;
import br.com.moipstore.model.Order;
import br.com.moipstore.model.Product;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.repository.CustomerRepository;
import br.com.moipstore.repository.OrderRepository;
import br.com.moipstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @Override
    public Order createOrder(OrderDomain orderDomain) {

        API api = getApi();

        Customer customer = customerRepository.findOne(orderDomain.getCustomerId());

        br.com.moip.resource.Order createdOrder = api.order().create(createMoipOrderRequest(customer, orderDomain));
        final Order order = new Order(createdOrder.getId(), paymentService.calculateAmount(orderDomain), createItems(createdOrder), customer);
        return orderRepository.save(order);
    }

    private List<Item> createItems(br.com.moip.resource.Order createdOrder) {
        return createdOrder.getItems()
                .stream()
                .map(item -> new Item(item.getProduct(), item.getQuantity(), new BigDecimal(item.getPrice())))
                .collect(Collectors.toList());
    }

    private OrderRequest createMoipOrderRequest(Customer customer, OrderDomain orderDomain) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.ownId("order_own_id");

        orderDomain.getItems().forEach(itemDomain -> {
            Product product = productRepository.findOne(itemDomain.getProductCode());
            orderRequest.addItem(product.getName(), itemDomain.getQuantity(), product.getDescription(), product.getPrice().intValueExact());
        });

        orderRequest.customer(createCustomer(customer));

        return orderRequest;
    }

    private CustomerRequest createCustomer(Customer customer) {
        return new CustomerRequest()
                .ownId("customer_own_id")
                .fullname(customer.getFullName())
                .email(customer.getEmail())
                .birthdate(new ApiDateRequest().date(Date.from(customer.getBirthDate())))
                .taxDocument(TaxDocumentRequest.cpf(customer.getTaxDocument()))
                .phone(new PhoneRequest().setAreaCode(customer.getPhone().getAreaCode()).setNumber(customer.getPhone().getNumber()))
                .shippingAddressRequest(new ShippingAddressRequest().street(customer.getShippingAddress().getStreet())
                        .streetNumber(customer.getShippingAddress().getStreetNumber())
                        .complement(customer.getShippingAddress().getComplement())
                        .city(customer.getShippingAddress().getCity())
                        .state(customer.getShippingAddress().getState())
                        .district(customer.getShippingAddress().getDistrict())
                        .country(customer.getShippingAddress().getCountry())
                        .zipCode(customer.getShippingAddress().getZipCode())
                );
    }

    private API getApi() {
        Authentication auth = new BasicAuth("01010101010101010101010101010101", "ABABABABABABABABABABABABABABABABABABABAB");

        Client client = new Client(Client.SANDBOX, auth);

        return new API(client);
    }
}
