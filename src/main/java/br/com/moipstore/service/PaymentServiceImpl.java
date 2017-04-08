package br.com.moipstore.service;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentDomain;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Override
    public Integer calculateAmount(OrderDomain orderDomain) {
        return null;
    }

    @Override
    public Payment createPayment(PaymentDomain paymentRequest) {
        API api = getMoipApi();

        br.com.moip.resource.Payment createdPayment = api.payment().create(
                new br.com.moip.request.PaymentRequest()
                        .orderId(paymentRequest.getOrderId())
                        .installmentCount(1)
                        .fundingInstrument(new FundingInstrumentRequest()
                                .creditCard(createCreditCardRequest(paymentRequest)))
        );

        return new Payment(createdPayment.getId(), createdPayment.getStatus().getDescription(), new BigDecimal(createdPayment.getAmount().getTotal()));
    }

    private CreditCardRequest createCreditCardRequest(PaymentDomain paymentRequest) {
        return new CreditCardRequest()
                .hash(paymentRequest.getCreditCardHash())
                .holder(new HolderRequest()
                                .fullname(paymentRequest.getHolderDomain().getFullName())
                                .birthdate(paymentRequest.getHolderDomain().getBirthDate())
                                .phone(new PhoneRequest()
                                           .setAreaCode(paymentRequest.getHolderDomain().getPhoneAreaCode())
                                           .setNumber(paymentRequest.getHolderDomain().getPhoneNumber())
                                )
                                .taxDocument(TaxDocumentRequest.cpf(paymentRequest.getHolderDomain().getTaxDocument()))
                );
    }

    private API getMoipApi() {
        Authentication auth = new BasicAuth("01010101010101010101010101010101", "ABABABABABABABABABABABABABABABABABABABAB");

        Client client = new Client(Client.SANDBOX, auth);

        return new API(client);
    }
}
