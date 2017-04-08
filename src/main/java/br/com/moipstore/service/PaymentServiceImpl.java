package br.com.moipstore.service;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moipstore.model.Payment;
import br.com.moipstore.model.Product;
import br.com.moipstore.model.request.ItemDomain;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.model.request.PaymentDomain;
import br.com.moipstore.repository.PaymentRepository;
import br.com.moipstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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
        Payment payment = new Payment(createdPayment.getId(), createdPayment.getStatus().getDescription(), createdPayment.getAmount().getTotal());
        paymentRepository.save(payment);

        return payment;
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

    @Override
    public Integer calculateAmount(List<ItemDomain> items, int numberOfInstallments, boolean shouldApplyCoupon) {
        double amount = 0;
        for (ItemDomain item : items) {
            Product product = productRepository.findOne(item.getProductCode());
            amount += (product.getPrice() * item.getQuantity());
        }
        if (shouldApplyCoupon){
            amount *= 0.95;

        }
        if(numberOfInstallments > 1){
            amount *= 1.025 ;
        }
        //Loses accuracy from 2 decimal place
        return (int) amount;
    }

    private API getMoipApi() {
        Authentication auth = new BasicAuth("01010101010101010101010101010101", "ABABABABABABABABABABABABABABABABABABABAB");

        Client client = new Client(Client.SANDBOX, auth);

        return new API(client);
    }
}
