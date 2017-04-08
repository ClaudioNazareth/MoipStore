package br.com.moipstore.rest.controller;

import br.com.moipstore.model.Order;
import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentDomain;
import br.com.moipstore.repository.OrderRepository;
import br.com.moipstore.repository.ProductRepository;
import br.com.moipstore.service.OrderService;
import br.com.moipstore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentRestControler {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentDomain paymentRequest){

        Payment createdPayment = paymentService.createPayment(paymentRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdPayment.getId()).toUri();

        return created(location).build();
    }

}
