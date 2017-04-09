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
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MoipAPI moipAPI;

    @Override
    public Order createOrder(OrderDomain orderDomain) {

        API api = moipAPI.getAPI();

        Customer customer = customerRepository.findOne(orderDomain.getCustomerId());

        br.com.moip.resource.Order createdOrder = api.order().create(createMoipOrderRequest(customer, orderDomain));

        //Only for tests purpose , in real world the coupon must be validate first
        boolean shouldApplyCoupon = orderDomain.getCoupon() != null ? true : false;

        final Order order = new Order(createdOrder.getId(), paymentService.calculateAmount(orderDomain.getItems(), orderDomain.getNumberOfInstallments(), shouldApplyCoupon), createItems(createdOrder), customer);
        return orderRepository.save(order);
    }

    private List<Item> createItems(br.com.moip.resource.Order createdOrder) {
        return createdOrder.getItems()
                .stream()
                .map(item -> new Item(item.getProduct(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());
    }

    private OrderRequest createMoipOrderRequest(Customer customer, OrderDomain orderDomain) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.ownId(generateOrderOwnId(customer));

        orderDomain.getItems().forEach(itemDomain -> {
            Product product = productRepository.findOne(itemDomain.getProductCode());
            orderRequest.addItem(product.getName(), itemDomain.getQuantity(), product.getName(), product.getPrice().intValue());
        });

        orderRequest.customer(createCustomer(customer));

        return orderRequest;
    }
    private CustomerRequest createCustomer(Customer customer) {
        return new CustomerRequest()
                .ownId(customer.getCode())
                .fullname(customer.getFullName())
                .email(customer.getEmail())
                .birthdate(new ApiDateRequest().date(customer.getBirthDate()))
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

    private String generateOrderOwnId(Customer customer) {
        return "ORD-"+ customer.getCode() + Instant.now();
    }
}
