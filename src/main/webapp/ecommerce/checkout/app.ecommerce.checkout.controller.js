/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with Checkout | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.checkout')
        .controller('EcommerceCheckoutController', EcommerceCheckoutController);

    EcommerceCheckoutController.$inject = ['$scope','EcommerceCartService', 'EcommerceCheckoutService', 'EcommercePaymentService', 'SweetAlert', 'CreditCardService'];

    function EcommerceCheckoutController($scope, EcommerceCartService, EcommerceCheckoutService, EcommercePaymentService, SweetAlert, CreditCardService) {
        var ctrl = this;

        ctrl.amount = 0;
        ctrl.selectedProducts = [];
        ctrl.shouldApplyCoupon = false;
        ctrl.card ={
            number:null,
            CVC: null,
            expirationMonth:null,
            expirationYear:null,
            name:null
        };

        ctrl.loading = false;
        ctrl.coupon = null;
        ctrl.installment = null;
        ctrl.installments = [];

        ctrl.makePayment = makePayment;
        ctrl.applyCoupon = applyCoupon;

        activate();

        function activate() {
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateAmount();
        }

        function calculateAmount() {
            ctrl.amount = EcommercePaymentService.calculateAmount(ctrl.selectedProducts, ctrl.shouldApplyCoupon);
            calculateInstallments();
        }

        function calculateInstallments() {
            ctrl.installments = [];
            for (var i = 1; i <= 13; i++) {
                var installment = {number: i, text: i + " x " + (ctrl.amount / i).toFixed(2)};
                ctrl.installments.push(installment);
            }
        }
        
        function applyCoupon() {
            ctrl.shouldApplyCoupon = true;
            SweetAlert.swal({
                title: "Success",
                text: "Coupon applied with success "
            });
            ctrl.coupon = null;
            ctrl.installment = null;
            calculateAmount();
        }

        function makePayment() {

            if( CreditCardService.isCreditCardValid(ctrl.card)){
                ctrl.loading = true;
                var orderRequest = createOrderRequest();

                EcommerceCheckoutService.createOrder(orderRequest).then(onCreateOrderSuccess, onCreateOrderError);
            } else {
                SweetAlert.swal({
                    title: "CreditCard Invalid",
                    text: "Invalid credit card. Verify parameters: number, cvc, expiration Month, expiration Year"
                });
            }

            function createOrderRequest() {
                var itens = [];
                ctrl.selectedProducts.forEach(function (selectedProduct) {
                    var item = {productCode: selectedProduct.code, quantity: selectedProduct.quantity};
                    itens.push(item);
                });
                var orderRequest = {
                    customerId: "cust",
                    items: itens
                };
                return orderRequest;
            }

            function onCreateOrderSuccess(response) {
                var paymentRequest = {
                    orderId : response.headers('Location').split("order/")[1],
                    creditCardHash : CreditCardService.getCreditCardHash()
                };

                EcommercePaymentService.createPayment(paymentRequest).then(onPaymentSuccess, onPaymentError);

                function onPaymentSuccess(response) {
                    ctrl.loading = false;
                    alert(response.headers('Location').split("payment/")[1])
                }

                function onPaymentError() {
                    SweetAlert.swal({
                        title: "Error",
                        text: "Error trying to create a Payment"
                    });
                }
            }

            function onCreateOrderError(response) {
                SweetAlert.swal({
                    title: "Error",
                    text: "Error trying to create an Order"
                });
            }
        }
    }
})();

