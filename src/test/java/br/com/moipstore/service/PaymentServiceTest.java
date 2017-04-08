package br.com.moipstore.service;

import br.com.moipstore.data.OrderData;
import br.com.moipstore.data.PaymentData;
import br.com.moipstore.model.Order;
import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.OrderDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;


    @Test
    public void whenPaymentIsCreatedSuccessfullyTheServiceMustReturnAnPersistedPaymentEntity() throws Exception {
        Order order = orderService.createOrder(OrderData.getOrderDomainWithOneInstallments());

        Payment payment = paymentService.createPayment(PaymentData.createPaymentDomain(order.getCode()));
        assertThat(payment).as("Payment must not be null").isNotNull();
        assertThat(payment.getAmount()).as("Payment must be 701.98").isEqualTo(70198);
        assertThat(payment.getStatus()).as("Payment must be in Em análise ").isEqualTo("Em análise");
        assertThat(payment.getCode()).as("Payment must have a code").isNotNull();
    }

    @Test
    public void whenCouponIsAppliedTheDiscountMustBeApplied() throws Exception {
        OrderDomain orderDomain = OrderData.getOrderDomainWithOneInstallments();
        Integer amount = paymentService.calculateAmount(orderDomain.getItems(), orderDomain.getNumberOfInstallments(), true);
        assertThat(amount).as("The amount should not be null").isNotNull();
        assertThat(amount).as("The amount should not be negative").isNotNegative();
        assertThat(amount).as("The amount must have a discount of 5 % 666.88").isEqualTo(66688);
    }

    @Test
    public void whenCouponIsNotAppliedTheDiscountMustNotBeApplied() throws Exception {
        OrderDomain orderDomain = OrderData.getOrderDomainWithOneInstallments();
        Integer amount = paymentService.calculateAmount(orderDomain.getItems(), orderDomain.getNumberOfInstallments(), false);
        assertThat(amount).as("The amount should not be null").isNotNull();
        assertThat(amount).as("The amount should not be negative").isNotNegative();
        assertThat(amount).as("The amount must not have the discount the value must be 701.98").isEqualTo(70198);
    }

    @Test
    public void whenCouponIsAppliedAndHaveMoreThanOneInstallmentsTheDiscountAndInterestMustBeApplied() throws Exception {
        OrderDomain orderDomain = OrderData.getOrderDomainWithMoreThanOneInstallments();
        Integer amount = paymentService.calculateAmount(orderDomain.getItems(), orderDomain.getNumberOfInstallments(), true);
        assertThat(amount).as("The amount should not be null").isNotNull();
        assertThat(amount).as("The amount should not be negative").isNotNegative();
        assertThat(amount).as("The amount must have a discount of 5 % 683.55").isEqualTo(68355);
    }

    @Test
    public void whenCouponIsNotAppliedAndHaveMoreThanOneInstallmentsOnlyTheInterestMustBeApplied() throws Exception {
        OrderDomain orderDomain = OrderData.getOrderDomainWithMoreThanOneInstallments();
        Integer amount = paymentService.calculateAmount(orderDomain.getItems(), orderDomain.getNumberOfInstallments(), false);
        assertThat(amount).as("The amount should not be null").isNotNull();
        assertThat(amount).as("The amount should not be negative").isNotNegative();
        assertThat(amount).as("The amount must not have the discount, but must have te interest of 2.5 719.52 ").isEqualTo(71952);
    }

}