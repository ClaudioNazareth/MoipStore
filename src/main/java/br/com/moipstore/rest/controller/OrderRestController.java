package br.com.moipstore.rest.controller;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moip.resource.Order;
import br.com.moipstore.rest.request.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){

        Authentication auth = new BasicAuth("01010101010101010101010101010101", "ABABABABABABABABABABABABABABABABABABABAB");

        Client client = new Client(Client.SANDBOX, auth);

        API api = new API(client);

        Order createdOrder = api.order().create(new br.com.moip.request.OrderRequest()
                .ownId("order_own_id")
                .addItem("Nome do produto", 1, "Mais info...", 100)
                .customer(new CustomerRequest()
                        .ownId("customer_own_id")
                        .fullname("Jose da Silva")
                        .email("josedasilva@email.com")
                        .birthdate(new ApiDateRequest().date(new Date()))
                        .taxDocument(TaxDocumentRequest.cpf("22222222222"))
                        .phone(new PhoneRequest().setAreaCode("11").setNumber("55443322"))
                        .shippingAddressRequest(new ShippingAddressRequest().street("Avenida Faria Lima")
                                .streetNumber("3064")
                                .complement("12 andar")
                                .city("São Paulo")
                                .state("SP")
                                .district("Itaim")
                                .country("BRA")
                                .zipCode("01452-000")
                        )
                )
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.getId()).toUri();

        return created(location).build();
    }
}
