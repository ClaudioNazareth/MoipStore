package br.com.moipstore.rest.controller;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moip.resource.Payment;
import br.com.moipstore.rest.request.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestControler {

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest paymentRequest){
        Authentication auth = new BasicAuth("01010101010101010101010101010101", "ABABABABABABABABABABABABABABABABABABABAB");

        Client client = new Client(Client.SANDBOX, auth);

        API api = new API(client);

        Payment createdPayment = api.payment().create(
                new br.com.moip.request.PaymentRequest()
                        .orderId(paymentRequest.getOrderId())
                        .installmentCount(1)
                        .fundingInstrument(
                                new FundingInstrumentRequest()
                                        .creditCard(
                                                new CreditCardRequest()
                                                        .hash("H+Ew10LGKYsNWInmNJ/YHYB3qbYIUclkXUpwtMHDSPzFoT7M6Otx8AkYVAhgAqU4Hg/GYUEgiZthBJOtrS7WUWyQpnjbiIO0X6t/TJtcAcVCFp+oTuRkm8qbIlJCg46tc2lp/z48awTF9nKUKDfdd6soXc190kc0xc8fyFd4d5Gf+kF2bBgKF918MuYTb6+y48v7OoXhBq57a6cF7tKNFbp5zlILVrq5/i9N68GzxJ3xfsR8ecp3jzt16iDwANDxc/FTC2ybo9vY8huC4RhMRxaHw23s1hJ0szBwHakSlZ2cVimujCv9zPoRH7C1ZhkHKdTEp/OR2JQTL8RyAiierA==")
                                                        .holder(
                                                                new HolderRequest()
                                                                        .fullname("Jose Portador da Silva")
                                                                        .birthdate("1988-10-10")
                                                                        .phone(
                                                                                new PhoneRequest()
                                                                                        .setAreaCode("11")
                                                                                        .setNumber("55667788")
                                                                        )
                                                                        .taxDocument(TaxDocumentRequest.cpf("22222222222"))
                                                        )
                                        )
                        )
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdPayment.getId()).toUri();

        return created(location).build();

    }
}